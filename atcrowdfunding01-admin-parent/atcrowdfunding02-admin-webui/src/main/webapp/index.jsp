<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26/9/2021
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<script src="jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
<%--    ajax--%>


       $(function(){

            $("#btn1").click(function(){
                $.ajax({
                    "url": "send/array/one.html",
                    "type": "post",
                    "data": {
                        "array": [5,8,12]
                    },
                    "dataType": "text",
                    "success": function(response){
                        alert(response);
                    },
                    "error": function(response){
                        alert(response);
                    }
                });
            });


            $("#btn2").click(function(){

                var array=[5,8,12];
                var arrayStr = JSON.stringify(array);
                $.ajax({
                    "url": "send/array/two.html",
                    "type": "post",
                    "data": arrayStr,
                    "dataType": "text",
                    "contentType": "application/json;charset=UTF-8",
                    "success": function(response){
                        alert(response);
                    },
                    "error": function(response){
                        alert(response);
                    }
                });
            });

           $("#btn3").click(function(){
                 layer.msg("test layer");
            });
       });



</script>
<head>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
    <a href="test/ssm.html">test SSM ENVIROMENT</a>

    <button id="btn1">Send [5,8,12] One</button>


    <button id="btn2">Send [5,8,12] Two</button>

    <button id="btn3">test layer click</button>


</body>
</html>
