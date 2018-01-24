package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2018/1/17
 * Description 价目模板
 */
public class SmsPriceTemplate extends BaseEntity {

    /**
     * 模板名称
     */
    private String name;

    /**
     * 模板描述
     */
    private String description;

    /**
     * 模板状态 0 有效 1 无效
     */
    private Integer status;

    /**
     * 添加时间
     */
    private String addTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"description\":\"")
                .append(description).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"addTime\":\"")
                .append(addTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
