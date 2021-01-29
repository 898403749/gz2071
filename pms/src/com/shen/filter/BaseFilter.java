package com.shen.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseFilter  implements Filter{

	@Override
	public void destroy() {}
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)arg0;
		HttpServletResponse resp=(HttpServletResponse)arg1;
		doFilter(req,resp,arg2);
	}
	
	public abstract void doFilter(HttpServletRequest req,HttpServletResponse resp,FilterChain chain) throws IOException, ServletException;
}
