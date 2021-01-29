package com.yueqian.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * * ʹ�ö��̷߳�װ�˿ͻ����Ͷˣ�
 *
 * 1.������Ϣ
 * 
 * 2.�ӿ���̨��ȡ��Ϣ
 * 
 * 3.�ͷ���Դ
 * 
 * 4.��дrun
 * 
 * @author user
 *
 */
public class ChatClient implements Runnable {
	private BufferedReader console;//���Խ��ռ��̵�����
	private DataOutputStream dos;//���������
	private Socket client;//Socket�ͻ���
	private boolean isRunning;//��־λ,����Ƿ�����
	private String name;//�û���

	//�вι�����,����������,Socket�ͻ���,name,û���û�������
	public ChatClient(Socket client, String name) {
		//��ʼ������
		isRunning = true;//ʲô�����ߵ�״̬
		this.client = client;
		this.name = name;
		console = new BufferedReader(new InputStreamReader(System.in));//��ʼ�������������ݵĶ���
		try {
			//��ʼ�������:DataOutputStream������,client.getOutputStream()������ǲ����Լ�new������
			dos = new DataOutputStream(client.getOutputStream());
			// ��������,����
			send(name);//�����ڵ�¼��ʱ��,�����ַ��͸������
		} catch (IOException e) {
			System.out.println("------Client Send------");
			this.release();//����ڳ�ʼ�������Ƿ����쳣��,���ͷ���Դ
		}
	}

	@Override
	public void run() {
		//���û����ߵ�ʱ��,����ѭ��
		while (isRunning) {
			String msg = this.getStringFromConsole();
			//����������Ϣ��Ϊ�վ�,������Ϣ
			if (!msg.equals("")) {
				this.send(msg);
			}
		}
	}

	/**
	 * ������Ϣ
	 */
	private void send(String msg) {
		try {
			dos.writeUTF(msg);//��UTF-8���봫������
			dos.flush();//ˢ�µĲ���
		} catch (IOException e) {
			System.out.println("------Client send------");
			release();//����ڷ��͹����з����쳣��,Ҫ�ر���Դ
		}
	}

	/**
	 * �ӿ���̨(���Ƿ�����)��ȡ��Ϣ,Ҳ���Ǽ������������
	 */
	private String getStringFromConsole() {
		try {
			return console.readLine();//��ȡһ������Ϣ
		} catch (IOException e) {
			System.out.println("------Client console------");
			release();	// �ͷ���Դ
		}
		return "";
	}

	// �ͷ���Դ
	private void release() {
		this.isRunning = false;
		CloseUtil.close(dos, client);
	}
}
