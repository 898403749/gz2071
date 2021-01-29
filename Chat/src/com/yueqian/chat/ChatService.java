package com.yueqian.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在线聊天室：服务端
 * 
 * 目标：加入容器实现群聊和私聊
 * 
 * @author user
 *
 */
public class ChatService {

	/**
	 * 读写分离，写时复制出一个新的数组，完成插入、修改或者移除操作后将新数组赋值给array
	 * 
	 * 知道Vector是增删改查方法都加了synchronized，保证同步，但是每个方法执行的时候都要去获得锁，性能就会大大下降，
	 * 而CopyOnWriteArrayList 只是在增删改上加锁，但是读不加锁，在读方面的性能就好于Vector
	 * ，CopyOnWriteArrayList支持读多写少的并发情况。
	 */

	//Vector和ArrayList的区别,Vector线程安全的,ArrayList是线程不安全,底层的数据结构都是数组
	//CopyOnWriteArrayList,Vector在增删改查上面都加了同步锁,ArrayList都没有加,CopyOnWriteArrayList在增删改上面加了同步锁,当时查询上面没有加
	private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<>();

	public static void main(String[] args) throws IOException {
		System.out.println("------server------");
		// 1.指定端口：使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(9999);
		// 2.阻塞式等待连接accept
		while (true) {
			Socket client = server.accept();
			Channel c = new Channel(client);
			all.add(c); // 管理所有的成员
			new Thread(c).start();
			System.out.println("一个客户端建立了连接,当前在线人数:"+all.size());
		}
	}

	// 一个客户代表一个Channel,客户端类,本质就是:socket,就是每一个客户端
	static class Channel implements Runnable {
		private DataInputStream dis;
		private DataOutputStream dos;
		private Socket client;
		private boolean isRunning;
		private String name;

		//构造器
		public Channel(Socket client) {
			
			//以下代码只在用户登录完成后执行
			isRunning = true;
			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				// 获取名称
				this.name = receive();
				// 欢迎你的到来
				this.send("欢迎上线");//给自己发信息
				this.sendOthers(this.name + "上线啦！", true);//给其他人发信息,告诉别人上线的用户
			} catch (Exception e) {
				release();//释放资源
			}
		}

		// 接收消息
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

		// 发送消息
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
		 * 群聊：获取自己的消息，发给其他人 私聊：约定数据格式：@xxx:msg
		 * 
		 * @param msg
		 */
		public void sendOthers(String msg, boolean isSys) {
			boolean isPrivate = msg.startsWith("@");//以@开头什么有可能是私聊
			if (isPrivate) { // 私聊
				int idx = msg.indexOf(":");//截取名字
				// 获取目标和数据
				String targetName = msg.substring(1, idx);//截取到名字
				msg = msg.substring(idx + 1);
				//截取到名字后,要拿着截取到的名字去集合里面去查找,如果有这个人就给这个人发私信
				for (Channel other : all) {
					if (other.name.equals(targetName)) { // 目标
						other.send(this.name + "悄悄的对你说：" + msg);
						break;
					}
				}
			} else { // 群聊
				for (Channel other : all) {
					if (this == other) { // 自己
						continue;
					}
					if (!isSys) {
						other.send(this.name + "对所有人说：" + msg); // 群聊消息
					} else {
						other.send(msg); // 系统消息
					}
				}
			}
		}

		// 关闭资源
		public void release() {
			this.isRunning = false;
			CloseUtil.close(dis, dos, client);
			// 退出
			all.remove(this);
			sendOthers(this.name + "下线了！", true);
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
