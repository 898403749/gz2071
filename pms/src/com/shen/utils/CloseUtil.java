package com.shen.utils;

import java.io.Closeable;

public class CloseUtil {
	
	//���ô˷����ر�������������Ϊ�õ������������󴴽����ȷ���
	public static void close(Closeable... ables) {       
		for (Closeable able:ables) {
			try {
				if (able!=null) {        //����Ϊ��
					able.close();      //�رո���
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
