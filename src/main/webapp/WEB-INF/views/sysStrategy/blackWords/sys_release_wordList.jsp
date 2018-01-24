<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<form class="layui-form">
    <input hidden name="group_id" id="group_id" value="${group_id}">
    <input hidden name="groupType" id="groupType" value="${groupType}">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">屏蔽词</label>
            <div class="layui-input-inline">
                <input type="text" id="words" name="words" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-inline">
                <input type="tel" id="remark" name="remark" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">屏蔽词类型</label>
            <div class="layui-input-inline">
                <select name="screenType" class="layui-select">
                    <option value="">全部</option>
                    <c:forEach items="${screenType}" var="type" >
                        <option value="${type.key}">${type.value.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</form>
<div class="layui-input-block layui-btn-group">
        <button class="layui-btn" data-type="serchTable">查询</button>
        <button class="layui-btn" data-type="add">新增</button>
        <button class="layui-btn" data-type="batchAdd">批量导入</button>
</div>
<table id="releaseWordsTable" lay-filter="releaseWordsTable"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>
</script>
<script type="text/html" id="screenTypeTemp">
    <c:forEach items="${screenType}" var="type" >
       {{# if(d.screenType == ${type.key}){ }}
            ${type.value.name}
       {{# } }}
    </c:forEach>
</script>
<div id="addDiv" style="display: none;">
    <div style="margin-top: 10px;">
        <form class="layui-form">
            <input hidden name="group_id" value="${group_id}">
            <input hidden name="groupType"  value="${groupType}">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">屏蔽词</label>
                    <div class="layui-input-inline">
                        <textarea name="wordsStr" placeholder="请输入屏蔽词,每行一个" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">屏蔽词类型</label>
                    <div class="layui-input-inline">
                        <select name="screenType" class="layui-select">
                            <c:forEach items="${screenType}" var="type">
                                <option value="${type.key}">${type.value.name}</option>
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
        <form class="layui-form">
            <input hidden name="group_id" value="${group_id}">
            <input hidden name="groupType"  value="${groupType}">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">屏蔽词</label>
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
                            <c:forEach items="${screenType}" var="type" >
                                <option value="${type.key}">${type.value.name}</option>
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
<script>
    /**
     * 渲染form
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
            elem: '#releaseWordsTable',
            method: 'post',
            url: '${ctx}/blackWords/findData',
            where : {group_id:$("#group_id").val()},
            cols: [[ //标题栏
                {field: 'words', title: '屏蔽词',align: 'center'}
                , {field: 'screenType', title: '屏蔽词类型',align: 'center',templet: '#screenTypeTemp'}
                , {field: 'addtime', title: '创建时间', align: 'center'}
                , {field: 'remark', title: '备注', align: 'center'}
                , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
            ]]
            , id: 'releaseWordsTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        //监听表格复选框选择
        table.on('checkbox(releaseWordsTable)', function (obj) {
            console.log(obj)
        });

        //监听单元格编辑
        table.on('edit(releaseWordsTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段

        });

        /**
         * 重新加载数据
         */
        function reloadTable(){
            initUserTable.reload({
                where: {
                    words: $('#words').val(),
                    remark: $('#remark').val(),
                    screenType: $('#screenType').val()
                }
            });
        }

        //添加屏蔽词
        form.on('submit(add)',function (data) {
            $.ajax({
                type : 'post',
                url : '${ctx}/blackWords/addBlackWords',
                contentType : 'application/json',
                dataType : 'json',
                data : JSON.stringify(data.field),
                success : function(result){
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

        //监听工具条
        table.on('tool(releaseWordsTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'delete') {
                layer.confirm("确定删除该屏蔽词吗？",function (index) {
                    $.ajax({
                        type : 'post',
                        url : '${ctx}/blackWords/deleteById/'+ data.id + "/" + $("#groupType").val(),
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
                    title: '编辑屏蔽词信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['450px', '350px'], //宽高
                    content: '${ctx}/blackWords/initEdit/' + data.id +"/" + $("#groupType").val()
                })
            }
        });
        var active = {
            serchTable: function () {
                reloadTable();
            },
            add: function () {
                layer.open({
                    type: 1,
                    title: '添加屏蔽词',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['400px', '400px'], //宽高
                    content: $("#addDiv")
                })
            },
            batchAdd: function () {
                layer.open({
                    type: 1,
                    title: '批量导入屏蔽词',
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

