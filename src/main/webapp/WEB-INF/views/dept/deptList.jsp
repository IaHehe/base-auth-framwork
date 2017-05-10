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
    <title>部门管理</title>
    <jsp:include page="../static.jsp"/>
</head>

<body>

<!--面包屑导航-->
<div style="width: 100%;height: 10%;">
    <section class="content-header">
        <h1>
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 运维功能</a></li>
            <li class="active">业务部门</li>
        </ol>
    </section>
</div>

<div class="easyui-panel" style="width:100%;height:90%;">
    <div class="easyui-layout" data-options="fit:true">
        <!--左边树-->
        <div data-options="region:'west',split:true" style="width:230px;padding:0 10px">
            <input type="hidden" id="selectedTreeNodeId" />
            <div id="menuContent" class="menuContent">
                <ul id="deptTree" class="ztree"></ul>
            </div>
        </div>

        <!--右边表单-->
        <div data-options="region:'center'" style="overflow: hidden;">
            <div style="margin-top: 30px;">
                <form class="form-horizontal" role="form" id="editDeptForm">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">上级部门名</label>
                        <div  class="col-sm-8">
                            <input type="hidden"  name="id" id="deptId" />
                            <input type="hidden"  name="pId" id="pId" />
                            <input type="text"  class="form-control"  name="parentName" id="parentName" readonly  placeholder="上级部门名"/>
                            <span id="selectDeptTip" class="hidden text-danger">请选择部门名称</span>
                    </div>
                    <span><a id="menuBtn" href="#" onclick="DepartmentModule.showMenu(); return false;">选择...</a></span>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" maxlength="30" name="deptName"
                                   id="deptName" onfocus="hideTip('deptNameTip');" placeholder="部门名称"/>
                            <span id="deptNameTip" class="hidden text-danger">请输入部门名称</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">应用</label>
                        <div class="col-sm-8">
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status1" value="1" checked>应用
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status2" value="2">禁用
                            </label>
                        </div>
                    </div>
                    <shiro:hasAnyRoles name="SUPER_ADMIN">
                    <div class="form-group" style="margin-top: 30px;">
                        <label class="col-sm-2 control-label hidden">操作</label>
                        <div class="col-sm-10 pull-right">
                            <shiro:hasPermission name="${MENUCODE}:1">
                            <button type="button" id="addSaleRole" class="btn btn-success  btn-sm" onclick="DepartmentModule.submitGroup(1);">
                                添加部门
                            </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="${MENUCODE}:6">
                            <button style="margin-left: 30px;" type="button" id="editSaleRole" class="btn btn-danger btn-sm" onclick="DepartmentModule.submitGroup(2);">
                                编辑部门
                            </button>
                            </shiro:hasPermission>
                            <button style="margin-left: 30px;" type="reset" id="editSaleRole" class="btn btn-primary btn-sm">重  置</button>
                        </div>
                    </div>
                    </shiro:hasAnyRoles>
                </form>
            </div>
        </div>
    </div>
</div>


<!--下拉菜单树-->
<div id="menuContent2" style="z-index: 2000;display:none; position: absolute;overflow: auto;">
    <ul id="editZtree" class="ztree"
        style="overflow-y:auto;margin-top:0; width:520px; height: 120px;border: solid 1px darkgray;background:#eee;"></ul>
</div>

<script src="${ctx}/js/module/systemmgr/department.js"></script>
</body>
</html>