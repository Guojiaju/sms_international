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
   <%-- <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <select id="stat" name="stat" class="layui-select">
                        <option value="">全部</option>
                        <option value="0">已到账</option>
                        <option value="1">未到账</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-block">
                    <button class="layui-btn" type="button" data-type="serchTable">查询</button>
                </div>
            </div>
        </div>
    </form>--%>
    <table id="recordsTable" lay-filter="recordsTable"></table>
</div>
<script type="text/html" id="chargeTypeTemp">
    {{# if(d.chargeType == 0){ }}
    人工充值
    {{# } else if(d.chargeType){ }}
    系统返还
    {{# } else{ }}
    其他
    {{# } }}
</script>
<script type="text/html" id="statusTemp">
    {{# if(d.stat == 0){ }}
    已到账
    {{# } else { }}
    未到账
    {{# } }}
</script>
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

    layui.use(['table', 'layer', 'jquery'], function () {
        var table = layui.table,
                layer = layui.layer,
                $ = layui.jquery;
        //方法级渲染
        var initUserTable = table.render({
            elem: '#recordsTable',
            method: 'post',
            url: '${ctx }/chargeRecords/findAllByUid/'+ $('#uid').val(),
            cellMinWidth: 80,
            cols: [[ //标题栏
                {field: 'company', title: '企业名称', align: 'center'}
                , {field: 'view_amount', title: '充值金额', align: 'center'}
                , {field: 'create_time', title: '充值日期', align: 'center'}
                , {field: 'chargeType', title: '充值方式', align: 'center', templet: '#chargeTypeTemp'}
                , {field: 'status', title: '是否到账', align: 'center', templet: '#statusTemp'}
                , {field: 'info', title: '到账说明', align: 'center'}
                , {field: 'add_uid', title: '操作人', align: 'center'}
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

        //监听单元格编辑
        table.on('edit(recordsTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段
        });

        //监听工具条
        table.on('tool(recordsTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'pay') {
                $("#id").val(data.id);
                $("#info").val(data.info);
                layer.open({
                    type: 1,
                    title: '到账',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['350px', '250px'], //宽高
                    content: $('#payStatDiv')
                })
            }
        });
        var active = {
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        company: $('#company').val(),
                        uid: $('#uid').val(),
                        stat: $('#stat').val()
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