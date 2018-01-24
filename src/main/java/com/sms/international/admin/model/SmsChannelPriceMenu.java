package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/11/27
 * Description 通道价目表
 */
public class SmsChannelPriceMenu extends BaseEntity{

    /**
     * 通道编号
     */
    private Integer channelId;

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

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
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
