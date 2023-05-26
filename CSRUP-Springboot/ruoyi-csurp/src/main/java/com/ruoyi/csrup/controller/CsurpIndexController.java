package com.ruoyi.csrup.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysMenu;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.csrup.domain.CsurpIndex;
import com.ruoyi.csrup.service.ICsurpIndexService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import static java.lang.String.valueOf;

/**
 * 指标Controller
 *
 * @author ruoyi
 * @date 2023-04-26
 */
@RestController
@RequestMapping("/csrup/csurpIndex")
public class CsurpIndexController extends BaseController {
    @Autowired
    private ICsurpIndexService csurpIndexService;

    /**
     * 查询指标列表
     */
    @GetMapping("/list")
    public AjaxResult list(CsurpIndex csurpIndex) {
        List<CsurpIndex> list = csurpIndexService.selectCsurpIndexList(csurpIndex);
        return success(list);
    }

    /**
     * 查询总得分
     */
    @GetMapping(value = "/total/{id}")
    public AjaxResult list(@PathVariable("id") Long companyId) {
        Double score = csurpIndexService.selectTotalScore(companyId);
        return success(score);
    }

    /**
     * 导出指标列表
     */
    @Log(title = "指标", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CsurpIndex csurpIndex) {
        List<CsurpIndex> list = csurpIndexService.selectCsurpIndexList(csurpIndex);
        ExcelUtil<CsurpIndex> util = new ExcelUtil<CsurpIndex>(CsurpIndex.class);
        util.exportExcel(response, list, "指标数据");
    }

    /**
     * 获取指标详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(csurpIndexService.selectCsurpIndexById(id));
    }

    /**
     * 新增指标
     */
    @Log(title = "指标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CsurpIndex csurpIndex) {
        return toAjax(csurpIndexService.insertCsurpIndex(csurpIndex));
    }

    /**
     * 修改指标
     */
    @Log(title = "指标", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CsurpIndex csurpIndex) {
        return toAjax(csurpIndexService.updateCsurpIndex(csurpIndex));
    }

    /**
     * 删除指标
     */
    @Log(title = "指标", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(csurpIndexService.deleteCsurpIndexByIds(ids));
    }
}
