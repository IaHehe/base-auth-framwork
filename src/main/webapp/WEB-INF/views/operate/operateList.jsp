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
    <title>操作色管理</title>
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
            <li class="active">按钮定义</li>
        </ol>
    </section>
</div>
<div style="width: 100%;height: 90%;">
    <table id="oper_table" toolbar="#oper_toolbar">
        <thead>
        <tr>
            <th data-options="field:'operName',width:100,align:'center'">名称</th>
            <th data-options="field:'operCode',width:100,align:'center'">编码</th>
            <th data-options="field:'status',width:100, align:'center',formatter: fmtStatus">状态</th>
            <th data-options="field:'x',width:100,align:'center',formatter: OperModule.fmtOperate">操作</th>
        </tr>
        </thead>
    </table>
</div>

<shiro:hasAnyRoles name="SUPER_ADMIN">
<div id="oper_toolbar" style="padding:10px 10px;">
    <div class="form-inline">
        <form id="role_form">
            <div>
                <div class="btn-group" data-toggle="buttons-checkbox">
                    <shiro:hasPermission name="${MENUCODE}:1">
                    <a class="btn btn-sm btn-success" id="saveOper" href="javascript:void(0)"
                       onclick="OperModule.beforeEditOper(1,-1);">添加按钮
                    </a>
                    </shiro:hasPermission>
                </div>
            </div>
        </form>
    </div>
</div>
</shiro:hasAnyRoles>

<!--操作列的按钮-->
<div id="operDiv" class="hidden">
    <shiro:hasPermission name="${MENUCODE}:5">
    <a href="javascript:void(0)"  onclick="OperModule.beforeEditOper(2,#id#);">修改</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="${MENUCODE}:3">
    <a href="javascript:void(0)"  onclick="OperModule.beforeDelete(#id#);">删除</a>
    </shiro:hasPermission>
</div>

<!--添加、修改modal-->
<div id="editOperModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <form class="form-horizontal" role="form" id="editOperForm">
        <div class="modal-dialog bs-example-modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="mySmallModalLabel">编辑操作</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="hidden" id="id" name="id"/>
                        <label class="col-sm-2 control-label">操作名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" maxlength="25" name="operName"
                                   id="operName" required placeholder="操作名称"/>
                        </div>
                    </div>
                    <div class="form-group" id="operCodeDiv">
                        <label class="col-sm-2 control-label">编码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" maxlength="25" name="operCode"
                                   id="operCode" placeholder="操作编码"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-sm btn-warning" id="saveBtn"
                            onclick="OperModule.acceptEditOper();">提 交
                    </button>
                    <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal" id="closeModal">取 消
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>


<!-- 删除操作Modal -->
<div class="modal fade" id="deleteOperModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
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
                　您确定删除吗? <input type="hidden" id="removeId" name="removeId"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-warning" onclick="OperModule.deleteOper()">
                    确 定
                </button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">
                    取 消
                </button>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/js/module/systemmgr/operate.js"></script>
</body>
</html>
