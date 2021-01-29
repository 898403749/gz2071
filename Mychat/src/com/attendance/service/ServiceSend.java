package com.attendance.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

import com.attendance.utils.CloseUtil;

public class ServiceSend implements Runnable {
	
	Scanner sc=new Scanner(System.in);
	private Socket socket;
    private boolean flag=true;
	private Map<String,Socket> mapsocket;
	final String ENCODING = "UTF-8";
	
	public ServiceSend(Map<String,Socket> mapsocket) {
		this.mapsocket = mapsocket;
	}

    public void send() {
    	OutputStream os;
		String str;
    	System.out.println("输入发送内容");
		str=sc.next();
		String[] strs=str.split(",");
		socket=mapsocket.get(strs[0]);
		try {
			os=socket.getOutputStream();
			os.write((strs[1]).getBytes(ENCODING));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("发送失败");
			flag=false;
			CloseUtil.close(this.socket);
	    }
	}

	@Override
	public void run() {
		while(flag) {
			send();
		}
	}
	
}
