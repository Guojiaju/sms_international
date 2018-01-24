package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2018/1/17
 * Description 价目模板详细配置
 */
public class SmsPriceTemplateDetail {

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
    private double price ;

    /**
     * 模板id
     */
    private Integer temp_id;

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

    public Integer getTemp_id() {
        return temp_id;
    }

    public void setTemp_id(Integer temp_id) {
        this.temp_id = temp_id;
    }
}
