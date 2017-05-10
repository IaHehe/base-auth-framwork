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
    <title>用户数据权限</title>
    <jsp:include page="../static.jsp"/>
</head>

<body>

<!--非组员有权限树-->
<shiro:lacksRole name="MEMBER">
    <!--当前数据的用户角色非组员有权限树-->
    <c:if test="${userRoleGroupDto.roleId != 5}">
        <div id="menuContent" class="menuContent">
            <ul id="userDataAuthTree" class="ztree"></ul>
        </div>
    </c:if>
</shiro:lacksRole>
<shiro:hasRole name="MEMBER">
    <div>
        <h3>销售人员名下的自有数据。</h3>
    </div>
</shiro:hasRole>

<!--当前数据选中的用户的角色是组员-->
<c:if test="${userRoleGroupDto.roleId == 5}">
    <div>
        <h3>销售人员名下的自有数据。</h3>
    </div>
</c:if>

<script src="${ctx}/js/module/systemmgr/userDataAuth.js"></script>
</body>
</html>