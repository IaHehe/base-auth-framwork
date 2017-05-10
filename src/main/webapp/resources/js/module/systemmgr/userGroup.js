/**
 * 用户组管理
 */
var UserGroupModule = {
    initTable: function () {
        $('#userGroup_table').datagrid({
            idField: 'id',
            url: window.basePath + '/userGroup/findUserGroupPage',
            fitColumns: true,
            fit: true,
            rownumbers: true,
            pagination: true
        });
    },
    searchUserGroup: function(param){//搜索用户组
        debugger;
        param = param || $("#userGoupSearchForm").serializeJson();
        $('#userGroup_table').datagrid('load', param);
    },
    searchAddUser: function(){//添加modal中搜索用户
        $('#batchUser_table').datagrid('load', $("#addUserForm").serializeJson());
    },
    bindUserGroup: function(){
        var checkArr = $('#batchUser_table').datagrid('getChecked');//取消所有勾选
        var idArr = [];
        if(checkArr && checkArr.length >0){
            for(var i = 0,len = checkArr.length; i < len; i++){
                idArr.push(checkArr[i].id);
            }
        }
        if(idArr.length == 0)
            return false;

        var me = this;
        var userIds = idArr.join(',').toString();
        var groupId = $('#selectedTreeNodeId').val();
        var url = window.basePath + '/userGroup/insertBatch';
        var params = {userIds: userIds,groupId: groupId};
        $.post(url,params, function (data) {
            if(data && data.data){
                $('#batchUserModal').modal('hide');
                me.searchUserGroup({groupId: groupId});
            }else{
                alert(data.msg);
            }
        });
    },
    showAddUserModal: function() {
        $('#addUserForm')[0].reset();
        var groupId = $('#selectedTreeNodeId').val();
        if (!groupId) {
            alert('请选择团队!');
            return false;
        }
        $('#nowGroupTitle').html($('#selectedTreeNodeName').val());
        $('#batchUserModal').modal('show');
        var me = this;

        setTimeout(function () {
            $('#batchUser_table').datagrid({
                idField: 'id',
                url: window.basePath + '/user/findUserPage',
                fitColumns: true,
                fit: true,//自动大小
                rownumbers: false,//行号
                pagination: true,//分页控件
                frozenColumns: [[
                    {field: 'ck', checkbox: true}
                ]],
                onBeforeCheck: me.checkUserExistsGroup,
                onCheckAll: me.disableCheckAll
            });
        }, 500);

        $('#batchUser_table').datagrid('load',{});
    },
    disableCheckAll: function(rows){
        $('#batchUser_table').datagrid('uncheckAll');
        return false;
    },
    checkUserExistsGroup: function(index,row){debugger;
        var url = window.basePath + "/userGroup/checkUserExistsGroup/" + row.id;
        var exists = asyncAjax(url);
        if(!exists){
            alert('此用户已分配了团队');
        }
        return exists;
    },
    fmtOperate: function (val, row, index) {debugger;
        var rowRoleId = row.roleId;
        var uid = Number($('#loginUserId').val());
        var loginRoleID = Number($('#loginRoleId').val());

        var userId = row.userId;
        if(userId == 1){
            return '';
        }
        if(uid== userId){//自己不能删除自己、调整自己职务、缩小自己权限
            return '';
        }

        //非当前组或者是非超级管事员;是组员也无权限
        var loginUserGroupId = Number($('#loginUserGroupId').val());
        if(row.groupId != loginUserGroupId && uid != 1){
            return '';
        }

        var htmls = $('#operDiv').show().html();
        htmls = htmls.replaceAll(/(#id#)/, userId);
        htmls = htmls.replaceAll(/(#roleId#)/,rowRoleId || 5);
        return htmls;
    },
    showDeleteModal: function(userId){
        $('#removeUserId').val(userId);
        $('#deleteUserGroupModal').modal('show');
    },
    deleteUserFromGroup:function(){
        var me = this;
        var userId = $('#removeUserId').val();
        var groupId = $('#selectedTreeNodeId').val();
        var url = window.basePath + '/userGroup/deleteUserFromGroup';
        $.post(url,{userId: userId, groupId:groupId}, function (data) {
            if(data && data.data){
                $('#deleteUserGroupModal').modal('hide');
                me.searchUserGroup({groupId:groupId});
            }else{
                alert(data.msg);
            }
        });
    },
    roleChooseModal: function(userId,roleId){
        $('#roleId1,#roleId2').prop('checked');
        $('#roleId1').prop('disabled',false);
        if(roleId == 3){
            $('#roleId1').prop('checked',true);
        }
        if(roleId == 4){
            $('#roleId2').prop('checked',true);
        }

        if($('#selectedTreeNodeId').val() == 1){//总公司不能有团队长
            $('#roleId1').prop('disabled',true);
        }

        //一个团队只能有一个团队长
        var url = window.basePath + '/userGroup/existsManagerInGroup/';
        url += $('#selectedTreeNodeId').val();
        var has = asyncAjax(url);
        if(has){
            $('#roleId1').prop('disabled',true);
        }

        $('#giveRoleUserId').val(userId);
        $('#roleChooseModal').modal('show');
    },
    acceptUserRole:function(){//分配用户角色
        var me = this;
        var userId = $('#giveRoleUserId').val();
        var roleId = $('#accpetUserRoleForm :radio[name="roleId"]:checked').val();

        var url = window.basePath + '/userGroup/acceptUserRole';
        $.post(url,{userId:userId, roleId:roleId},function(data){
            if(data && data.data){
                $('#roleChooseModal').modal('hide');
                me.searchUserGroup({groupId: $('#selectedTreeNodeId').val()});
            }else{
                alert(data.msg);
            }
        });
    }
};

/** 树属性设置**/
UserGroupModule.zTreeObj = null;
UserGroupModule.zTreeSetting = {
    data: {
        simpleData: {enable: true}
    },
    async: {
        enable: true,
        url: window.basePath + "/group/loadgroupTree",
        dataFilter: function(treeId, parentNode, responseData) {
            var zNodes = [];
            if (!responseData) return null;
            if (responseData.data) {
                var list = responseData.data;
                for (var i = 0; i < list.length; i++) {
                    var node = list[i];
                    zNodes.push({"id": node.id, "pId": node.pId,
                        "name": node.groupName,status: node.status,open:true});
                }
            }
            return zNodes;
        }
    },
    callback: {
        onClick: function(event, treeId, treeNode){
            $('#selectedTreeNodeId,#groupId').val(treeNode.id);//当前选中的树节点id
            $('#selectedTreeNodeName').val(treeNode.name);

            UserGroupModule.searchUserGroup({groupId: treeNode.id});
        }
    }
};

/**
 * 初始化加载数据
 */
$(document).ready(function () {
    $.fn.zTree.init($("#userGroupTree"), UserGroupModule.zTreeSetting);
    UserGroupModule.zTreeObj = $.fn.zTree.getZTreeObj("userGroupTree");

    UserGroupModule.initTable();

    $("#roleChooseModal").on("hide.bs.modal", function () {
        $('#roleId1').prop('checked');
        $('#roleId2').prop('checked');
        $('#accpetUserRoleForm')[0].reset();

        $(this).removeData("bs.modal");
    });
});