package com.attendance.service;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.attendance.service.ClientSend;
import com.attendance.utils.ReadUtil;

public class Client {
	public static void main(String[] args) throws IOException {
		Socket socket=new Socket("127.0.0.1",8888);
		new Thread(new ReadUtil(socket)).start();
		new Thread(new ClientSend(socket)).start();
		
	}
	
}
