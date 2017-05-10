/**
 * 用户组权限
 */
var UserGroupPermission = {
    userPermissTabs: function () {
        var me = this;
        $('#userPermissTabs').tabs({
            tabWidth: 150,
            fit: true,
            border: true,
            onSelect: me.onSelect
        });
    },
    onSelect: function (title, index) {//选中tab叶事件
        var userId = $('#userId').val();
        var roleId = $('#roleId').val();
        var groupId = $('#groupId').val();
        var url = '';

        if (index == 0) {
            var path = '/userMenu/loadUserMenuOpers';
            url = UserGroupPermission.buildUrl(path, userId, roleId, groupId);
        }
        if (index == 1) {
            var path = '/userDataAuth/dataAuthTreePage';
            url = UserGroupPermission.buildUrl(path, userId, roleId, groupId);
        }

        $('#userPermissTabs').tabs('getSelected').panel('refresh', url);
    },
    getTabIndex: function(){
        var tab = $('#userPermissTabs').tabs('getSelected');
        return $('#userPermissTabs').tabs('getTabIndex', tab);
    },
    initUserMenuCheckbox: function () {
        var roleId = $('#roleId').val(); //角色id
        var url = window.basePath + '/roleMenu/loadRoleMenuOpers/' + roleId;
        var params = {t: new Date().getTime()};
        var list = asyncAjax(url, params);

        //1.基本菜单与按钮的列表
        this.selectedCheckbox(list);

        //2.未选中即角色没有,则直接不显示
        $('.menuChkbox').each(function () {
            if (!$(this).prop('checked')) {
                var nonRow = '#tr' + $(this).val();
                $(nonRow).remove();
            }
        });

        //3.仅组上分配的展示
        var groupId = $('#groupId').val();
        url = window.basePath + '/groupMenu/loadGroupMenuOpers/';
        url += groupId + '/' + roleId;

        list = asyncAjax(url, params);
        if (list && list.length > 0) {
            this.notCheckAll(); //取消现在的基本数据非选中
            this.selectedCheckbox(list);
        }

        //4.用户菜单排除
        var userId = $('#userId').val();
        url = window.basePath + '/userMenu/findMyMenus/' + userId;
        list = asyncAjax(url, params);
        if (list && list.length > 0) {
            this.notCheckAll(); //取消现在的基本数据非选中
            this.selectedCheckbox(list);
        }
    },
    accpetResource: function () {//提交分配组角色功能
        var index = this.getTabIndex();
        if (index == 0) {
            this.saveMenuPermission();//功能
        }
        if (index == 1) {
            this.saveDataPermission();//数据权限
        }
    },
    saveMenuPermission: function(){//功能权限保存
        var userMenuArr = this.getCheckedBox();//所有选中的键值
        if (userMenuArr.length == 0) {
            alert('请选择模块权限');
            return false;
        }

        var userId = $('#userId').val();
        var url = window.basePath + '/userMenu/saveUserMenuOperates/' + userId;
        var params = {userMenuOpers: JSON.stringify(userMenuArr)};
        $.post(url, params, function (data) {
            if (data && data.data) {
                $('#userPermissionModal').modal('hide');
                alert('保存成功');
            } else {
                alert(data.msg);
            }
        });
    },
    saveDataPermission: function () {//数据权限保存
        var nodes = UserDataAuth.zTree.getCheckedNodes(true);
        if(nodes.length == 0) {
            alert('请勾选');
            return false;
        }
        var dataArr = [];
        for(var i= 0,len = nodes.length;i < len; i++){
            var chkStatus = nodes[i].getCheckStatus();
            if(!chkStatus.half){
                dataArr.push(nodes[i].id);
            }
        }

        var userId = $('#userId').val();
        var url = window.basePath + '/userDataAuth/saveUserDataAuth';
        var params = {userId: userId, dataAuthStr: dataArr.toString()};

        $.post(url,params,function(data){
            if(data && data.data){
                $('#userPermissionModal').modal('hide');
            }else{
                alert(data.msg);
            }
        });
    },
    showUserGroupPermissionModal: function (userId, roleId) {
        var groupId = $('#selectedTreeNodeId').val();
        var path = '/userMenu/showUserPermissionPage';
        var url = this.buildUrl(path, userId, roleId, groupId);

        $('#userPermissionModal').modal({
            backdrop: 'static',
            show: true,
            remote: url
        });
    },
    getCheckedBox: function () {
        var userId = $('#userId').val(); //角色id
        var arr = [];
        $('.menuChkbox').each(function () {//菜单
            if ($(this).prop('checked')) {
                var roleMenu = {userId: userId};
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
    },
    buildUrl: function (url, userId, roleId, groupId) {
        url = window.basePath + url;
        url += '?userId=' + userId;
        url += "&roleId=" + roleId;
        url += "&groupId=" + groupId;

        return url;
    },
    notCheckAll: function () { //取消选中所有checkbox
        $('.myChekbox').each(function () {
            $(this).prop('checked', false);
        });
    },
    checkAllMenus: function (obj) {//全选反选菜单与按钮
        var index = this.getTabIndex();
        if (index == 0) {
            $('.myChekbox').each(function () {
                $(this).prop('checked', obj.checked);
            });
        }
        if (index == 1) {//数据权限
            UserDataAuth.zTree.checkAllNodes(obj.checked);
        }
    },
    chooseRowOper: function (obj, id) {
        $('.operChkbox' + id).each(function () {
            $(this).prop('checked', obj.checked);
        });
    },
    selectedCheckbox: function (list) {
        if (list && list.length > 0) {
            for (var i = 0, len = list.length; i < len; i++) {
                var roleMenu = list[i];
                $('#menu' + roleMenu.menuId).prop('checked', true);//菜单选中

                var opers = roleMenu.operates || '';
                var operArr = opers.split(',');
                for (var key in operArr) {
                    $('#oper' + roleMenu.menuId + '_' + operArr[key]).prop('checked', true);//操作选中
                }
            }
        }
    }

};