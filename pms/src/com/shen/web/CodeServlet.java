package com.shen.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shen.utils.CodeUtil;

@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String,Object> valiMap=CodeUtil.generateCode();
		String codeStr=(String)valiMap.get("validate");
		HttpSession session=req.getSession();
		session.setAttribute("codeStr", codeStr);
		System.out.println("{SERVER} ÑéÖ¤Âë£º"+codeStr);
		BufferedImage img=(BufferedImage)valiMap.get("codePic");
		resp.setHeader("Pragma","no-cache");
		resp.setHeader("Cache-Control","no-cache");
		resp.setDateHeader("Expires",-1);
		ImageIO.write(img,"jpeg",resp.getOutputStream());
	}

}
