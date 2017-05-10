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
    <title>菜单管理</title>
    <jsp:include page="../static.jsp"/>
</head>

<body>
<div style="width: 100%;height: 10%;">
    <section class="content-header">
        <h1>
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 运维功能</a></li>
            <li class="active">菜单管理</li>
        </ol>
    </section>
</div>

<div style="width: 100%;height: 90%;">
    <table id="menu_table" cellspacing="0" cellpadding="0" toolbar="#menu_toolbar">
        <thead>
        <tr>
            <th data-options="field:'menuName',width:180">菜单名</th>
            <th data-options="field:'url',width:120,align:'center'">链接地址</th>
            <th data-options="field:'status',width:60,align:'center',formatter:fmtStatus">状态</th>
            <th data-options="field:'sort',width:60,align:'center'">排序号</th>
            <shiro:hasAnyRoles name="SUPER_ADMIN">
            <th data-options="field:'x',width:200,align:'center',fit:true,formatter: MenuModule.fmtOperate">操作</th>
            </shiro:hasAnyRoles>
        </tr>
        </thead>
    </table>
</div>

<%--搜索条件--%>
<shiro:hasAnyRoles name="SUPER_ADMIN">
<div id="menu_toolbar" style="padding:10px 10px;">
    <div>
        <shiro:hasPermission name="${MENUCODE}:1">
        <a class="btn btn-sm btn-success" style="margin-left: 30px;" id="saveUser" href="javascript:void(0)"
           onclick="MenuModule.beforeEditMenu(1,-1);">添加菜单
        </a>
        </shiro:hasPermission>
    </div>
</div>
</shiro:hasAnyRoles>

<!--操作列的按钮-->
<div id="operDiv" class="hidden">
    <shiro:hasPermission name="${MENUCODE}:6">
    <a href="javascript:void(0)" onclick="MenuModule.beforeEditMenu(2,#id#);">修改</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="${MENUCODE}:12">
    <a style="margin-left: 3px;" href="javascript:void(0)" onclick="MenuModule.beforeDisable(#id#,#status#)">启/禁用</a>
    </shiro:hasPermission>
</div>

<!--添加修改菜单modal-->
<div id="editMenuModal" class="modal fade bs-example-modal-sm">
    <form class="form-horizontal" user="form" id="editMenuForm">
    <div class="modal-dialog bs-example-modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="mySmallModalLabel">编辑菜单</h4>
            </div>
            <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">父菜单名</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="pId" name="pId"/>
                            <input type="text" class="form-control input-sm easyui-combotree"
                                   data-options="url:'${ctx}/menu/loadComboTree',onClick:MenuModule.getSelectedNode" style="width: 470px;height: 30px;"
                                   maxlength="25" name="pName" id="pName"
                                   placeholder="链接地址"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <!--修改时此id-->
                        <input type="hidden" id="id" name="id"/>

                        <label class="col-sm-2 control-label">菜单名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control input-sm" maxlength="50" name="menuName"
                                   id="menuName" placeholder="请输入菜单名称" required/>
                            <span style="display:none;" id="loginName_tip">菜单名称已存在</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">链接地址</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control input-sm" maxlength="100" name="url" id="url" placeholder="链接地址"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序号</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control input-sm" name="sort" id="sort"  placeholder="排序号"/>
                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-sm btn-success" id="saveBtn" onclick="MenuModule.acceptEditMenu();">提 交</button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal" id="closeModal">取 消</button>
            </div>
        </div>
    </div>
    </form>
</div>

<!-- 禁用菜单Modal -->
<div class="modal fade" id="disableMenuModal" tabindex="-1" User="dialog" aria-labelledby="myModalLabel"
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
                <form class="form-horizontal" user="form" id="disableMenuForm">
                    <input type="hidden" id="removeId" name="removeId"/>
                    <div class="form-group" id="statusRadioDiv">
                        <label class="col-sm-2 control-label">应用</label>
                        <div>
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status1" value="1" checked="checked">应用
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status2" value="2">禁用
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-warning" onclick="MenuModule.submitDisableMenu()">确 定</button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">取 消</button>
            </div>
        </div>
    </div>
</div>

<!-- 重置密码Modal -->
<div class="modal fade" id="resetPwdModal" tabindex="-1" user="dialog" aria-labelledby="myModalLabel"
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
                <h3>登录密码将重置为：123456</h3> <input type="hidden" id="resetUserId" name="resetUserId"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">
                    取 消
                </button>
                <button type="button" class="btn btn-sm btn-success" onclick="UserModule.deleteUser()">
                    提 交
                </button>
            </div>
        </div>
    </div>
</div>

</body>
<script src="${ctx}/js/module/systemmgr/menu.js"></script>
</html>
