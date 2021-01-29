package com.shen.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

public class CodeUtil {
	private static int width = 80;    //ͼƬ width
	private static int height = 34;   //ͼƬ height
	private static int codeCount = 4; //����ͼƬ����ʾ��֤��ĸ���
	
	private static int baseX = 12;
	private static int xx = 15;
	private static int fontSize = 22;
	private static int codeY = 24;
	private static char[] codeSequence = { 
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static Map<String,Object> generateCode() {
		
		BufferedImage buffImg = new BufferedImage( width, height, 
					BufferedImage.TYPE_INT_RGB ); 
		Graphics gd = buffImg.getGraphics();
		
		Random random = new Random();  //����һ���������������
		gd.setColor(Color.WHITE);      //��ͼ�����Ϊ��ɫ
		gd.fillRect(0, 0, width, height);
		
		//{ps} ��������, ����Ĵ�СӦ�ø���ͼƬ�ĸ߶�������
		Font font = new Font("Fixedsys", Font.BOLD, fontSize);
		gd.setFont(font); //{1}�������塣
		
		gd.setColor(Color.BLACK); //{2} ���߿�
		gd.drawRect(0, 0, width-1, height-1);
		
		//{} ������� 40  ��������, ʹͼ���е���֤�벻�ױ���������̽�⵽��
		gd.setColor( Color.GRAY );
		for ( int i = 0; i<30; i++ ) {
			int x = random.nextInt( width );
			int y = random.nextInt( height );
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}
		
		// randomCode ���ڱ��������������֤��, �Ա��û���¼�������֤��
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

        // �������  codeCount ���ֵ���֤�롣
        for ( int i=0; i<codeCount; i++ ) {
            // �õ������������֤�����֡�
            String code = String.valueOf( codeSequence[random.nextInt(36)] );
            // �����������ɫ������������ɫֵ, ���������ÿλ���ֵ���ɫֵ������ͬ��
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // �������������ɫ����֤����Ƶ�ͼ���С�
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i)*xx+baseX, codeY);
            randomCode.append(code);
        }
        
        Map<String,Object> map = new HashMap<String,Object>();       
        map.put("validate", randomCode.toString());  //{1} �����֤��
        map.put("codePic", buffImg);  //{2} ������ɵ���֤��   BufferedImage ����
        
        return map;
    }
   
}


//public static void main(String[] args) throws Exception {
//    OutputStream out = new FileOutputStream( "E:\\Apk3\\upload\\"+ 
//    			System.currentTimeMillis() +".jpg" );
//    Map<String,Object> map = CodeUtil.generateCodeAndPic();
//    ImageIO.write( (RenderedImage) map.get("codePic"), "jpeg", out );
//    System.out.println("��֤���ֵΪ: "+ map.get("code"));
//}
