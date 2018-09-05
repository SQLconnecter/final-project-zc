<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(function() {
        var toolbar = [{
            iconCls: 'icon-tip',
            text: "专辑详情",
            handler: function () {
                /*
                 * 查看数据
                 *
                 * */
               var select= $('#tablu').treegrid("getSelected");
                var p=$('#tablu').treegrid("getParent",select.id);
                if(p==null){
                    /*弹出对话框，显示专辑的信息*/
                    $('#dalbum').dialog({
                        title: '专辑详情',
                        width: 400,
                        height: 200,
                        closed: false,
                        cache: false,
                        modal: true,
                        buttons:[
                           {
                            text:'关闭',
                            handler:function(){
                                /*关闭对话框*/
                                $('#dalbum').dialog("close");
                            }
                        }],
                        onOpen:function(){
                            $.ajax({
                                url:"${pageContext.request.contextPath}/album/album.do?albumid="+select.id,
                                data:"",
                                dataType: "json",
                                type:"POST",
                                success:function(result){

                               var section= '<table><tr>' +
                                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + result.coverImg + '" style="height:50px;"></td>' +
                                    '<td style="border:0">' +
                                    '<p>上线时间: ' +new Date(result.publishDate).toLocaleString() + '</p>' +
                                    '<p>状态: ' + result.status + '</p>' +
                                    '<p>评分: ' + result.score + '</p>' +
                                    '<p>作者: ' + result.author + '</p>' +
                                    '<p>集数: ' + result.children.length + '</p>' +
                                    '</td>' +
                                    '</tr></table>';
                                    $("#showbum").append(section);
                                }
                            });
                        }

                    });

                }else{
                    alert("请选中专辑！");
                }
            }
        }, '-', {
            text: "添加专辑",
            iconCls: 'icon-edit',
            handler: function () {
                $('#addAlbum').dialog({
                    title: '添加专辑',
                    width: 400,
                    height: 200,
                    closed: false,
                    cache: false,
                    modal: true,
                    buttons:[{
                        text:'确定',
                        handler:function(){
                            $.messager.progress();
                            $("#addCForm").form("submit", {
                                url: "${pageContext.request.contextPath}/album/newalb.do",
                                onSubmit: function(){
                                    var isValid = $(this).form('validate');
                                    if (!isValid){
                                        $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                                    }
                                    return isValid;	// 返回false终止表单提交

                                },
                                success: function(){
                                    $.messager.progress('close');	// 如果提交成功则隐藏进度条
                                    $('#addAlbum').dialog("close",true);
                                    $("#addAlbumForm").form("clear");
                                    $('#tablu').treegrid('reload');
                                }
                            });


                            $('#tablu').treegrid('reload');

                        }
                    },{
                        text:'取消',
                        handler:function(){
                            $('#addAlbum').dialog("close",true);
                        }
                    }]
                });

            }
        }, '-', {
            text: "添加章节",
            iconCls: 'icon-edit',
            handler: function () {
                    /*弹出对话框，显示专辑的信息*/
                    $('#addchap').dialog({
                        title: '添加章节',
                        width: 400,
                        height: 200,
                        closed: false,
                        cache: false,
                        modal: true,
                        buttons:[{
                            text:'确定',
                            handler:function(){
                                $.messager.progress();
                                $("#addCForm").form("submit", {
                                    url: "${pageContext.request.contextPath}/album/newc.do",
                                    onSubmit: function(){
                                        var isValid = $(this).form('validate');
                                        if (!isValid){
                                            $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                                        }
                                        return isValid;	// 返回false终止表单提交

                                    },
                                    success: function(){
                                        $.messager.progress('close');	// 如果提交成功则隐藏进度条
                                        $('#addchap').dialog("close",true);
                                        $("#addCForm").form("clear");
                                        $('#tablu').treegrid('reload');
                                    }
                                });


                            }
                        },
                            {
                                text:'关闭',
                                handler:function(){
                                    /*关闭对话框*/
                                    $('#addchap').dialog("close");
                                }
                            }]
                    });

            }
        }, '-', {
            text: "下载资源",
            iconCls: 'icon-save',
            handler: function () {
                var select= $('#tablu').treegrid("getSelected");
                if(select==null){
                    alert("请选中章节！");
                }else{
                    var p=$('#tablu').treegrid("getParent",select.id);
                    if(p!=null){
                        /*弹出提示框，提示将要进行下载*/
                        var flag= confirm("将要进行下载？");
                        if(flag){
                           var urll=select.audioPath;
                           location.href="${pageContext.request.contextPath}/album/download?path="+urll;
                        }
                    }else{
                        alert("请选中章节！");
                    }
                }
            }
        }]
        $('#tablu').treegrid({
            url: '${pageContext.request.contextPath}/album/all.do',
            onDblClickRow: function (row) {
                $("#vedio_dd").dialog("open")
                $("#vedio").prop("src", "${pageContext.request.contextPath}" + row.audioPath)
            },
            method: "post",
            idField: 'id',
            treeField: 'title',
            rownumbers: true,//显示行号
            collapsible:true,//定义面板是否可以折叠
            striped:true,//隔行变色,
            pagination:true,//底部显示工具栏组件

            toolbar:toolbar,
            columns:[[
                {title:'名字',field:'title',width:180},  //这一行就是treeField定义的树节点的列
                {field:'audioPath',title:'下载路径',width:60,align:'right'},
                {field:'size',title:'空间大小（MB）',width:80},
                {field:'duration',title:'章节时长',width:80}

            ]],
            fit: true,
            fitColumns:true,

        });

    })
    $('#fileb').filebox({
        buttonText: '选择文件',
        buttonAlign: 'left'
    })

