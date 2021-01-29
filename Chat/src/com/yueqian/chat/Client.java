package com.yueqian.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * * 在线聊天室：客户端
 * 
 * @author user
 *
 */
public class Client {
	public static void main(String[] args) throws Exception {
		System.out.println("------client------");
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入名称：");
		String name = sc.nextLine();
		// 建立连接：使用Socket创建客户端+服务器的地址和端口号
		//会自动和服务器取得连接
		Socket client = new Socket("localhost", 9999);//127.0.0.1
		// 客户端发送消息:开了一个线程,发送信息
		new Thread(new ChatClient(client, name)).start();
		// 获取消息,也开了一个线程,接收信息
		new Thread(new Receive(client)).start();
	}
}
