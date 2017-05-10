<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>销管系统</title>
    <link rel="shortcut icon" type="image/x-icon" href="${ctx}/images/favicon.ico" media="screen" />
    <link href="${ctx}/js/plugin/superui/content/ui/css/layout.css" rel="stylesheet"/>
    <link href="${ctx}/js/plugin/superui/content/ui/css/login.css" rel="stylesheet"/>
</head>

<body id="X-login-page" class="login-bg">
<div class="main ">
    <div class="login-dom login-max">
        <div class="logo text-center">
            <img src="${ctx}/js/plugin/superui/content/ui/img/logo.png" width="180px" height="180px"/>
        </div>

        <div class="login container " id="login">
            <form class="login-form">
                <div id="tipMsg" style="display: none;margin-left: 200px;color: red;font-size: 16px;"></div>
                <div class="login-box border text-small" id="box">
                    <div class="name border-bottom">
                        <input type="text" id="loginName" name="loginName" value="admin1" placeholder="请输入账号"/>
                    </div>
                    <div class="pwd">
                        <input type="password" id="password" name="password" value="123456" placeholder="请填写密码"/>
                    </div>
                </div>
                <input type="button" class="btn text-center login-btn" onclick="login();" value="立即登录"/>
            </form>
        </div>

        <div class="text-center" 　style="magin-top:30px;">
            版权所有 © 北京众信易保科技有限公司 美好生活一起包
        </div>
    </div>

</div>
</body>

<script src="${ctx}/js/plugin/superui/content/ui/global/jQuery/jquery.min.js"></script>
<script type="text/javascript">

    $(function(){
        $('#loginName').focus();
        var longUserName = $('#loginUserName').val();
        if(longUserName){
            window.location.href = "${ctx}/";
        }
    });

    function login() {
        var loginName = $('#loginName').val();
        if (!loginName) {
            $('#tipMsg').show().html('请输入账号');
            return false;
        }
        var password = $('#password').val();
        if (! password) {
            $('#tipMsg').show().html('请填写密码');
            return false;
        }

        var url ='${ctx}/login?t=' + new Date().getTime();
        $.ajax({
            url: url,
            type: 'POST',
            dataType: "json",
            data: {loginName: loginName, password: password},
            error: function(XMLHttpRequest){
                console.log(XMLHttpRequest);
            },
            success: function (data) {
                if (data && data.data) {
                    window.location.href = "${ctx}/index";
                }else{
                    $('#tipMsg').show().html(data.msg);
                }
            }
        });

    }
</script>
</html>
