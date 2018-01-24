package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/11/27
 * Description 用户价目表
 */
public class SmsUserPriceMenu extends BaseEntity{

    /**
     * 用户编号
     */
    private Integer uid;

    /**
     * 国家id
     */
    private Integer countryId;

    /**
     * 国家
     */
    private String country;

    /**
     * 价格
     */
    private double price;

    /**
     * 状态 0正常 1暂停发送
     */
    private Integer stat;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }
}
