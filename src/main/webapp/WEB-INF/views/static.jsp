<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctx = request.getContextPath();
%>

<link href="<%= ctx%>/js/plugin/superui/content/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link rel="stylesheet" href="<%= ctx%>/js/plugin/superui/content/adminlte/dist/css/AdminLTE.css">
<link rel="stylesheet" href="<%= ctx%>/js/plugin/superui/content/adminlte/dist/css/skins/_all-skins.css">
<link href="<%= ctx%>/js/plugin/superui/content/min/css/supershopui.common.min.css" rel="stylesheet"/>

<link rel="stylesheet" href="<%= ctx%>/js/plugin/superui/content/ui/global/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%= ctx%>/js/plugin/jquery-easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%= ctx%>/js/plugin/jquery-easyui/themes/icon.css">
<link rel="stylesheet" href="<%= ctx%>/js/plugin/ztree/css/metroStyle/metroStyle.css">
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- jQuery 2.2.3 -->
<script src="<%= ctx%>/js/plugin/superui/content/ui/global/jQuery/jquery.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<%= ctx%>/js/plugin/superui/content/ui/global/bootstrap/js/bootstrap.min.js"></script>
<!--jquery-easyui 1.5.2-->
<script type="text/javascript" src="<%= ctx%>/js/plugin/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%= ctx%>/js/plugin/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%= ctx%>/js/plugin/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%= ctx%>/js/plugin/validate/messages_zh.js"></script>

<script src="<%= ctx%>/js/plugin/ztree/js/jquery.ztree.all.min.js"></script>
<script src="<%= ctx%>/js/common.js"></script>
<script>
    window.basePath = '<%= ctx%>';
</script>
