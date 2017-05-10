<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="userGoupSearchForm" class="form-inline" role="form">
<table class="table table-bordered" style="font-size: 12px;">
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
                <span class="checkbox">
                    <label>
                        <input class="myChekbox menuChkbox" type="checkbox" name="menuIds" id="menu${menu.id}"
                           value="${menu.id}">${menu.menuName}
                    </label>
                </span>
            </td>
            <td>
                <span class="checkbox">
                    <label style="margin-left: 5px;">
                        <input class="hidden" type="checkbox" name="row"
                           onchange="UserGroupPermission.chooseRowOper(this,${menu.id})"/><a>全选</a>
                    </label>
                </span>
                <c:forEach items="${operList}" var="oper">
                     <span class="checkbox">
                         <label style="margin-left: 5px;">
                            <input class="myChekbox operChkbox${menu.id}" type="checkbox" name="operIds"
                                id="oper${menu.id}_${oper.id}"
                                value="${oper.id}">${oper.operName}
                        </label>
                     </span>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</form>
<script>
    $(function () {
        UserGroupPermission.initUserMenuCheckbox();
    });
</script>