</script>
<table id="tablu"></table>
<div id="dalbum">
    <form id="showbum" ></form>
</div>
<div id="addAlbum">
    <form id="addAlbumForm" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>
                    专辑主题：
                </td>
                <td>
                    <input type="text"  name="title" class="easyui-textbox" data-options="required:true" width="80">
                </td>
            </tr>
            <tr>
                <td>
                    封面插图：
                </td>
                <td>
                    <input class="easyui-filebox" name="img" required ="required" style="width:300px">
                </td>
            </tr>
            <tr>
                <td>
                    是否上线轮播：
                </td>
                <td>
                    <select  class="easyui-combobox" name="status" style="width:200px;">
                        <option value="true">是</option>
                        <option value="false">否</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    简介：
                </td>
                <td>
                    <input class="easyui-textbox" name="brife" data-options="iconCls:'icon-edit'" required ="required" style="width:300px">
                </td>
            </tr>
            <tr>
                <td>
                    作者：
                </td>
                <td>
                    <input class="easyui-textbox" name="author" required ="required" data-options="iconCls:'icon-edit'" style="width:300px">
                </td>
            </tr>
            <tr>
                <td>
                    播音：
                </td>
                <td>
                    <input class="easyui-textbox" name="broadCast" required ="required" data-options="iconCls:'icon-edit'" style="width:300px">
                </td>
            </tr>
            <tr>
                <td>
                    上架日期：
                </td>
                <td>
                    <input   type= "text" name="publishDate" class= "easyui-datebox" required ="required"> </input>
                </td>
            </tr>

        </table>

    </form>
</div>



<div id="addchap">
    <form id="addCForm" method="post" enctype="multipart/form-data" >
        <table>
            <tr>
                <td>
                    章节主题：
                </td>
                <td>
                    <input type="text"  name="title" class="easyui-textbox" data-options="required:true" width="80">
                </td>
            </tr>
            <tr>
                <td>
                    上传文件：
                </td>
                <td>
                    <input id="fileb" type="text" name="file" style="width:300px">
                </td>
            </tr>
            <tr>
                <td>
                    所属专辑：
                </td>
                <td>
                    <input  class="easyui-combobox" name="albumid"
                            data-options="valueField:'id',textField:'title',required:true,url:'${pageContext.request.contextPath}/album/all.do'" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="vedio_dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <video id="vedio" src="" controls="controls" width="300" height="200">

    </video>
</div>