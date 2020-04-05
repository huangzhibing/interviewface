<%--
  Created by IntelliJ IDEA.
  User: huangzhibing
  Date: 2020/3/22
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
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
                        <h1 style="text-align: center;">个人中心</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form name="student" class="form-horizontal" role="form" action="/student/editStudentInfo" id="editfrom" method="post">
                        <input style="display: none;" name="studentId" value="${student.studentId}">
                        <div class="form-group">
                            <label for="studentName" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="studentName" id="studentName" value="${student.studentName}" readonly="readonly" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">学生姓名</label>
                            <div class="col-sm-10">
                                <input type="text" name="name" class="form-control" id="name" value="${student.name}" placeholder="请输入姓名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="schoolName" class="col-sm-2 control-label">所属学校</label>
                            <div class="col-sm-10">
                                <input type="text"  name="schoolName" class="form-control" id="schoolName" value="${student.schoolName}" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">邮箱</label>
                            <div class="col-sm-10">
                                <input type="text"  name="email" class="form-control" id="email" value="${student.email}" placeholder="请输入邮箱">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mobile" class="col-sm-2 control-label">手机号</label>
                            <div class="col-sm-10">
                                <input type="text"  name="mobile" class="form-control" id="mobile" value="${student.mobile}" placeholder="请输入手机号">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <button class="btn btn-default">重置</button>
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
<script>
    $("#nav li:nth-child(1)").addClass("active");
    if("${message}" != ""){
        alert("${message}");
    }
</script>
</html>

