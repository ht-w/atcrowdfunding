<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27/9/2021
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp"%>

<link href="css/pagination.css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/role.js" charset="UTF-8"></script>
<script type="text/javascript">
    $(function (){
        window.pageNum = 1;
        window.pageSize = 5;
        window.keyword = "";

        generatePage();

        $("#searchBtn").click(function(){
           window.keyword =$("#keywordInput").val();
           generatePage();
        });


        $("#showAddModalBtn").click(function(){
           $("#addModal").modal("show");
        });


        $("#saveRoleBtn").click(function(){
             var roleName = $.trim($("#addModal [name=roleName]").val());

             $.ajax({
                "url": "role/save.json",
                "type": "post",
                "data": {
                    "name": roleName
                },
                "dataType": "json",
                "success": function(response){
                    if(response.result == "Success"){
                        layer.msg("操作成功");
                        window.pageNum=999;
                        generatePage();
                    }
                      if(response.result == "Fail"){
                        layer.msg("操作失败" + response.message);
                    }
                },
                "error": function(response){
                     layer.msg("statusCode="+response.status + " message="+response.statusText);
                }
             });

              // 关闭模态框
            $("#addModal").modal("hide");

              // 清理模态框文本框
            $("#addModal [name=roleName]").val("");
        });


        $("#rolePageBody").on("click",".pencilBtn",function () {
            $("#editModal").modal("show");
            var roleName = $(this).parent().prev().text();
            window.roleId = this.id;
            $("#editModal [name=roleName]").val(roleName);

        });

        $("#editRoleBtn").click(function(){
            var roleName = $("#editModal [name=roleName]").val();
            $.ajax({
              "url": "role/edit.json",
              "type": "post",
              "dataType": "json",
              "data": {
                 "id": window.roleId,
                 "name": roleName
              },
              "success": function(response){
                 if(response.result == "Success"){
                     layer.msg("操作成功");
                     generatePage();
                 }
                 if(response.result == "Fail"){
                     layer.msg("操作失败" + response.message);
                 }
              },
              "error": function(response){
                 layer.msg("statusCode="+response.status + " message="+response.statusText);
              }

            });

            $("#editModal").modal("hide");

        });


         $("#confirmRoleBtn").click(function(){
            var requestBody = JSON.stringify(window.roleIds);
            $.ajax({
              "url": "role/remove.json",
              "type": "post",
              "contentType": "application/json;charset=UTF-8",
              "data": requestBody,
              "dataType": "json",
              "success": function(response){
                 if(response.result == "Success"){
                     layer.msg("操作成功");
                     generatePage();
                 }
                 if(response.result == "Fail"){
                     layer.msg("操作失败" + response.message);
                 }
              },
              "error": function(response){
                 layer.msg("statusCode="+response.status + " message="+response.statusText);
              }

            });

            $("#confirmModal").modal("hide");


        });



        $("#rolePageBody").on("click",".removeBtn",function () {

            var roleArray = [{
                "id": this.id,
                "name": $(this).parent().prev().text()
            }]

            showConfirmModal(roleArray);
        });


<%--     select all box--%>

        $("#summaryBox").click(function(){
            var currentStatus = this.checked;
            $(".itemBox").prop("checked", currentStatus);


        });


<%--        reversely select all box--%>


        $("#rolePageBody").on("click",".itemBox",function () {

            var checkedBoxCount = $(".itemBox:checked").length;
            var totalBoxCount = $(".itemBox").length;
            $("#summaryBox").prop("checked", checkedBoxCount == totalBoxCount);
        });


        $("#batchRemoveBtn").click(function(){
            var roleArray=[];

            $(".itemBox:checked").each(function(){
                var id = this.id;
                var name = $(this).parent().next().text();

                roleArray.push({
                    "id": id,
                    "name": name
                });
            });

            if(roleArray.length==0){
              layer.msg("请至少选择一个来删除");
              return;
            }

            showConfirmModal(roleArray);


        });


         $("#rolePageBody").on("click",".checkBtn",function(){
              window.roleId = this.id;
              $("#assignModal").modal("show");
              generateAuthTree();
         });

         $("#assignBtn").click(function(){
            var authIdArray = [];

            var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");

            var checkedNodes = zTreeObj.getCheckedNodes();

            for (var i = 0; i < checkedNodes.length; i++){
                authIdArray.push(checkedNodes[i].id);
            }

            var requestBody = {
                "roleId": [window.roleId],
                "authIdList": authIdArray
            };

            requestBody = JSON.stringify(requestBody);

            $.ajax({
                "url": "assign/save/role/auth/relationship.json",
                "type": "post",
                "data": requestBody,
                "contentType": "application/json;charset=UTF-8",
                "dataType": "json",
                "success": function(response){
                    if(response.result == "Success"){
                     layer.msg("操作成功");
                    }
                    if(response.result == "Fail"){
                     layer.msg("操作失败" + response.message);
                    }
                 },
              "error": function(response){
                 layer.msg("statusCode="+response.status + " message="+response.statusText);
               }


            });

            $("#assignModal").modal("hide");
         });
    });
</script>
<body>

<%@include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp"%>
    </div>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
            </div>
            <div class="panel-body">
                <form class="form-inline" role="form" style="float:left;">
                    <div class="form-group has-feedback">
                        <div class="input-group">
                            <div class="input-group-addon">查询条件</div>
                            <input id="keywordInput" class="form-control has-success" type="text" placeholder="请输入查询条件">
                        </div>
                    </div>
                    <button id="searchBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                </form>
                <button type="button" id="batchRemoveBtn" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                <button type="button" id="showAddModalBtn" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i> 新增</button>
                <br>
                <hr style="clear:both;">
                <div class="table-responsive">
                    <table class="table  table-bordered">
                        <thead>
                        <tr>
                            <th width="30">#</th>
                            <th width="30"><input id="summaryBox" type="checkbox"></th>
                            <th>名称</th>
                            <th width="100">操作</th>
                        </tr>
                        </thead>
                        <tbody id="rolePageBody">
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="6" align="center">
                                <div id="Pagination" class="pagination"><!-- 这里显示分页导航条 --></div>
                            </td>
                        </tr>

                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/modal-role-add.jsp"%>
<%@ include file="/WEB-INF/modal-role-edit.jsp"%>
<%@ include file="/WEB-INF/modal-role-confirm.jsp"%>
<%@ include file="/WEB-INF/modal-role-assign-auth.jsp"%>


</body>
</html>