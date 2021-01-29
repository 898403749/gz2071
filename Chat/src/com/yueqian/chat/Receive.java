package com.yueqian.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 使用多线程封装了客户接收端
 * 
 * 1.接收消息
 * 
 * 2.释放资源
 * 
 * 3.重写run
 * 
 * @author user
 *
 */
public class Receive implements Runnable{
	 private DataInputStream dis;
	     private Socket client;
	     private boolean isRunning;
	     
	     //有参构造器
	     public Receive(Socket client) {
	         this.isRunning = true;
	         this.client = client;
	         try {
	        	 //通过socket对象得到输入流
	             dis = new DataInputStream(client.getInputStream());
	         } catch (IOException e) {
	             System.out.println("------Client Receive------");
	             this.release();//关闭资源
	         }
	     }
	     
	     //接收消息
	     public String receive() {
	         String msg = "";
	         try {
	             msg = dis.readUTF();
	         } catch (IOException e) {
	             System.out.println("-------Receive receive------");
	             release();
	         }
	         return msg;
	     }
	     
	     @Override
	     public void run() {
	    	 
	    	 //如果在线就接收
	         while(isRunning) {
	             String msg = this.receive();
	             if(!msg.equals("")) {//如果接收到想信息不为空就数出到控制台
	                 System.out.println(msg);
	             }
	         }
	     }
	     
	     //释放资源
	     private void release() {
	         this.isRunning = false;
	         CloseUtil.close(dis,client);
	     }
}
