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
		//{1} ��ȡ�ͻ�������� URI
		String uri = req.getRequestURI();
		int pos = uri.lastIndexOf("/");
		//{2} �õ���������
		uri = uri.substring(pos+1, uri.length());
		String ret = null;
		try {
			ret = dispatcher( uri, req, resp );
			if( ret!=null ){
				parseResult( ret, req, resp );
			}else{
				resp.sendError(404, "�Ҳ��������Դ��");
			}
		} catch (NoSuchMethodException e) {
			resp.sendError(404, "�Ҳ��������Դ(Mapping): "+ uri);
		}
	}
	
	//{ps} ��Ҫ�ǽ�������ֵ, ����Ҫ������һ����Ӧ����
	//����ֵ�ĸ�ʽ:
	//[ps] ���� : ����
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
			print("����ֵ��ʽ����");
		}
		String action = mat.group(1);   //����
		String content = mat.group(2);  //����
		respDispatch(action, content, req, resp);
	}
	
	
	private void respDispatch( 
		String action,  String content, 
		HttpServletRequest req, HttpServletResponse resp) 
		throws IOException, ServletException
	{
		//{1} ��ȡ������ֽ������
		// ��Ϊ UserController.class ��û����Щ������
		Class clz = this.getClass().getSuperclass();
		Method mtd = null;
		try {
			//{2} �õ���������
			mtd = clz.getDeclaredMethod(
						action, String.class, 
						HttpServletRequest.class, 
						HttpServletResponse.class );
			//{3} ����Ҫ���� �������������֮һ��
			//mtd.invoke( ��������, ������������ֵ );
			mtd.invoke( this, content, req, resp );
			
		} catch (NoSuchMethodException e) {
			print("{����Ա����} ���ǲ���д����ֵ�ˡ�");
		} catch (IllegalAccessException |
			SecurityException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e2) {
			//{ps} ��Ҫ�������ڲ�ת��ʱ�����쳣, ��ӡ������Ϣ��
			Throwable cause = e2.getCause();
			cause.printStackTrace();
			resp.sendError(500, cause.toString());
		}
	}
	
	protected void redirect( String url, HttpServletRequest req, 
			HttpServletResponse resp )
		throws IOException, ServletException{
		//{1} �õ���Ŀ��ӳ���ַ��
		String ctxPath = req.getContextPath();
		//{2} ִ�������ض���
		resp.sendRedirect( ctxPath + url );
	}
	
	protected void forward( String uri, HttpServletRequest req, 
			HttpServletResponse resp )
		throws IOException, ServletException{
		//{1} ִ���ڲ�ת����
		req.getRequestDispatcher( uri )
		.forward( req, resp );
	}
	
	protected void data( String content, HttpServletRequest req, 
			HttpServletResponse resp )
		throws IOException, ServletException{
		//{1} ������Ӧ���������ݸ�ʽ��
		resp.setContentType("text/html;charset=UTF-8");
		//{2} �õ������
		resp.getWriter().write( content );
	}

	private String dispatcher( 
		String method, HttpServletRequest req, 
		HttpServletResponse resp )
		throws NoSuchMethodException {
		//{1} ����Ҫ�õ���ǰ��: 
		//    ��: UserController.class, AdminController..��
		//    ������������õ�ǰ��, new ˭����˭ ?
		Class clazz = this.getClass();
		Method mtd = null;
		String ret = null;
		try {
			//{2} ͨ�����ȡ��������
			mtd = clazz.getDeclaredMethod(
				method, HttpServletRequest.class,
				HttpServletResponse.class );
			//{3} ���� UserController ��ĳ��������
			//mtd.invoke( ��������, ����ֵ�б� );
			ret = (String)mtd.invoke( this, req, resp );
		} catch (SecurityException | 
				IllegalAccessException |
				IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			//{ps} ��һ�д����Ҫ���������
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




