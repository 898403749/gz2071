package com.shen.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CreateUploadFolder implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context=arg0.getServletContext();
		String realPath=context.getRealPath("upload");
		File file=new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
	}
}
