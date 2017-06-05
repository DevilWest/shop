package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bangzhu;
import model.Jiaoyi;
import model.Product;
import model.User;
import model.Youjian;

import org.apache.struts2.ServletActionContext;

import util.Util;


import com.opensymphony.xwork2.ActionSupport;

import dao.BangzhuDao;
import dao.JiaoyiDao;
import dao.ProductDao;
import dao.UserDao;
import dao.YoujianDao;

public class ManageAction extends ActionSupport{
	
	
	private static final long serialVersionUID = -4304509122548259589L;
	
	private UserDao userDao;
	
	private ProductDao productDao;
	
	private JiaoyiDao jiaoyiDao;
	
	private String url = "./";
	
	
	

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	//跳转到首页
	public String index(){
		return "success1";
		
	}
	//跳转到登录页面
	public String login2(){
		this.setUrl("login.jsp");
		return SUCCESS;
		
	}
	
	//登录操作
	public String login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		User user = userDao.selectBean(" where username = '"+username 
				+"' and password= '"+password +"' and userlock=0 and role="+role);
		if (user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			this.setUrl("index");
			return "redirect";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者账号错误');window.location.href='index';</script>");
		}
		return null;
	}
	
	//退出操作
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
	//跳转到修改密码页面
	public String changepwd() {
		
		this.setUrl("user/user.jsp");
		return SUCCESS;
	}
	
	//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String password3 = request.getParameter("password3");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"'");
		
		if(!password2.equals(password3)){
			out.print(Util.tiaozhuan2("两次输入密码不一致", "index","userlist"));
			out.flush();
			out.close();
		}else if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			out.print(Util.tiaozhuan2("操作成功", "index","userlist"));
			out.flush();
			out.close();
		}else{
			out.print(Util.tiaozhuan2("原密码错误", "index","userlist"));
			out.flush();
			out.close();
		}
	}
	//跳转到注册页面
	public String register() {
		this.setUrl("register.jsp");
		return SUCCESS;
	}
