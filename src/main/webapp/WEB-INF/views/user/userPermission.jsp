<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
    </button>
    <h4 class="modal-title" id="myModalLabel">用户权限</h4>
    <input type="hidden" id="userId" value="${userRoleGroupDto.userId}"/>
    <input type="hidden" id="roleId" value="${userRoleGroupDto.roleId}"/>
    <input type="hidden" id="groupId" value="${userRoleGroupDto.groupId}"/>
</div>
<div class="modal-body">
    <div id="userPermissTabs" style="width:99%;height:300px;">
        <div title="功能" style="padding:10px">
        </div>
        <div title="数据" style="padding:10px"></div>
    </div>
</div>
<div class="modal-footer">
    <span class="checkbox">
        <label>
            <input type="checkbox" class="btn btn-sm btn-facebook" onclick="UserGroupPermission.checkAllMenus(this)"/>全选/反选
        </label>
        <button type="button" style="margin-left: 10px;" class="btn btn-sm btn-warning" onclick="UserGroupPermission.accpetResource()">
            保 存
        </button>
        <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">
            取消
        </button>
    </span>
</div>

<script>
    $(function(){
        setTimeout(function(){
            UserGroupPermission.userPermissTabs();
        },200);

        $("#userPermissionModal").on("hide.bs.modal", function () {
            $(this).removeData("bs.modal");
        });
    });
</script>