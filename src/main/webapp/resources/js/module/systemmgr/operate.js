$(function () {
    OperModule.initTable();

    $("#editOperModal").on("hide.bs.modal", function() {
        if(validator){
            validator.resetForm();//重置验证
        }
        $('#editOperForm')[0].reset()
        $(this).removeData("bs.modal");
    });
});

/**
 * 角色管理模块
 */
var validator;
var OperModule = {
    initTable: function () {
        $('#oper_table').datagrid({
            collapsible: false,//是否可折叠的
            idField: 'id',
            url: window.basePath + '/operate/findOperateList',
            fitColumns: true,
            nowrap: true,
            fit: true,//自动大小
            rownumbers: true, //行号
            singleSelect: true //是否单选

        });
    },
    reloadTable: function(){//表格重新加载数据
        $('#oper_table').datagrid('reload');
    },
    submitEditOper: function(){//提交编辑菜单
        var me = this;
        var url = window.basePath + "/operate/editOperate";

        $.post(url, $('#editOperForm').serialize(), function (data) {
            if (data && data.data) {
                $('#editOperModal').modal("hide");
                $("#editOperForm")[0].reset();
                me.reloadTable();
            } else {
                alert('保存失败');
            }
        });
    },
    acceptEditOper: function(){
        var me = this;
        validator = $('#editOperForm').validate({
            submitHandler: function (form) {
                me.submitEditOper();
                return false;
            },
            rules: {
                operName: {
                    required: true,
                }
            }
        });
    },
    beforeEditOper:function(flag,id){
        $('#id').val('');
        $("#editOperForm")[0].reset();
        $('#operCodeDiv').show();

        if (flag == 2) {
            $('#operCodeDiv').hide();
            var saleOper = this.getSaleOperById(id);
            if (saleOper) {
                for(var key in saleOper){
                    $('#' +key).val(saleOper[key]);
                }
            }
        }

        $('#editOperModal').modal('show');
    },
    asyncLoadData:function(url,data){
        var result = null;
        $.ajax({
            url: url,
            async: false,
            data: data,
            success: function (data) {
                if (data && data.success) {
                    result = data.data;
                }
            }
        });
        return result;
    },
    getSaleOperById: function (id) {
        var url = this.getUrl("/operate/getOperate/" + id);
        return this.asyncLoadData(url,{});
    },
    fmtOperate: function (val, row, index) {
        var htmls = $('#operDiv').html();
        htmls = htmls.replace(/(#id#)/g,row.id);
        return htmls;
    },
    beforeDelete: function(operId){
        $('#removeId').val(operId);
        $('#deleteOperModal').modal('show');
    },
    deleteOper: function(){
        var me = this;
        var operId = $('#removeId').val();
        var url = this.getUrl('/operate/deleteOper/' + operId);

        $.get(url,{t: new Date().getTime()},function(data){
            if(data && data.data){
                $('#deleteOperModal').modal('hide');
                me.reloadTable();
            }
        });
    },
    getUrl: function(url){
        return window.basePath + url;
    },
    checkUniqueCode: function(val){
        if(!val){
            return false;
        }

        var url = this.getUrl("/operate/checkUniqueCode");
        return this.asyncLoadData(url,{operateCode: val});
    },
};