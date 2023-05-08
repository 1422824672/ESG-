package com.ruoyi.csrup.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 指标对象 csurp_index
 * 
 * @author ruoyi
 * @date 2023-04-26
 */
public class CsurpIndex extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 父id */
    private Long parentId;

    /**
     * 公司id
     */
    private Long companyId;

    /** 指标名称 */
    @Excel(name = "指标名称")
    private String name;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Long sort;

    /** 定性：文本框用户输入文字（300字） */
    @Excel(name = "定性：文本框用户输入文字（300字）")
    private String qualitative;

    /** 性质 */
    @Excel(name = "性质")
    private String nature;

    /** 定量：文本框用户输入对应数值，给出单位） */
    @Excel(name = "定量：文本框用户输入对应数值，给出单位）")
    private String quantify;

    /** 填写说明 */
    @Excel(name = "填写说明")
    private String illustrate;

    /** 指标评分 */
    @Excel(name = "指标评分")
    private String score;

    /** 父指标名称 */
    private String parentName;

    /** 子指标 */
    private List<CsurpIndex> children = new ArrayList<>();

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getQualitative() {
        return qualitative;
    }

    public void setQualitative(String qualitative) {
        this.qualitative = qualitative;
    }

    public String getQuantify() {
        return quantify;
    }

    public void setQuantify(String quantify) {
        this.quantify = quantify;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<CsurpIndex> getChildren() {
        return children;
    }

    public void setChildren(List<CsurpIndex> children) {
        this.children = children;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setIllustrate(String illustrate) 
    {
        this.illustrate = illustrate;
    }

    public String getIllustrate() 
    {
        return illustrate;
    }
    public void setScore(String score) 
    {
        this.score = score;
    }

    public String getScore() 
    {
        return score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("name", getName())
            .append("sort", getSort())
            .append("nature", getNature())
            .append("qualitative", getQualitative())
            .append("quantify", getQuantify())
            .append("illustrate", getIllustrate())
            .append("score", getScore())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
