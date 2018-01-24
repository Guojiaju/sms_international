package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/12/5
 * Description 客户充值记录
 */
public class SmsChargeRecords extends BaseEntity{

    /**
     * 用户编号
     */
    private Integer uid;

    /**
     * 充值金额
     */
    private Float amount;

    /**
     * 充值类型 0为人工充值,1为系统返还,2其它
     */
    private Integer chargeType;

    /**
     *  0为到账,1为待收款,2为失败
     */
    private Integer stat;

    /**
     * 赠送金额
     */
    private float gift;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人
     */
    private String add_uid;

    /**
     * 充值时间
     */
    private String create_time;

    /**
     * 操作类型 0 后台操作，1前台操作，2其他
     */
    private Integer operate_type;

    /**
     * 充值密码
     */
    private String password;

    private String info;

    private String view_amount;

    /**
     * 公司名
     */
    private String company;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public float getGift() {
        return gift;
    }

    public void setGift(float gift) {
        this.gift = gift;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAdd_uid() {
        return add_uid;
    }

    public void setAdd_uid(String add_uid) {
        this.add_uid = add_uid;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(Integer operate_type) {
        this.operate_type = operate_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getView_amount() {
        return this.amount.toString();
    }

    public void setView_amount(String view_amount) {
        this.view_amount = this.amount.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"uid\":")
                .append(uid);
        sb.append(",\"amount\":")
                .append(amount);
        sb.append(",\"chargeType\":")
                .append(chargeType);
        sb.append(",\"stat\":")
                .append(stat);
        sb.append(",\"gift\":")
                .append(gift);
        sb.append(",\"remark\":\"")
                .append(remark).append('\"');
        sb.append(",\"add_uid\":\"")
                .append(add_uid).append('\"');
        sb.append(",\"create_time\":\"")
                .append(create_time).append('\"');
        sb.append(",\"operate_type\":")
                .append(operate_type);
        sb.append(",\"info\":\"")
                .append(info).append('\"');
        sb.append(",\"company\":\"")
                .append(company).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
