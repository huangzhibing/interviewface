<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
	<head>
	<meta charset="UTF-8">
	<title>学生注册</title>
	<link rel="stylesheet" href="css/bootstrap.css" />
	<style type="text/css">
	          .error-msg{
              	  font-size: 1em;
              	  font-weight: bold;
              	  color: #f66;
              }
              body{
                  background-image: url("/assets/img/backgrounds/registe.jpg");
                  background-attachment:fixed;
                  background-repeat: no-repeat;
                  background-size: 100%,100%;
                  /*filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/assets/img/backgrounds/registe.jpg',sizingMethod='scale');*/
                  /*background-repeat: no-repeat;*/
                  /*background-positon: 100%, 100%;*/
              }
              .container{
                  padding-top: 12%;
              }
              span{
                  color: #f66;
              }
              label{
                  color: black;
              }
              #ps{
                  color: white;
              }
    </style>
    <script type="text/javascript">
              function isEmptyValue(event){
            	  var element = event.currentTarget;
	          	  var v = element.value.trim();
	          	  element.nextSibling.setAttribute("data-flag","true");
	          	  var msg = "请输入数据";
	    		  if(element.hasAttribute("data-msg")){
	    		  	  msg = element.getAttribute("data-msg");
	    		  }
	          	  if(v.length==0){
	          	  	  element.nextSibling.innerText = msg;
	          	  }else if(element==document.getElementById("email")){
		          		  if(element.value.indexOf("@")==-1){
		          			   element.nextSibling.innerText = "邮箱地址不正确";
				    	  }else{
			          	  	  element.nextSibling.innerText = "";
			          	  }
	          	  }else if(element==document.getElementById("number")){
	          		      var user=document.getElementById("number").value.trim();
			    	      if(user.length==10){
			    	          element.nextSibling.innerText = "";
			    	      }else{
			          	  	  element.nextSibling.innerText = "请输入正确的学号";
			          	  }
				  }else if(element==document.getElementById("password")){
					      var pwd=document.getElementById("password").value.trim();
		    	          if(pwd.length<4||pwd.length>20){
		    	              element.nextSibling.innerText = "密码长度在4-20个字节之间";
		    	          }else{
			          	  	  element.nextSibling.innerText = "";
			          	  }  
				  }else if(element==document.getElementById("password2")){
				      var pwd2=document.getElementById("password2").value.trim();
				      var pwd=document.getElementById("password").value.trim();
	    	          if(pwd==pwd2){
	    	        	  element.nextSibling.innerText = "";
	    	          }else{
	    	        	  element.nextSibling.innerText = "确认密码要跟密码一致";
		          	  }  
			      }else{
		          	   element.nextSibling.innerText = "";
	          	  }
	          }
              function registForm(){
          		  var flag = document.querySelectorAll("[data-flag='true']");
          		  var err = document.querySelectorAll(".error-msg");
          		  if(flag.length<err.length){
          		  	alert("请将表格填写完整！");
          		  	return false;
          		  }
          		  for(var i = 0;i < err.length;i++){
          		  	if(err[i].innerText.length>0){
          		  		alert("请将表格填写完整！");
          		  		return false;
          		  	}
          		  }
          		  /* writeData(); */
          		  /* genderValue(); */
		    	  alert("注册成功！");
          		  return true;
          	  }
              function resetForm(){
        	  	  var flag = document.querySelectorAll("[data-flag='true']");
        	  	  for(var i = 0;i < flag.length;i++){
        	  	  	flag[i].removeAttribute("data-flag");
        	  	  }
        	  }
	</script>
	</head>
	<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
     <label class="navbar-brand" style="font-size: 1.5em;height: 30px;">学生注册：</label>
    </nav>
	 <div class="container col-lg-9 col-lg-offset-3">
	    <form id="form" action="/register" method="post" class="form-horizontal">
            <input name="role" value="1" style="display: none;">
	         <div class="form-group" style="margin-bottom: 0em;">
	              <label class="col-lg-4 control-label"><span>*</span>学 号：</label>
	              <div class="col-lg-4">
	                  <input id="username" name="username" data-msg="请输入学号" class="form-control" placeholder="学号" style=""/><label class="error-msg"></label>
	              </div>
	         </div>
            <div class="form-group" style="margin-bottom: 0em;">
                <label class="col-lg-4 control-label"><span>*</span>所属高校：</label>
                <div class="col-lg-4">
                    <input id="schoolName" name="schoolName" data-msg="请输入所属高校" class="form-control" placeholder="所属高校" style=""/><label class="error-msg"></label>
                </div>
            </div>
	         <div class="form-group" style="margin-bottom: 0em;">
	              <label class="col-lg-4 control-label"><span>*</span>密&nbsp;码：</label>
	              <div class="col-lg-4">
	                  <input id="password" name="password" type="password" data-msg="请输入密码" class="form-control" placeholder="密码"/><label class="error-msg"></label>
	              </div>
	         </div>
	         <div class="form-group" style="margin-bottom: 0em;">
	              <label class="col-lg-4 control-label"><span>*</span>确认密码：</label>
	              <div class="col-lg-4">
	                  <input id="password2" type="password" data-msg="请输入确认密码" class="form-control" placeholder="再次输入" /><label class="error-msg"></label>
	              </div>
	         </div>
	         <div class="form-group" style="margin-bottom: 0em;">
	              <label class="col-lg-4 control-label"><span>*</span>姓&nbsp;名：</label>
	              <div class="col-lg-4">
	                  <input id="name" name="name" data-msg="请输入真实姓名" class="form-control" placeholder="真实姓名"/><label class="error-msg"></label>
	              </div>
	         </div>
	         <div class="form-group" style="margin-bottom: 0em;">
	              <label class="col-lg-4 control-label"><span>*</span>电子邮件：</label>
	              <div class="col-lg-4">
	                  <input id="email" name="email" data-msg="请输入邮箱" class="form-control" placeholder="邮箱"/><label class="error-msg"></label>
	              </div>
	         </div>
            <div class="form-group" style="margin-bottom: 0em;">
                <label class="col-lg-4 control-label"><span>*</span>手机号：</label>
                <div class="col-lg-4">
                    <input id="mobile" name="mobile" data-msg="请输入手机号" class="form-control" placeholder="手机号"/><label class="error-msg"></label>
                </div>
            </div>
	         <div class="form-group">
	             <div class="col-lg-offset-4 col-lg-4">
                       <h3 style="color: red;">${message}</h3>
	                   <button class="btn btn-primary">注册</button>
	                   <button class="btn btn-warning" type="reset">重置</button>
	             </div>
	         </div>
	    </form>
	  </div>
	  <script type="text/javascript">
          if(${message} !== ''){
              alert(${message});
          }
      var tags = document.querySelectorAll("input");
      for(var i = 0; i < tags.length; i++){
      	tags[i].oninput = isEmptyValue;
      	tags[i].onblur = isEmptyValue;
      }
      document.forms['form'].onsubmit = registForm;
      document.forms['form'].onreset = resetForm;
	  </script>
	  <script type="text/javascript" src="/js/jquery-2.2.0.js"></script>
	  <script type="text/javascript" src="/js/bootstrap.js"></script>
	</body>
</html>