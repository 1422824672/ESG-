package com.ruoyi.csrup.mapper;

import java.util.List;
import com.ruoyi.csrup.domain.CsurpCompany;

/**
 * 公司信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-26
 */
public interface CsurpCompanyMapper 
{
    /**
     * 查询公司信息
     * 
     * @param id 公司信息主键
     * @return 公司信息
     */
    public CsurpCompany selectCsurpCompanyById(Long id);

    /**
     * 查询公司信息列表
     * 
     * @param csurpCompany 公司信息
     * @return 公司信息集合
     */
    public List<CsurpCompany> selectCsurpCompanyList(CsurpCompany csurpCompany);

    /**
     * 新增公司信息
     * 
     * @param csurpCompany 公司信息
     * @return 结果
     */
    public int insertCsurpCompany(CsurpCompany csurpCompany);

    /**
     * 修改公司信息
     * 
     * @param csurpCompany 公司信息
     * @return 结果
     */
    public int updateCsurpCompany(CsurpCompany csurpCompany);

    /**
     * 删除公司信息
     * 
     * @param id 公司信息主键
     * @return 结果
     */
    public int deleteCsurpCompanyById(Long id);

    /**
     * 批量删除公司信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCsurpCompanyByIds(Long[] ids);
}
