package com.yueqian.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * * 使用多线程封装了客户发送端：
 *
 * 1.发送消息
 * 
 * 2.从控制台获取消息
 * 
 * 3.释放资源
 * 
 * 4.重写run
 * 
 * @author user
 *
 */
public class ChatClient implements Runnable {
	private BufferedReader console;//可以接收键盘的数据
	private DataOutputStream dos;//数据输出流
	private Socket client;//Socket客户端
	private boolean isRunning;//标志位,标记是否在线
	private String name;//用户名

	//有参构造器,有两个参数,Socket客户端,name,没个用户的名字
	public ChatClient(Socket client, String name) {
		//初始化数据
		isRunning = true;//什么是上线的状态
		this.client = client;
		this.name = name;
		console = new BufferedReader(new InputStreamReader(System.in));//初始化键盘输入数据的对象
		try {
			//初始化输出流:DataOutputStream处理流,client.getOutputStream()这个流是不能自己new出来的
			dos = new DataOutputStream(client.getOutputStream());
			// 发送名称,方法
			send(name);//就是在登录的时候,将名字发送给服务端
		} catch (IOException e) {
			System.out.println("------Client Send------");
			this.release();//如果在初始化数据是发生异常了,就释放资源
		}
	}

	@Override
	public void run() {
		//当用户下线的时候,结束循环
		while (isRunning) {
			String msg = this.getStringFromConsole();
			//如果输入的信息不为空就,发送信息
			if (!msg.equals("")) {
				this.send(msg);
			}
		}
	}

	/**
	 * 发送消息
	 */
	private void send(String msg) {
		try {
			dos.writeUTF(msg);//用UTF-8编码传输数据
			dos.flush();//刷新的操作
		} catch (IOException e) {
			System.out.println("------Client send------");
			release();//如果在发送过程中发生异常了,要关闭资源
		}
	}

	/**
	 * 从控制台(不是服务器)获取消息,也就是键盘输入的数据
	 */
	private String getStringFromConsole() {
		try {
			return console.readLine();//读取一整行信息
		} catch (IOException e) {
			System.out.println("------Client console------");
			release();	// 释放资源
		}
		return "";
	}

	// 释放资源
	private void release() {
		this.isRunning = false;
		CloseUtil.close(dos, client);
	}
}