//注册操作
	public void register2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String truename = request.getParameter("truename");
		User  bean = userDao.selectBean(" where username = '"+username+"'");
		if(bean==null){
			bean = new User();
			bean.setCreatetime(new Date());
			bean.setPassword(password);
			bean.setRole(0);
			bean.setTruename(truename);
			bean.setUsername(username);
			userDao.insertBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('注册成功');window.location.href='method!login2';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名已经存在，请换个用户名注册');window.location.href='method!login2';</script>");
		}
		

	}
	
	
	//商品列表
	public String productlist()  {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pageNum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String where =" where user.id="+u.getId()+" and deletestatus=0 order by id desc ";
		int total = productDao.selectBeanCount(" where user.id="+u.getId()+" and deletestatus=0 ");
		request.setAttribute("list", productDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!productlist");
		this.setUrl("product/productlist.jsp");
		return SUCCESS;
	}
	
	
	
	//跳转到添加商品页面
	public String productadd() {
		
		this.setUrl("product/productadd.jsp");
		return SUCCESS;
	}
	
	
	private File uploadfile;

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
//添加商品操作
	public void productadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String info = request.getParameter("info");
		String imgpath = this.uploadImg(uploadfile);
		Product bean = new Product();
		bean.setCreatetime(new Date());
		bean.setInfo(info);
		bean.setName(name);
		bean.setPath(imgpath);
		bean.setPrice(Double.parseDouble(price));
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		bean.setUser(u);
		productDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan("操作成功，等待管理员审核", "method!productlist","productlist"));
		out.flush();
		out.close();
	}

	//图片上传
	public  String  uploadImg(File uploadImg) throws IOException {
		String savaPath = ServletActionContext.getServletContext().getRealPath(
				"/")+ "/uploadfile/";

		String time = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS")
				.format(new Date()).toString();
		String imageFileName = time+".jpg";
		File imageFile = new File(savaPath + imageFileName);
		Util.copyFile(uploadImg, imageFile);
		return imageFileName;
	}
	
	//商品信息
	public String product() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			this.setUrl("product/login.jsp");
			return SUCCESS;
		}else{
			Product bean = productDao.selectBean(" where id= "+request.getParameter("id"));
			request.setAttribute("bean", bean);
			this.setUrl("product/product.jsp");
			return SUCCESS;
		}
		
		
	}
	//修改商品页面
	public String productupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("product/productupdate.jsp");
		return SUCCESS;
	}
	
	//修改商品操作
	public void productupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String info = request.getParameter("info");
		Product bean = productDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setCreatetime(new Date());
		bean.setInfo(info);
		bean.setName(name);
		if(uploadfile!=null){
			String imgpath = this.uploadImg(uploadfile);
			bean.setPath(imgpath);
		}
		bean.setPrice(Double.parseDouble(price));
		bean.setProductlock(0);
		productDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan("操作成功,等待管理员重新审核", "method!productlist","productlist"));
		out.flush();
		out.close();
	}
	
	//删除商品操作
	public void productdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "+request.getParameter("id"));
		productDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan2("操作成功", "method!productlist","productlist"));
		out.flush();
		out.close();
	}
	
	//商品列表2 管理员审核商品
	public String productlist2()  {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pageNum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}

		String where =" where  deletestatus=0 order by productlock ";
		int total = productDao.selectBeanCount(" where  deletestatus=0 ");
		request.setAttribute("list", productDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!productlist2");
		this.setUrl("product/productlist2.jsp");
		return SUCCESS;
	}
	//审核通过操作
	public void productupdate4() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "+request.getParameter("id"));
		if(bean.getProductlock()==0)
		bean.setProductlock(1);
		productDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan2("操作成功", "method!productlist2","productlist2"));
		out.flush();
		out.close();
	}
	//审核不通过操作
	public void productupdate5() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "+request.getParameter("id"));
		if(bean.getProductlock()==0)
		bean.setProductlock(2);
		productDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan2("操作成功", "method!productlist2","productlist2"));
		out.flush();
		out.close();
	}
	//商品列表3
	public String productlist3()  {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");
		if(name!=null&&!"".equals(name)){
			sb.append("name like '%"+name+"%'");
			sb.append(" and ");
			sb2.append("name like '%"+name+"%'");
			sb2.append(" and ");
			request.setAttribute("name", name);
		}


		
		sb.append(" productlock=1 and deletestatus=0 order by productlock");
		String where = sb.toString();
		
		sb2.append(" productlock=1 and deletestatus=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pageNum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}


		int total = productDao.selectBeanCount(where2);
		request.setAttribute("list", productDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!productlist3");
		this.setUrl("product/productlist3.jsp");
		return SUCCESS;
	}

	public JiaoyiDao getJiaoyiDao() {
		return jiaoyiDao;
	}

	public void setJiaoyiDao(JiaoyiDao jiaoyiDao) {
		this.jiaoyiDao = jiaoyiDao;
	}
	
	//发起交易页面
	public String jiaoyiadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("jiaoyi/jiaoyiadd.jsp");
		return SUCCESS;
	}
//发起交易操作
	public void jiaoyiadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String content = request.getParameter("content");
		String productid = request.getParameter("product");
		String usertoid = request.getParameter("userto");
		HttpSession session = request.getSession();
		User userfrom = (User)session.getAttribute("user");
		User userto = userDao.selectBean(" where id= "+usertoid);
		Product product = productDao.selectBean(" where id= "+productid);
		Jiaoyi  bean = jiaoyiDao.selectBean(" where  userfrom.id="+userfrom.getId()+ "  and status='正在交易' and userto.id="+userto.getId() +" and product.id="+product.getId());
		if(bean==null){
			bean = new Jiaoyi();
			if(bean.getContent()!=null){
				bean.setContent(bean.getContent()+userfrom.getUsername()+"说："+content+"\n");
			}else{
				bean.setContent(userfrom.getUsername()+"说："+content+"\n");
			}
			
			bean.setCreatetime(new Date());
			bean.setProduct(product);
			bean.setStatus("正在交易");
			bean.setUserfrom(userfrom);
			bean.setUserto(userto);
			jiaoyiDao.insertBean(bean);
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print(Util.tiaozhuan("操作成功", "method!jiaoyilist","jiaoyilist"));
			out.flush();
			out.close();
		}else{
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print(Util.tiaozhuan("操作失败，您已经对该商品发起交易，不能重复发起", "method!jiaoyilist","jiaoyilist"));
			out.flush();
			out.close();
		}
		
	}
