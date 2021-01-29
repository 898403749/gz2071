package com.attendance.utils;

import java.io.Closeable;

public class CloseUtil {
	
	public static void close(Closeable... ables) {
		for (Closeable able:ables) {
			try {
				if (able!=null) {
					able.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
