<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    window.basePath = '${pageContext.request.contextPath}';
</script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>用户分组管理</title>
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
    <input type="hidden" id="loginUserId" value="<shiro:principal property="userId"/>" placeholder="当前登陆用户id">
    <input type="hidden" id="loginUserGroupId" value="<shiro:principal property="groupId"/>" placeholder="当前登陆所在的组id">
    <input type="hidden" id="loginRoleId" value="<shiro:principal property="roleId"/>" placeholder="当前登陆角色id">

    <div class="easyui-layout" data-options="fit:true" style="width:100%;height:98%;">
        <!--左边树-->
        <div data-options="region:'west',split:true" title="请选择团队" style="width:230px;height: 100%;;padding:0 10px">
            <input type="hidden" id="selectedTreeNodeId" placeholder="树选中的节点id"/>
            <input type="hidden" id="selectedTreeNodeName" placeholder="树选中的节点名称"/>
            <div id="menuContent" class="menuContent">
                <ul id="userGroupTree" class="ztree"></ul>
            </div>
        </div>

        <!--右边数据-->
        <div data-options="region:'center'" style="overflow-y: hidden;">
            <table id="userGroup_table" toolbar="#search_toolbar">
                <thead>
                <tr>
                    <th data-options="field:'loginName',width:150,align:'center'">用户名</th>
                    <th data-options="field:'userName',width:80,align:'center'">名称</th>
                    <th data-options="field:'status',width:50, align:'center',formatter: fmtStatus">状态</th>
                    <th data-options="field:'roleName',width:80,align:'center'">角色</th>
                    <th data-options="field:'groupName',width:80,align:'center'">团队</th>
                    <th data-options="field:'updateTime',width:100,align:'center',formatter: fmtDate">更新时间</th>
                    <th data-options="field:'createUserName',width:100,align:'center'">更新人</th>
                    <th data-options="field:'x',width:200,align:'center',fit:true,formatter: UserGroupModule.fmtOperate">操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<%--搜索条件--%>
