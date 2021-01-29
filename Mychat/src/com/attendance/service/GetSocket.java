package com.attendance.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.attendance.utils.ReadUtil;

public class GetSocket implements Runnable{
	
	private Map<String,Socket> mapsocket=new HashMap<String,Socket>();
	private ServerSocket ss;
	private boolean flag=true;
	
	public Map<String, Socket> getMapsocket() {
		return mapsocket;
	}

	public GetSocket(ServerSocket ss) {
		this.ss = ss;
	}

	@Override
	public void run() {
		boolean flag=true;
	    try {
		    while(flag) {
		    	Socket socket = ss.accept();
		    	String ip = socket.getInetAddress().getHostAddress();
                int port = socket.getPort();
                mapsocket.put(ip+":"+port+"",socket);
                System.out.println("获取"+ip+":"+port+"Socket成功");
                new Thread(new ReadUtil(mapsocket.get(ip+":"+port+""))).start();
		    }
	    } catch (IOException e) {
		    e.printStackTrace();
            System.out.println("获取Socket失败");
		    flag=false;
	}
	}

}
