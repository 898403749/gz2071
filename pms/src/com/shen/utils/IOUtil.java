package com.shen.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {

	public byte[] input(String read_path) throws IOException {
		InputStream in = null;
		BufferedInputStream bin=null;
	    ByteArrayOutputStream out = null;
	    try {
	        in = new FileInputStream(new File(read_path));
	        bin= new BufferedInputStream(in);
	        out = new ByteArrayOutputStream();
			byte[] bytes = new byte[1024];
			int len = 0;
	        while((len = bin.read(bytes)) != -1){
	            out.write(bytes, 0, len);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        new CloseUtil().close(out,bin,in);
	    }
		return out.toByteArray();

	}
	
	public void output(String write_path,byte[] b) throws IOException {
		OutputStream os=new FileOutputStream(write_path);
		BufferedOutputStream bos=new BufferedOutputStream(os);
		bos.write(b);
		new CloseUtil().close(bos,os);
	}

}