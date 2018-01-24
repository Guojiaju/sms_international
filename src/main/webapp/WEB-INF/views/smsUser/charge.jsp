<%--
  Created by IntelliJ IDEA.
  User: guojiaju
  Date: 2017/11/22
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="../common/inc/jscss.jsp" %>
    <%@ include file="../common/inc/header.jsp"%>
    <style>
        body {
            padding: 10px;
        }
    </style>
</head>
<body>
<div style="margin: 5px;">
    <form id="form" class="layui-form" lay-filter="chargeForm">
        <input hidden name="uid" value="${user.id}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">企业代码</label>
                <div class="layui-input-inline">
                   <input class="layui-input" name="username" id="username" value="${user.username}" readonly>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">企业名称</label>
                <div class="layui-input-inline">
                    <input class="layui-input" name="company" id="company" value="${user.company}" readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">充值金额</label>
                <div class="layui-input-inline">
                   <input class="layui-input" lay-verify="required" name="amount" id ="amount" value="" onChange="checkData(this)">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span>元</span>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label text-left">充值方式</label>
                <div class="layui-input-inline ml-25">
                    <select name="chargeType" id="chargeType">
                        <option value="0">人工充值</option>
                        <option value="1">系统返还</option>
                        <option value="2">其他</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">是否到账</label>
                <div class="layui-input-inline">
                    <input name="stat" type="radio" value="0" checked title="已到账"/>
                    <input name="stat" type="radio" value="1" title="未到账"/>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">充值密码</label>
                <div class="layui-input-inline">
                    <input type="password" lay-verify="required" name="password" id="password" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="save">充值</button>
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
                url : '${ctx}/smsUser/doCharge',
                contentType : 'application/json',
                dataType : 'json',
                data : JSON.stringify(data.field),
                success : function(result){
                    layer.msg(result > 0 ? "充值成功":"充值失败",{time:2000},function(){
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

        /**
         * 监听开通子账号下拉框选中事件
         */
        form.on('select(childFun)', function(data){
            if(data.value==1){
                document.getElementById("childNum").style.visibility = 'visible';
            }else{
                document.getElementById("childNum").style.visibility = 'hidden';
            }
        });
    });

</script>
</body>
</html>
