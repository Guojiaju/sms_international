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
        <c:if test="${!empty user.id }">
            <input type="hidden" name="id" id="id" value="${user.id }"/>
            <input type="hidden" name="rolesId" id="rolesId"/>
        </c:if>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input name="username" type="text" value="${user.username }" lay-verify="required" <c:if test="${not empty user.id }">readonly</c:if> datatype="*4-12" ajaxurl="${ctx }/admin/checkUsername"  placeholder="请输入用户名！ " errormsg="输入2-12位[数字]或[字母]或[下划线]！" class="layui-input" />
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">管理员姓名</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="realname" value="${user.realname }" datatype="*2-20" placeholder="请输入管理员姓名！ " lay-verify="required"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" class="layui-input" name="password" autocomplete="off" <c:if test="${not empty user.id }">lay-ignore="lay-ignore" </c:if> datatype="*6-20" placeholder="请输入密码！" errormsg="请输入6位到20位密码！"/>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="phone" value="${user.phone }" datatype="m" placeholder="请输入手机号码！" lay-verify="required" errormsg="请输入正确的手机号码！" />
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">所属组</label>
                <div class="layui-input-block">
                    <c:forEach items="${roles}" var="role" varStatus="status">
                        <input type="checkbox" name="rolesId" value="${role.id }" ${role.checked } title="${role.roleName}" />
                    </c:forEach>
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
    layui.use(['form','layer'], function() {
        var form = layui.form,
                layer = layui.layer;
        form.on('submit(save)',function (data) {

            var ids = "";
            $('#form input[type="checkbox"]:checked').each(function(){
                ids += $(this).val()+",";
            });

            if(ids == null || ids.length <=0){
                layer.alert("请选择所属组！");
                return false;
            }
            data.field["rolesId"] = ids;
            $.ajax({
                type : 'post',
                url : '${ctx}/admin/doEditOrAdd',
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

