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
	private static int width = 80;    //图片 width
	private static int height = 34;   //图片 height
	private static int codeCount = 4; //定义图片上显示验证码的个数
	
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
		
		Random random = new Random();  //创建一个随机数生成器类
		gd.setColor(Color.WHITE);      //将图像填充为白色
		gd.fillRect(0, 0, width, height);
		
		//{ps} 创建字体, 字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD, fontSize);
		gd.setFont(font); //{1}设置字体。
		
		gd.setColor(Color.BLACK); //{2} 画边框。
		gd.drawRect(0, 0, width-1, height-1);
		
		//{} 随机产生 40  条干扰线, 使图象中的认证码不易被其它程序探测到。
		gd.setColor( Color.GRAY );
		for ( int i = 0; i<30; i++ ) {
			int x = random.nextInt( width );
			int y = random.nextInt( height );
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}
		
		// randomCode 用于保存随机产生的验证码, 以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

        // 随机产生  codeCount 数字的验证码。
        for ( int i=0; i<codeCount; i++ ) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf( codeSequence[random.nextInt(36)] );
            // 产生随机的颜色分量来构造颜色值, 这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i)*xx+baseX, codeY);
            randomCode.append(code);
        }
        
        Map<String,Object> map = new HashMap<String,Object>();       
        map.put("validate", randomCode.toString());  //{1} 存放验证码
        map.put("codePic", buffImg);  //{2} 存放生成的验证码   BufferedImage 对象
        
        return map;
    }
   
}


//public static void main(String[] args) throws Exception {
//    OutputStream out = new FileOutputStream( "E:\\Apk3\\upload\\"+ 
//    			System.currentTimeMillis() +".jpg" );
//    Map<String,Object> map = CodeUtil.generateCodeAndPic();
//    ImageIO.write( (RenderedImage) map.get("codePic"), "jpeg", out );
//    System.out.println("验证码的值为: "+ map.get("code"));
//}
