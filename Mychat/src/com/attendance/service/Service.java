package com.attendance.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

import com.attendance.service.ServiceSend;

public class Service {
	public static void main(String[] args) throws IOException {
		
		Map<String,Socket> mapsocket;
		ServerSocket ss=new ServerSocket(8888);
		GetSocket gs=new GetSocket(ss);
		
		new Thread(gs).start();
		
		mapsocket=gs.getMapsocket();
		
		new Thread(new ServiceSend(mapsocket)).start();
	}

}
