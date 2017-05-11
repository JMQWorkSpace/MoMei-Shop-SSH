package com.momei.control;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.momei.domain.UserInfo;
import com.momei.service.BookService;
import com.momei.service.CommentService;
import com.momei.service.UserService;

public class CommentServlet extends HttpServlet {

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

		  request.setCharacterEncoding("UTF-8");
		  String flag=request.getParameter("flag");
		  
		  if(flag.equals("add_comment"))
		  {
			  int userId=Integer.parseInt(request.getParameter("userId"));
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  String content=request.getParameter("content");
			  
			  //��Ӷ�������
			  CommentService cs=CommentService.getInstance();
			  cs.add(userId,bookId, content);
			  //�����û�����(����100����)
			  UserService us=UserService.getInstance();
			  us.addScore(userId);
			  //�޸Ķ�������״̬Ϊ������
			  BookService bs=BookService.getInstance();
			  bs.up_comment(bookId);
			  
			  //�޸�session��user����
			  HttpSession session=request.getSession();
		         UserInfo user=(UserInfo) session.getAttribute("user");
		         user.setScore(user.getScore()+100);
		         session.setAttribute("user", user);
			  
			  response.setContentType("text/html;charset=utf-8");
			  
			  response.getWriter().print("���۳ɹ������100����!");
		  }
	}

}
