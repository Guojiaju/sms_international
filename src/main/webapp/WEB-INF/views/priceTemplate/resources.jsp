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
        <input hidden id="temp_id" name="uid" value="${temp_id}">
        <c:forEach items="${details}" var="item">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">国家或地区</label>
                    <div class="layui-input-inline">
                        <select name="countryId" lay-verify="required" lay-search>
                            <option value="">直接选择或搜索选择</option>
                            <c:forEach items="${country}" var="country">
                                <option value="${country.id}" <c:if test="${item.countryId == country.id}">selected</c:if>>${country.country}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">价格</label>
                    <div class="layui-input-inline">
                        <input name="price" class="layui-input" value="${item.price}" data-value="${item.price}" onChange="checkData(this)"/>
                    </div>
                    <div class="layui-form-mid layui-word-aux">元</div>
                    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" onclick="remove(this);">删除</button>
                </div>
            </div>
        </c:forEach>
    </form>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-sm" onclick="addPrice();">添加价目数据</button>
            <button class="layui-btn layui-btn-sm" onclick="save();">保存</button>
        </div>
    </div>
</div>
<script>
    var layer;
    layui.use(['form','layer'], function(){
        var form = layui.form,
            layer = layui.layer;
    });
    /**
     * 添加
     */
    function addPrice(){
        var form = $("#form");
        var children = '<div class="layui-form-item">'
                     + '<div class="layui-inline">'
                     + '<label class="layui-form-label">国家或地区</label>'
                     + '<div class="layui-input-inline">'
                     + '<select name="countryId" lay-verify="required" lay-search>'
                     + '<option value="">直接选择或搜索选择</option>'
                     + '<c:forEach items="${country}" var="country">'
                     + '<option value="${country.id}"> ${country.country}</option>'
                     + '</c:forEach>'
                     + '</select>'
                     + '</div></div>&nbsp;'
                     + '<div class="layui-inline">'
                     + '<label class="layui-form-label">价格</label>'
                     + '<div class="layui-input-inline">'
                     + '<input name="price" class="layui-input" value="" data-value="${item.price}" onChange="checkData(this)"/>'
                     + '</div><div class="layui-form-mid layui-word-aux">元</div>'
                     +'<button type="button" class="layui-btn layui-btn-danger layui-btn-sm" onclick="remove(this);">删除</button></div>&nbsp;'
                     + '</div>';
        form.append(children);
        renderForm();
    }

    /**
     * 保存
     */
    function save(){
        var data = [];
        $("#form .layui-form-item").each(function(){
            var param = {};
            param['countryId'] = $(this).find('select').val();
            param['country'] = $(this).find('option:checked').text();
            param['price'] = $(this).find('input[name="price"]').val();
            param['temp_id'] = $("#temp_id").val();
            if((param['countryId'] != null && param['countryId'] != '') && (param['price'] !=null && param['price'] != '')){
                data.push(param);
            }
        });
        $.ajax({
            type:'post',
            dataType :'json',
            contentType : 'application/json',
            url:'${ctx}/priceTemplate/savePriceInfos/'+ ${temp_id},
            data : JSON.stringify(data),
            success : function(result){
                layer.msg(result > 0 ? "操作成功" : "操作失败", {time: 2000}, function () {
                    parent.layer.closeAll();
                });
            },
            error:function(){
                layer.msg("系统错误", {time: 2000});
            }
        });
    }

    /**
     * 删除一行
     */
    function remove(obj) {
        $(obj).parents(".layui-form-item").remove();
    }

    /**
     * 重新渲染form
     */
    function renderForm(){
        layui.use('form', function(){
            var form = layui.form;
            form.render();
        });
    }

    /**
     * 检测输入的内容是否是数字或者小数
     */
    function checkData(obj){
        var value = $(obj).val();
        if(!checkNumber(value)){
            $(obj).val($(obj).attr('data-value'));
            layer.tips("请输入整数或者小数,且小数点后只能保留3位",$(obj));
        }
    }
</script>

