function getPageInfo (){
    var ajaxResult = $.ajax({
        "url": "role/page/info.json",
        "type": "post",
        "data": {
            "keyword": window.keyword,
            "pageNum": window.pageNum,
            "pageSize": window.pageSize
        },
        "dataType": "json",
        "async": false
    });

    var statusCode = ajaxResult.status;

    if(statusCode != 200){
        layer.msg("失败！状态码=" + statusCode + "错误信息=" + ajaxResult.statusText);
    }

 // 响应状态码为200，进入下面的代码
    // 通过responseJSON取得handler中的返回值
    var R = ajaxResult.responseJSON;

    // 从resultEntity取得result属性
    var result = R.result;

    // 判断result是否是FAILED
    if (result == "Fail") {
        // 显示失败的信息
        layer.msg(R.message);
        return null;
    }

    // result不是失败时，获取pageInfo
    var pageInfo = R.data;

    // 返回pageInfo
    return pageInfo;
}


function generatePage(){
    var pageInfo = getPageInfo();
    fillTableBody(pageInfo);
}

function fillTableBody(pageInfo){

    // 清除tbody中的旧内容
    $("#rolePageBody").empty();

    // 使无查询结果时，不显示导航条
    $("#Pagination").empty();

    // 判断pageInfo对象是否有效，无效则表示未查到数据
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0) {
        $("#rolePageBody").append("<tr><td colspan='4' align='center'>抱歉！没有查询到想要的数据</td></tr>");
        return;
    }

    // pageInfo有效，使用pageInfo的list填充tbody
    for (var i = 0; i < pageInfo.list.length; i++) {

        var role = pageInfo.list[i];
        var roleId = role.id;
        var roleName = role.name;
        var numberTd = "<td>"+(i+1)+"</td>";
        var checkboxTd = "<td><input type='checkbox' id='"+roleId+"' class='itemBox'/></td>";
        var roleNameTd = "<td>" + roleName + "</td>";

        var checkBtn = "<button type='button' id='"+roleId+"' class='btn btn-success btn-xs checkBtn'><i class=' glyphicon glyphicon-check'></i></button>"

        var pencilBtn = "<button type='button' id='"+roleId+"' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>"

        var removeBtn = "<button type='button' id='"+roleId+"' class='btn btn-danger btn-xs removeBtn'><i class=' glyphicon glyphicon-remove'></i></button>"

        // 拼接三个小按钮成一个td
        var buttonTd = "<td>"+checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";

        // 将所有的td拼接成tr
        var tr = "<tr>" + numberTd + checkboxTd + roleNameTd + buttonTd + "</tr>";

        // 将拼接后的结果，放入id=rolePageTBody
        $("#rolePageBody").append(tr);
    }

    // 调用generateNavigator()方法传入pageInfo，进行生成分页页码导航条
    generateNavigator(pageInfo);


}

function generateNavigator(pageInfo){

        var totalRecord = pageInfo.total

        var properties = {
            num_edge_entries: 3,
            num_display_entries: 5,
            callback: pageSelectCallback,
            items_per_page: pageInfo.pageSize,
            current_page: pageInfo.pageNum-1,
            prev_text: "prev",
            next_text: "next"
        };

        $("#Pagination").pagination(totalRecord, properties);

}

function pageSelectCallback(pageIndex, JQuery){
    window.pageNum = pageIndex+1;
    generatePage();
    return false;
}

function showConfirmModal(roleArray){
    $("#confirmModal").modal("show");

    $("#confirmList").empty();

    window.roleIds = [];

    for (var i = 0; i < roleArray.length; i++){
        var roleId = roleArray[i].id;
        window.roleIds.push(roleId);
        var roleName = roleArray[i].name;
        $("#confirmList").append(roleName+"<br/>");

    }

}

function generateAuthTree(){
    var ajaxResult = $.ajax({
        "url": "assign/get/tree.json",
        "type": "post",
        "dataType": "json",
        "async": false,
    });

    if (ajaxResult.status!=200){
        layer.msg("失败！状态码=" + statusCode + "错误信息=" + ajaxResult.statusText);
        return;
    }

    var R = ajaxResult.responseJSON;

    if (R.result == "Fail"){
       layer.msg("操作失败！"+R.message);
    }

    if (R.result == "Success"){

    var authList = R.data;

    var setting ={
        "data": {
            "simpleData":{
                "enable": true,
                "pIdKey": "categoryId"
            },
            "key": {
                "name": "title"
            }
        },
        "check": {
            "enable": true
        }
    };
 // 生成树形结构信息
    $.fn.zTree.init($("#authTreeDemo"), setting, authList);

     var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");

        //默认展开
     zTreeObj.expandAll(true);

 }


    ajaxResult = $.ajax({
        "url" : "assign/get/checked/auth/id.json",
        "type": "post",
        "dataType": "json",
        "async": false,
        "data": {
            "roleId": window.roleId
        }
    });

    if (ajaxResult.status!=200){
        layer.msg("失败！状态码=" + statusCode + "错误信息=" + ajaxResult.statusText);
        return;
    }

    R = ajaxResult.responseJSON;

    if(R.result == "Fail"){
        layer.msg("操作失败！"+R.message);
    }

    if (R.result == "Success"){
        var authIdArray = R.data;

        for (var i=0; i< authIdArray.length;i++){
            var authId = authIdArray[i];
            var treeNode = zTreeObj.getNodeByParam("id", authId);
            var checked = true;
            var checkTypeFlag = false;
            zTreeObj.checkNode(treeNode,checked,checkTypeFlag);

        }

    }
}
