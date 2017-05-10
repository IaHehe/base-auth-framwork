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
            <li class="active">团队管理</li>
        </ol>
    </section>
</div>

<div class="easyui-panel" style="width:100%;height:90%;">
    <div class="easyui-layout" data-options="fit:true">
        <!--左边树-->
        <div data-options="region:'west',split:true" style="width:230px;height:99%;padding:0 10px">
            <input type="hidden" id="selectedTreeNodeId" />

            <div style="margin-top: 5px;">
                <shiro:hasPermission name="${MENUCODE}:1">
                <button type="button" id="addSaleRole" class="btn btn-success btn-sm" onclick="GroupModule.beforeEdit(1);">
                    添加
                </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="${MENUCODE}:6">
                <button style="margin-left: 20px;" type="button" id="editSaleRole" class="btn btn-danger btn-sm" onclick="GroupModule.beforeEdit(2);">
                    编辑
                </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="${MENUCODE}:3">
                <button style="margin-left: 20px;" type="button" id="editSaleRole" class="btn btn-danger btn-sm" onclick="GroupModule.removeGroup();">
                    删除
                </button>
                </shiro:hasPermission>
            </div>

            <div id="menuContent" class="menuContent">
                <ul id="groupTree" class="ztree"></ul>
            </div>
        </div>

        <!--右边表单-->
        <div data-options="region:'center'" style="overflow: hidden;">
            <div style="margin-top: 30px;">
                <form class="form-horizontal" role="form" id="editgroupForm">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">上级组名</label>
                        <div  class="col-sm-8">
                            <input type="hidden"  name="way" id="way" />
                            <input type="hidden"  name="id" id="groupId" />
                            <input type="hidden"  name="pId" id="pId" />
                            <input type="text"  class="form-control"  name="parentName" id="parentName" readonly  placeholder="分组名称"/>
                            <span id="selectGroupTip" class="hidden text-danger">请选择分组名称</span>
                    </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">分组名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" maxlength="30" name="groupName"
                                   id="groupName" onfocus="hideTip('groupNameTip');" placeholder="分组名称"/>
                            <span id="groupNameTip" class="hidden text-danger">请输入分组名称</span>
                        </div>
                    </div>

                    <shiro:lacksRole name="MEMBER">
                    <div class="form-group" style="margin-top: 30px;">
                        <label class="col-sm-2 control-label hidden">应用</label>
                        <div class="col-sm-10 pull-right">
                            <button type="button" id="addSaleRole" class="btn btn-success btn-sm" onclick="GroupModule.submitGroup(1);">
                                提  交
                            </button>
                            <button style="margin-left: 20px;" type="reset" id="editSaleRole" class="btn btn-danger btn-sm">重 置</button>
                        </div>
                    </div>
                    </shiro:lacksRole>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 删除组Modal -->
<div class="modal fade" id="removeGroupModal" tabindex="-1" User="dialog" aria-labelledby="myModalLabel"
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
                　您确定删除吗?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-warning" onclick="GroupModule.submitRemoveGroup()">确 定</button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">取 消</button>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/js/module/systemmgr/group.js"></script>
</body>
</html>