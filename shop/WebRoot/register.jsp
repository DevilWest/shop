<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理平台</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />


<style type="text/css">
img, div { behavior: url(iepngfix.htc) }
</style>

<script language="javascript" type="text/javascript">

function checkregisterform()
{
	 
	 if (document.getElementById('usernameid').value=="")
	{
		alert("用户名不能为空");
		return false;
	}
	var valid=/^\w+$/;
	if(!valid.test(document.getElementById('usernameid').value)){
		alert("用户名必须是数字、字母或下划线");
		return false;
	}

	if (document.getElementById('passwordid').value=="")
	{
		alert("密码不能为空");
		return false;
	}
	if (document.getElementById('passwordid').value.length<6)
	{
		alert("密码长度必须大于6位");
		return false;
	}
	if (document.getElementById('password2id').value != document.getElementById('passwordid').value)
	{
		alert("确认密码与密码不一致");
		return false;
	}	 
	if (document.getElementById('truenameid').value=="")
	{
		alert("真实姓名不能为空");
		return false;
	}

	return true;
	
}
</script>


</head>

<body>
<body id="login">
		<div id="wrappertop"></div>
			<div id="wrapper">
					<div id="content">
						<div id="header">
							<h1><h2>用户注册</h2></h1>
						</div>
							
						<div id="darkbannerwrap">
						</div>
						<form action="method!register2" method="post"  onsubmit="return checkregisterform()">
						<fieldset class="form">
						
                        	<p>
								<label>用户名</label>
								 <input type="text" name="username" size="30" class="required"  id="usernameid"/>
							</p>
							<p>
								<label>密码</label>
				 <input type="password" name="password" size="30" class="required" id="passwordid"/>
							</p>
							<p>
								<label>密码确认</label>
				 <input type="password" name="password2" size="30" class="required" id="password2id"/>
							</p>
							<p>
								<label>真实姓名</label>
				 <input type="text" name="truename" size="30" class="required" id="truenameid"/>
							</p>
							
							
							<button type="submit" class="positive" name="Submit">
								注册</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href=".">返回登录页面</a>
                            </fieldset>
						
						
					</form></div>
				</div>   


</body>
</html>