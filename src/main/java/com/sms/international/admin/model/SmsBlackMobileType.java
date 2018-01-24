package com.sms.international.admin.model;

import java.util.Date;

/**
 * Author guojiaju
 * Date 2017/3/28
 * Description 黑名单类型
 */
public class SmsBlackMobileType extends BaseEntity {

    /**
     * 类型名称
     */
    private String name;
    /**
     *  黑名单等级
     */
    private Integer level;

    private Date createdDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
