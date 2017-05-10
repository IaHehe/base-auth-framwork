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
    <title>用户管理</title>
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
        <li class="active">账户管理</li>
    </ol>
</section>
</div>

<div style="width: 100%;height: 90%;">
    <table id="user_table" toolbar="#search_toolbar">
        <thead>
        <tr>
            <th data-options="field:'loginName',width:150,align:'center'">用户名</th>
            <th data-options="field:'userName',width:80,align:'center'">名称</th>
            <th data-options="field:'email',width:150,align:'center'">邮箱</th>
            <th data-options="field:'mobile',width:100,align:'center'">电话号码</th>
            <th data-options="field:'jobTitle',width:100,align:'center'">岗位</th>
            <th data-options="field:'deptName',width:100,align:'center',formatter: UserModule.fmtDept">业务部门</th>
            <th data-options="field:'status',width:50, align:'center',formatter: fmtStatus">状态</th>
            <th data-options="field:'creatTime',width:100,align:'center',formatter: fmtDate">创建时间</th>
            <th data-options="field:'createUserName',width:100,align:'center'">创建人</th>
            <th data-options="field:'x',width:200,align:'center',fit:true,formatter: UserModule.fmtOperate">操作</th>
        </tr>
        </thead>
    </table>
</div>

<%--搜索条件--%>
<div id="search_toolbar" style="padding:10px 10px;">
    <div class="form-inline">
        <form id="userSearchForm" class="form-inline" role="form">
            <div class="form-group" style="margin: 5px;">
                <label class="control-label">状态：</label>
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
                <label class="control-label">邮箱：</label>
                <input  type="text" class="form-control input-sm" id="email_q" name="email"><br/>
            </div>
            <div class="form-group" style="margin: 5px;">
                <label class="control-label">手机号：</label>
                <input  type="text" class="form-control input-sm" id="mobile_q" name="mobile" maxlength="11">
            </div>
            <div class="form-group" style="margin: 5px;">
                <label class="control-label">岗位：</label>
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

            <shiro:hasAnyRoles name="SUPER_ADMIN">
                <div class="form-group" style="margin: 5px;margin-left: 25px;">
                    <a class="btn btn-sm btn-danger" id="saveUser" href="javascript:void(0)"
                       onclick="UserModule.searchUser();">搜索用户
                    </a>
                    <shiro:hasPermission name="${MENUCODE}:1">
                    <a class="btn btn-sm btn-primary" style="margin-left: 30px;" id="saveUser" href="javascript:void(0)"
                       onclick="UserModule.beforeEditUser(1,-1);">添加用户
                    </a>
                    </shiro:hasPermission>
                </div>
            </shiro:hasAnyRoles>
        </form>
    </div>
</div>

<!--操作列的按钮-->
<div id="operDiv" class="hidden">
    <shiro:hasPermission name="${MENUCODE}:6">
    <a  href="javascript:void(0)" onclick="UserModule.beforeEditUser(2,#id#);">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="${MENUCODE}:12">
    <a  style="margin-left: 3px;" href="javascript:void(0)" onclick="UserModule.beforeDelete(#id#,#status#)">启/禁用</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="${MENUCODE}:9">
    <a  style="margin-left: 3px;" href="javascript:void(0)" onclick="UserModule.showResetPassword(#id#)">重置密码</a>
    </shiro:hasPermission>
</div>

<!--添加、修改modal-->
<div id="editUserModal" class="modal fade bs-example-modal-sm">
    <form class="form-horizontal" user="form" id="editUserForm">
        <div class="modal-dialog bs-example-modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="mySmallModalLabel">编辑用户</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <!--修改时用户此id-->
                        <input type="hidden" id="id" name="id"/>

                        <label class="col-sm-2 control-label">用户名称</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control input-sm" maxlength="50" name="loginName" required
                                   id="loginName" placeholder="请输入用户名称"  onblur="UserModule.checkUniqueLoginName(this.value)"/>
                            <span style="display:none;" id="loginName_tip">用户名称已存在</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control input-sm" maxlength="25" name="userName" required id="userName"
                                   placeholder="姓名"/>
                            <span class="hidden text-danger">请输入姓名</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control input-sm" maxlength="50" name="email" required id="email"
                                   placeholder="公司邮箱"/>
                            <span class="hidden text-danger">请输入邮箱</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">手机号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control input-sm" maxlength="11" required placeholder="手机号"
                                   name="mobile" id="mobile">
                            <span class="hidden text-danger">手机号</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">岗位</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control input-sm" maxlength="50" required placeholder="岗位"
                                   name="jobTitle" id="jobTitle">
                            <span class="hidden text-danger">岗位</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">业务部门</label>
                        <div class="col-sm-10">
                            <select id="deptId" name="deptId" class="form-control input-sm">
                                <option value="">请选择</option>
                                <c:forEach items="${deptList}" var="dept" begin="1">
                                    <option value="${dept.id}">${dept.deptName}</option>
                                </c:forEach>
                            </select>
                            <span class="hidden text-danger">业务部门</span>
                        </div>
                    </div>
                    <div class="form-group myPWD">
                        <label class="col-sm-2 control-label">登录密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control input-sm" name="password" required
                                   id="password" placeholder="登录密码"/>
                        </div>
                    </div>
                    <div class="form-group myPWD">
                        <label class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control input-sm"  name="repassword" required
                                   id="repassword" placeholder="确认登录密码"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-sm btn-warning" id="saveBtn" onclick="UserModule.editSaleUser();">提 交</button>
                    <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal" id="closeModal">取 消</button>
                </div>

            </div>
        </div>
    </form>
</div>

<!-- 删除角色Modal -->
<div class="modal fade" id="deleteUserModal" tabindex="-1" User="dialog" aria-labelledby="myModalLabel"
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
                <form class="form-horizontal" user="form" id="enableForm">
                    <input type="hidden" id="removeId" name="removeId"/>
                    <div class="form-group" id="statusRadioDiv">
                        <div>
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status1" value="1" checked>应用
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status2" value="2">禁用
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-warning" onclick="UserModule.deleteUser()">确 定</button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">取 消</button>
            </div>
        </div>
    </div>
</div>

<!-- 重置密码Modal -->
<div class="modal fade" id="resetPwdModal" tabindex="-1" User="dialog" aria-labelledby="myModalLabel"
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
                <button type="button" class="btn btn-sm btn-warning" onclick="UserModule.resetPwd()">
                    提 交
                </button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">
                    取 消
                </button>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/js/module/systemmgr/user.js"></script>
</body>
</html>
