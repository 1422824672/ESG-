package com.ruoyi.csrup.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.csrup.mapper.CsurpIndexMapper;
import com.ruoyi.csrup.domain.CsurpIndex;
import com.ruoyi.csrup.service.ICsurpIndexService;

import javax.annotation.Resource;

/**
 * 指标Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-26
 */
@Service
public class CsurpIndexServiceImpl implements ICsurpIndexService 
{
    @Resource
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
     * 查询总得分
     *
     * @param companyId 公司Id
     * @return 指标
     */
    @Override
    public Double selectTotalScore(Long companyId) {
        CsurpIndex csurpIndex = new CsurpIndex();
        csurpIndex.setCompanyId(companyId);
        List<CsurpIndex> csurpIndexList = selectCsurpIndexList(csurpIndex);
        //构造二级指标列表
        List<CsurpIndex> csurpSecondIndexList = new ArrayList<>();
        //构造三级指标列表
        List<CsurpIndex> csurpThirdIndexList = new ArrayList<>();
        //构造二级指标对应权重集合
        Map<Long, BigDecimal> weights = new HashMap<>();
        //构造二级指标对应三级指标个数集合
        Map<Long, Integer> thirdNumber = new HashMap<>();
        //构造二级指标对应三级指标分数总和集合
        Map<Long, Double> thirdIndexScore = new HashMap<>();
        //获取二级和三级指标
        for (CsurpIndex csurpIndexSon : csurpIndexList) {
            if (csurpIndexSon.getParentId() != 0) {
                csurpThirdIndexList.add(csurpIndexSon);
                //指标个数集合添加
                if (thirdNumber.containsKey(csurpIndexSon.getParentId())) {
                    thirdNumber.put(csurpIndexSon.getParentId(), thirdNumber.get(csurpIndexSon.getParentId()) + 1);
                } else {
                    thirdNumber.put(csurpIndexSon.getParentId(), 1);
                }
                //总和集合添加
                if (thirdIndexScore.containsKey(csurpIndexSon.getParentId())) {
                    thirdIndexScore.put(csurpIndexSon.getParentId(),
                            thirdIndexScore.get(csurpIndexSon.getParentId()) + Double.parseDouble(csurpIndexSon.getScore()));
                } else {
                    thirdIndexScore.put(csurpIndexSon.getParentId(), Double.parseDouble(csurpIndexSon.getScore()));
                }
                continue;
            }
            if (Objects.isNull(csurpIndexSon.getWeight())) {
                weights.put(csurpIndexSon.getId(), BigDecimal.valueOf(0));
            } else {
                weights.put(csurpIndexSon.getId(), csurpIndexSon.getWeight());
            }
            csurpSecondIndexList.add(csurpIndexSon);
        }
        //总得分
        Double totalScore = 0.0;
        for (CsurpIndex csurpThirdIndex : csurpThirdIndexList) {
            totalScore = totalScore + Double
                    .parseDouble(String
                            .valueOf(weights
                                    .get(csurpThirdIndex
                                            .getParentId())
                                    .multiply(BigDecimal
                                            .valueOf(thirdIndexScore
                                                    .get(csurpThirdIndex
                                                            .getParentId())
                                                    / csurpThirdIndexList
                                                    .size()
                                                    * 10L))));
        }
        return totalScore;
    }

    /**
     * 获取二级指标数
     *
     * @return 指标
     */
    private Integer getSecondIndex(CsurpIndex csurpIndex) {
        return csurpIndexMapper.countSecondIndex(csurpIndex);
    }

    /**
     * 获取指定二级下三级指标数
     *
     * @return 指标
     */
    private Integer getThirdIndex(CsurpIndex csurpIndex) {
        return csurpIndexMapper.countThirdIndex(csurpIndex);
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
