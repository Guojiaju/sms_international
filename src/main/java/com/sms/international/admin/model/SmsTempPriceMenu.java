package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/12/4
 * Description 临时价目实体类
 */
public class SmsTempPriceMenu extends BaseEntity{

    /**
     * 国家编号
     */
    private Integer countryId;

    /**
     * 国家
     */
    private String country;

    /**
     * 通道编号
     */
    private Integer channelId;

    /**
     * 通道
     */
    private String channel;

    /**
     * 单价
     */
    private double price;

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

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