//我发起的交易列表	
	public String jiaoyilist()  {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pageNum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String where =" where userfrom.id="+u.getId()+" and status='正在交易' order by id desc ";
		int total = jiaoyiDao.selectBeanCount(" where userfrom.id="+u.getId()+" and status='正在交易' ");
		request.setAttribute("list", jiaoyiDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!jiaoyilist");
		this.setUrl("jiaoyi/jiaoyilist.jsp");
		return SUCCESS;
	}
	//交易页面
	public String jiaoyiupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jiaoyi bean = jiaoyiDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("jiaoyi/jiaoyiupdate.jsp");
		return SUCCESS;
	}
	
	
	//交易操作
	public void jiaoyiupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String content = request.getParameter("content");
		
		Jiaoyi  bean = jiaoyiDao.selectBean(" where id="+request.getParameter("id"));
		bean.setContent(bean.getContent()+bean.getUserfrom().getUsername()+"说："+content+"\n");
		jiaoyiDao.updateBean(bean);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(Util.tiaozhuan("操作成功", "method!jiaoyilist","jiaoyilist"));
		out.flush();
		out.close();
	}
	//交易明细
	public String jiaoyiupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jiaoyi bean = jiaoyiDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("jiaoyi/jiaoyiupdate3.jsp");
		return SUCCESS;
	}
	
	//我收到的交易列表
	public String jiaoyilist2()  {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pageNum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String where =" where userto.id="+u.getId()+" and status='正在交易' order by id desc ";
		int total = jiaoyiDao.selectBeanCount(" where userto.id="+u.getId()+" and status='正在交易' ");
		request.setAttribute("list", jiaoyiDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!jiaoyilist2");
		this.setUrl("jiaoyi/jiaoyilist2.jsp");
		return SUCCESS;
	}
	
	//成功的交易
	public String jiaoyilist3()  {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pageNum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String where =" where (userto.id="+u.getId()+" or userfrom.id="+u.getId()+") and status='完成交易' order by id desc ";
		int total = jiaoyiDao.selectBeanCount(" where (userto.id="+u.getId()+" or userfrom.id="+u.getId()+") and status='完成交易' ");
		request.setAttribute("list", jiaoyiDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!jiaoyilist3");
		this.setUrl("jiaoyi/jiaoyilist3.jsp");
		return SUCCESS;
	}
	
	//终止的交易
	public String jiaoyilist4()  {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pageNum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String where =" where (userto.id="+u.getId()+" or userfrom.id="+u.getId()+") and status='终止交易' order by id desc ";
		int total = jiaoyiDao.selectBeanCount(" where (userto.id="+u.getId()+" or userfrom.id="+u.getId()+") and status='终止交易' ");
		request.setAttribute("list", jiaoyiDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!jiaoyilist4");
		this.setUrl("jiaoyi/jiaoyilist4.jsp");
		return SUCCESS;
	}
	
	//交易页面
	public String jiaoyiupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jiaoyi bean = jiaoyiDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("jiaoyi/jiaoyiupdate5.jsp");
		return SUCCESS;
	}
	
	
	//交易操作
	public void jiaoyiupdate6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String content = request.getParameter("content");
		
		Jiaoyi  bean = jiaoyiDao.selectBean(" where id="+request.getParameter("id"));
		bean.setContent(bean.getContent()+bean.getUserto().getUsername()+"说："+content+"\n");
		jiaoyiDao.updateBean(bean);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(Util.tiaozhuan("操作成功", "method!jiaoyilist2","jiaoyilist2"));
		out.flush();
		out.close();
	}
	
	
	//完成交易
	public void jiaoyidelete10() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jiaoyi  bean = jiaoyiDao.selectBean(" where id="+request.getParameter("id"));
		bean.setStatus("完成交易");
		jiaoyiDao.updateBean(bean);
		Product product = bean.getProduct();
		product.setDeletestatus(1);
		productDao.updateBean(product);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan2("操作成功", "method!jiaoyilist","jiaoyilist"));
		out.flush();
		out.close();
	}
	//终止交易
	public void jiaoyidelete11() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jiaoyi  bean = jiaoyiDao.selectBean(" where id="+request.getParameter("id"));
		bean.setStatus("终止交易");
		jiaoyiDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan2("操作成功", "method!jiaoyilist","jiaoyilist"));
		out.flush();
		out.close();
	}
	
	//完成交易
	public void jiaoyidelete12() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jiaoyi  bean = jiaoyiDao.selectBean(" where id="+request.getParameter("id"));
		bean.setStatus("完成交易");
		jiaoyiDao.updateBean(bean);
		Product product = bean.getProduct();
		product.setDeletestatus(1);
		productDao.updateBean(product);
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan2("操作成功", "method!jiaoyilist2","jiaoyilist2"));
		out.flush();
		out.close();
	}
	//终止交易
	public void jiaoyidelete13() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jiaoyi  bean = jiaoyiDao.selectBean(" where id="+request.getParameter("id"));
		bean.setStatus("终止交易");
		jiaoyiDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan2("操作成功", "method!jiaoyilist2","jiaoyilist2"));
		out.flush();
		out.close();
	}
	
	
	
	
	//用户列表
	public String userlist()  {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pageNum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		String where =" where role=0 and userlock=0 order by id desc ";
		int total = userDao.selectBeanCount(" where role=0 and userlock=0 ");
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!userlist");
		this.setUrl("user/userlist.jsp");
		return SUCCESS;
	}
	
	//删除用户
	public void userdelete() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setUserlock(1);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan2("操作成功", "method!userlist","userlist"));
		out.flush();
		out.close();
	}
	
	//更新用户页面
	public String userupdate() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("user/userupdate.jsp");
		return SUCCESS;
	}
	
	
	//更新用户操作
	public void userupdate2() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String truename = request.getParameter("truename");
		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setTruename(truename);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan("操作成功", "method!userlist","userlist"));
		out.flush();
		out.close();
	}
	
	
	private YoujianDao youjianDao;


	public YoujianDao getYoujianDao() {
		return youjianDao;
	}

	public void setYoujianDao(YoujianDao youjianDao) {
		this.youjianDao = youjianDao;
	}
	
	//发邮件页面
	public String youjianupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("uid", request.getParameter("id"));
		this.setUrl("youjian/youjianupdate.jsp");
		return SUCCESS;
	}
	
	
	//发邮件操作
	public void youjianupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String content = request.getParameter("content");
		String uid = request.getParameter("uid");
		Youjian bean = new Youjian();
		bean.setContent(content);
		bean.setCreatetime(new Date());
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		bean.setUserfrom(u);
		bean.setUserto(userDao.selectBean(" where id= "+uid));

		youjianDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan("操作成功", "method!userlist","userlist"));
		out.flush();
		out.close();
	}
	
	//邮件列表
	public String youjianlist()  {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pageNum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		
		String where =" where userto.id="+u.getId()+" order by id desc ";
		int total = youjianDao.selectBeanCount(" where userto.id="+u.getId());
		request.setAttribute("list", youjianDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!youjianlist");
		this.setUrl("youjian/youjianlist.jsp");
		return SUCCESS;
	}
	
	private BangzhuDao bangzhuDao;


	public BangzhuDao getBangzhuDao() {
		return bangzhuDao;
	}

	public void setBangzhuDao(BangzhuDao bangzhuDao) {
		this.bangzhuDao = bangzhuDao;
	}
	
	//帮助列表
	public String bangzhulist()  {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pageNum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		String where =" where 1=1 order by id desc ";
		int total = bangzhuDao.selectBeanCount(" where 1=1 ");
		request.setAttribute("list", bangzhuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!bangzhulist");
		this.setUrl("bangzhu/bangzhulist.jsp");
		return SUCCESS;
	}
	
	//删除帮助
	public void bangzhudelete() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Bangzhu bean = bangzhuDao.selectBean(" where id= "+request.getParameter("id"));

		bangzhuDao.deleteBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan2("操作成功", "method!bangzhulist","bangzhulist"));
		out.flush();
		out.close();
	}
	
	//更新帮助
	public String bangzhuupdate() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Bangzhu bean = bangzhuDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("bangzhu/bangzhuupdate.jsp");
		return SUCCESS;
	}
	//查看帮助
	public String bangzhuupdate3() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Bangzhu bean = bangzhuDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("bangzhu/bangzhuupdate3.jsp");
		return SUCCESS;
	}
	
	
	//更新帮助操作
	public void bangzhuupdate2() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Bangzhu bean = bangzhuDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setTitle(title);
		bean.setContent(content);
		bangzhuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan("操作成功", "method!bangzhulist","bangzhulist"));
		out.flush();
		out.close();
	}
	//添加帮助页面
	public String bangzhuadd() {

		this.setUrl("bangzhu/bangzhuadd.jsp");
		return SUCCESS;
	}
	
	
	//添加帮助操作
	public void bangzhuadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Bangzhu bean = new Bangzhu();
		bean.setTitle(title);
		bean.setContent(content);
		bean.setCreatetime(new Date());
		bangzhuDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Util.tiaozhuan("操作成功", "method!bangzhulist","bangzhulist"));
		out.flush();
		out.close();
	}
	
	//帮助列表
	public String bangzhulist2()  {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pageNum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pageNum"));
			pagesize = Integer.parseInt(request.getParameter("numPerPage"));
		}
		String where =" where 1=1 order by id desc ";
		int total = bangzhuDao.selectBeanCount(" where 1=1 ");
		request.setAttribute("list", bangzhuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", pagesize);
		request.setAttribute("pn", currentpage);
		request.setAttribute("url", "method!bangzhulist2");
		this.setUrl("bangzhu/bangzhulist2.jsp");
		return SUCCESS;
	}
	
}
