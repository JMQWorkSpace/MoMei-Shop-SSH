package com.momei.control;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.momei.util.GetYZM;

public class EmailServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	       this.doPost(request, response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String toU=request.getParameter("Email");
		
		/*
		 * ��Ŀ�����䷢���ʼ�
		 */
		String uTopic="ī÷ע��";
		String ucontenet=GetYZM.Gy();//�������6λ����֤��
		HttpSession session = request.getSession();
		session.setAttribute("YZM", ucontenet);//��ʱ�洢
		
		//�Լ������䣬������
		String uname="18617245418";
		String password="momei2016";
		
		//��֯�ʼ�
		Properties props=new Properties();//������ȡ�����ļ�
		props.put("mail.transport.protocol", "smtp");//�ʼ�����Э��
		props.put("mail.host","smtp.163.com");//�ʼ���������Э�������
		props.put("mail.smtp.auth", "true");//���밲ȫ��֤
		
        Session session2=Session.getInstance(props);//����һ���ʼ������Ự
		
		//��֯�ʼ�
		MimeMessage msg=new MimeMessage(session2);
		
		//��֯�ʼ�����
		Address toAddress=new InternetAddress("18617245418@163.com","momei");
		
		try {
			msg.addRecipient(Message.RecipientType.TO, toAddress);
			msg.setFrom(toAddress);
			
			//���Ͷ���
			msg.addRecipients(Message.RecipientType.TO, toU);
			
			//��������
			msg.setSubject(uTopic);
			
			//��������
			msg.setText(ucontenet);
			
			//�����ʼ�
			msg.saveChanges();
			
			//ȡ�÷��͵Ĺ���
			Transport ts=session2.getTransport();
			ts.connect(uname,password);
			ts.sendMessage(msg, msg.getAllRecipients());
			
			//�ر�����
			ts.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().print("�ʼ����ͳɹ�,��ע�����!");
	}

}
