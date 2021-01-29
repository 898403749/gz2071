package com.shen.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, 
		HttpServletResponse resp) 
			throws ServletException, IOException {
		//{1} 获取客户端请求的 URI
		String uri = req.getRequestURI();
		int pos = uri.lastIndexOf("/");
		//{2} 拿到方法名。
		uri = uri.substring(pos+1, uri.length());
		String ret = null;
		try {
			ret = dispatcher( uri, req, resp );
			if( ret!=null ){
				parseResult( ret, req, resp );
			}else{
				resp.sendError(404, "找不到相关资源。");
			}
		} catch (NoSuchMethodException e) {
			resp.sendError(404, "找不到相关资源(Mapping): "+ uri);
		}
	}
	
	//{ps} 主要是解析返回值, 决定要调用哪一个响应方法
	//返回值的格式:
	//[ps] 动作 : 内容
	//[1] redirect:/Admin/delUser
	//[2] forward:/Admin/viewUser
	//[3] data:{username:andy,password:123}
	private void parseResult( String ret, 
		HttpServletRequest req, 
		HttpServletResponse resp ) throws IOException, ServletException
	{
		String reg = "([^:]+):(.+)";
		Pattern cmp = Pattern.compile( reg );
		Matcher mat = cmp.matcher( ret );
		if( !mat.matches() ){
			print("返回值格式有误。");
		}
		String action = mat.group(1);   //动作
		String content = mat.group(2);  //内容
		respDispatch(action, content, req, resp);
	}
	
	
	private void respDispatch( 
		String action,  String content, 
		HttpServletRequest req, HttpServletResponse resp) 
		throws IOException, ServletException
	{
		//{1} 获取父类的字节码对象。
		// 因为 UserController.class 中没有这些方法。
		Class clz = this.getClass().getSuperclass();
		Method mtd = null;
		try {
			//{2} 得到方法对象。
			mtd = clz.getDeclaredMethod(
						action, String.class, 
						HttpServletRequest.class, 
						HttpServletResponse.class );
			//{3} 这里要调用 父类的三个方法之一。
			//mtd.invoke( 主调对象, 传入三个参数值 );
			mtd.invoke( this, content, req, resp );
			
		} catch (NoSuchMethodException e) {
			print("{程序员先生} 你是不是写错返回值了。");
		} catch (IllegalAccessException |
			SecurityException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e2) {
			//{ps} 主要处理在内部转发时发生异常, 打印出错信息。
			Throwable cause = e2.getCause();
			cause.printStackTrace();
			resp.sendError(500, cause.toString());
		}
	}
	
	protected void redirect( String url, HttpServletRequest req, 
			HttpServletResponse resp )
		throws IOException, ServletException{
		//{1} 拿到项目根映射地址。
		String ctxPath = req.getContextPath();
		//{2} 执行请求重定向。
		resp.sendRedirect( ctxPath + url );
	}
	
	protected void forward( String uri, HttpServletRequest req, 
			HttpServletResponse resp )
		throws IOException, ServletException{
		//{1} 执行内部转发。
		req.getRequestDispatcher( uri )
		.forward( req, resp );
	}
	
	protected void data( String content, HttpServletRequest req, 
			HttpServletResponse resp )
		throws IOException, ServletException{
		//{1} 设置响应体正文内容格式。
		resp.setContentType("text/html;charset=UTF-8");
		//{2} 拿到输出器
		resp.getWriter().write( content );
	}

	private String dispatcher( 
		String method, HttpServletRequest req, 
		HttpServletResponse resp )
		throws NoSuchMethodException {
		//{1} 现在要拿到当前类: 
		//    如: UserController.class, AdminController..。
		//    这个方法可以拿当前类, new 谁则是谁 ?
		Class clazz = this.getClass();
		Method mtd = null;
		String ret = null;
		try {
			//{2} 通过类获取方法对象
			mtd = clazz.getDeclaredMethod(
				method, HttpServletRequest.class,
				HttpServletResponse.class );
			//{3} 调用 UserController 的某个方法。
			//mtd.invoke( 主调对象, 参数值列表 );
			ret = (String)mtd.invoke( this, req, resp );
		} catch (SecurityException | 
				IllegalAccessException |
				IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			//{ps} 这一行代码后还要变更。。。
		}
		return ret;
	}

	protected void print(Object obj) {
		System.out.println("{SYS} "+ obj);
	}

	@Override
	protected void doPost(HttpServletRequest req, 
		HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet( req, resp );
	}
}




