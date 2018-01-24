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
        <input hidden id="id" name="id" value="${obj.id}">
        <input hidden id="type" name="type" value="${obj.type}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">UID</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="uid" value="${obj.uid}" size="16" readonly/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">拓展号</label>
                <div class="layui-input-inline">
                    <input type="text" id="expend" class="layui-input" name="expend" value="${obj.expend}" size="16" placeholder="请输入拓展号" lay-verify="required"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户推送拓展</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="userexpend" value="${obj.userexpend}" size="16" placeholder="请输入拓展号"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">签名</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="store" value="${obj.store}" size="20"  placeholder="请输入拓展号" lay-verify="required"/>
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
    layui.use(['form','jquery'], function() {
        var form = layui.form,
                $ = layui.jquery;
        form.on('submit(save)',function (data) {
            $.ajax({
                type : 'post',
                url : '${ctx}/sign/doUpdate',
                contentType : 'application/json',
                dataType : 'json',
                data : JSON.stringify(data.field),
                beforeSend:function(){
                    var data = checkExpend($("#id").val(),$("#expend").val(),$("#type").val());
                    if(data > 0){
                        return false;
                    }
                },
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
         * 检查拓展
         * @param id
         * @param expend
         * @param type
         * @returns {*}
         */
        function checkExpend(id,expend,type){
            var result ;
            $.ajax({
                url:'${ctx }/sign/checkExpend/'+id+"/"+expend+"/"+type,
                type:'POST',
                contentType:'application/json',
                dataType: 'json',
                async: false,
                success : function (data) {
                    if(data > 0){
                        layer.tips('该拓展号已存在，请重新输入','#expend');
                        result = data;
                    }
                },
                error : function (data) {
                    layer.msg("系统异常");
                }
            });
            return result;
        }
    });

</script>

