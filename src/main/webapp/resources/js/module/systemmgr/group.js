/**
 * 组管理
 */
var zTree;
var setting = {
    async: {
        enable: true,
        url: window.basePath + "/group/loadgroupTree",
        dataFilter: ajaxDataFilter
    },
    data: {
        simpleData: {enable: true}
    },
    callback: {
        onClick: clickFun
    }
};
function clickFun(event, treeId, treeNode){
    return false;

    var pNode = treeNode.getParentNode();
    if(!pNode){
        $('#editgroupForm')[0].reset();
        return false;
    }

    var groupId = treeNode.id;
    $('#selectedTreeNodeId').val(groupId); //隐藏在页面的组id,以后直接取用
    $('#selectedTreeNodeName').val(treeNode.name);

    $('#groupId').val(groupId);
    $('#groupName').val(treeNode.name);


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
            zNodes.push({"id": node.id, "pId": node.pId,"name": node.groupName,status: node.status,open:true});
        }
    }
    return zNodes;
};

/**
 * 左边分组树
 */
var GroupModule = {
    checkPermission: function(){
        var m_groupId = $('#selectedTreeNodeId').val() || '';
        return SaleBase.checkOperateSaleAuth(m_groupId);
    },
    beforeEdit: function (flag) {debugger;
        $('#editgroupForm')[0].reset();
        $('#way').val(flag);

        var node = this.getSelectedNode();
        if(!node){
            alert('请选择团队');
            return false;
        }
        var pNode = node.getParentNode();
        if(flag == 1){ //添加
            $('#pId').val(node.id);
            $('#groupName').val("");
            $('#parentName').val(node.name);
        }
        if(flag == 2){//修改
            if(!pNode){
                return false;
            }
            $('#groupId').val(node.id);
            $('#groupName').val(node.name);
            $('#parentName').val(pNode.name);
        }
    },
    getSelectedNode: function(){
        var node = null;
        var nodes = zTree.getSelectedNodes(true); //取点中的节点
        if (nodes && nodes.length > 0) {
            node = nodes[0];
        }
        return node;
    },
    submitGroup: function () {//提交保存
        if (!$('#parentName').val()){
            $('#parentName').next("span").removeClass("hidden");
            return false;
        }
        if (!$('#groupName').val()) {
            $('#groupName').next("span").removeClass("hidden");
            return false;
        }
        var flag = $('#way').val()
        var url = window.basePath + "/group/editGroup/" + flag;
        $.post(url, $('#editgroupForm').serialize(), function (data) {
            if (data && data.data) {
                $('#editgroupForm')[0].reset();
                zTree.reAsyncChildNodes(null, "refresh"); //树reload
            }
        });
    },
    beforeDelete: function(){
        var node = this.getSelectedNode();
        if(!node){
            alert('请选择团队');
            return false;
        }

        this.checkExistsUsers(node.id);
    },
    checkExistsUsers: function (groupId) {//当前组下是否存在用户
        debugger;
        var url = window.basePath + "/group/checkExistsUsers/" + groupId;
        var exists = asyncAjax(url,null);
        if (exists) {
            alert('该组下存在用户,无法删除');
        } else {
            $('#removeGroupModal').modal('show');
        }
    },
    removeGroup: function () {debugger;
        var node = this.getSelectedNode();
        if(!node){
            alert('请选择团队');
            return false;
        }

        if(node.id == 1){
            alert('总公司无法删除');
            return false;
        }
        if (node.isParent) {
            alert('当前组下还有子分组，无法删除');
            return false;
        } else {
            /*if(!this.checkPermission()){
                alert('你没有权限操作');
                return false;
            }*/
            this.checkExistsUsers(node.id);
        }
    },
    submitRemoveGroup: function () {//确认删除组
        var groupId = zTree.getSelectedNodes(true)[0].id;
        var url = window.basePath + "/group/removeGroupById/" + groupId;
        $.get(url, {}, function (data) {
            if (data && data.data) {
                $('#removeGroupModal').modal("hide");
                $('#editgroupForm')[0].reset();
                zTree.reAsyncChildNodes(null, "refresh"); //树reload
            }
        });
    }

};

/**
 * 初始化加载数据
 */
$(document).ready(function () {
    $.fn.zTree.init($("#groupTree"), setting);
    zTree = $.fn.zTree.getZTreeObj("groupTree");
});