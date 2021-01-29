package com.yueqian.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * ʹ�ö��̷߳�װ�˿ͻ����ն�
 * 
 * 1.������Ϣ
 * 
 * 2.�ͷ���Դ
 * 
 * 3.��дrun
 * 
 * @author user
 *
 */
public class Receive implements Runnable{
	 private DataInputStream dis;
	     private Socket client;
	     private boolean isRunning;
	     
	     //�вι�����
	     public Receive(Socket client) {
	         this.isRunning = true;
	         this.client = client;
	         try {
	        	 //ͨ��socket����õ�������
	             dis = new DataInputStream(client.getInputStream());
	         } catch (IOException e) {
	             System.out.println("------Client Receive------");
	             this.release();//�ر���Դ
	         }
	     }
	     
	     //������Ϣ
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
	    	 
	    	 //������߾ͽ���
	         while(isRunning) {
	             String msg = this.receive();
	             if(!msg.equals("")) {//������յ�����Ϣ��Ϊ�վ�����������̨
	                 System.out.println(msg);
	             }
	         }
	     }
	     
	     //�ͷ���Դ
	     private void release() {
	         this.isRunning = false;
	         CloseUtil.close(dis,client);
	     }
}
