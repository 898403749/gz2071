package com.yueqian.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * * ���������ң��ͻ���
 * 
 * @author user
 *
 */
public class Client {
	public static void main(String[] args) throws Exception {
		System.out.println("------client------");
		Scanner sc = new Scanner(System.in);
		System.out.println("���������ƣ�");
		String name = sc.nextLine();
		// �������ӣ�ʹ��Socket�����ͻ���+�������ĵ�ַ�Ͷ˿ں�
		//���Զ��ͷ�����ȡ������
		Socket client = new Socket("localhost", 9999);//127.0.0.1
		// �ͻ��˷�����Ϣ:����һ���߳�,������Ϣ
		new Thread(new ChatClient(client, name)).start();
		// ��ȡ��Ϣ,Ҳ����һ���߳�,������Ϣ
		new Thread(new Receive(client)).start();
	}
}
