<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<div class="pageContent">
<form method="post" name=form1 action="method!useradd2" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent nowrap" layoutH="80">
	
							<dl>
							<dt >
							用户名
							</dt>
							<dd >
								<input class="required " type="text" name="username" size="20" maxlength="20" />
							</dd>
						</dl>
						
						<dl>
							<dt >
							真实姓名
							</dt>
							<dd >
								<input class="required " type="text" name="truename" size="20" maxlength="20" />
							
							</dd>
						</dl>
						
						<dl>
							<dt >
							
							</dt>
							<dd >
								<input class="required " type="text" name="truename" size="20" maxlength="20" />
							
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

