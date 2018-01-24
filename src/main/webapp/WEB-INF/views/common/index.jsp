<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@include file="inc/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>国际短信平台管理端</title>
    <%@include file="inc/jscss.jsp" %>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">国际短信平台管理端</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
       <%-- <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">待办</a></li>
            <li class="layui-nav-item"><a href="">报表</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>--%>
        <ul class="layui-nav layui-layout-right" lay-filter="settings">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${admin_user}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="${ctx}/admin/logOut">退出系统</a></dd>
                    <dd><a href="javascript:void(0);" lay-event="updatePassword">修改密码</a></dd>
                </dl>
            </li>
           <%-- <li class="layui-nav-item"></li>--%>
        </ul>
    </div>
    <div id="passwordModal" style="display: none;">
        <div style="margin-top: 10px;">
            <form id="form" class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">原密码</label>
                        <div class="layui-input-inline">
                            <input type="text" name="password" required placeholder="请输入原密码" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">新密码</label>
                        <div class="layui-input-inline">
                            <input type="text" name="newPassword" required placeholder="请输入新密码" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">确认密码</label>
                        <div class="layui-input-inline">
                            <input type="text" id="confirmPassword" name="confirmPassword" required placeholder="请输入新密码" class="layui-input">
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
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <c:forEach items="${menuListMap }" var="menu" varStatus="i">
                    <li class="layui-nav-item <c:if test="${i.index==0}">layui-nav-itemed</c:if>">
                        <a class="" href="javascript:;">${menu.key.name }</a>
                        <dl class="layui-nav-child">
                            <c:forEach items="${menu.value }" var="m1" varStatus="mi">
                                <dd><a href="${ctx }${m1.url }" target="mainFrame" title="${m1.name }">${m1.name }</a>
                                </dd>
                            </c:forEach>
                        </dl>
                    </li>
                </c:forEach>
<%--
                <li class="layui-nav-item"><a href="http://www.layui.com/demo/grid.html" target="_blank">模板地址</a></li>
--%>
                <li class="layui-nav-item"><a href="${ctx }/testSend" target="mainFrame">测试发送</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 10px;"></div>
        <iframe src="${ctx }/testSend" id="mainFrame" name="mainFrame" width="100%" frameborder="0" height="96%">
            您的浏览器不支持框架
        </iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
    </div>
</div>
<script>
    //修改菜单点击事件，点击其他关闭自己
    $(function () {
        $(".layui-nav-item").click(function () {
            if(!$(this).hasClass('layui-nav-itemed')){
                $(this).removeClass('layui-nav-itemed');
            }else{
                $(this).parent('.layui-nav-tree').find('li').each(function(index,element){
                    $(element).removeClass('layui-nav-itemed');
                });
                $(this).addClass('layui-nav-itemed');
            }
        });
    });

    //JavaScript代码区域
    layui.use(['element','layer','form'], function () {
        var element = layui.element,
                layer = layui.layer,
                form =layui.form;

        //点击修改密码弹出修改密码框
        element.on('nav(settings)', function (obj) {
           if(obj.text() == '修改密码'){
               layer.open({
                    type: 1,
                    title: '修改密码',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['380px', '300px'], //宽高
                    content: $('#passwordModal')
               });
            }
        });

        //修改密码
        form.on('submit(save)',function (data) {
            if(data.field['password'] == null || data.field['password'] == ''){
                return false;
            }

            if(data.field['newPassword'] == null || data.field['newPassword'] == ''){
                return false;
            }

            if(data.field['confirmPassword'] == null || data.field['confirmPassword'] == ''){
                return false;
            }

            if(data.field['newPassword'] != data.field['confirmPassword']){
                layer.tips("两次密码输入不一致,请重新输入",'#confirmPassword');
                return false;
            }
            $.ajax({
                type : 'post',
                url : '${ctx}/admin/doChangePwd',
                contentType : 'application/json',
                dataType : 'json',
                data : JSON.stringify(data.field),
                success : function(result){
                    if(result.status == 'y'){
                        layer.msg(result.info +",请重新登陆",{time:2000},function(){
                            window.location.href='${ctx}/admin/logOut';
                        });
                    }else if(result.status == 'n'){
                        layer.msg(result.info,{time:2000});
                    }
                },
                error : function(result){
                    layer.alert("系统错误");
                }
            });
            return false;
        });
    });
</script>
</body>
</html>