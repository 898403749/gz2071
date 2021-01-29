package com.yueqian.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ���������ң������
 * 
 * Ŀ�꣺��������ʵ��Ⱥ�ĺ�˽��
 * 
 * @author user
 *
 */
public class ChatService {

	/**
	 * ��д���룬дʱ���Ƴ�һ���µ����飬��ɲ��롢�޸Ļ����Ƴ������������鸳ֵ��array
	 * 
	 * ֪��Vector����ɾ�Ĳ鷽��������synchronized����֤ͬ��������ÿ������ִ�е�ʱ��Ҫȥ����������ܾͻ����½���
	 * ��CopyOnWriteArrayList ֻ������ɾ���ϼ��������Ƕ����������ڶ���������ܾͺ���Vector
	 * ��CopyOnWriteArrayList֧�ֶ���д�ٵĲ��������
	 */

	//Vector��ArrayList������,Vector�̰߳�ȫ��,ArrayList���̲߳���ȫ,�ײ�����ݽṹ��������
	//CopyOnWriteArrayList,Vector����ɾ�Ĳ����涼����ͬ����,ArrayList��û�м�,CopyOnWriteArrayList����ɾ���������ͬ����,��ʱ��ѯ����û�м�
	private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<>();

	public static void main(String[] args) throws IOException {
		System.out.println("------server------");
		// 1.ָ���˿ڣ�ʹ��ServerSocket����������
		ServerSocket server = new ServerSocket(9999);
		// 2.����ʽ�ȴ�����accept
		while (true) {
			Socket client = server.accept();
			Channel c = new Channel(client);
			all.add(c); // �������еĳ�Ա
			new Thread(c).start();
			System.out.println("һ���ͻ��˽���������,��ǰ��������:"+all.size());
		}
	}

	// һ���ͻ�����һ��Channel,�ͻ�����,���ʾ���:socket,����ÿһ���ͻ���
	static class Channel implements Runnable {
		private DataInputStream dis;
		private DataOutputStream dos;
		private Socket client;
		private boolean isRunning;
		private String name;

		//������
		public Channel(Socket client) {
			
			//���´���ֻ���û���¼��ɺ�ִ��
			isRunning = true;
			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				// ��ȡ����
				this.name = receive();
				// ��ӭ��ĵ���
				this.send("��ӭ����");//���Լ�����Ϣ
				this.sendOthers(this.name + "��������", true);//�������˷���Ϣ,���߱������ߵ��û�
			} catch (Exception e) {
				release();//�ͷ���Դ
			}
		}

		// ������Ϣ
		public String receive() {
			String msg = "";
			try {
				msg = dis.readUTF();
			} catch (IOException e) {
				System.out.println("-------Chat receive------");
				release();
			}
			return msg;
		}

		// ������Ϣ
		public void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				System.out.println("--------Chat send-------");
				release();
			}
		}

		/**
		 * Ⱥ�ģ���ȡ�Լ�����Ϣ������������ ˽�ģ�Լ�����ݸ�ʽ��@xxx:msg
		 * 
		 * @param msg
		 */
		public void sendOthers(String msg, boolean isSys) {
			boolean isPrivate = msg.startsWith("@");//��@��ͷʲô�п�����˽��
			if (isPrivate) { // ˽��
				int idx = msg.indexOf(":");//��ȡ����
				// ��ȡĿ�������
				String targetName = msg.substring(1, idx);//��ȡ������
				msg = msg.substring(idx + 1);
				//��ȡ�����ֺ�,Ҫ���Ž�ȡ��������ȥ��������ȥ����,���������˾͸�����˷�˽��
				for (Channel other : all) {
					if (other.name.equals(targetName)) { // Ŀ��
						other.send(this.name + "���ĵĶ���˵��" + msg);
						break;
					}
				}
			} else { // Ⱥ��
				for (Channel other : all) {
					if (this == other) { // �Լ�
						continue;
					}
					if (!isSys) {
						other.send(this.name + "��������˵��" + msg); // Ⱥ����Ϣ
					} else {
						other.send(msg); // ϵͳ��Ϣ
					}
				}
			}
		}

		// �ر���Դ
		public void release() {
			this.isRunning = false;
			CloseUtil.close(dis, dos, client);
			// �˳�
			all.remove(this);
			sendOthers(this.name + "�����ˣ�", true);
		}

		@Override
		public void run() {
			while (isRunning) {
				String msg = receive();
				if (!msg.equals("")) {
					// send(msg);
					sendOthers(msg, false);
				}
			}
		}
	}
}
