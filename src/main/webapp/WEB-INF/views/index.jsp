<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    window.basePath = '${ctx}';
</script>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>销管系统</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="static.jsp"/>
    <style type="text/css">
        html {
            overflow: hidden;
        }
    </style>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="hold-transition skin-blue sidebar-mini fixed">
<div class="wrapper">
    <header class="main-header">
        <span href="" class="logo">
            <span class="logo-lg"><b>销管</b>系统</span>
        </span>

        <nav class="navbar navbar-static-top" role="navigation">
            <%--<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">--%>
                <%--<span class="sr-only">切换导航</span>--%>
            <%--</a>--%>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="hidden-xs"><i class="fa fa-users"></i>
                              <span id="loginUserName"><shiro:principal property="userName"/></span>
                            </span>
                        </a>
                    </li>
                    <li class="dropdown user user-menu">
                        <a href="${ctx}/loginout">
                            <span class="hidden-xs"><i class="fa fa-power-off"></i>退出</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!--左侧菜单-->
    <aside class="main-sidebar">
        <section class="sidebar">
            <ul class="sidebar-menu"></ul>
        </section>
    </aside>

    <!-- 中间主页面 -->
    <div class="content-wrapper" id="content-wrapper" style="height: 300px;">
        <div class="page-content-body " id="tab-page-content"></div>
    </div>
</div>

<script src="${ctx}/js/plugin/superui/content/min/js/supershopui.common.js"></script>
<script type="text/javascript">
    $(function () {
        //addTabs(({id: '-1', title: '欢迎页', close: false, url: window.basePath + '/userGroup/userGroupListPage'}));
        //App.fixIframeCotent();

        var menus = [];
        $.ajax({
            url: '${ctx}/menu/loadMenu',
            async: false,
            success: function (data) {
                if (data && data.success) {
                    menus = data.data;
                }else{
                    alert(data.msg);
                }
            }
        });
        $('.sidebar-menu').sidebarMenu({data: menus, param: {t: new Date().getTime()}});

        //处理菜单ajax方式加载
        App.handleSidebarAjaxContent();
    });
</script>
</body>
</html>

