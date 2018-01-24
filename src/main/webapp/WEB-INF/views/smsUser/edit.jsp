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
        <c:if test="${user != null}">
            <input hidden name="id" value="${user.id}">
        </c:if>
        <c:if test="${parent != null}">
            <input hidden name="parentId" value="${parent.id}">
        </c:if>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">企业代码</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" placeholder="请输入企业代码" value="${user.username}" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="text" name="pwd" id="pwd" placeholder="请输入密码" value ="" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux mt-9">
                    <button type="button" class="layui-btn" onclick="generatePwd()">生成</button>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">企业名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="company" lay-verify="required" placeholder="请输入企业名称" value="${user.company}${parent.cname}" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">联系人</label>
                <div class="layui-input-inline">
                    <input type="text" name="linkman" required placeholder="请输入联系人" value ="${user.linkman}${parent.link_man}" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" lay-verify="required" placeholder="请输入手机号" value ="${user.phone}${parent.phone}" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="tel" placeholder="请输入电话" value ="${user.tel}${parent.tel}" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">QQ</label>
                <div class="layui-input-inline">
                    <input type="text" name="qq"  placeholder="请输入qq" value ="${user.qq}${parent.qq}" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">mail</label>
                <div class="layui-input-inline">
                    <input type="text" name="mail" placeholder="请输入邮箱" value ="${user.mail}${parent.email}" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">业务员</label>
                <div class="layui-input-inline">
                    <input type="text" name="sales" placeholder="请输入业务员" value ="${user.sales}${parent.sales}"
                           class="layui-input" <c:if test="${user.sales != null}">readonly</c:if>>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">客服</label>
                <div class="layui-input-inline">
                    <input type="text" name="kefu" placeholder="请输入客服" value ="${user.kefu}"
                           class="layui-input" <c:if test="${user.kefu != null}">readonly</c:if>>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">内容类别</label>
                <div class="layui-input-inline">
                    <select name="userkind">
                        <option value="1" <c:if test="${user.userkind == 1}">selected</c:if>>验证码组</option>
                        <option value="2" <c:if test="${user.userkind == 2}">selected</c:if>>通知组</option>
                        <option value="3" <c:if test="${user.userkind == 3}">selected</c:if>>会员组</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">客户类型</label>
                <div class="layui-input-inline">
                    <select name="usertype">
                        <option value="1" <c:if test="${user.usertype == 1}">selected</c:if>>代理</option>
                        <option value="2" <c:if test="${user.usertype == 2}">selected</c:if>>终端客户</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">付费类型</label>
                <div class="layui-input-inline">
                    <select name="paytype">
                        <option value="0" <c:if test="${user.usertype == 0}">selected</c:if>>预付费</option>
                        <option value="1" <c:if test="${user.usertype == 1}">selected</c:if>>后付费</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">签名</label>
                <div class="layui-input-inline">
                    <input type="text" name="sign" required lay-verify="required" placeholder="请输入签名" value ="${user.sign}"
                           class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"><font color="red">签名以'【' 开头，以'】'结尾</font></div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入内容" class="layui-textarea">${user.remark}</textarea>
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
            var sign = data.field['sign'];
            if(!sign.startsWith("【")){
                sign = "【" + sign;
            }
            if(!sign.endsWith("】")){
                sign = sign +"】";
            }
            data.field['sign'] = sign;
            $.ajax({
                type : 'post',
                url : '${ctx}/smsUser/doAddOrEdit',
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

    /**
     * 生成密码
     */
    function generatePwd(){
        $("#pwd").val(randPassword());
    }
</script>

