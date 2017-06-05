<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<form id="pagerForm" method="post" action="${url }">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${ps}" />
</form>




<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		
			<li><a class="edit" href="method!product?id={sid_user}" target="dialog" mask="true"><span>浏览商品</span></a></li>

			<li><a class="delete" href="method!productdelete?id={sid_user}" target="ajaxTodo" title="确定要删除吗?" ><span>删除商品</span></a></li>
			
			<li><a class="edit" href="method!productupdate?id={sid_user}" target="dialog" mask="true"><span>修改商品</span></a></li>
			
			
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				
				<th width="100">商品名</th>
				<th width="100">价格</th>
				<th width="100">添加时间</th>
				<th width="100">审核状态</th>

			</tr>
		</thead>
		<tbody>

			<c:forEach items="${list}" var="bean"  >
			<tr target="sid_user" rel="${bean.id}">
				
				<td>
				${bean.name}
				</td>
				<td>
				${bean.price}
				</td>
				<td>
				${fn:substring(bean.createtime,0, 19)}
				</td>
				<td>
				<c:if test="${bean.productlock==0}">未审核</c:if>
				<c:if test="${bean.productlock==1}">审核通过</c:if>
				<c:if test="${bean.productlock==2}">审核未通过</c:if>
				</td>
				
				
				
			</tr>			
			</c:forEach>
			
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			
			<span>共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${ps}" pageNumShown="10" currentPage="${pn}"></div>

	</div>
</div>

  </body>
</html>
