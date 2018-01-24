<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: guojiaju
  Date: 2017/11/22
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<div style="margin: 5px;">
    <form id="form" class="layui-form">
        <input hidden name="id" value="${user.id}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">企业名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="company" value="${user.company}" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">企业代码</label>
                <div class="layui-input-inline">
                    <input type="text" name="company" value="${user.username}" class="layui-input" readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系人</label>
                <div class="layui-input-inline">
                    <input type="text" name="linkman" value ="${user.linkman}" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">联系电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" value ="${user.phone}" class="layui-input" readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="tel" value ="${user.tel}" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">QQ</label>
                <div class="layui-input-inline">
                    <input type="text" name="qq" value ="${user.qq}" class="layui-input" readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">mail</label>
                <div class="layui-input-inline">
                    <input type="text" name="mail" value ="${user.mail}" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">业务员</label>
                <div class="layui-input-inline">
                    <input type="text" name="sales" value ="${user.sales}" class="layui-input"readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客服</label>
                <div class="layui-input-inline">
                    <input type="text" name="kefu" value ="${user.kefu}" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">内容类别</label>
                <div class="layui-input-inline">
                    <select name="userkind" disabled>
                        <option value="1" <c:if test="${user.userkind == 1}"></c:if>>验证码组</option>
                        <option value="2" <c:if test="${user.userkind == 2}"></c:if>>通知组</option>
                        <option value="3" <c:if test="${user.userkind == 3}"></c:if>>会员组</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">付费类型</label>
                <div class="layui-input-inline">
                    <select name="paytype" disabled>
                        <option value="0" <c:if test="${user.usertype == 0}"></c:if>>预付费</option>
                        <option value="1" <c:if test="${user.usertype == 1}"></c:if>>后付费</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">客户类型</label>
                <div class="layui-input-inline">
                    <select name="usertype" disabled>
                        <option value="1" <c:if test="${user.usertype == 1}"></c:if>>代理</option>
                        <option value="2" <c:if test="${user.usertype == 2}"></c:if>>终端客户</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">签名</label>
                <div class="layui-input-inline">
                    <input type="text" name="kefu" value ="${user.sign}" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-inline">
                    <textarea name="remark" class="layui-textarea" readonly>${user.remark}</textarea>
                </div>
            </div>
        </div>
    </form>
</div>
<script>

    /**
     * 页面加载完成执行form渲染
     */
    $(function(){
        renderForm();
    });

    /**
     * 重新渲染form
     */
    function renderForm(){
        layui.use('form', function(){
            var form = layui.form;
            form.render();
        });
    }

    layui.use('form', function() {
        var form = layui.form;
    });
</script>

