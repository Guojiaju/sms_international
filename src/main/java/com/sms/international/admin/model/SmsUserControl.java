package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/11/27
 * Description 用户控制信息
 */
public class SmsUserControl extends BaseEntity{

    private Integer uid;

    /**
     * 是否前台显示报告;0为不显示,1为显示(默认0)
     */
    private Integer isShowRpt;

    /**
     * 是否开通子账号;0为不开通;1为开通(默认0)
     */
    private Integer childFun;

    /**
     * 子账号数量;在childfun为1是起作用(默认0)
     */
    private Integer childNum;

    /**
     * 是否审核;1为不审核;0为审核(默认1)
     */
    private Integer isRelease;

    /**
     * 短信审核数量启始值;在isRelease为0时起作用(默认0)
     */
    private Integer releaseNum;

    /**
     * 重号过滤;0为不过滤;1为10分钟;2为1小时;3为24小时(默认0)
     */
    private Integer repeatFilter;

    /**
     * 重号过滤数量;在repeatFilter大于0时起作用(默认0)
     */
    private Integer repeatNum;

    /**
     * 签名位置;1为前置;2为后置;3为任意(默认1)
     */
    private Integer signPosition;

    /**
     * 签名方式;0为强制签名;1为自签;2为系统补全拓展(默认0)
     */
    private Integer expidSign;

    /**
     * 绑定IP;最大可以绑定10个,用逗号分隔
     */
    private String proxyIp;

    /***
     * 切换通道的时候用，短信组别
     */
    private Integer userkind;

    /**
     * 用户每秒速度限制(默认为0)
     */
    private Integer speed;

    /**
     * 是否过滤全量黑名单0为不过滤,1为过滤
     */
    private Integer blackAll;

    /***
     * 是否支持重复相同签名0不支持，1支持
     */
    private Integer repeatSign;

    /***
     * 相同签名允许重复个数
     */
    private Integer repeatSignNum;

    /**
     * 接入方式，0http,1cmpp,9非接口
     */
    private Integer submitType;

    /**
     * 通道id
     */
    private Integer channelId;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIsShowRpt() {
        return isShowRpt;
    }

    public void setIsShowRpt(Integer isShowRpt) {
        this.isShowRpt = isShowRpt;
    }

    public Integer getChildFun() {
        return childFun;
    }

    public void setChildFun(Integer childFun) {
        this.childFun = childFun;
    }

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    public Integer getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(Integer isRelease) {
        this.isRelease = isRelease;
    }

    public Integer getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(Integer releaseNum) {
        this.releaseNum = releaseNum;
    }

    public Integer getRepeatFilter() {
        return repeatFilter;
    }

    public void setRepeatFilter(Integer repeatFilter) {
        this.repeatFilter = repeatFilter;
    }

    public Integer getRepeatNum() {
        return repeatNum;
    }

    public void setRepeatNum(Integer repeatNum) {
        this.repeatNum = repeatNum;
    }

    public Integer getSignPosition() {
        return signPosition;
    }

    public void setSignPosition(Integer signPosition) {
        this.signPosition = signPosition;
    }

    public Integer getExpidSign() {
        return expidSign;
    }

    public void setExpidSign(Integer expidSign) {
        this.expidSign = expidSign;
    }

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public Integer getUserkind() {
        return userkind;
    }

    public void setUserkind(Integer userkind) {
        this.userkind = userkind;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getBlackAll() {
        return blackAll;
    }

    public void setBlackAll(Integer blackAll) {
        this.blackAll = blackAll;
    }

    public Integer getRepeatSign() {
        return repeatSign;
    }

    public void setRepeatSign(Integer repeatSign) {
        this.repeatSign = repeatSign;
    }

    public Integer getRepeatSignNum() {
        return repeatSignNum;
    }

    public void setRepeatSignNum(Integer repeatSignNum) {
        this.repeatSignNum = repeatSignNum;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getSubmitType() {
        return submitType;
    }

    public void setSubmitType(Integer submitType) {
        this.submitType = submitType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"uid\":")
                .append(uid);
        sb.append(",\"isShowRpt\":")
                .append(isShowRpt);
        sb.append(",\"childFun\":")
                .append(childFun);
        sb.append(",\"childNum\":")
                .append(childNum);
        sb.append(",\"isRelease\":")
                .append(isRelease);
        sb.append(",\"releaseNum\":")
                .append(releaseNum);
        sb.append(",\"repeatFilter\":")
                .append(repeatFilter);
        sb.append(",\"repeatNum\":")
                .append(repeatNum);
        sb.append(",\"signPosition\":")
                .append(signPosition);
        sb.append(",\"expidSign\":")
                .append(expidSign);
        sb.append(",\"proxyIp\":\"")
                .append(proxyIp).append('\"');
        sb.append(",\"userkind\":")
                .append(userkind);
        sb.append(",\"speed\":")
                .append(speed);
        sb.append(",\"blackAll\":")
                .append(blackAll);
        sb.append(",\"repeatSign\":")
                .append(repeatSign);
        sb.append(",\"repeatSignNum\":")
                .append(repeatSignNum);
        sb.append(",\"submitType\":")
                .append(submitType);
        sb.append(",\"channelId\":")
                .append(channelId);
        sb.append('}');
        return sb.toString();
    }
}
