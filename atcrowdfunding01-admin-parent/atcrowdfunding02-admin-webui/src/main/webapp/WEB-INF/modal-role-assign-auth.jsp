<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/10/2021
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
html>
<head>
    <title>Title</title>
</head>
<body>
<div id="assignModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">尚筹网系统弹窗</h4>
            </div>
            <form>
                <div class="modal-body">
                    <ul id="authTreeDemo" class="ztree"></ul>
                </div>
                <div class="modal-footer">
                    <button id="assignBtn" type="button" class="btn btn-primary">好的，我设置好了！执行</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>