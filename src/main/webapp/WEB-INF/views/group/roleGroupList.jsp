<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色分组管理</title>
    <jsp:include page="../static.jsp"/>
</head>

<body>
<div style="width: 100%;height: 10%;">
    <section class="content-header">
        <h1>
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
            <li class="active">团队成员</li>
        </ol>
    </section>
</div>

<div class="easyui-panel" style="width:100%;height:90%;">
    <div class="easyui-layout" data-options="fit:true">
        <!--左边树-->
        <div data-options="region:'west',split:true" title="请选择团队" style="width:230px;height: 100%;;padding:0 10px">
            <input type="hidden" id="loginUserId" value="<shiro:principal property="userId"/>" placeholder="当前登陆用户id">
            <input type="hidden" id="loginUserGroupId" value="<shiro:principal property="groupId"/>" placeholder="当前登陆所在的组id">
            <input type="hidden" id="loginRoleId" value="<shiro:principal property="roleId"/>" placeholder="当前登陆角色id">
            <input type="hidden" id="selectedTreeNodeId" />
            <input type="hidden" id="selectedTreeNodeName" />

            <div id="menuContent" class="menuContent">
                <ul id="roleGroupTree" class="ztree"></ul>
            </div>
        </div>

        <!--右边数据-->
        <div data-options="region:'center'" title="团队角色" style="overflow-y: hidden;">
            <table id="roleGroup_table" class="hidden">
                <thead>
                <tr>
                    <th data-options="field:'roleName',width:150,align:'center'">角色名称</th>
                    <th data-options="field:'groupName',width:80,align:'center'">团队名称</th>
                    <th data-options="field:'x',width:80,align:'center',fit:true,formatter: RoleGroupModule.fmtOperate">操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<!--操作列的按钮-->
<div id="operDiv" class="hidden">
    <shiro:hasPermission name="${MENUCODE}:7">
    <a href="javascript:void(0)" onclick="RoleGroupModule.showOperateModal(#roleId#,'#roleName#');">功能</a>
    </shiro:hasPermission>
</div>

<!-- 组角色权限Modal -->
<div class="modal fade" id="resourceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
</div>

<script src="${ctx}/js/module/systemmgr/roleGroup.js"></script>
</body>
</html>