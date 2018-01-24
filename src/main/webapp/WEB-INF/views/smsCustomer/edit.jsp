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
        <input hidden name="id" value="${customer.id}">
        <div class="layui-form-item">
            <label class="layui-form-label">客户名称</label>
            <div class="layui-input-inline">
                <input type="text" name="cname" required  lay-verify="required" placeholder="请输入客户名称" value="${customer.cname}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" required lay-verify="required" placeholder="请输入手机号" value ="${customer.phone}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话</label>
            <div class="layui-input-inline">
                <input type="text" name="tel" placeholder="请输入电话" value ="${customer.tel}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系人</label>
            <div class="layui-input-inline">
                <input type="text" name="link_man" required lay-verify="required" placeholder="请输入联系人" value ="${customer.link_man}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-inline">
                <input type="text" name="address" required lay-verify="required" placeholder="请输入地址" value ="${customer.address}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">QQ</label>
            <div class="layui-input-inline">
                <input type="text" name="qq" placeholder="请输入qq" value ="${customer.qq}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">email</label>
            <div class="layui-input-inline">
                <input type="text" name="email" placeholder="请输入邮箱" value ="${customer.email}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">业务员</label>
            <div class="layui-input-inline">
                <input type="text" name="sales" required lay-verify="required" placeholder="请输入业务员" value ="${customer.sales}" 
                       class="layui-input" <c:if test="${customer.sales != null and customer.sales != ''}">readonly</c:if>>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入内容" class="layui-textarea">${customer.remark}</textarea>
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
                url : '${ctx}/smsCustomer/addOrEdit',
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

