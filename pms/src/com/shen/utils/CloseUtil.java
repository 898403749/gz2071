package com.shen.utils;

import java.io.Closeable;

public class CloseUtil {
	
	//可用此方法关闭所有流，参数为用到的所有流，后创建的先放入
	public static void close(Closeable... ables) {       
		for (Closeable able:ables) {
			try {
				if (able!=null) {        //流不为空
					able.close();      //关闭该流
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
