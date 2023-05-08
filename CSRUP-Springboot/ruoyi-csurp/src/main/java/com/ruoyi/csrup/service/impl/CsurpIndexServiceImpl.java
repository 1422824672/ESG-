package com.ruoyi.csrup.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.csrup.mapper.CsurpIndexMapper;
import com.ruoyi.csrup.domain.CsurpIndex;
import com.ruoyi.csrup.service.ICsurpIndexService;

/**
 * 指标Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-26
 */
@Service
public class CsurpIndexServiceImpl implements ICsurpIndexService 
{
    @Autowired
    private CsurpIndexMapper csurpIndexMapper;

    /**
     * 查询指标
     * 
     * @param id 指标主键
     * @return 指标
     */
    @Override
    public CsurpIndex selectCsurpIndexById(Long id)
    {
        return csurpIndexMapper.selectCsurpIndexById(id);
    }

    /**
     * 查询指标列表
     * 
     * @param csurpIndex 指标
     * @return 指标
     */
    @Override
    public List<CsurpIndex> selectCsurpIndexList(CsurpIndex csurpIndex)
    {
        return csurpIndexMapper.selectCsurpIndexList(csurpIndex);
    }

    /**
     * 新增指标
     * 
     * @param csurpIndex 指标
     * @return 结果
     */
    @Override
    public int insertCsurpIndex(CsurpIndex csurpIndex)
    {
        csurpIndex.setCreateTime(DateUtils.getNowDate());
        return csurpIndexMapper.insertCsurpIndex(csurpIndex);
    }

    /**
     * 修改指标
     * 
     * @param csurpIndex 指标
     * @return 结果
     */
    @Override
    public int updateCsurpIndex(CsurpIndex csurpIndex)
    {
        csurpIndex.setUpdateTime(DateUtils.getNowDate());
        return csurpIndexMapper.updateCsurpIndex(csurpIndex);
    }

    /**
     * 批量删除指标
     * 
     * @param ids 需要删除的指标主键
     * @return 结果
     */
    @Override
    public int deleteCsurpIndexByIds(Long[] ids)
    {
        return csurpIndexMapper.deleteCsurpIndexByIds(ids);
    }

    /**
     * 删除指标信息
     * 
     * @param id 指标主键
     * @return 结果
     */
    @Override
    public int deleteCsurpIndexById(Long id)
    {
        return csurpIndexMapper.deleteCsurpIndexById(id);
    }
}
