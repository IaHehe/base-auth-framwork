/**
 * 用户管理模块
 */
var UserModule = {
    initTable: function() {
        $('#user_table, #batchUser_table').datagrid({
            idField: 'id',
            url: window.basePath + '/user/findUserPage',
            fitColumns: true,
            nowrap: true,
            fit: true,
            checkOnSelect: false,
            rownumbers: true,
            singleSelect: false,
            pagination: true
        });
    },
    searchUser: function () {//搜索
        var reg = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        var mobile = $("#mobile_q").val();
        if (mobile && !reg.test(mobile)) {
            alert('电话号码不正确');
            return false;
        }
        var params = $("#userSearchForm").serializeJson();
        $('#user_table').datagrid('load', params);
    },
    reloadTable: function () {//表格重新加载数据
        $('#user_table').datagrid('reload');
    },
    checkUniqueLoginName: function (val) {
        if (!val) {
            return false;
        }
        var url = this.getUrl("/user/checkUniqueLoginName?loginName=" + val);
        $.get(url, {t: new Date().getTime()}, function (data) {
            if (data && !data.data) {
                $('#loginName_tip').show();
            }
        });
    },
    beforeEditUser: function (flag, id) {debugger;
        $('#id').val('');
        $("#editUserForm")[0].reset();
        $('#status1').prop('checked', true);
        $('#loginName,#userName').attr('readonly', false);
        $('.myPWD').show();

        if (flag == 2) {
            var saleUser = this.getSaleUserById(id);
            if (saleUser) {
                for (var key in saleUser) {
                    $('#' + key).val(saleUser[key]);
                }
                $('#repassword').val(saleUser.password);
                $('#deptId').val(saleUser.department ? saleUser.department.id : ''); //部门
                if (saleUser.status == 2) {
                    $('#status2').prop('checked', true);
                } else {
                    $('#status1').prop('checked', true);
                }

                $('#loginName,#userName').attr('readonly', true);
                $('.myPWD').hide();
            }
        }

        $('#editUserModal').modal({
            show: true
        });
    },
    getSaleUserById: function (id) {
        var url = this.getUrl("/user/getUserById/" + id);
        return asyncAjax(url, {t: new Date().getTime()});
    },
    submitEditUser: function () {
        var me = this;
        var url = this.getUrl("/user/editUser");
        $.post(url, $('#editUserForm').serialize(), function (data) {
            if (data && data.data) {
                $('#editUserModal').modal("hide");
                $("#editUserForm")[0].reset();
                me.reloadTable();
            } else {
                alert('保存失败');
            }
        });

        return false;
    },
    editSaleUser: function () {//编辑
        var me = this;
        $('#loginName_tip').hide();
        this.validator = $('#editUserForm').validate({
            submitHandler: function (form) {
                return me.submitEditUser();
            },
            messages: {
                repassword: {
                    equalTo: '确认密码与登陆密码不一致'
                }
            },
            rules: {
                loginName: {
                    required: true,
                    email: true
                },
                email: {
                    required: true,
                    email: true
                },
                mobile: {
                    required: true,
                    digits: true,
                    rangelength: [11, 11]
                    //isMobile: true
                },
                password: {
                    required: true,
                    rangelength: [6, 32],
                },
                repassword: {
                    required: true,
                    rangelength: [6, 32],
                    equalTo: '#password'
                }
            }
        });
    },
    clearTipMsg: function (me) {
        $(me).next("span").addClass("hidden");
    },
    fmtDept: function (val, row, index) {
        return row.department ? row.department.deptName : '';
    },
    fmtOperate: function (val, row, index) {
        if (!row || !row.id)
            return '';
        if (row.id == 1)
            return '';

        var htmls = $('#operDiv').show().html();
        htmls = htmls.replace(/(#id#)/g, row.id);
        htmls = htmls.replaceAll(/(#status#)/,row.status);
        return htmls;
    },
    beforeDelete: function (userId,status) {
        $('#removeId').val(userId);
        if(status == 2){
            $('#status2').prop('checked',true);
        }
        $('#deleteUserModal').modal({
            show: true
        });
    },
    showResetPassword: function (userId) {//重置密码
        $('#resetUserId').val(userId);
        $('#resetPwdModal').modal('show');
    },
    resetPwd: function () {
        var userId = $('#resetUserId').val();
        var url = this.getUrl('/user/resetPwd');
        var params = {userId: userId, t: new Date().getTime()};

        $.post(url, params, function (data) {
            if (data && data.data) {
                alert('重置密码成功');
                $('#resetPwdModal').modal('hide');
            }
        });
    },
    getUrl: function (url) {
        return window.basePath + url;
    },
    deleteUser: function() {
        var me = this;
        var userId = $('#removeId').val();
        var status = $('#enableForm :radio[name="status"]:checked').val();
        var url = this.getUrl('/user/deleteUser/' + userId + '/' + status);
        $.get(url, {t: new Date().getTime()}, function (data) {
            if (data && data.data) {
                $('#deleteUserModal').modal('hide');
                me.reloadTable();
            }
        });
    }
};

UserModule.validator = null;

/**
 * 初始化加载
 */
$(function () {
    UserModule.initTable();

    $("#editUserModal").on("hide.bs.modal", function () {debugger;
        if (UserModule.validator) {
            UserModule.validator.resetForm();//重置验证
        }
        $(this).removeData("modal");
        $('#loginName_tip').hide();
    });

    $("#deleteUserModal").on("hide.bs.modal", function () {
        $('#enableForm')[0].reset();
        $(this).removeData("bs.modal");
    });

});