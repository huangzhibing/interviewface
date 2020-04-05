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
	<style type="text/css">
	body{
	   background: url(images/a.jpg)repeat;
	}
	#login-box {
		/*border:1px solid #F00;*/
		padding: 35px;
		border-radius:15px;
		background: #56666B;
		color: #fff;
	}

	</style>
    <script type="text/javascript">
        if("${message}" != ""){
            alert("${message}");
        }
        function register() {
            var formdata = $('form[id="from1"]').serialize();

            // var value = $("input[type='radio']:checked").val()
            var role = $('input:radio[name="role"]:checked').val();
            // var val=$('input:radio[name="sex"]:checked').val();
            if(role == null){
                alert("请选择角色后注册！");
            }else {
                location.href="/showRegister?role="+role;
            }
        }
    </script>
</head>
<body>
	<div class="container" id="top">
		<div class="row" style="margin-top: 280px; ">
			<div class="col-md-4"></div>
			<div class="col-md-4" id="login-box">
				<form class="form-horizontal" role="form" action="/login" id="from1" method="post">
				  <div class="form-group">
				    <label for="firstname" class="col-sm-3 control-label">用户名</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control required" id="userID" placeholder="请输入用户名" name="username">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-3 control-label">密码</label>
				    <div class="col-sm-9">
				      <input type="password" class="form-control required" id="password" placeholder="请输入密码" name="password">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-12 col-sm-12">
				      <div class="checkbox">
						<label class="checkbox-inline">
							<input type="radio" name="role" value="1">学生
						</label>
						<label class="checkbox-inline">
							<input type="radio" name="role" value="2">学校
						</label>

                          <label class="checkbox-inline">
                              <input type="radio" name="role" value="3">招聘方
                          </label>
                          <label class="checkbox-inline">
                              <input type="radio" name="role" value="0">管理员
                          </label>
				      </div>
				    </div>
				  </div>
                    <div class="form-group pull-right" style="margin-right: 15px;">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default btn-info">登录</button>
                        </div>
                    </div>
                    <div class="form-group pull-right" style="margin-right: 15px;">
                        <div class="col-sm-8 col-sm-10">
                            <button type="button" onclick="register()" class="btn btn-default btn-info">注册</button>
                        </div>
                    </div>

				</form>
			</div>
			<div class="col-md-4"></div>
		</div>		
	</div>
</body>
</html>