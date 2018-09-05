<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(function(){
        $("#btn").click(function (data) {
            var titles = $("#customer_tree").combotree("getText");
            var params = $("#customer_tree").combotree("getValues");
            var a = "";
            $.each(params, function (index, param) {
                if (params.length - 1 == index) {
                    a += param;
                } else {
                    a += param + ",";
                }
            })
            $("#customer_form").form("submit", {
                url: "${pageContext.request.contextPath}/user/exportself.do",
                queryParams: {
                    titles: titles,
                    params: a
                },
            });
            $("#customer_dd").dialog("close");
        })
        var usertoolbar = [{
            iconCls: 'icon-tip',
            text: "全部导出",
            handler: function () {
                /*
                 * 查看数据
                 *
                 * */
                location.href="${pageContext.request.contextPath}/user/export.do";
            }
        }, '-', {
            text: "自定义",
            iconCls: 'icon-edit',
            handler: function () {
                $("#customer_dd").dialog("open");

            }
        }, '-', {
            text: "导入",
            iconCls: 'icon-edit',
            handler: function () {
                $('#userdd').dialog({
                    title: '导入数据',
                    width: 400,
                    height: 200,
                    closed: false,
                    cache: false,
                    modal: true,
                    buttons:[{
                        text:'确定',
                        handler:function(){
                            $("#userform").form("submit", {
                                url: "${pageContext.request.contextPath}/user/import.do",
                            });
                            $('#userdd').dialog("close");
                        }
                    }]

                });
            }
        }, '-', {
            text: "下载模板",
            iconCls: 'icon-save',
            handler: function () {
                location.href="${pageContext.request.contextPath}/user/model.do";
            }
        }]
        $('#userdata').edatagrid({
            url: '${pageContext.request.contextPath}/user/show.do',
            method: "post",
            updateUrl: "${pageContext.request.contextPath}/user/edit.do",
            columns: [[

                {field: 'id', title: 'ID', width: 100},
                {field: 'name', title: '名字', width: 100},
                {field: 'password', title: '密码', width: 100},
                {field: 'salt', title: '私盐', width: 100},
                {field: 'photoImg', title: '头像', width: 100},
                {field: 'dharmaName', title: '法名', width: 100},
                {field: 'sex', title: '性别', width: 100,
                    formatter: function(value,row,index){
                        if (row.status){
                            return "男";
                        } else {
                            return "女";
                        }
                    }
                },
                {field: 'province', title: '所在省份', width: 100},
                {field: 'city', title: '所在城市', width: 100},
                {field: 'sign', title: '签名', width: 100},
                {field: 'phoneNum', title: '联系方式', width: 100},
                {field: 'status', title: '状态', width: 100,
                    editor: {
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
                {field: 'registerDate', title: '注册时间', width: 100,
                    formatter: function(value,row,index){
                        var date =new Date(value);
                        var cc=date.toLocaleString();
                        return cc;
                    }
                },

            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageSize: 5,
            pageList: [5, 10, 15, 20],
            toolbar: usertoolbar,
            autoSave: true
        });
    })

</script>
<table id="userdata"></table>
<div id="customer_dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <select id="customer_tree" class="easyui-combotree" style="width:200px;"
            data-options="checkbox:true,multiple:true,onlyLeafCheck:true,required:true,
            data:[{
    'id':'custome',
    'checked':false,
    'text': '请选择',
    'children': [
      {
        'id':'id',
        'text': '用户ID',
        'checked': true
      },
      { 'id':'name',
        'text': '名字',
        'checked': true
      },
      { 'id':'password',
        'text': '密码',
        'checked': true
      },
      { 'id':'salt',
        'text': '私盐',
        'checked': true
      },
      { 'id':'photoImg',
        'text': '头像',
        'checked': true
      },
      { 'id':'dharmaName',
        'text': '法名',
        'checked': true
      },
      { 'id':'sex',
        'text': '性别',
        'checked': true
      },
      { 'id':'province',
        'text': '省份',
        'checked': true
      },
      { 'id':'city',
        'text': '城市',
        'checked': true
      },
      { 'id':'sign',
        'text': '签名',
        'checked': true
      },
      { 'id':'phoneNum',
        'text': '联系方式',
        'checked': true
      },
      { 'id':'status',
        'text': '状态',
        'checked': true
      },
      { 'id':'registerDate',
        'text': '注册时间',
        'checked': true
      }
    ]
  }
]"></select>
    <form action="" method="post" id="customer_form">
        <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提交</a>
    </form>

</div>

<div id="userdd">
    <form id="userform" method="post" enctype="multipart/form-data">
        <div>
            <input class="easyui-filebox" name="file" required ="required" style="width:300px">
        </div>

    </form>
</div>