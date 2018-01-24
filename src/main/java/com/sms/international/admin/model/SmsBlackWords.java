package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/12/19
 * Description 系统屏蔽词
 */
public class SmsBlackWords extends BaseEntity {

    /**
     * 屏蔽词
     */
    private String words;

    /**
     * 备注
     */
    private String remark;

    /**
     * 组别 5是审核屏蔽，6是自动屏蔽词
     */
    private Integer group_id;

    /**
     * 创建时间
     */
    private String addtime;

    /**
     * 屏蔽词类型
     */
    private Integer screenType;

    /**
     * 屏蔽词
     */
    private String wordsStr;

    /**
     * 组别
     */
    private Integer groupType;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getScreenType() {
        return screenType;
    }

    public void setScreenType(Integer screenType) {
        this.screenType = screenType;
    }

    public String getWordsStr() {
        return wordsStr;
    }

    public void setWordsStr(String wordsStr) {
        this.wordsStr = wordsStr;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"words\":\"")
                .append(words).append('\"');
        sb.append(",\"remark\":\"")
                .append(remark).append('\"');
        sb.append(",\"group_id\":")
                .append(group_id);
        sb.append(",\"addtime\":\"")
                .append(addtime).append('\"');
        sb.append(",\"screenType\":")
                .append(screenType);
        sb.append(",\"wordsStr\":\"")
                .append(wordsStr).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
