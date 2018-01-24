<%--
  Created by IntelliJ IDEA.
  User: guojiaju
  Date: 2017/11/22
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="../common/inc/header.jsp"%>
<%@include file="../common/inc/jscss.jsp"%>
<div style="margin: 5px;">
    <form id="form" class="layui-form">
        <input hidden name="id" value="${channel.id}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">通道名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="channelName" required placeholder="请输入通道名称" value="${channel.channelName}" size="20" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">提供商</label>
                <div class="layui-input-inline">
                    <input type="text" name="channelProvider" placeholder="请输入提供商" value="${channel.channelProvider}" size="20" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">接入方式</label>
                <div class="layui-input-inline">
                    <select name="gatewayTemplate" id="gatewayTemplate">
                        <option value="0" <c:if test="${channel.gatewayTemplate == 0 }">selected</c:if>>任意</option>
                        <option value="1" <c:if test="${channel.gatewayTemplate == 1 }">selected</c:if>>CMPP</option>
<%--
                        <option value="2" <c:if test="${channel.gatewayTemplate == 2 }">selected</c:if>>SGIP</option>
--%>
                        <option value="3" <c:if test="${channel.gatewayTemplate == 3 }">selected</c:if>>SMPP</option>
                        <option value="4" <c:if test="${channel.gatewayTemplate == 4 }">selected</c:if>>HTTP</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">网关端口</label>
                <div class="layui-input-inline">
                    <input type="text" name="gatewayPort" placeholder="请输入端口号" value="${channel.gatewayPort}" size="20" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">网关IP</label>
                <div class="layui-input-inline">
                    <input type="text" name="gatewayIp" placeholder="请输入IP" value="${channel.gatewayIp}" size="20" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">账号</label>
                <div class="layui-input-inline">
                    <input type="text" name="gatewayAccount" placeholder="请输入账号" value="${channel.gatewayAccount}" size="20" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="text" name="gatewayPass" placeholder="请输入密码" value="${channel.gatewayPass}" size="20" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">网关url</label>
                <div class="layui-input-inline">
                    <input type="text" name="gatewayUrl" placeholder="请输入url" value="${channel.gatewayUrl}" size="20" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">本地IP</label>
                <div class="layui-input-inline">
                    <input type="text" name="localIp" placeholder="请输入密码" value="${channel.localIp}" size="20" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">流速(条/秒)</label>
                <div class="layui-input-inline">
                    <c:if test="${channel.localDelay > 0}">
                        <input type="text" name="localDelay" placeholder="请输入流速" value="<fmt:formatNumber type="number" value="${1000/channel.localDelay}" maxFractionDigits="0"/>" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" size="20" class="layui-input">
                    </c:if>
                    <c:if test="${channel.localDelay == null}">
                        <input type="text" name="localDelay" placeholder="请输入流速" value="" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" size="20" class="layui-input">
                    </c:if>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">队列IP</label>
                <div class="layui-input-inline">
                    <input type="text" name="rabbitIp" placeholder="请输入队列IP" value="${channel.rabbitIp}" size="20" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">队列端口</label>
                <div class="layui-input-inline">
                    <input type="text" name="rabbitPort" placeholder="请输入队列端口" value="${channel.rabbitPort}" size="20" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">队列账号</label>
                <div class="layui-input-inline">
                    <input type="text" name="rabbitAccount" placeholder="请输入队列IP" value="${channel.rabbitAccount}" size="20" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">队列密码</label>
                <div class="layui-input-inline">
                    <input type="text" name="rabbitPass" placeholder="请输入队列端口" value="${channel.rabbitPass}" size="20" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">报备方式</label>
                <div class="layui-input-inline">
                    <select name="recordType">
                        <option value="0" <c:if test="${channel.recordType == 0}"></c:if>>无</option>
                        <option value="1" <c:if test="${channel.recordType == 1}"></c:if>>先报备后发</option>
                        <option value="2" <c:if test="${channel.recordType == 2}"></c:if>>先发后报备</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">签名位置</label>
                <div class="layui-input-inline">
                    <select name="signPosition">
                        <option value="0" <c:if test="${channel.signPosition == 0}"></c:if>>任意</option>
                        <option value="1" <c:if test="${channel.signPosition == 1}"></c:if>>前置</option>
                        <option value="2" <c:if test="${channel.signPosition == 2}"></c:if>>后置</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">运行状态</label>
                <div class="layui-input-inline">
                    <input name="status" type="radio" value="0" <c:if test="${empty channel || channel.status == 0 }">checked</c:if> title="正常"/>
                    <input name="status" type="radio" value="1" <c:if test="${channel.status == 1 }">checked</c:if> title="停止"/>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-inline">
                    <input type="text" name="remark" placeholder="请输入备注信息" value="${channel.remark}" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="save">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>
    layui.use('form', function() {
        var form = layui.form;
        form.on('submit(save)',function (data) {
            $.ajax({
                type : 'post',
                url : '${ctx}/channel/doAddOrEdit',
                contentType : 'application/json',
                dataType : 'json',
                data : JSON.stringify(data.field),
                success : function(result){
                    layer.msg(result > 0 ? "操作成功":"操作失败",{time:2000},function(){
                        parent.layer.closeAll(); //执行关闭
                        parent.location.reload();
                    });
                },
                error : function(result){
                    layer.alert("系统错误");
                }
            });
            return false;
        });
    });
</script>

