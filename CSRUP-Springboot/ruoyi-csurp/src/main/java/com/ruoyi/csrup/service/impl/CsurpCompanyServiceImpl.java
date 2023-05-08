package com.ruoyi.csrup.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.csrup.mapper.CsurpCompanyMapper;
import com.ruoyi.csrup.domain.CsurpCompany;
import com.ruoyi.csrup.service.ICsurpCompanyService;

/**
 * 公司信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-26
 */
@Service
public class CsurpCompanyServiceImpl implements ICsurpCompanyService 
{
    @Autowired
    private CsurpCompanyMapper csurpCompanyMapper;

    /**
     * 查询公司信息
     * 
     * @param id 公司信息主键
     * @return 公司信息
     */
    @Override
    public CsurpCompany selectCsurpCompanyById(Long id)
    {
        return csurpCompanyMapper.selectCsurpCompanyById(id);
    }

    /**
     * 查询公司信息列表
     * 
     * @param csurpCompany 公司信息
     * @return 公司信息
     */
    @Override
    public List<CsurpCompany> selectCsurpCompanyList(CsurpCompany csurpCompany)
    {
        return csurpCompanyMapper.selectCsurpCompanyList(csurpCompany);
    }

    /**
     * 新增公司信息
     * 
     * @param csurpCompany 公司信息
     * @return 结果
     */
    @Override
    public int insertCsurpCompany(CsurpCompany csurpCompany)
    {
        csurpCompany.setCreateTime(DateUtils.getNowDate());
        return csurpCompanyMapper.insertCsurpCompany(csurpCompany);
    }

    /**
     * 修改公司信息
     * 
     * @param csurpCompany 公司信息
     * @return 结果
     */
    @Override
    public int updateCsurpCompany(CsurpCompany csurpCompany)
    {
        csurpCompany.setUpdateTime(DateUtils.getNowDate());
        return csurpCompanyMapper.updateCsurpCompany(csurpCompany);
    }

    /**
     * 批量删除公司信息
     * 
     * @param ids 需要删除的公司信息主键
     * @return 结果
     */
    @Override
    public int deleteCsurpCompanyByIds(Long[] ids)
    {
        return csurpCompanyMapper.deleteCsurpCompanyByIds(ids);
    }

    /**
     * 删除公司信息信息
     * 
     * @param id 公司信息主键
     * @return 结果
     */
    @Override
    public int deleteCsurpCompanyById(Long id)
    {
        return csurpCompanyMapper.deleteCsurpCompanyById(id);
    }
}
