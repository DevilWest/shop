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

<!--  
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li>&nbsp;</li>
				<li>&nbsp;</li>
			</ul>
		</div>
	</div>
	</form>
</div>
-->
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
	
			<li><a class="add" href="method!bangzhuadd" target="dialog" mask="true"><span>添加帮助</span></a></li>

			<li><a class="delete" href="method!bangzhudelete?id={sid_user}" target="ajaxTodo" title="确定要删除吗?" ><span>删除帮助</span></a></li>
			<li><a class="edit" href="method!bangzhuupdate?id={sid_user}" target="dialog" mask="true"><span>修改帮助</span></a></li>
			<li><a class="edit" href="method!bangzhuupdate3?id={sid_user}" target="dialog" mask="true"><span>查看帮助</span></a></li>

			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="120">标题</th>

				<th width="100">添加时间</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${list}" var="bean"  >
			<tr target="sid_user" rel="${bean.id}">
				<td>
				${bean.title}
				</td>
				
				
				
				<td>
				${fn:substring(bean.createtime,0, 19)}
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
