<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<div class="pageContent">
	
	<form  method="post" action="method!productadd2" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this);">
	
<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>物品名</label>
				 ${bean.name }
			</div>
			
			<div class="unit">
				<label>物品图片</label>
				 <img src="uploadfile/${bean.path }">
			</div>
			
			<div class="unit">
				<label>价格</label>
				  ${bean.price }
			</div>
			
			<div class="unit">
				<label>物品简介</label>
				 <textarea class="InputCss" rows="7" cols="50" name="info" readonly="readonly">${bean.info }</textarea>
			</div>
			
			<div class="unit">
				<label>发布人</label>
				  ${bean.user.truename }
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">确定</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>