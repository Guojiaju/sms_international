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
        <c:if test="${obj != null}">
            <input hidden name="id" value="${obj.id}">
        </c:if>
        <div class="layui-form-item">
            <label class="layui-form-label">模板名称</label>
            <div class="layui-input-inline">
                <input type="text" name="name" lay-verify="required" placeholder="请输入模板名称" value="${obj.name}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">模板描述</label>
            <div class="layui-input-inline">
                <input type="text" name="description" lay-verify="required" placeholder="请输入模板描述" value="${obj.description}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <input name="status" type="radio" value="0" <c:if test="${empty obj ||obj.status == 0 }">checked</c:if> title="有效"/>
                <input name="status" type="radio" value="1" <c:if test="${obj.status == 1 }">checked</c:if> title="无效"/>
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
    layui.use(['form','jquery'], function() {
        var form = layui.form,
                $ = layui.jquery;
        form.on('submit(save)',function (data) {
            $.ajax({
                type : 'post',
                url : '${ctx}/priceTemplate/doEdit',
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

