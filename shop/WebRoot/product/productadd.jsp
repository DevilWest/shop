<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<div class="pageContent">
	
	<form  method="post" action="method!productadd2" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this);">
	
<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>物品名</label>
				 <input type="text" name="name" size="30" class="required"/>
			</div>
			
			<div class="unit">
				<label>物品图片</label>
				 <input  name="uploadfile"  type="file"  class="required"/>
			</div>
			
			<div class="unit">
				<label>价格</label>
				  <input type="text" name="price" class="required"/>
			</div>
			
			<div class="unit">
				<label>物品简介</label>
				 <textarea class="InputCss" rows="7" cols="50" name="info"></textarea>
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