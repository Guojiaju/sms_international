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
    <form id="form" class="layui-form" lay-filter="controlForm">
        <input hidden name="uid" value="${uid}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">状态显示</label>
                <div class="layui-input-inline">
                    <select name="isShowRpt" id="isShowRpt">
                        <option value="0" <c:if test="${control.isShowRpt == 0}"> selected </c:if>>不显示</option>
                        <option value="1" <c:if test="${control.isShowRpt == 1}"> selected </c:if>>显示</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">开通子账号</label>
                <div class="layui-input-inline">
                    <select name="childFun" id="childFun" lay-filter="childFun" >
                        <option value="0" <c:if test="${control.childFun == 0}"> selected </c:if>>不开通</option>
                        <option value="1" <c:if test="${control.childFun == 1}"> selected </c:if>>开通</option>
                    </select>
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <input id="childNum" name="childNum" value="${control.childNum}" type="text" onkeyup="numberValidate(this)" class="layui-input" style=" width:60px;margin-top: -8px; <c:if test="${control.childFun == 0}">visibility: hidden;</c:if>" placeholder="数量"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">签名位置</label>
                <div class="layui-input-inline">
                    <select name="signPosition" id="signPosition">
                        <option value="1" <c:if test="${control.signPosition == 1}"> selected </c:if>>前置</option>
                        <option value="2" <c:if test="${control.signPosition == 2}"> selected </c:if>>后置</option>
                        <option value="3" <c:if test="${control.signPosition == 3}"> selected </c:if>>任意</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">是否审核</label>
                <div class="layui-input-inline">
                    <select name="isRelease" id="isRelease" lay-filter="isRelease">
                        <option value="1" <c:if test="${control.isRelease == 1}"> selected </c:if>>不审核</option>
                        <option value="0" <c:if test="${control.isRelease == 0}"> selected </c:if>>审核</option>
                    </select>
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <input id="releaseNum" name="releaseNum" value="${control.releaseNum}" type="text"  onkeyup="numberValidate(this)" class="layui-input" style=" width:60px;margin-top: -8px; <c:if test="${control.isRelease == 1}">visibility: hidden;</c:if>" placeholder="数量"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">签名方式</label>
                <div class="layui-input-inline">
                    <select name="expidSign" id="expidSign">
                        <option value="0" <c:if test="${control.expidSign == 0}"> selected </c:if>>强制签名</option>
                        <option value="1" <c:if test="${control.expidSign == 1}"> selected </c:if>>自定义拓展</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">重号过滤</label>
                <div class="layui-input-inline">
                    <select name="repeatFilter" id="repeatFilter" lay-filter="repeatFilter">
                        <option value="0" <c:if test="${control.repeatFilter == 0}"> selected </c:if>>不过滤</option>
                        <option value="1" <c:if test="${control.repeatFilter == 1}"> selected </c:if>>10分钟</option>
                        <option value="2" <c:if test="${control.repeatFilter == 2}"> selected </c:if>>1小时</option>
                        <option value="3" <c:if test="${control.repeatFilter == 3}"> selected </c:if>>24小时</option>
                        <option value="4" <c:if test="${control.repeatFilter == 4}"> selected </c:if>>7天</option>
                        <option value="5" <c:if test="${control.repeatFilter == 5}"> selected </c:if>>30天</option>
                    </select>
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <input id="repeatNum" name="repeatNum" value="${control.repeatNum}" type="text"  onkeyup="numberValidate(this)" class="layui-input" style=" width:60px;margin-top: -8px;<c:if test="${control.repeatFilter == 0}">visibility: hidden;</c:if>" placeholder="数量"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">通道</label>
                <div class="layui-input-inline">
                    <select name="channelId" id="channelId">
                        <c:forEach items="${channel}" var="c">
                            <option value="${c.id}" <c:if test="${control.channelId == c.id}"> selected </c:if>><c:if test="${c.status != 0}"><font color="red">(停止)</font></c:if>${c.channelName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">流速</label>
                <div class="layui-input-inline">
                    <input id="speed" name="speed" value="${control.speed}" type="text"  class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">条/秒</div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">全黑名单</label>
                <div class="layui-input-inline">
                    <select name="blackAll" id="blackAll">
                        <option value="0" <c:if test="${control.blackAll == 0}"> selected </c:if>>不过滤</option>
                        <option value="1" <c:if test="${control.blackAll == 1}"> selected </c:if>>过滤</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">重复签名</label>
                <div class="layui-input-inline">
                    <select style="margin-left:10px;height:30px;width: 120px;" name="repeatSign" id="repeatSign" lay-filter="repeatSign">
                        <option value="1" <c:if test="${control.repeatSign == 1}"> selected </c:if>>是</option>
                        <option value="0" <c:if test="${control.repeatSign == 0}"> selected </c:if>>否</option>
                    </select>
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <input id="repeatSignNum" name="repeatSignNum" value="${control.repeatSignNum}" type="text"  onkeyup="numberValidate(this)" class="layui-input" style=" width:60px;margin-top: -8px;<c:if test='${control.repeatSign == 0}'>visibility: hidden;</c:if>" placeholder="数量"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">接入方式</label>
                <div class="layui-input-inline">
                    <select name="submitType" id="submitType">
                        <option value="0" <c:if test="${control.submitType == 0}"> selected </c:if>>HTTP</option>
                        <option value="1" <c:if test="${control.submitType == 1}"> selected </c:if>>CMPP</option>
                        <option value="9" <c:if test="${control.submitType == 9}"> selected </c:if>>非接口</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">绑定ip</label>
                <div class="layui-input-inline">
                    <input id="proxyIp" name="proxyIp" value="${control.proxyIp}" type="text" style="width: 300px;" class="layui-input" placeholder="不绑IP请留空, 多个IP使用英文半角逗号隔开"/>
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
                url : '${ctx}/smsUser/saveOrUpdateControl',
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

        /**
         * 监听重号过滤下拉框选中事件
         */
        form.on('select(repeatFilter)', function(data){
            if(data.value!=0){
                document.getElementById("repeatNum").style.visibility = 'visible';
            }else{
                document.getElementById("repeatNum").style.visibility = 'hidden';
            }
        });

        /**
         * 监听是否审核下拉框选中事件
         */
        form.on('select(isRelease)', function(data){
            if(data.value ==0){
                document.getElementById("releaseNum").style.visibility = 'visible';
            }else{
                document.getElementById("releaseNum").style.visibility = 'hidden';
            }
        });

        /**
         * 监听是否重复签名下拉框选中事件
         */
        form.on('select(repeatSign)', function(data){
            if(data.value !=0){
                document.getElementById("repeatSignNum").style.visibility = 'visible';
            }else{
                document.getElementById("repeatSignNum").style.visibility = 'hidden';
            }
        });
    });
</script>
</body>
</html>
