package com.momei.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	//���ɶ�̬��֤��
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//����ͼ��
		int width=95;
		int height=43;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		//����ͼ��
		Graphics g = image.getGraphics();
		
		//ȷ��������ɫ
		g.setColor(Color.gray);
		
		//������
		g.fillRect(0, 0, width, height);
		
		//�ھ�������С���Σ���С���������֤��
		g.setColor(Color.white);
		
		//������
		g.fillRect(1, 1, width-2, height-2);
		
		// ���ø�����
		Random random = new Random();
        //��ɫ��Ϊ��ɫ
		g.setColor(Color.blue);
		 for (int i = 0; i < 20; i++) {
		  int xs = random.nextInt(width);
		  int ys = random.nextInt(height);
		  int xe = xs + random.nextInt(width);
	      int ye = ys + random.nextInt(height);
		  g.drawLine(xs, ys, xe, ye);
		  }

		
		//����ַ�
		String data="abcdefghejklmnopqrstuvwxyzABCDEFGHEJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer buff = new StringBuffer();
		
		//�����ȡ�ַ�
		for (int i = 0; i<5; i++) {
			//��62���ַ������ȡ�ַ�
			int index = random.nextInt(62);
			//��ȡһ���ַ�
			String str = data.substring(index, index+1);
			
			if(index%2==0)
			{
			g.setColor(Color.green);
			}
			else if(index%3==0)
			{
				g.setColor(Color.yellow);	
			}
			else if(index%5==0)
			{
				g.setColor(Color.blue);	
			}else
			{
				g.setColor(Color.red);	
			}
			g.setFont(new Font("����",Font.BOLD,38));
			
			//���ַ�����ͼƬ��
			g.drawString(str, 18*i, 33);//10*i��Ϊ�˷�ֹ���ַ��ص���һ��
			
			buff.append(str);
			}
		
		//��StringBuffer�е���֤��ŵ�session���棬Ŀ������LoginServlet.Java��ȡ��֤��
		HttpSession session = request.getSession();
		session.setAttribute("number", buff.toString());
		
	
		//����ͼƬ�������
		response.setContentType("image/jpeg");
		ImageIO.write(image, "jpg", response.getOutputStream());
		
	}

}
