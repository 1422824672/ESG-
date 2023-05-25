package com.ruoyi.csrup.controller;

import java.util.List;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysRole;
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
import com.ruoyi.csrup.domain.CsurpCompany;
import com.ruoyi.csrup.service.ICsurpCompanyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公司信息Controller
 * 
 * @author ruoyi
 * @date 2023-04-26
 */
@RestController
@RequestMapping("/csrup/csurpCompany")
public class CsurpCompanyController extends BaseController
{
    @Autowired
    private ICsurpCompanyService csurpCompanyService;

    /**
     * 查询公司信息列表
     */
    @PreAuthorize("@ss.hasPermi('csrup:csurpCompany:list')")
    @GetMapping("/list")
    public TableDataInfo list(CsurpCompany csurpCompany) {
        startPage();
        csurpCompany.setId(getLoginUser().getUser().getCompanyId());
        List<CsurpCompany> list = csurpCompanyService.selectCsurpCompanyList(csurpCompany);
        return getDataTable(list);
    }

    /**
     * 导出公司信息列表
     */
    @PreAuthorize("@ss.hasPermi('csrup:csurpCompany:export')")
    @Log(title = "公司信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CsurpCompany csurpCompany)
    {
        List<CsurpCompany> list = csurpCompanyService.selectCsurpCompanyList(csurpCompany);
        ExcelUtil<CsurpCompany> util = new ExcelUtil<CsurpCompany>(CsurpCompany.class);
        util.exportExcel(response, list, "公司信息数据");
    }

    /**
     * 获取公司信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('csrup:csurpCompany:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(csurpCompanyService.selectCsurpCompanyById(id));
    }

    /**
     * 新增公司信息
     */
    @PreAuthorize("@ss.hasPermi('csrup:csurpCompany:add')")
    @Log(title = "公司信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CsurpCompany csurpCompany)
    {
        return toAjax(csurpCompanyService.insertCsurpCompany(csurpCompany));
    }

    /**
     * 修改公司信息
     */
    @PreAuthorize("@ss.hasPermi('csrup:csurpCompany:edit')")
    @Log(title = "公司信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CsurpCompany csurpCompany)
    {
        return toAjax(csurpCompanyService.updateCsurpCompany(csurpCompany));
    }

    /**
     * 删除公司信息
     */
    @PreAuthorize("@ss.hasPermi('csrup:csurpCompany:remove')")
    @Log(title = "公司信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(csurpCompanyService.deleteCsurpCompanyByIds(ids));
    }

    /**
     * 查询公司下拉列表
     */
    @GetMapping("/selectList")
    public AjaxResult selectList(CsurpCompany csurpCompany)
    {
        List<CsurpCompany> list = csurpCompanyService.selectCsurpCompanyList(csurpCompany);
        return success(list);
    }
}
