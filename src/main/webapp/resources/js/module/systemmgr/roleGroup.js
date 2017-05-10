/**
 * 用户组管理
 */
var RoleGroupModule = {
    loadRoleGroupTable: function (groupId) {
        $('#roleGroup_table').datagrid("load", {'groupId': groupId});
    },
    loadRoleTable: function (groupId) {
        $('#roleGroup_table').datagrid({
            idField: 'id',
            url: window.basePath + '/roleGroup/findRoleGroupDataGrid',
            fitColumns: true,
            nowrap: true,
            fit: true,
            rownumbers: true
        });
    },
    fmtOperate: function (val, row, index) {
        var loginRoleId = $('#loginRoleId').val(); //登陆用户的角色id
        var loginUserGroupId = Number($('#loginUserGroupId').val());//登陆用户所属的组id
        var treeGroupId = Number($('#selectedTreeNodeId').val());

        if(row.roleId == 1 && loginRoleId != 1)
            return '';

        var htmls = $('#operDiv').show().html();
        htmls = htmls.replaceAll(/(#roleId#)/, row.roleId);

        if(loginRoleId == 1)
            return htmls;
        if(loginUserGroupId != treeGroupId)
            return '';
        return htmls;
    },
    checkAllMenus: function (obj) {//全选反选菜单与按钮
        $('.myChekbox').each(function () {
            $(this).prop('checked', obj.checked);
        });
    },
    notCheckAll:function(){ //取消选中所有checkbox
        $('.myChekbox').each(function () {
            $(this).prop('checked', false);
        });
    },
    chooseRowOper: function(obj,id){
        $('.operChkbox' + id).each(function () {
            $(this).prop('checked', obj.checked);
        });
    },
    showOperateModal: function (roleId) {
        var groupId = $('#selectedTreeNodeId').val();
        var url = window.basePath + '/roleGroup/showRoleMenuPage';
        url += '?groupId=' + groupId + '&roleId=' + roleId;

        $('#resourceModal').modal({
            backdrop: 'static',
            show: true,
            remote: url
        });
    },
    selectedCheckbox: function(list){
        if (list && list.length > 0) {
            for (var i = 0, len = list.length; i < len; i++) {
                var roleMenu = list[i];
                $('#menu' + roleMenu.menuId).prop('checked', true);//菜单选中

                var opers = roleMenu.operates || '';
                var operArr = opers.split(',');
                for (var key in operArr) {
                    $('#oper' + roleMenu.menuId + '_'+ operArr[key]).prop('checked', true);//操作选中
                }
            }
        }
    },
    initGroupCheckBox: function(){//选中权限checkbox
        var roleId = $('#sroleId').val(); //角色id
        var url = window.basePath + '/roleMenu/loadRoleMenuOpers/' + roleId;
        var list = asyncAjax(url, {t: new Date().getTime()});

        //1.基本菜单与按钮的列表
        this.selectedCheckbox(list);

        //2.未选中即角色没有,则直接不显示
        $('.menuChkbox').each(function(){
            if(!$(this).prop('checked')){
                var nonRow = '#tr' + $(this).val();
                $(nonRow).remove();
            }
        });

        //3.仅组上分配的展示
        var groupId = $('#groupId').val();
        var url = window.basePath + '/groupMenu/loadGroupMenuOpers/';
        url += groupId + '/' + roleId;

        list = asyncAjax(url, {t: new Date().getTime()});
        if(list && list.length > 0){
            this.notCheckAll(); //取消现在的基本数据非选中
            this.selectedCheckbox(list);
        }
    },
    accpetResource: function () {//提交分配组角色功能
        var roleMenuArr = this.getCheckedBox();//所有选中的键值
        if (roleMenuArr.length == 0) {
            alert('请选择模块权限');
            return false;
        }

        var url = window.basePath + '/groupMenu/saveGroupMenuOperates';
        var params = {groupMenuOpers: JSON.stringify(roleMenuArr)};
        $.post(url, params, function (data) {
            if (data && data.data) {
                $('#resourceModal').modal('hide');
                alert('保存成功');
            } else {
                alert(data.msg);
            }
        });
    },
    getCheckedBox: function () {
        var roleId = $('#sroleId').val(); //角色id
        var groupId = $('#groupId').val();
        var arr = [];
        $('.menuChkbox').each(function () {//菜单
            if ($(this).prop('checked')) {
                var roleMenu = {groupId: groupId,roleId: roleId};
                roleMenu.menuId = $(this).val();

                var opers = [];
                $('.operChkbox' + roleMenu.menuId).each(function () {//按钮
                    if ($(this).prop('checked')) {
                        opers.push($(this).val());
                    }
                });
                roleMenu.operates = opers.join(',').toString();
                arr.push(roleMenu);
            }
        });

        return arr;
    }
};

/** 树属性设置**/
RoleGroupModule.zTreeObj = null;
RoleGroupModule.zTreeSetting = {
    data: {
        simpleData: {enable: true}
    },
    async: {
        enable: true,
        url: window.basePath + "/group/loadgroupTree",
        dataFilter: function (treeId, parentNode, responseData) {
            var zNodes = [];
            if (!responseData) return null;
            if (responseData.data) {
                var list = responseData.data;
                for (var i = 0; i < list.length; i++) {
                    var node = list[i];
                    zNodes.push({
                        "id": node.id, "pId": node.pId,
                        "name": node.groupName, status: node.status, open: true
                    });
                }
            }
            return zNodes;
        }
    },
    callback: {
        onClick: function (event, treeId, treeNode) {
            var groupId = treeNode.id;
            $('#selectedTreeNodeId,#groupId').val(groupId);//当前选中的树节点id
            $('#selectedTreeNodeName').val(treeNode.name);

            RoleGroupModule.loadRoleGroupTable(groupId);
        }
    }
};

/**
 * 初始化加载数据
 */
$(document).ready(function () {
    $("#resourceModal").on("hide.bs.modal", function () {
        $(this).removeData("bs.modal");
    });

    //初始化团队树
    $.fn.zTree.init($("#roleGroupTree"), RoleGroupModule.zTreeSetting);
    RoleGroupModule.zTreeObj = $.fn.zTree.getZTreeObj("roleGroupTree");

    RoleGroupModule.loadRoleTable(''); //初始化角色组表格数据
});