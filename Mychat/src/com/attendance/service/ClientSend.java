package com.attendance.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.attendance.utils.CloseUtil;

public class ClientSend implements Runnable {
	
	Scanner sc=new Scanner(System.in);
	private Socket socket;
	private boolean flag=true;
	final String ENCODING = "UTF-8";
	
	public ClientSend(Socket socket) {
		this.socket = socket;
	}

	public void send() {
		OutputStream os;
		String str;
		try {
			os=this.socket.getOutputStream();
			System.out.println("���뷢�����ݣ�");
			str=sc.next();
			os.write(str.getBytes(ENCODING));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("����ʧ��");
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
