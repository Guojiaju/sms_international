package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/11/29
 * Description 通道信息实体类
 */
public class SmsChannel extends BaseEntity{

    /**
     * 网关配置模板(0为任意,1为cmpp,3为smpp,4为http)
     */
    private Integer gatewayTemplate;

    /**
     * 网关端口号
     */
    private Integer gatewayPort;

    /**
     * 网关IP地址
     */
    private String gatewayIp;

    /**
     * 网关账号
     */
    private String gatewayAccount;

    /**
     * 网关密码
     */
    private String gatewayPass;

    /**
     * 网关url地址(主要用于http接口的网关)
     */
    private String gatewayUrl;

    /**
     * 本地ip(主要用于sgip协议)
     */
    private String localIp;

    /**
     * 每次发送间隔时间(单位毫秒),默认值 10
     */
    private Integer localDelay;

    /**
     * 网关提供的流速(无实际作用,主要做为参考,真实环境主要还是看localDelay的睡眠时间),默认值 100
     */
    private Integer localReadNum;

    /**
     * 消息队列端口
     */
    private Integer rabbitPort;

    /**
     * 消息队列IP
     */
    private String rabbitIp;

    /**
     * 消息队列账号
     */
    private String rabbitAccount;

    /**
     * 消息队列密码
     */
    private String rabbitPass;

    /**
     * 通道名称
     */
    private String channelName;

    /**
     * 通道状态0为正常,1为停止,默认值 0
     */
    private Integer status;

    /**
     * 通道单价
     */
    private double price;

    /**
     * 通道提供商
     */
    private String channelProvider;

    /**
     * 备注
     */
    private String remark;

    /**
     * 计费字数
     */
    private Integer sendWordsLen;

    /**
     * 最大字数
     */
    private Integer sendWordsMaxlen;

    /**
     * 报备方式(0为无,1为先报备后发,2为先发后报备)
     */
    private Integer recordType;

    /**
     * 签名位置(0为任意,1为前置,2为后置)
     */
    private Integer signPosition;


    public Integer getGatewayTemplate() {
        return gatewayTemplate;
    }

    public void setGatewayTemplate(Integer gatewayTemplate) {
        this.gatewayTemplate = gatewayTemplate;
    }

    public Integer getGatewayPort() {
        return gatewayPort;
    }

    public void setGatewayPort(Integer gatewayPort) {
        this.gatewayPort = gatewayPort;
    }

    public String getGatewayIp() {
        return gatewayIp;
    }

    public void setGatewayIp(String gatewayIp) {
        this.gatewayIp = gatewayIp;
    }

    public String getGatewayAccount() {
        return gatewayAccount;
    }

    public void setGatewayAccount(String gatewayAccount) {
        this.gatewayAccount = gatewayAccount;
    }

    public String getGatewayPass() {
        return gatewayPass;
    }

    public void setGatewayPass(String gatewayPass) {
        this.gatewayPass = gatewayPass;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public Integer getLocalDelay() {
        return localDelay;
    }

    public void setLocalDelay(Integer localDelay) {
        this.localDelay = localDelay;
    }

    public Integer getLocalReadNum() {
        return localReadNum;
    }

    public void setLocalReadNum(Integer localReadNum) {
        this.localReadNum = localReadNum;
    }

    public Integer getRabbitPort() {
        return rabbitPort;
    }

    public void setRabbitPort(Integer rabbitPort) {
        this.rabbitPort = rabbitPort;
    }

    public String getRabbitIp() {
        return rabbitIp;
    }

    public void setRabbitIp(String rabbitIp) {
        this.rabbitIp = rabbitIp;
    }

    public String getRabbitAccount() {
        return rabbitAccount;
    }

    public void setRabbitAccount(String rabbitAccount) {
        this.rabbitAccount = rabbitAccount;
    }

    public String getRabbitPass() {
        return rabbitPass;
    }

    public void setRabbitPass(String rabbitPass) {
        this.rabbitPass = rabbitPass;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChannelProvider() {
        return channelProvider;
    }

    public void setChannelProvider(String channelProvider) {
        this.channelProvider = channelProvider;
    }

    public Integer getSendWordsLen() {
        return sendWordsLen;
    }

    public void setSendWordsLen(Integer sendWordsLen) {
        this.sendWordsLen = sendWordsLen;
    }

    public Integer getSendWordsMaxlen() {
        return sendWordsMaxlen;
    }

    public void setSendWordsMaxlen(Integer sendWordsMaxlen) {
        this.sendWordsMaxlen = sendWordsMaxlen;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getSignPosition() {
        return signPosition;
    }

    public void setSignPosition(Integer signPosition) {
        this.signPosition = signPosition;
    }
}
