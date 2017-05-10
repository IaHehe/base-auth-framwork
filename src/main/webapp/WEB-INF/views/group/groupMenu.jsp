<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
    </button>
    <h4 class="modal-title" id="myModalLabel">角色（功能）权限</h4>
</div>
<div class="modal-body">
    <div>
    <form class="form-inline" role="form" id="roleMenuForm">
        <input type="hidden" id="sroleId" value="${roleInfo.id}"/>
        <input type="hidden" id="groupId" value="${groupInfo.id}"/>

        <table class="table table-bordered" style="font-size: 12px;">
             <caption style="color: red;font-weight:bold;"><span>团队名称：${groupInfo.groupName}  </span><span style="margin-left: 20px;">角色名称：${roleInfo.roleName}</span></caption>
            <thead>
            <tr>
                <td style='vertical-align: middle;text-align: center;'>模块权限</td>
                <td>按钮权限</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${menuList}" var="menu">
                <tr id="tr${menu.id}">
                    <td style='vertical-align: middle;text-align: center;width:100px;'>
                        <span class="checkbox"> <label>
                            <input class="myChekbox menuChkbox" type="checkbox" name="menuIds" id="menu${menu.id}"
                                   value="${menu.id}">${menu.menuName}
                        </label></span>
                    </td>
                    <td>
                        <span class="checkbox"><label style="margin-left: 5px;">
                            <input class="hidden" type="checkbox" name="row"  onchange="RoleGroupModule.chooseRowOper(this,${menu.id})"/><a>全选</a>
                        </label></span>
                        <c:forEach items="${operList}" var="oper">
                             <span class="checkbox"><label style="margin-left: 5px;">
                                 <input class="myChekbox operChkbox${menu.id}" type="checkbox" name="operIds"
                                        id="oper${menu.id}_${oper.id}"
                                        value="${oper.id}">${oper.operName}
                             </label></span>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
    </div>
</div>
<div class="modal-footer">
    <span class="checkbox">
        <label>
            <input type="checkbox" class="btn btn-sm btn-facebook" onclick="RoleGroupModule.checkAllMenus(this)"/>全选/反选
        </label>
        <button type="button" style="margin-left: 10px;" class="btn btn-sm btn-warning" onclick="RoleGroupModule.accpetResource()">
            保 存
        </button>
        <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">
            取消
        </button>
    </span>
</div>

<script>
    $(function(){
        RoleGroupModule.initGroupCheckBox();
    });
</script>