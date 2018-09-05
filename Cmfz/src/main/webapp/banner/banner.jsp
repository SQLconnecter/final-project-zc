<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(function () {

        var toolbar = [{
            iconCls: 'icon-edit',
            text: "添加",
            handler: function () {
                /*
                 * 录入数据
                 *
                 * */
                $('#dd').dialog({
                    title: '添加图片',
                    width: 500,
                    height: 250,
                    closed: false,
                    cache: false,
                    modal: true,
                    buttons:[{
                        text:'保存',
                        handler:function(){
                            /*在这里做信息的提交，可以做表单的提交*/
                            submit();
                            $('#dd').dialog('close');
                            $('#dg').datagrid('reload');
                        }
                    },{
                        text:'关闭',
                        handler:function(){
                            $('#dd').dialog("close",true);
                        }
                    }]

                });
            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-help',
            handler: function () {
                /*
                 * 删除数据
                 * 首先获取选中的行，拿到行的id
                 * 发送删除的请求到后台，一定是异步请求
                 * 删除成功注意刷新数据展示页面
                 *
                 * */
                var row = $("#dg").datagrid("getSelected");
                if(row != null){
                    var index = $("#dg").datagrid("getRowIndex", row);
                    var makesure=confirm("你确定要删除改行？");
                    if(makesure){
                        $.ajax({
                            url:"${pageContext.request.contextPath}/banner/drop.do",
                            data:"id="+row.id,
                            dataType: "json",
                            type:"POST",
                            success:function(result){
                                $('#dg').datagrid('reload');
                            }
                        });
                    }

                }else {
                    alert("请先选中行");
                }
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-help',
            handler: function () {
                /*
                 *使当前选中行可编辑模式
                 * */
                var row = $("#dg").datagrid("getSelected");
                if (row != null) {

                    var index = $("#dg").datagrid("getRowIndex", row)
                    //当前行可编辑
                    $("#dg").edatagrid("editRow", index);
                } else {
                    alert("请先选中行");
                }


            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-help',
            handler: function () {
                var row = $("#dg").datagrid("getSelected");
                if (row != null) {
                    $("#dg").edatagrid("saveRow");
                    $('#dg').datagrid('reload');
                } else {
                    alert("请先选中行编辑！");
                }

            }
        }]

        $('#dg').edatagrid({
            url: '${pageContext.request.contextPath}/banner/banner.do',
            method: "post",
            updateUrl: "${pageContext.request.contextPath}/banner/edit.do",
            columns: [[

                {field: 'id', title: '编号', width: 100},
                {field: 'title', title: '名称', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    },
                    formatter: function(value,row,index){
                        if (row.status){
                            return "Y";
                        } else {
                            return "N";
                        }
                    }

                },
                {field: 'description', title: '描述', width: 100},
                {field: 'createDate', title: '时间', width: 100,
                    formatter: function(value,row,index){
                        var date =new Date(value);
                        var cc=date.toLocaleString();
                        return cc;
                    }
                }

            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageSize: 5,
            pageList: [5, 10, 15, 20],
            toolbar: toolbar,
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>上线时间: ' +new Date(rowData.createDate).toLocaleString() + '</p>' +
                    '<p>状态: ' + rowData.status + '</p>' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });
        $('#fb').filebox({
            buttonText: '选择图片',
            buttonAlign: 'right',
            accept:'image/jpg'
        })
    })
    function submit() {
        $("#addFom").form("submit", {
            url: "${pageContext.request.contextPath}/banner/new.do"
        })
    }
</script>

<table id="dg"></table>
<div id="dd">

    <form id="addFom" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>
                    图片名：
                </td>
                <td>
                    <input type="text" name="title" class="easyui-textbox" data-options="required:true" width="80">
                </td>
            </tr>
            <tr>
                <td>
                    图片源：
                </td>
                <td>
                    <input class="easyui-filebox" name="img" style="width:300px">
                </td>
            </tr>
            <tr>
                <td>
                    是否上线轮播：
                </td>
                <td>
                    <select id="cc" class="easyui-combobox" name="status" style="width:200px;">
                        <option value="true">是</option>
                        <option value="false">否</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    描述：
                </td>
                <td>
                    <input class="easyui-textbox" name="description" data-options="iconCls:'icon-edit'" style="width:300px">
                </td>
            </tr>


        </table>
    </form>
</div>

