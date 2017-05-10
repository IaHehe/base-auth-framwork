<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
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
            <li class="active">团队角色</li>
        </ol>
    </section>
</div>

<div style="width: 100%;height: 90%;">
    <table id="role_table" toolbar="#role_toolbar">
        <thead>
        <tr>
            <th data-options="field:'roleName',width:100,align:'center'">名称</th>
            <th data-options="field:'roleCode',width:100,align:'center'">编码</th>
            <th data-options="field:'status',width:100, align:'center',formatter: fmtStatus">状态</th>
            <th data-options="field:'remark',width:100,align:'center'">备注</th>
            <th data-options="field:'x',width:100,align:'center',formatter: RoleModule.fmtOperate">操作</th>
        </tr>
        </thead>
    </table>
</div>

<shiro:hasAnyRoles name="SUPER_ADMIN">
<div id="role_toolbar"  style="padding:10px 10px;">
    <div class="form-inline">
        <form id="role_form">
            <div>
                <div class="btn-group" data-toggle="buttons-checkbox">
                    <a class="btn btn-sm btn-success" id="saveRole" href="javascript:void(0)"
                       onclick="RoleModule.beforeEditRole(1,-1);">添加角色
                    </a>
                </div>
            </div>
        </form>
    </div>
</div>
</shiro:hasAnyRoles>

<!--操作列的按钮-->
<div id="operDiv">
    <shiro:hasPermission name="${MENUCODE}:6">
    <a href="javascript:void(0)"  onclick="RoleModule.beforeEditRole(2,#id#);">修改</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="${MENUCODE}:7">
    <a href='javascript:void(0);' onclick=RoleModule.showSource(#id#);>功能</a>
    </shiro:hasPermission>
</div>

<!--添加、修改modal-->
<div id="editRoleModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog bs-example-modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="mySmallModalLabel">编辑角色</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="editRoleForm">
                    <div class="form-group">
                        <input type="hidden" id="roleId" name="id"/>
                        <label class="col-sm-2 control-label">角色名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" maxlength="25" name="roleName"
                                   id="rolename" onfocus="RoleModule.clearTipMsg(this);" placeholder="角色名称"/>
                            <span class="hidden text-danger">请输入角色名称</span>
                        </div>
                    </div>
                    <div class="form-group" id="roleCodeDiv">
                        <label class="col-sm-2 control-label">编码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" maxlength="25" name="roleCode"
                                   id="roleCode" onfocus="RoleModule.clearTipMsg(this);" placeholder="角色编码"/>
                            <span class="hidden text-danger">请输入角色编码</span>
                        </div>
                    </div>
                    <div class="form-group" id="roleCodeDiv">
                        <label class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" maxlength="50" onfocus="RoleModule.clearTipMsg(this);"
                                   placeholder="备注" name="remark" id="remark" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">应用</label>
                        <div>
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status1" value="1" checked>应用
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status2" value="2">禁用
                            </label>
                        </div>
                    </div>
                    <span id="errMsg" class="hidden text-danger"></span>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-warning" id="saveBtn" onclick="RoleModule.editSaleRole();">提 交
                </button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal" id="closeModal">取 消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="resourceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
</div>

<script src="${ctx}/js/module/systemmgr/role.js"></script>
</body>
</html>
