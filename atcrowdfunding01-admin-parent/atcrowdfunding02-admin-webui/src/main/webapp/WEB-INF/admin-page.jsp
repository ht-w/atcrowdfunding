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
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
    $(function(){
        initPagination();


          $("a#delete").click(function(){
            return confirm("你确定要删除" + $(this).parent().parent().find("td:nth-child(3)").text()+"这个用户吗?");
        });
    });






    function initPagination(){
        var totalRecord = ${requestScope.pageInfo.total};

        var properties = {
            num_edge_entries: 3,
            num_display_entries: 5,
            callback: pageSelectCallback,
            items_per_page: ${requestScope.pageInfo.pageSize},
            current_page: ${requestScope.pageInfo.pageNum-1},
            prev_text: "prev",
            next_text: "next"
        };

        $("#Pagination").pagination(totalRecord, properties);

    }


    function pageSelectCallback(pageIndex, jQuery){
        var pageNum = pageIndex+1;
        window.location.href="admin/get/page.html?pageNum="+pageNum+"&keyword=${param.keyword}";
        return false;
    }


</script>
<body>

<%@include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form  action="admin/get/page.html"  method="post" class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="keyword" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='admin/add/page.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty requestScope.pageInfo.list}">
                                   <tr>
                                       <td colspan="6" align="center">抱歉！没有查询到你要的数据！</td>
                                   </tr>
                                </c:if>


                                <c:if test="${!empty requestScope.pageInfo.list}">
                                    <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td><input type="checkbox"></td>
                                        <td>${admin.login}</td>
                                        <td>${admin.userName}</td>
                                        <td>${admin.email}</td>
                                        <td>
                                            <a href="assign/page/${admin.id}/${requestScope.pageInfo.pageNum}/${param.keyword}.html" class="btn btn-success btn-xs"><i class="glyphicon glyphicon-check"></i></a>
                                            <a href="admin/update/${admin.id}/${requestScope.pageInfo.pageNum}/${param.keyword}.html" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></a>
                                            <a id="delete" href="admin/remove/${admin.id}/${requestScope.pageInfo.pageNum}/${param.keyword}.html" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></a>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </c:if>

                            </tbody>
                            <tfoot>
                            <tr>
                                 <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
