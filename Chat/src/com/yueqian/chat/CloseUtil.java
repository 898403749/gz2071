package com.yueqian.chat;

import java.io.Closeable;
/**
 * Closeable�ӿ��ּ̳�������ĸ��ӿ�AutoCloseable
 * 
 * ����close()�����ǹر��������ͷ�������ص��κη�����������ѱ��رգ���ô���ô˷���û��Ч����
 * 
 * ֻҪʵ����AutoCloseable��Closeable�ӿڵ����ӿڣ�������ʹ�øô������ʵ���쳣�������Դ�ر��쳣�׳�˳�� 
 *  
 * InputStream��OutputStream�඼ʵ���˸ýӿڡ�
 * @author user
 *
 */
public class CloseUtil {
	/**
	 *  * �ͷ���Դ 
	 */
	public static void close(Closeable... targets) {
		for (Closeable target : targets) {
			try {
				if (target != null) {
					target.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
