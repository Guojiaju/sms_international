package com.sms.international.admin.model;

import java.util.Date;

/**
 * Author guojiaju
 * Date 2017/3/28
 * Description
 */
public class SmsBlackWordsType extends BaseEntity {

    /**
     * 类型名称
     */
    private String name;
    /**
     *  屏蔽词等级
     */
    private Integer level;

    /**
     * 创建时间
     */
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
