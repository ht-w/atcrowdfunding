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
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/menu.js" charset="UTF-8"></script>
<script type="text/javascript">

    $(function(){

        generateTree();

        $("#treeDemo").on("click",".addBtn",function () {
         window.pid = this.id;
         $("#menuAddModal").modal("show");
         return false;
        });


        $("#treeDemo").on("click",".removeBtn",function () {
         window.id = this.id;
         $("#menuConfirmModal").modal("show");
         var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
         var currentNode = zTreeObj.getNodeByParam("id", window.id);
         var name = currentNode.name;
         var icon = currentNode.icon;

             // 回显-向id=removeNodeSpan的span标签添加html语句（显示图标与节点名）
         $("#removeNodeSpan").html("[<i class='"+icon+"'>"+name+"]</i>");

        // 关闭默认跳转
        return false;

        });


        $("#treeDemo").on("click",".editBtn",function () {
         window.id = this.id;
         $("#menuEditModal").modal("show");
         // 要实现通过id拿到整个节点的信息，需要拿到zTreeObj
         var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
         var currentNode = zTreeObj.getNodeByParam("id", window.id);

         $("#menuEditModal [name=name]").val(currentNode.name);
         $("#menuEditModal [name=url]").val(currentNode.url);
         $("#menuEditModal [name=icon]").val([currentNode.icon]);

           return false;
        });



        $("#menuConfirmBtn").click(function(){
            $.ajax({
                "url": "menu/remove.json",
                "dataType": "json",
                "type": "post",
                "data": {
                    "id": window.id,
                },
                "success": function(response){
                     if(response.result == "Success"){
                         layer.msg("操作成功");
                         generateTree();

                     }
                    if(response.result == "Fail"){
                        layer.msg("操作失败" + response.message);
                     }
                 },
                "error": function(response){
                    layer.msg("statusCode="+response.status + " message="+response.statusText);
                 }

            });

            $("#menuConfirmModal").modal("hide");


        });



        $("#menuSaveBtn").click(function(){
            var name = $.trim($("#menuAddModal [name=name]").val());
            var url = $.trim($("#menuAddModal [name=url]").val());
            var icon = $("#menuAddModal [name=icon]:checked").val();
            $.ajax({
                "url": "menu/save.json",
                "dataType": "json",
                "type": "post",
                "data": {
                    "name": name,
                    "url": url,
                    "icon": icon,
                    "pid": window.pid
                },
                "success": function(response){
                     if(response.result == "Success"){
                         layer.msg("操作成功");
                         generateTree();

                     }
                    if(response.result == "Fail"){
                        layer.msg("操作失败" + response.message);
                     }
                 },
                "error": function(response){
                    layer.msg("statusCode="+response.status + " message="+response.statusText);
                 }

            });

            $("#menuAddModal").modal("hide");

            $("#menuResetBtn").click();

        });



        $("#menuEditBtn").click(function(){

            var name = $.trim($("#menuEditModal [name=name]").val());
            var url = $.trim($("#menuEditModal [name=url]").val());
            var icon = $("#menuEditModal [name=icon]:checked").val();
            $.ajax({
                "url": "menu/edit.json",
                "dataType": "json",
                "type": "post",
                "data": {
                    "name": name,
                    "url": url,
                    "icon": icon,
                    "id": window.id
                },
                "success": function(response){
                     if(response.result == "Success"){
                         layer.msg("操作成功");
                         generateTree();

                     }
                    if(response.result == "Fail"){
                        layer.msg("操作失败" + response.message);
                     }
                 },
                "error": function(response){
                    layer.msg("statusCode="+response.status + " message="+response.statusText);
                 }

            });

            $("#menuEditModal").modal("hide");

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
            <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限分配列表
                <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal">
                    <i class="glyphicon glyphicon-question-sign"></i>
                </div>
            </div>
            <div class="panel-body">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/modal-menu-add.jsp"%>
<%@include file="/WEB-INF/modal-menu-edit.jsp"%>
<%@ include file="/WEB-INF/modal-menu-remove.jsp"%>

</body>
</html>