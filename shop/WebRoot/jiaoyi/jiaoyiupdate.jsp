<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<div class="pageContent">
	
	<form method="post" name=form1 action="method!jiaoyiupdate2" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
	
<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>物品名</label>
				 ${bean.product.name }
				 <input type="hidden" name="id"  value="${bean.id }">
				 
			</div>
			
			<div class="unit">
				<label>物品图片</label>
				 <img src="uploadfile/${bean.product.path }">
			</div>
			
			<div class="unit">
				<label>价格</label>
				  ${bean.product.price }
			</div>
			<div class="unit">
				<label>商品发布人</label>
				  ${bean.userto.username }
			</div>
			
			<div class="unit">
				<label>交易内容</label>
				 <textarea class="InputCss" rows="7" cols="50" readonly="readonly">${bean.content }</textarea>
			</div>
			
			
			<div class="unit">
				<label>新增交易内容</label>
				 <textarea class="InputCss" rows="7" cols="50" name="content" ></textarea>
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>