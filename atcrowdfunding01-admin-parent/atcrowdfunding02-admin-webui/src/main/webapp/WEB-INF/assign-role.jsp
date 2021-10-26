<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27/9/2021
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp"%>
<body>
<%@include file="/WEB-INF/include-nav.jsp"%>
<script type="text/javascript">
    $(function(){
       $("#toRightBtn").click(function(){
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");
       });

       $("#toLeftBtn").click(function (){
            $("select:eq(1)>option:selected").appendTo("select:eq(0)");
       });

       $("#submitBtn").click(function () {
            $("select:eq(1)>option").prop("selected","selected");
       });



    });


</script>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp"%>
    </div>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#">数据列表</a></li>
            <li class="active">分配角色</li>
        </ol>
        <div class="panel panel-default">
            <div class="panel-body">
                <form action="/assign/assignRole.html" method="post" role="form" class="form-inline">
                    <input type="hidden" name="adminId" value="${requestScope.adminId}">
                    <input type="hidden" name="pageNum" value="${requestScope.pageNum}">
                    <input type="hidden" name="keyword" value="${param.keyword}">
                    <div class="form-group">
                        <label for="exampleInputPassword1">未分配角色列表</label><br>
                        <select  class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                            <c:forEach items="${requestScope.unassignedRoles}" var="unassignedRole">
                                <option value="${unassignedRole.id}">${unassignedRole.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <ul>
                            <li id="toRightBtn"class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                            <br>
                            <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                        </ul>
                    </div>
                    <div class="form-group" style="margin-left:40px;">
                        <label for="exampleInputPassword1">已分配角色列表</label><br>
                        <select  name="roleIdList" class="form-control" multiple="multiple" size="10" style="width:100px;overflow-y:auto;">
                            <c:forEach items="${requestScope.assignedRoles}" var="assignedRole">
                                <option value="${assignedRole.id}">${assignedRole.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button id="submitBtn" type="submit" style="width:100px;margin-top: 20px;margin-left: 230px;" class="btn btn-sm btn-success btn-block">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>