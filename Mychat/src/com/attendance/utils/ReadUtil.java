package com.attendance.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.attendance.utils.CloseUtil;

public class ReadUtil implements Runnable{
	
	private Socket socket;
	private boolean flag=true;
	final String ENCODING = "UTF-8";
	
	public ReadUtil(Socket socket) {
		this.socket = socket;
	}
	
	public void read() {
		InputStream is;
		byte[] b=new byte[1024];
		try {
			is=this.socket.getInputStream();
			int len=is.read(b);
			System.out.println(new String(b,0,len,ENCODING));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("∂¡»° ß∞‹");
			flag=false;
			CloseUtil.close(this.socket);
		}
	}

    @Override
	public void run() {
    	while(flag) {
			read();
		}
	}
	

}
