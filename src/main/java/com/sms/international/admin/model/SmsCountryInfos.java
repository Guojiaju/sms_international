package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/11/27
 * Description 国家信息
 */
public class SmsCountryInfos extends BaseEntity{

    /**
     * 国家
     */
    private String country;

    /**
     * 国家英文名
     */
    private String country_en;

    /**
     * 国际域名缩写
     */
    private String country_ab;

    /**
     * 国家代码
     */
    private String countryCode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_en() {
        return country_en;
    }

    public void setCountry_en(String country_en) {
        this.country_en = country_en;
    }

    public String getCountry_ab() {
        return country_ab;
    }

    public void setCountry_ab(String country_ab) {
        this.country_ab = country_ab;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
