<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<div class="pageContent">
<form method="post" name=form1 action="method!bangzhuupdate2" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent nowrap" layoutH="80">
	<input  type="hidden" name="id"  value="${bean.id }"/>
							<dl>
							<dt >
							标题：
							</dt>
							<dd >
								<input class="required " type="text" name="title" size="20" maxlength="20" value="${bean.title }"/>
							</dd>
						</dl>
						
						<dl>
							<dt >
							内容：
							</dt>
							<dd >
								<textarea rows="7" cols="50" name="content">${bean.content }</textarea>
							
							</dd>
						</dl>
						
					
					
				
						<div id="biaotou"></div>
	</div>
<div class="formBar">
      <ul>
            <li>
                 <div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div>
            </li>
            <li>
                  <div class="button"><div class="buttonContent"><button type="Button" class="close">取消</button></div></div>
            </li>
      </ul>
</div>
</form>
</div>

