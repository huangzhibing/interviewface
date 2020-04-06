<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
    <title>修改招聘方信息</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-2.2.0.js"></script>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">修改招聘方信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/admin/editEmployer" id="editform" method="post">
                        <input style="display: none;" name="employerId" value="${employer.employerId}">
                        <div class="form-group ">
                            <label for="employerName" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="text" class="form-control" id="employerName"
                                       name="employerName" placeholder="请输入用户名"
                                <c:if test='${employer!=null}'>
                                       value="${employer.employerName}"
                                </c:if>>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">招聘方</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name" name="name"
                                       placeholder="请输入姓名" value="${employer.name}">
                            </div>
                        </div>
<%--                        <div class="form-group">--%>
<%--                            <label for="inputPassword3" class="col-sm-2 control-label">性别</label>--%>
<%--                            <div class="col-sm-10">--%>
<%--                                <label class="checkbox-inline">--%>
<%--                                    <input type="radio" name="sex" value="男" checked>男--%>
<%--                                </label>--%>
<%--                                <label class="checkbox-inline">--%>
<%--                                    <input type="radio" name="sex" value="女">女--%>
<%--                                </label>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        --%>
<%--                        <div class="form-group">--%>
<%--                            <label for="inputPassword3" class="col-sm-2 control-label" name="grade">所属院系</label>--%>
<%--                            <div class="col-sm-10">--%>
<%--                                <select class="form-control" name="collegeid" id="college">--%>
<%--                                    <c:forEach items="${collegeList}" var="item">--%>
<%--                                        <option value="${item.collegeid}">${item.collegename}</option>--%>
<%--                                    </c:forEach>--%>
<%--                                </select>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                        <div class="form-group">
                            <label for="status" class="col-sm-2 control-label">招聘方状态</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="status" name="status"
                                       placeholder="请输入招聘方状态" value="${employer.status}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">电子邮箱</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="email" name="email"
                                       placeholder="请输入电子邮箱" value="${employer.email}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">联系方式</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="mobile" name="mobile"
                                       placeholder="请输入联系方式" value="${employer.mobile}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">营业执照</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="picPath" name="picPath"
                                       placeholder="请输入营业执照" value="${employer.picPath}">
                            </div>
                        </div>

                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="button" id="button1">保存</button>
                            <button class="btn btn-default" type="button" id="button2">审核通过</button>
                            <button class="btn btn-default" type="button" id="button3">审核不通过</button>
<%--                            <button class="btn btn-default" type="reset">重置</button>--%>
                        </div>
                    </form>
                </div>

            </div>

        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(3)").addClass("active")

    <%--var collegeSelect = $("#college option");--%>
    <%--for (var i = 0; i < collegeSelect.length; i++) {--%>
    <%--    if (collegeSelect[i].value == '${employer.collegeid}') {--%>
    <%--        collegeSelect[i].selected = true;--%>
    <%--    }--%>
    <%--}--%>
    var id = ${employer.employerId};
    var formdata = $('form[id="editform"]').serialize();
    $('#button1').click(function () {
        $.ajax({
            url:'/admin/editEmployer?type=save',
            type:'POST',
            dataType:"json",
            data:formdata,
            success : function (data) {
                alert(data["message"]);
            }
        });
        location.href="/admin/editEmployer";
    });
    $('#button2').click(function () {
        $.ajax({
            url:'/admin/editEmployer?type=pass',
            type:'POST',
            dataType:"json",
            data:formdata,
            success : function (data) {
                alert(data["message"]);
            }
        });
        location.href="/admin/editEmployer";
    });
    $('#button3').click(function () {
        $.ajax({
            url:'/admin/editEmployer?type=noPass',
            type:'POST',
            dataType:"json",
            data:formdata,
            success : function (data) {
                alert(data["message"]);
            }
        });
        location.href="/admin/editEmployer";
    });
</script>
</html>