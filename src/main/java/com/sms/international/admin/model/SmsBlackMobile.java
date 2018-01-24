package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/12/11
 * Description 系统黑名单
 */
public class SmsBlackMobile extends BaseEntity{

    /**
     * 黑名单号码
     */
    private Long mobile;

    /**
     * 策略组id
     */
    private Integer group_id;

    /**
     * 创建时间
     */
    private String addtime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 危险类型
     */
    private Integer mobileType;

    /**
     * 危险等级
     */
    private Integer level;

    /**
     * 关联关系
     */
    private String relation;

    /**
     * 国家
     */
    private String country;

    /**
     * 页面传回来的号码字符串
     */
    private String mobileStr;

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getMobileType() {
        return mobileType;
    }

    public void setMobileType(Integer mobileType) {
        this.mobileType = mobileType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobileStr() {
        return mobileStr;
    }

    public void setMobileStr(String mobileStr) {
        this.mobileStr = mobileStr;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"mobile\":")
                .append(mobile);
        sb.append(",\"group_id\":")
                .append(group_id);
        sb.append(",\"addtime\":\"")
                .append(addtime).append('\"');
        sb.append(",\"remark\":\"")
                .append(remark).append('\"');
        sb.append(",\"mobileType\":")
                .append(mobileType);
        sb.append(",\"level\":")
                .append(level);
        sb.append(",\"relation\":\"")
                .append(relation).append('\"');
        sb.append(",\"country\":\"")
                .append(country).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
