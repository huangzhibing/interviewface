<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
					    	<h1 style="text-align: center;">添加招聘需求信息</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form class="form-horizontal" role="form" action="/school/addEmployment" id="editfrom" method="post">
							  <div class="form-group">
							    <label for="employerName" class="col-sm-2 control-label">招聘公司</label>
							    <div class="col-sm-10">
							      <input type="text" class="form-control" id="employerName" name="employerName" placeholder="请输入招聘公司名称">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="position" class="col-sm-2 control-label">招聘职位</label>
							    <div class="col-sm-10">
							      <input type="text" class="form-control" id="position" name="position" placeholder="请输入招聘职位">
							    </div>
							  </div>
                            <div class="form-group">
                                <label for="besinessDate" class="col-sm-2 control-label">工作时间</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="besinessDate" name="besinessDate" placeholder="请输入工作时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="workAddress" class="col-sm-2 control-label">工作地址</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="workAddress" name="workAddress" placeholder="请输入工作地址">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="price" class="col-sm-2 control-label">薪资</label>
                                <div class="col-sm-10">
                                    <input type="number" class="form-control" id="price" name="price" placeholder="请输入薪资">
                                </div>
                            </div>
							  <div class="form-group" style="text-align: center">
								<button class="btn btn-default" type="submit">提交</button>
								<button class="btn btn-default" type="reset">重置</button>
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
	</script>
</html>