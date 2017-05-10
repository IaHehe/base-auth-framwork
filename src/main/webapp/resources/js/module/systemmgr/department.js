/**
 *  部门管理
 */
var zTree;
var setting = {
    async: {
        enable: true,
        url: window.basePath + "/department/loadDeptTree",
        dataFilter: ajaxDataFilter
    },
    data: {
        simpleData: {enable: true}
    },
    callback: {
        onClick: loadUserDataFun
    }
};
function loadUserDataFun(event, treeId, treeNode){
    var pNode = treeNode.getParentNode();
    var deptId = treeNode.id;
    if(deptId == 1){
        return false;
    }

    $('#selectedTreeNodeId').val(deptId); //隐藏在页面的组id,以后直接取用
    $('#selectedTreeNodeName').val(treeNode.name);

    $('#deptId').val(deptId);
    $('#deptName').val(treeNode.name);

    if(pNode){
        $('#pId').val(pNode.id);
        $('#parentName').val(pNode.name);
    }
};
function ajaxDataFilter(treeId, parentNode, responseData) {
    var zNodes = [];
    if (!responseData) return null;
    if (responseData.data) {
        var list = responseData.data;
        for (var i = 0; i < list.length; i++) {
            var node = list[i];
            zNodes.push({"id": node.id, "pId": node.pId,"name": node.deptName,status: node.status,open:true});
        }
    }
    return zNodes;
};


var DepartmentModule = {
    checkPermission: function(){
        var m_deptId = $('#selectedTreeNodeId').val() || '';
        return SaleBase.checkOperateSaleAuth(m_deptId);
    },
    submitGroup: function (flag) {//提交保存
        if (!$('#parentName').val()){
            showTip('selectDeptTip');
            return false;
        }
        if (!$('#deptName').val()) {
            showTip('deptNameTip');
            return false;
        }
        var url = window.basePath + "/department/editDept/" + flag;
        $.post(url, $('#editDeptForm').serialize(), function (data) {
            if (data && data.data) {
                $('#editDeptForm')[0].reset();
                zTree.reAsyncChildNodes(null, "refresh"); //树reload
            }
        });
    },
    checkExistsUsers: function (deptId) {//当前组下是否存在用户
        var exists = true;
        var url = window.base + "/department/checkExistsUsers/" + deptId;
        $.ajax({
            url: url,
            async: false,
            success: function (data) {
                if (data && data.data) {
                    alert('该组下存在用户,无法删除');
                } else {
                    $('#removeGroupModal').modal('show');
                }
            }
        });
        return exists;
    },
    removeGroup: function () {
        var nodes = zTree.getSelectedNodes(true); //取点中的节点
        debugger;
        if (nodes && nodes.length > 0) {
            var node = nodes[0];
            if(node.id == 1){
                alert('总公司无法删除');
                return false;
            }
            if (node.isParent) {
                alert('当前组下还有子分组，无法删除');
                return false;
            } else {
                if(!this.checkPermission()){
                    alert('你没有权限操作');
                    return false;
                }
                this.checkExistsUsers(node.id);
            }
        }
    },
    submitRemoveGroup: function () {//确认删除组
        var deptId = zTree.getSelectedNodes(true)[0].id;
        var url = window.base + "/department/removeGroupById/" + deptId;
        $.get(url, {}, function (data) {
            if (data && data.data) {
                $('#removeGroupModal').modal("hide");
                zTree.reAsyncChildNodes(null, "refresh"); //树reload
            }
        });
    },
    initEditTree: function () {
        var me = this;
        var ext = {
            async: setting.async,
            data: setting.data,
            check: {
                enable: true,
                chkStyle: "radio",
                radioType: "all"
            },
            callback: {
                onClick: me.onClick,
                onCheck: me.onCheck
            }
        };
        $('#menuContent2').show();
        $.fn.zTree.init($("#editZtree"), ext);
        zTree = $.fn.zTree.getZTreeObj("deptTree");
    },
    onClick: function (e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("editZtree");
        zTree.checkNode(treeNode, !treeNode.checked, null, true);
        return false;
    },
    onCheck: function (e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("editZtree"),
            nodes = zTree.getCheckedNodes(true),
            v = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            v += nodes[i].name + ",";
        }
        if (v.length > 0) v = v.substring(0, v.length - 1);
        $("#parentName").val(v);
        $("#pId").val(nodes[0].id);
        hideTip('selectDeptTip'); //隐藏提示

        $('#menuContent2').hide();
    },
    showMenu: function () {
        this.initEditTree();

        var cityObj = $("#parentName");
        var cityOffset = $("#parentName").offset();
        $("#menuContent2").css({
            left: cityOffset.left + "px",
            top: cityOffset.top + cityObj.outerHeight() + "px"
        }).slideDown("fast");

        $("body").bind("mousedown", this.onBodyDown);
    },
    hideMenu: function () {
        $("#menuContent2").fadeOut("fast");
        $("body").unbind("mousedown", this.onBodyDown);
    },
    onBodyDown: function (event) {
        if (!(event.target.id == "menuBtn"
            || event.target.id == "parentName"
            || event.target.id == "menuContent2"
            || $(event.target).parents("#menuContent2").length > 0)) {
            $("#menuContent2").fadeOut("fast");
            $("body").unbind("mousedown", this.onBodyDown);
        }
    }
};

/**
 * 初始化加载数据
 */
$(document).ready(function () {
    $.fn.zTree.init($("#deptTree"), setting);
    zTree = $.fn.zTree.getZTreeObj("deptTree");
});