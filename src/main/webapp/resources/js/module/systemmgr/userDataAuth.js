
var UserDataAuth = {
    getSelectedNode: function(){
        var node = null;
        var nodes = this.zTree.getSelectedNodes(true); //取点中的节点
        if (nodes && nodes.length > 0) {
            node = nodes[0];
        }
        return node;
    }
};

/*树*/
UserDataAuth.zTree=null;
UserDataAuth.setting = {
    async: {
        enable: true,
        url: window.basePath + "/userDataAuth/loadUserGroupTree/" +$('#selectedTreeNodeId').val(),
        dataFilter: function (treeId, parentNode, responseData) {
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
        }
    },
    data: {
        simpleData: {enable: true}
    },
    check: {
        enable: true,
        chkStyle: "checkbox",
        radioType: "all"
    },
    callback: {
        onAsyncSuccess: function (event, treeId, treeNode, msg) {
            var url = window.basePath + '/userDataAuth/findUserDataAuth/';
            url += $('#userId').val();

            var list = asyncAjax(url,{});
            for(var key in list){
                var tree = UserDataAuth.zTree;
                var node = tree.getNodeByParam("id",list[key].dataAuth,null);
                tree.checkNode(node, true, true);
                tree.updateNode(node);
            }
        }
    }
};

/**
 * 初始化加载数据
 */
$(document).ready(function () {
    $.fn.zTree.init($("#userDataAuthTree"), UserDataAuth.setting);
    UserDataAuth.zTree = $.fn.zTree.getZTreeObj("userDataAuthTree");
});