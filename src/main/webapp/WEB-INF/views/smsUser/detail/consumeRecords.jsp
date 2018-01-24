<%--
  Created by IntelliJ IDEA.
  User: guojiaju
  Date: 2017/11/22
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<div style="margin: 5px;">
    <input hidden id="uid" value="${uid}">
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">开始时间</label>
                <div class="layui-input-inline">
                    <input type="number" name="startTime" value="" id="startTime" lay-verify="date"
                           placeholder="yyyyMMdd" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">结束时间</label>
                <div class="layui-input-inline">
                    <input type="number" name="endTime" id="endTime" lay-verify="date" placeholder="yyyyMMdd"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-block">
                    <button class="layui-btn" type="button" data-type="serchTable">查询</button>
                </div>
            </div>
        </div>
    </form>
    <table id="recordsTable" lay-filter="recordsTable"></table>
</div>
<script>
    /**
     * 页面加载完成执行form渲染
     */
    $(function () {
        renderForm();
    });

    /**
     * 重新渲染form
     */
    function renderForm() {
        layui.use(['form', 'table'], function () {
            var form = layui.form, table = layui.table;
            form.render();
            table.render();
        });
    }

    layui.use(['table', 'layer', 'jquery','laydate'], function () {
        var table = layui.table,
                layer = layui.layer,
                $ = layui.jquery,
                laydate = layui.laydate;


        //日期
        laydate.render({
            elem: '#startTime',
            type : 'datetime',
            format : 'yyyyMMdd'
        });

        laydate.render({
            elem: '#endTime',
            type : 'datetime',
            format : 'yyyyMMdd'
        });

        //方法级渲染
        var initUserTable = table.render({
            elem: '#recordsTable',
            method: 'post',
            url: '${ctx }/consume/findByPage',
            where : {uid : $("#uid").val()},
            cellMinWidth: 80,
            cols: [[ //标题栏
                {field: 'date', title: '消费日期', align: 'center'}
                , {field: 'send', title: '消费总额', align: 'center'}
                , {field: 'unsend', title: '未计费总额', align: 'center'}
            ]]
            , id: 'recordsTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        var active = {
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        uid: $('#uid').val(),
                        startTime: $('#startTime').val(),
                        endTime : $('#endTime').val()
                    }
                });
            }
        };
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>