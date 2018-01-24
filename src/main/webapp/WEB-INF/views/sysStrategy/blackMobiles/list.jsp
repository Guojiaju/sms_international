<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%--<input hidden id="ctx" value="${ctx}">--%>
<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">黑名单</label>
            <div class="layui-input-inline">
                <input type="text" id="mobile" name="mobile" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-inline">
                <input type="tel" id="remark" name="remark" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">黑名单类型</label>
            <div class="layui-input-inline">
                <select name="mobileType" class="layui-select">
                    <option value="">请选择</option>
                    <c:forEach items="${mobileType}" var="type" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">国家</label>
            <div class="layui-input-inline">
                <select id="country" name="country" class="layui-select">
                    <option value="">全部</option>
                    <c:forEach items="${country}" var="country">
                        <option value="${country.id}">${country.country}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-block layui-btn-group">
                <button class="layui-btn" type="button" data-type="serchTable">查询</button>
                <button class="layui-btn" type="button" data-type="add">新增</button>
                <button class="layui-btn" type="button" data-type="batchAdd">批量导入</button>
            </div>
        </div>
    </div>
</form>
<table id="mobileTable" lay-filter="mobileTable"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
</script>
<div id="addDiv" style="display: none;">
    <div style="margin-top: 10px;">
        <form id="addForm" class="layui-form">
            <input hidden name="group_id" value="2">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">黑名单</label>
                    <div class="layui-input-inline">
                        <textarea name="mobileStr" placeholder="请输入黑名单,每行一个" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">黑名单类型</label>
                    <div class="layui-input-inline">
                        <select name="mobileType" class="layui-select">
                            <c:forEach items="${mobileType}" var="type" >
                                <option value="${type.id}" <c:if test="${type.id == 4}">selected</c:if>>${type.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-inline">
                        <input type="text" name="remark" placeholder="请输入备注信息" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="add">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>

<div id="batchAddDiv" style="display: none;">
    <div style="margin-top: 10px;">
        <form id="batchAddForm" class="layui-form">
            <input hidden name="group_id" value="2">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">黑名单</label>
                    <div class="layui-input-inline">
                        <input type="file" class="layui-input" placeholder="请选择文件">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">黑名单类型</label>
                    <div class="layui-input-inline">
                        <select name="mobileType" class="layui-select">
                            <c:forEach items="${mobileType}" var="type" >
                                <option value="${type.id}" <c:if test="${type.id == 4}">selected</c:if>>${type.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-inline">
                        <input type="text" name="remark" placeholder="请输入备注信息" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="import">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/html" id="typeTemp">
    <c:forEach items="${mobileType}" var="type" >
        {{# if(d.mobileType == ${type.id}){ }}
            ${type.name}
        {{# } }}
    </c:forEach>
</script>
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

    layui.use(['table','form','layer','jquery'], function () {
        var table = layui.table,
                layer = layui.layer,
                $ = layui.jquery,
                form = layui.form;
        //方法级渲染
        var initUserTable = table.render({
            elem: '#mobileTable',
            method: 'post',
            url: '${ctx}/smsBlackMobile/findData',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {field: 'mobile', title: '黑名单',align:'center'}
                , {field: 'mobileType', title: '黑名单类型',align:'center',templet:'#typeTemp'}
                , {field: 'addtime', title: '创建时间',align:'center'}
                , {field: 'remark', title: '备注',align:'center'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo',align:'center'}
            ]]

            , id: 'mobileTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        //监听表格复选框选择
        table.on('checkbox(mobileTable)', function (obj) {
            console.log(obj)
        });

        //监听单元格编辑
        table.on('edit(mobileTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段

        });

        //重新加载页面table
        function reloadTable(){
            initUserTable.reload({
                where: {
                    mobile: $('#mobile').val(),
                    remark: $('#remark').val(),
                    mobileType: $('#mobileType').val(),
                    country : $('#country').val()
                }
            });
        }
        //添加黑名单
        form.on('submit(add)',function (data) {
            $.ajax({
                type : 'post',
                url : '${ctx}/smsBlackMobile/addBlackMobile',
                contentType : 'application/json',
                dataType : 'json',
                data : JSON.stringify(data.field),
                success : function(result){
                    console.log(result);
                    layer.msg(result.msg,{time:2000},function(){
                        layer.closeAll(); //执行关闭
                        //重新加载页面table
                        reloadTable();
                    });
                },
                error : function(result){
                    layer.alert("系统错误");
                }
            });
            return false;
        });

        form.on('submit(import)',function (data) {
            $.ajax({
                type : 'post',
                url : '${ctx}/smsBlackMobile/doImport',
                contentType : 'application/json',
                dataType : 'json',
                data : JSON.stringify(data.field),
                beforeSend: function(XHR){
                    layer.load();
                },
                success : function(result){
                    layer.msg(result.msg > 0 ? "操作成功":"操作失败",{time:2000},function(){
                        layer.closeAll(); //执行关闭
                        reloadTable();
                    });
                },
                error : function(result){
                    layer.alert("系统错误");
                }
            });
            return false;
        });

        //监听工具条
        table.on('tool(mobileTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'delete') {
                layer.confirm("确定删除该黑名单吗？",function (index) {
                    $.ajax({
                        type : 'post',
                        url : '${ctx}/smsBlackMobile/deleteById/'+ data.id ,
                        contentType : 'application/json',
                        dataType : 'json',
                        success : function(result){
                            layer.msg(result > 0 ? "删除成功":"删除失败",{time:2000},function(){
                                layer.close(index); //执行关闭
                                reloadTable();
                            });
                        },
                        error : function(result){
                            layer.alert("系统错误");
                        }
                    });
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    title: '编辑黑名单信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['400px', '300px'], //宽高
                    content: '${ctx}/smsBlackMobile/initEdit/' + data.id
                })
            }
        });
        var active = {
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        mobile: $('#mobile').val(),
                        remark: $('#remark').val(),
                        mobileType: $('#mobileType').val(),
                        country : $('#country').val()
                    }
                });
            },
            add: function () {
                layer.open({
                    type: 1,
                    title: '添加黑名单',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['400px', '400px'], //宽高
                    content: $("#addDiv")
                })
            },
            batchAdd: function () {
                layer.open({
                    type: 1,
                    title: '导入黑名单',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['400px', '300px'], //宽高
                    content: $('#batchAddDiv')
                })
            }
        };
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

