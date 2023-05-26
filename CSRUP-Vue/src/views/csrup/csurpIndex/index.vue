<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="指标名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入指标名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasRole="['verify', 'admin']" :disabled="companyInfo.status == '1'">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :disabled="companyInfo.status === '0'">导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="el-icon-sort" size="mini" @click="toggleExpandAll">展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-if="refreshTable" v-loading="loading" :data="csurpIndexList" row-key="id"
      :default-expand-all="isExpandAll" :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
      <el-table-column label="指标名称" align="center" prop="name" show-overflow-tooltip />
      <el-table-column label="层级" align="center" prop="sort" show-overflow-tooltip />
      <el-table-column label="定性" align="center" prop="qualitative" show-overflow-tooltip />
      <el-table-column label="定量" align="center" prop="quantify" show-overflow-tooltip />
      <el-table-column label="填写说明" align="center" prop="illustrate" show-overflow-tooltip />
      <el-table-column label="指标评分" align="center" prop="score" show-overflow-tooltip />
      <el-table-column label="审批意见" align="center" prop="remark" show-overflow-tooltip />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-plus" @click="handleAdd(scope.row)" v-hasRole="['verify', 'admin']" :disabled="companyInfo.status === '1'">新增</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" :disabled="companyInfo.status === '1'">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-if="checkRole(['admin'])" :disabled="companyInfo.status === '1'">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="mt20">
      <el-statistic :precision="2" :value="totalAmount" title="总分"></el-statistic>
    </div>

    <!-- 添加或修改指标对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="上级指标" prop="parentId" v-if="checkRole(['admin', 'verify'])">
          <treeselect v-model="form.parentId" :options="indexOptions" :normalizer="normalizer" :show-count="true"
            placeholder="选择上级指标" @select="selectTree" />
        </el-form-item>
        <el-form-item label="指标名称" prop="name" v-if="checkRole(['admin', 'verify']) && !isLastChild">
          <el-input v-model="form.name" placeholder="请输入指标名称" />
        </el-form-item>
        <el-form-item label="层级" prop="sort" v-if="checkRole(['admin', 'verify'])">
          <el-input v-model="form.sort" type="number" min="0" placeholder="请输入层级" />
        </el-form-item>
        <el-form-item label="性质" prop="nature" >
          <el-checkbox-group v-model="form.nature" @change="checkboxChange">
            <el-checkbox label="0" border>定性</el-checkbox>
            <el-checkbox label="1" border>定量</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="定性" prop="qualitative" v-if="showQualitative">
          <el-input v-model="form.qualitative" type="textarea" maxlength="300" placeholder="定性：文本框用户输入文字（300字）"
            @change="qualitativeChange" />
        </el-form-item>
        <el-form-item label="定量" prop="quantify" v-if="showQuantify">
          <el-input v-model="form.quantify" placeholder="定量：文本框用户输入对应数值，给出单位）" @change="quantifyChange" />
        </el-form-item>
        <el-form-item label="填写说明" prop="illustrate">
          <el-input v-model="form.illustrate" placeholder="请输入填写说明" />
        </el-form-item>
        <el-form-item label="指标权重" prop="score" v-if="checkRole(['admin', 'verify']) && checkIndex()">
          <el-input-number v-model="form.weight" :precision="2" :step="0.1" :max="1" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="指标评分" prop="score" v-if="checkRole(['admin', 'verify'])">
          <el-input-number v-model="form.score" :precision="2" :step="0.1" :max="1" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="审批意见" prop="remark" v-if="checkRole(['admin', 'verify'])">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listCsurpIndex,
  getCsurpIndex,
  delCsurpIndex,
  addCsurpIndex,
  updateCsurpIndex,
  getTotalScore
} from "@/api/csrup/csurpIndex";
import { getCsurpCompany } from "@/api/csrup/csurpCompany";
import { checkPermi, checkRole } from "@/utils/permission"; // 权限判断函数
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "CsurpIndex",
  dicts: ['quantify_type'],
  components: { Treeselect },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      // 指标表格数据
      csurpIndexList: [],
      indexOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部折叠
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        name: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "指标名称不能为空", trigger: "blur" },
        ],
        sort: [
          { required: true, message: "层级不能为空", trigger: "blur" },
        ]
      },
      showQualitative: false,
      showQuantify: false,
      showScore: false,
      totalAmount: 0,
      companyInfo: {},
      isLastChild: false
    };
  },
  created() {
    this.getList();
    this.getCompanyInfo();
  },
  activated() {
    this.getList();
    this.getCompanyInfo();
  },
  methods: {
    checkPermi,
    checkRole,
    getCompanyInfo() {
      getCsurpCompany(this.$route.query.companyId).then(res => {
        this.companyInfo = res.data
      })
    },
    /** 查询指标列表 */
    getList() {
      this.loading = true;
      this.queryParams.companyId = this.$route.query.companyId
      listCsurpIndex(this.queryParams).then(response => {
        getTotalScore(this.$route.query.companyId).then (response => {
          this.totalAmount = response.data;
          }
        );
        this.csurpIndexList = this.handleTree(response.data, "id");
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        parentId: 0,
        companyId: null,
        name: null,
        weight: null,
        sort: null,
        qualitative: null,
        quantify: null,
        illustrate: null,
        score: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        nature: []
      };
      this.resetForm("form");
    },
    /** 判断是否为二级指标 **/
    checkIndex() {
      return this.form.parentId === 0;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.id) {
        this.form.parentId = row.id;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加指标";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      const id = row.id || this.ids
      getCsurpIndex(id).then(response => {
        this.form = response.data;
        this.form.nature = this.form.nature != null ? this.form.nature.split(",") : []
        this.checkboxChange(this.form.nature != null ? this.form.nature : [])
        this.open = true;
        this.title = "修改指标";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.companyId = this.$route.query.companyId
      this.form.nature = this.form.nature.join(",")
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCsurpIndex(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCsurpIndex(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除指标编号为"' + ids + '"的数据项？').then(function () {
        return delCsurpIndex(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('csrup/csurpIndex/export', {
        ...this.queryParams
      }, `csurpIndex_${new Date().getTime()}.xlsx`)
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 查询指标下拉树结构 */
    getTreeselect() {
      this.queryParams.companyId = this.$route.query.companyId
      listCsurpIndex(this.queryParams).then(response => {
        this.indexOptions = [];
        const index = { id: 0, name: '社会责任', children: [] };
        index.children = this.handleTree(response.data, "id");
        this.indexOptions.push(index);
      });
    },
    selectTree(node, instanceId) {
      console.log(instanceId)
      if (node.sort >= 2) {
        this.isLastChild = true
      } else {
        this.isLastChild = false
      }
    },
    /** 转换菜单数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },
    // 性质判断
    checkboxChange(val) {
      if (val.length === 1) {
        this.showQualitative = false
        this.showQuantify = false
        if (val[0] === '0') {
          this.showQualitative = true
          this.showScore = true
        }
        if (val[0] === '1') {
          this.showQuantify = true
          this.showScore = false
        }
      }
      if (val.length === 2) {
        this.showQualitative = true
        this.showQuantify = true
        this.showScore = true
      }
      if (val.length === 0) {
        this.showQualitative = false
        this.showQuantify = false
        this.showScore = false
      }
    },
    // 定性判断
    qualitativeChange(val) {
      if (val) {
        this.form.score = '1'
      } else {
        this.form.score = '0'
      }
    },
    // 定量判断
    quantifyChange(val) {
      if (val.indexOf("%") != -1) { //包含
        this.form.score = '0.' + val.replace("%", '')
        this.showScore = true
      } else {
        this.showScore = false
      }
    }
  }
};
</script>
