package com.ruoyi.csrup.service;

import java.util.List;

import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.csrup.domain.CsurpIndex;

/**
 * 指标Service接口
 * 
 * @author ruoyi
 * @date 2023-04-26
 */
public interface ICsurpIndexService 
{
    /**
     * 查询指标
     * 
     * @param id 指标主键
     * @return 指标
     */
    public CsurpIndex selectCsurpIndexById(Long id);

    /**
     * 查询指标列表
     * 
     * @param csurpIndex 指标
     * @return 指标集合
     */
    public List<CsurpIndex> selectCsurpIndexList(CsurpIndex csurpIndex);

    /**
     * 新增指标
     * 
     * @param csurpIndex 指标
     * @return 结果
     */
    public int insertCsurpIndex(CsurpIndex csurpIndex);

    /**
     * 修改指标
     * 
     * @param csurpIndex 指标
     * @return 结果
     */
    public int updateCsurpIndex(CsurpIndex csurpIndex);

    /**
     * 批量删除指标
     * 
     * @param ids 需要删除的指标主键集合
     * @return 结果
     */
    public int deleteCsurpIndexByIds(Long[] ids);

    /**
     * 删除指标信息
     * 
     * @param id 指标主键
     * @return 结果
     */
    public int deleteCsurpIndexById(Long id);
}
