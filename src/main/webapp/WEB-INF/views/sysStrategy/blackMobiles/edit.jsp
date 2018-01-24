<%--
  Created by IntelliJ IDEA.
  User: guojiaju
  Date: 2017/11/22
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="../../common/inc/header.jsp" %>
<%@include file="../../common/inc/jscss.jsp" %>
<div style="margin: 5px;">
    <form id="updateDiv" class="layui-form">
        <input hidden name="id" value="${mobile.id}">
        <input hidden name="group_id" value="${mobile.group_id}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">黑名单</label>
                <div class="layui-input-inline">
                    <input class="layui-input" name="mobile" value="${mobile.mobile}" readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">黑名单类型</label>
                <div class="layui-input-inline">
                    <select name="mobileType" class="layui-select">
                        <c:forEach items="${mobileType}" var="type">
                            <option value="${type.id}"
                                    <c:if test="${type.id == mobile.mobileType}">selected</c:if>>${type.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-inline">
                    <input type="text" name="remark" value="${mobile.remark}" placeholder="请输入备注信息" class="layui-input">
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
    layui.use('form', function () {
        var form = layui.form;
        form.on('submit(save)', function (data) {
            $.ajax({
                type: 'post',
                url: '${ctx}/smsBlackMobile/doUpdate',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data.field),
                success: function (result) {
                    layer.msg(result > 0 ? "操作成功" : "操作失败", {time: 2000}, function () {
                        parent.layer.closeAll(); //执行关闭
                        parent.$("li[data-group='1']").click();
                    });
                },
                error: function (result) {
                    layer.alert("系统错误");
                }
            });
            return false;
        });
    });
</script>

