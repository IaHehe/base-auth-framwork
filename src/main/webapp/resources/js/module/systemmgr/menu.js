/**
 * 菜单管理
 */

$(function () {
    $('#menu_table').treegrid({
        url: window.basePath + '/menu/loadTreeGrid',
        height: 'auto',
        fitColumns: true,
        nowrap: false,
        striped: false,
        border: false,
        collapsible: false,
        singleSelect: true,
        lines: true,
        fit: true,
        idField: 'id',
        treeField: 'menuName'
    });

    $("#editMenuModal").on("hide.bs.modal", function() {
        var validator =MenuModule.validator;
        if(validator){
            validator.resetForm();//重置验证
        }

        $(this).removeData("modal");
    });

    $("#disableMenuModal").on("hide.bs.modal", function() {
        if(MenuModule.validator){
            MenuModule.validator.resetForm();//重置验证
        }
        $('#disableMenuForm')[0].reset();
        $(this).removeData("modal");
    });

});

/**
 * 菜单模块
 */
var MenuModule = {

    submitEditMenu: function(){//提交编辑菜单
        var me = this;
        var url = window.basePath + "/menu/saveOrUpdateMenu";

        $.post(url, $('#editMenuForm').serialize(), function (data) {
            if (data && data.data) {
                $('#editMenuModal').modal("hide");
                $("#editMenuForm")[0].reset();
                me.refreshTree();
            } else {
                alert('保存失败');
            }
        });

        return false;
    },
    acceptEditMenu: function(){
        var me = this;
        this.validator = $('#editMenuForm').validate({
            submitHandler: function (form) {
                return me.submitEditMenu();
            },
            rules: {
                loginName: {
                    required: true,
                    email: true
                }
            }
        });
    },
    beforeEditMenu: function(flag,menuId){debugger;
        $('#pName').combotree('reset');
        $('#pName').combotree('reload', window.basePath + '/menu/loadComboTree');
        $('#status1').prop('checked', true);
        $('#id').val('');
        var pId = -1; //默认是root节点

        if(flag == 2) {//修改
            var pNode = $('#menu_table').treegrid('getParent', menuId);
            pId = pNode ? pNode.id : pId;

            var currNode = $('#menu_table').treegrid('find', menuId);
            for (var key in currNode) {
                if(key =='status')
                    continue;
                $('#editMenuForm #' + key).val(currNode[key]);
            }

            if(currNode.children && currNode.children.length > 0){
                $('#status2').prop('disabled', true);
            }
            if (currNode.status == 2) {
                $('#status2').prop('checked', true);
            }
        }

        $('#pId').val(pId);
        $('#pName').combotree('setValue', pId);
        $('#editMenuModal').modal('show');
    },
    refreshTree: function(){//reload树数据
        $('#menu_table').treegrid('reload');
    },
    beforeDisable: function (menuId,status) {
        var currentNode = $('#menu_table').treegrid('find', menuId);
        if(currentNode && currentNode.children && currentNode.children.length > 0){
            alert('当前菜单存在子菜单，无法禁用');
            return false;
        }

        $('#removeId').val(menuId);
        if(status == 2){
            $('#status2').prop('checked',true);
        }
        $('#disableMenuModal').modal({
            show: true
        });
    },
    getSelectedNode: function(node){
        $('#editMenuForm #pId').val(node.id);
    },
    submitDisableMenu: function(){//确定禁用
        var me = this;
        var menuId = $('#removeId').val();
        var url = window.basePath + '/menu/disableMenu/' + menuId;
        var status = $('#disableMenuForm :radio[name="status"]:checked').val();
        $.post(url,{status: status}, function (data) {
            if(data && data.data){
                $('#disableMenuModal').modal('hide');
                me.refreshTree();
            }
        });
    },
    fmtOperate: function (val, row, index) {debugger;
        var htmls = $('#operDiv').html();
        htmls = htmls.replace(/(#id#)/g, row.id);
        htmls = htmls.replaceAll(/(#status#)/,row.status);
        return htmls;
    },
};

MenuModule.validator = null; //表单验证对象