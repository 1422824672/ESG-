package com.ruoyi.csrup.mapper;

import java.util.List;
import com.ruoyi.csrup.domain.CsurpIndex;

/**
 * 指标Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-26
 */
public interface CsurpIndexMapper 
{
    /**
     * 查询指标
     * 
     * @param id 指标主键
     * @return 指标
     */
    public CsurpIndex selectCsurpIndexById(Long id);

    /**
     * 查询三级指标数
     *
     * @param csurpIndex 指标主键
     * @return 指标
     */
    public Integer countThirdIndex(CsurpIndex csurpIndex);

    /**
     * 查询二级指标数
     *
     * @param csurpIndex 指标主键
     * @return 指标
     */
    public Integer countSecondIndex(CsurpIndex csurpIndex);

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
     * 删除指标
     * 
     * @param id 指标主键
     * @return 结果
     */
    public int deleteCsurpIndexById(Long id);

    /**
     * 批量删除指标
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCsurpIndexByIds(Long[] ids);
}