<div id="search_toolbar" style="padding:10px 10px;display: none;">
    <div class="form-inline">

        <form id="userGoupSearchForm" class="form-inline" role="form">
            <div class="form-group" style="margin: 5px;">
                <label class="control-label">状态：</label>
                <input type="hidden" id="groupId" name="groupId" />
                <select class="form-control input-sm" id="status_q" name="status">
                    <option value="">全部</option>
                    <option value="1">启用</option>
                    <option value="2">禁用</option>
                </select>
            </div>
            <div class="form-group" style="margin: 5px;">
                <label class="control-label">用户名：</label>
                <input  type="text" class="form-control input-sm" id="loginName_q" name="loginName">
            </div>
            <div class="form-group" style="margin: 5px;">
                <label class="control-label">姓名：</label>
                <input  type="text" class="form-control input-sm" id="userName_q" name="userName">
            </div>
            <div class="form-group" style="margin: 5px;">
                <label class="control-label">职位：</label>
                <input  type="text" class="form-control input-sm" id="jobTitle_q" name="jobTitle">
            </div>
            <div class="form-group" style="margin: 5px;">
                <label class="control-label">部门：</label>
                <select class="form-control input-sm" id="deptId_q" name="deptId">
                    <option value="">请选择</option>
                    <c:forEach items="${deptList}" var="dept" begin="1">
                        <option value="${dept.id}">${dept.deptName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group" style="margin: 5px;margin-left: 25px;">
                <a class="btn btn-sm btn-danger" id="saveUser" href="javascript:void(0)"
                   onclick="UserGroupModule.searchUserGroup();">搜索用户
                </a>
                <shiro:hasPermission name="${MENUCODE}:2">
                <a class="btn btn-sm btn-primary" style="margin-left: 30px;" id="saveUser" href="javascript:void(0)"
                   onclick="UserGroupModule.showAddUserModal();">批量加人
                </a>
               </shiro:hasPermission>
            </div>
        </form>
    </div>
</div>

<!--操作列的按钮-->
<div id="operDiv" class="hidden">
    <shiro:hasPermission name="${MENUCODE}:8">
    <a href="javascript:void(0)" onclick="UserGroupModule.roleChooseModal(#id#,#roleId#);">职务</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="${MENUCODE}:5">
    <a style="margin-left: 3px;" href="javascript:void(0)" onclick="UserGroupPermission.showUserGroupPermissionModal(#id#,#roleId#)">权限</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="${MENUCODE}:3">
    <a style="margin-left: 3px;" href="javascript:void(0)" onclick="UserGroupModule.showDeleteModal(#id#)">删除</a>
    </shiro:hasPermission>
</div>

<!--批量加人-->
<div class="modal fade" id="batchUserModal" tabindex="-1" user="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"><span id="nowGroupTitle"></span>： 批量添加成员</h4>
            </div>
            <div class="modal-body">
                <div style="height: 330px;">
                    <table id="batchUser_table" toolbar="#search_toolbar9">
                        <thead>
                        <tr>
                            <th data-options="field:'loginName',width:150,align:'center'">用户名</th>
                            <th data-options="field:'userName',width:80,align:'center'">名称</th>
                            <th data-options="field:'status',width:50, align:'center',formatter: fmtStatus">状态</th>
                            <th data-options="field:'deptName',width:100,align:'center',formatter: UserModule.fmtDept">业务部门</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-warning" onclick="UserGroupModule.bindUserGroup()">
                    提 交
                </button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">
                    取 消
                </button>
            </div>
        </div>
    </div>
</div>


<%--搜索条件2--%>
<div id="search_toolbar9" style="padding:2px;display: none;">
    <div class="form-inline">
        <form id="addUserForm" class="form-inline" role="form">
            <div class="form-group">
                <label class="control-label">状态：</label>
                <select class="form-control input-sm" id="status_q" name="status">
                    <option value="">全部</option>
                    <option value="1">启用</option>
                    <option value="2">禁用</option>
                </select>
            </div>
            <div class="form-group" style="margin-left: 60px;">
                <label class="control-label">用户名：</label>
                <input  type="text" class="form-control input-sm" id="loginName_q" name="loginName">
            </div>
            <div class="form-group" >
                <label class="control-label">姓名：</label>
                <input  type="text" class="form-control input-sm" id="userName_q" name="userName">
            </div>
            <div class="form-group" >
                <label class="control-label">部门：</label>
                <select class="form-control input-sm" id="deptId_q" name="deptId">
                    <option value="">请选择</option>
                    <c:forEach items="${deptList}" var="dept" begin="1">
                        <option value="${dept.id}">${dept.deptName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group" style="margin: 5px;margin-left: 25px;">
                <a class="btn btn-sm btn-danger" id="saveUser" href="javascript:void(0)"
                   onclick="UserGroupModule.searchAddUser();">搜索用户
                </a>
            </div>
        </form>
    </div>
</div>

<!-- 删除用户Modal -->
<div class="modal fade" id="deleteUserGroupModal" tabindex="-1" User="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    提示
                </h4>
            </div>
            <div class="modal-body">
                　您确定删除此用户吗? <input type="hidden" id="removeUserId" name="removeUserId"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-warning" onclick="UserGroupModule.deleteUserFromGroup()">确 定</button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">取 消</button>
            </div>
        </div>
    </div>
</div>

<!-- 职务Modal -->
<div class="modal fade" id="roleChooseModal" tabindex="-1" User="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    提示
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" user="form" id="accpetUserRoleForm">
                    <div class="form-group" id="statusRadioDiv">
                        <label class="col-sm-2 control-label">职务</label>
                        <div>
                            <input type="hidden" id="giveRoleUserId" name="giveUserId"/>
                            <label class="checkbox-inline">
                                <input type="radio" name="roleId" id="roleId1" value="3">团队长
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="roleId" id="roleId2" value="4">助理
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="roleId" id="roleId3" value="5" checked>组员
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-warning" onclick="UserGroupModule.acceptUserRole()">确 定</button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">取 消</button>
            </div>
        </div>
    </div>
</div>

<!-- 用户权限Modal -->
<div class="modal fade" id="userPermissionModal" tabindex="-1" User="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
</div>


<script src="${ctx}/js/module/systemmgr/userGroup.js"></script>
<script src="${ctx}/js/module/systemmgr/user.js"></script>
<script src="${ctx}/js/module/systemmgr/userGroupPermission.js"></script>
</body>
</html>