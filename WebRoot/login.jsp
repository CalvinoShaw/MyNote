<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html lang="en" class="ie6 ielt7 ielt8 ielt9"><![endif]--><!--[if IE 7 ]><html lang="en" class="ie7 ielt8 ielt9"><![endif]--><!--[if IE 8 ]><html lang="en" class="ie8 ielt9"><![endif]--><!--[if IE 9 ]><html lang="en" class="ie9"> <![endif]--><!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en"><!--<![endif]--> 
	<head>
		<meta charset="utf-8">
		<title>登录 - 沙漏 v1.0</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="css/site.css" rel="stylesheet">
		<link href="css/mycss.css" rel="stylesheet">
		<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
		<script type="text/javascript">
			function doCheck(){
				if(document.getElementById('username').value==''){
					var username_tip = document.getElementById('username_tip');
					username_tip.style.dsplay = "block";
					username_tip.innerHTML = '请输入用户名!';
					return false;
				}else if(document.getElementById('password').value==''){
					var password_tip = document.getElementById('password_tip');
					password_tip.style.dsplay = "block";
					password_tip.innerHTML = '请输入密码!';
					return false;
				}
			}
		</script>
	</head>
	<body>
		<div id="login-page" class="container">
			<h1 class="login_title_h1">沙漏 v1.0</h1>
			<form id="login-form" class="well" action="login_do.do?flag=login" method="POST" onSubmit="return doCheck()">
				<input id="username" type="text" name="username" placeholder="用户名" /><br />
				<label id="username_tip">aaaa</label>
				<input id="password" type="password" name="password" placeholder="密码" /><br />
				<label id="password_tip">bbbb</label>
				<label class="checkbox"> <input type="checkbox" /> 记住该帐号 </label>
				<button type="submit" class="btn btn-primary">登录</button>
				<button type="submit" class="btn">忘记密码</button>
			</form>	
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/site.js"></script>

	</body>
</html>