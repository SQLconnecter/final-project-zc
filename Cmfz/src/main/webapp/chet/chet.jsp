<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    /*对消息的订阅*/
    var goEasy = new GoEasy({
        appkey: "BC-035655ba5ddf4a3eb2e3669879a0bf8f"
    });
    goEasy.subscribe({
        channel: "zcconnection",
        onMessage: function (message) {
           alert("Channel:" + message.channel + " content:" + message.content);
            var json = JSON.parse(message.content)
            var cot = "";
            cot =" <div id=\"\" class=\"chat_topulz clearfix\">\n" +
                "                <div class=\"chat_topulava fl\">\n" +
                "                    <p class=\"chat_topuldiv_p1 date\">"+json.name+"</p>\n" +
                "                </div>\n" +
                "                <div class=\"chat_topulava fl\">\n" +
                "                    <img class=\"avatarImg\" src=\""+json.img+"\">\n" +
                "                </div>\n" +
                "                <div class=\"chat_topuldiv fl\">\n" +
                "                    <p class=\"chat_topuldiv_p1 date\">"+json.date+"</p>\n" +
                "\n" +
                "                    <p class=\"chat_topuldiv_p2 message\"><span>"+json.msg+"</span></p>\n" +
                "                </div>\n" +
                "            </div>"
            var name =json.name;
            if(name=='suns'){

            }else{
                $("#chatScroll").append(cot);
            }

        }
    });
    $(function(){
        $("#publishbtn").click(function(){
            var name="suns";
            var date=new Date();
            var msg=$("#publishMessages").val();
            var img="../images/Avatar.png";
            /*在这里做页面的刷新*/
            var cot = "";
            cot =" <div id=\"\" class=\"chat_topuly clearfix\">\n" +
                "                <div class=\"chat_topulava fr\">\n" +
                "                    <p class=\"chat_topuldiv_p1 date\">suns</p>\n" +
                "                </div>\n" +
                "                <div class=\"chat_topulava fr\">\n" +
                "                    <img class=\"avatarImg\" src=\""+img+"\">\n" +
                "                </div>\n" +
                "                <div class=\"chat_topuldiv fr\">\n" +
                "                    <p class=\"chat_topuldiv_p1 date\">2018-09-07 13:35:32</p>\n" +
                "\n" +
                "                    <p class=\"chat_topuldiv_p2 message\"><span>"+msg+"</span></p>\n" +
                "                </div>\n" +
                "            </div>";
            $("#chatScroll").append(cot);
            $("#publishMessages").val("");
            var message = {'name':name,'date':date,'msg':msg,'img':img};
            var json =JSON.stringify(message);
            goEasy.publish({
            channel: "zcconnection",
            message: json
            });
            /*发布消息*/
            /*goEasy.publish({
            channel: "zcconnection",
            message: "Hello, GoEasy!"
        });*/
        });

    });
</script>

    <div class="demos_con">
        <div id="chatScroll" class="chat J_chat">
            <%--这个是接受消息的区域--%>


        </div>
        <div class="demos_condsend clearfix">
            <input class="demos_condsend_1 J_demos_condsend_1 fl" id="publishMessages" type="text">
            <button id="publishbtn" class="demos_condsend_2 fr" >Publish</button>
        </div>
    </div>


