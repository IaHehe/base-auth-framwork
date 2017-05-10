$(function () {
    RoleModule.initTable(); //加载table

    $("#resourceModal").on("hide.bs.modal", function () {
        $('#roleMenuForm')[0].reset();
        $(this).removeData("bs.modal");
    });
});

/**
 * 角色管理模块
 */
var RoleModule = {
    initTable: function () {//初始化表格
        $('#role_table').datagrid({
            idField: 'id',
            url: window.basePath + '/role/findRoleList',
            fitColumns: true,
            nowrap: true,
            fit: true,
            rownumbers: true,
            singleSelect: true
        });
    },
    reloadTable: function () {//表格重新加载数据
        $('#role_table').datagrid('reload');
    },
    beforeEditRole: function (flag, id) {
        $('#roleId').val('');
        $("#editRoleForm")[0].reset();
        $('#status1').prop('checked', true);
        $('#roleCodeDiv').show();

        if (flag == 2) {
            $('#roleCodeDiv').hide();
            var saleRole = this.getSaleRoleById(id);
            if (saleRole) {
                $('#roleId').val(saleRole.id);
                $('#rolename').val(saleRole.roleName);
                $('#roleCode').val(saleRole.roleCode);
                $('#remark').val(saleRole.remark);
                if (saleRole.status == 2) {
                    $('#status2').prop('checked', true);
                }
            }
        }

        $("#roleCode").next("span").addClass("hidden").text('请输入角色编码');
        $('#editRoleModal').modal({
            show: true
        });
    },
    getSaleRoleById: function (id) {
        var url = this.getUrl("/role/getRole/" + id);
        return asyncAjax(url, {});
    },
    editSaleRole: function () {//编辑
        $("#roleCode").next("span").addClass("hidden").text('请输入角色编码');
        if (!$('#rolename').val()) {
            $("#rolename").next("span").removeClass("hidden");
            return false;
        }

        var roleCode = $('#roleCode').val();
        if (!roleCode) {
            $("#roleCode").next("span").removeClass("hidden");
            return false;
        }
        if (!$('#roleId').val()) {
            var exists = this.checkUniqueCode(roleCode);
            if (!exists) {
                $("#roleCode").next("span").removeClass("hidden").text('编码已存在');
                return false;
            }
        }

        if (!$('#remark').val()) {
            $("#remark").next("span").removeClass("hidden");
            return false;
        }

        var me = this;
        var url = this.getUrl("/role/editRole");
        $.post(url, $('#editRoleForm').serialize(), function (data) {
            if (data && data.data) {
                $('#editRoleModal').modal("hide");
                $("#editRoleForm")[0].reset();
                me.reloadTable();
            } else {
                alert('保存失败');
            }
        });
    },
    clearTipMsg: function (me) {
        $(me).next("span").addClass("hidden");
    },
    fmtOperate: function (val, row, index) {
        var htmls = $('#operDiv').html();
        htmls = htmls.replace(/(#id#)/g, row.id);
        return htmls;
    },
    showSource: function (roleId) {//弹出功能modal
        var url = window.basePath + '/roleMenu/showRoleMenuPage/' + roleId;
        url += "?t=" + new Date().getTime();

        $('#resourceModal').modal({
            backdrop: 'static',
            show: true,
            remote: url
        });
    },
    accpetResource: function () {//提交分配角色功能
        var roleId = $('#sroleId').val(); //角色id
        var roleMenuArr = this.getCheckedBox();
        if (roleMenuArr.length == 0) {
            alert('请选择模块权限');
            return false;
        }

        var url = window.basePath + '/roleMenu/saveRoleMenuOperates/' + roleId;
        var params = {roleMenuOpers: JSON.stringify(roleMenuArr)};
        $.post(url, params, function (data) {
            if (data && data.data) {
                $('#resourceModal').modal('hide');
                alert('保存成功');
            } else {
                alert(data.msg);
            }
        });
    },
    initCheckedBox: function () {//默认配置了角色则选中
        var roleId = $('#sroleId').val(); //角色id
        var url = window.basePath + '/roleMenu/loadRoleMenuOpers/' + roleId;
        var list = asyncAjax(url, {t: new Date().getTime()});

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
    getCheckedBox: function () {
        var roleId = $('#sroleId').val(); //角色id
        var arr = [];
        $('.menuChkbox').each(function () {//菜单
            if ($(this).prop('checked')) {
                var roleMenu = {roleId: roleId};
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
    getUrl: function (url) {
        return window.basePath + url;
    },
    checkUniqueCode: function (val) {
        if (!val) {
            return false;
        }

        var url = this.getUrl("/role/checkUniqueCode");
        return asyncAjax(url, {roleCode: val});
    },
    checkAllMenus: function (obj) {//全选反选菜单与按钮
        $('.myChekbox').each(function () {
            $(this).prop('checked', obj.checked);
        });
    },
    chooseRowOper: function(obj,id){
        $('.operChkbox' + id).each(function () {
            $(this).prop('checked', obj.checked);
        });
    }
}
;