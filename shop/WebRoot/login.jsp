<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物品交换平台</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function aa(){

window.close();
}
</script>


<style type="text/css">
img, div { behavior: url(iepngfix.htc) }
</style>

</head>

<body>
<body id="login">
		<div id="wrappertop"></div>
			<div id="wrapper">
					<div id="content">
						<div id="header">
							<span style="color:black;font-size: 18px;font-weight: bold;">大学二手交换网</span>
						</div>
							
						<div id="darkbannerwrap">
						</div>
						<form action="method!login" method="post">
						<fieldset class="form">
							${errorMessage}
                        	<p>
								<label for="user_name">账号:</label>
								<input name="username" id="user_name" type="text">
							</p>
							<p>
								<label for="user_password">密码:</label>
								<input name="password" id="user_password" type="password">
							</p>
							<p>
								<label for="user_name">用户角色：</label>
								<select name="role" >
								
								<option value="0"><span style="font-size: 10px;">用户</span></option>
								<option value="1"><span style="font-size: 10px;">系统管理员</span></option>
								</select>
							</p>
							<button type="submit" class="positive" name="Submit">
								登录</button>
								
								<button type="button" class="positive"  onclick="aa()" >
								退出</button>
                            						</fieldset>
						
						
					</form></div>
				</div>   
  
<div id="wrapperbottom_branding">

<div id="wrapperbottom_branding_text"><a href="method!register" >用户注册</a></div>
<div id="wrapperbottom_branding_text"><a href="index" >返回首页</a></div>
</div>

</body>
</html>