package com.momei.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.momei.domain.Book;
import com.momei.domain.BookSeqence;
import com.momei.domain.ShoppingCar_seq;
import com.momei.domain.UserInfo;
import com.momei.service.BookService;
import com.momei.service.GoodService;
import com.momei.service.SeqService;

public class BookServlet extends HttpServlet {

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
		  
		  if(flag.equals("add"))
		  {
			  //��ȡsession�еĹ��ﳵ���Ϻ��û���Ϣ
			  HttpSession session=request.getSession();
			  UserInfo user=(UserInfo) session.getAttribute("user");
			  
			  if(user==null)
			  {
				  //�Ƿ�����,�˻ص�½ҳ��
				  //response.sendRedirect("UserInfo/login.jsp");
				  request.setAttribute("login_flag","check_pay");
			      request.getRequestDispatcher("UserInfo/login.jsp").forward(request,response);
				  
			  }else
			  {
			  
			  ArrayList<ShoppingCar_seq> car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
			  
			  request.setAttribute("car_list", car_list);
			  request.getRequestDispatcher("UserInfo/book.jsp").forward(request, response);
			  }
		  }else if(flag.equals("add_data"))
		  {

			  int userId=Integer.parseInt(request.getParameter("userId"));
			  String message=request.getParameter("message");
			  String phoneNum=request.getParameter("phoneNum");
			  String address=request.getParameter("address");
			  String send_time=request.getParameter("send_time");
			  String pay_way=request.getParameter("send_way_hdfk");
			  
			   //��ȡ��ǰϵͳʱ��
		      Calendar cl=Calendar.getInstance();
		      
		      //��session��ȡ�����ﳵ�����м���
		      HttpSession session=request.getSession();
		      ArrayList<ShoppingCar_seq> car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
		      
		      //����ͳ��count��pay
		      int count=0;
		      double pay=0;
		      for(int i=0;i<car_list.size();i++)
		      {
		    	  ShoppingCar_seq sce=car_list.get(i);
		    	  count+=sce.getCount();
		    	  pay+=sce.getPrice()*sce.getCount();
		      }
		      
		     /*
		  	 * 1: ��ѯ����Id�ŵ����ֵ
		  	 * 2����Ӷ���
		  	 * 3�����ݶ�������Ӷ�������
		  	 */
		      
		      synchronized(this)
		      {
			      BookService bs=BookService.getInstance();
			      
			      //��ѯ���������ֵ
			      int max_bookId=bs.find_Max_bookId();
			      
			      int bookId=max_bookId+1;
				  //��Ӷ���
			      bs.addBook(bookId,userId, message, send_time, pay_way, phoneNum, address, cl,count,pay);
			      
			      //������Ӷ�������
			      SeqService se=SeqService.getInstance();
			      for(int i=0;i<car_list.size();i++)
			      {
			    	  ShoppingCar_seq sce=car_list.get(i);
			    	  se.addSeq(sce.getGoodsId(), bookId, sce.getCount());
			      }
		      }
		     
		      //�������ύ�ɹ�����չ��ﳵ
		      session.removeAttribute("car_list");
		      
		      request.setAttribute("Msg","�����ѳɹ��ύ,�����ĵȴ����ĸɹ�!");
		      
		      request.getRequestDispatcher("UserInfo/Main.jsp").forward(request, response);
		  }else if(flag.equals("check_new"))
		  {
			  //��ҳ��ȡ�¶���(���¶�ʱ�併��)
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  BookService bs=BookService.getInstance();
				int res[]=bs.getPageCountAndRowCount();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllNewBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_new.jsp").forward(request, response);
			  
		  }else if(flag.equals("recieve"))
		  {
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  //�ѽӵ�
			  BookService bs=BookService.getInstance();
			  bs.upbook_recieve(bookId);
			  
				int res[]=bs.getPageCountAndRowCount();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllNewBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_new.jsp").forward(request, response);
			  
		  }else if(flag.equals("check_recieve"))
		  {
			//��ҳ��ȡ�ѽӶ���(���¶�ʱ�併��)
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  BookService bs=BookService.getInstance();
				int res[]=bs.getPageCountAndRowCount_recieve();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllRecieveBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_recieve.jsp").forward(request, response);
		  }else if(flag.equals("showSeq_msg"))
		  {
			  response.setContentType("text/html;charset=utf-8");
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  //��ȡ��������
			  SeqService ss=SeqService.getInstance();
			  List<BookSeqence> list=ss.getSeq(bookId);
			  
			  //��json����б����ݴ��ؿͻ���
				JSONArray jsonobj=JSONArray.fromObject(list);
				
				response.getWriter().print(jsonobj);
		  }else if(flag.equals("finish"))
		  {
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  //�����,�޸Ķ���״̬Ϊ����ɣ�ͬʱ�޸���Ʒ�����(����)
			  BookService bs=BookService.getInstance();
			  bs.upbook_finish(bookId);
			  
			  //���ݶ����Ų�ѯ�����������е���Ʒid�͹�������
			  SeqService seqs=SeqService.getInstance();
			  List<BookSeqence> list_bsq=seqs.getSeq(bookId);
			  //�����޸���Ʒ����
			  GoodService gs=GoodService.getInstance();
			  for(int i=0;i<list_bsq.size();i++)
			  {
				  BookSeqence bsq=list_bsq.get(i);
				  gs.update_count(bsq.getGoodsId(), bsq.getCount());
			  }
			
				int res[]=bs.getPageCountAndRowCount_recieve();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllRecieveBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_recieve.jsp").forward(request, response);
		  }else if(flag.equals("check_finish"))
		  {
			//��ҳ��ȡ����ɶ���(���¶�ʱ�併��)
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  BookService bs=BookService.getInstance();
				int res[]=bs.getPageCountAndRowCount_finish();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllFinishBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_finish.jsp").forward(request, response);
		  }else if(flag.equals("find"))
		  {
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  String statu_index=request.getParameter("statu");
			  String statu="";
			  if(statu_index.equals("1"))
			  {
				  statu="�������ύ";
			  }else if(statu_index.equals("2"))
			  {
				  statu="�̼��ѽӵ�";
			  }else if(statu_index.equals("3"))
			  {
				  statu="���������";
			  }else if(statu_index.equals("4"))
			  {
				  statu="������ȡ��";
			  }
			 
			  //��ѯ����
			  BookService bs=BookService.getInstance();
			  Book b=bs.getBookById(bookId, statu);
			  
			  if(b==null)
			  {
				  request.setAttribute("count", 0);
			  }else
			  {
				  request.setAttribute("count", 1);
			  }
			  
			  request.setAttribute("result", b);
			 
			  request.setAttribute("statu_index", statu_index);
			  
			  request.getRequestDispatcher("admin/findBook_result.jsp").forward(request, response);
			
		  }else if(flag.equals("delete"))
		  {
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  String statu=request.getParameter("statu");//����״̬������ҳ���ת
			  //ɾ������
			  //��ɾ��������Ӧ����
			  SeqService ss=SeqService.getInstance();
			  ss.deleteSeqByBookId(bookId);
			  //��ɾ������
			  BookService bs=BookService.getInstance();
			  bs.deleteBook(bookId);
			  
			  if(statu.equals("new"))
			  {
				  int res[]=bs.getPageCountAndRowCount();
					int rowCount=res[0];
					int pageCount=res[1];
				  List<Book> list=bs.getAllNewBooks(pageNow);
					request.setAttribute("result", list);
					request.setAttribute("rowCount", rowCount+"");
					 request.setAttribute("pageCount", pageCount+"");
					 request.setAttribute("pageNow", pageNow+"");
					 request.getRequestDispatcher("admin/book_new.jsp").forward(request, response);
			  }else if(statu.equals("recieve"))
			  {
				  int res[]=bs.getPageCountAndRowCount_recieve();
					int rowCount=res[0];
					int pageCount=res[1];
				  List<Book> list=bs.getAllRecieveBooks(pageNow);
					request.setAttribute("result", list);
					request.setAttribute("rowCount", rowCount+"");
					 request.setAttribute("pageCount", pageCount+"");
					 request.setAttribute("pageNow", pageNow+"");
					 request.getRequestDispatcher("admin/book_recieve.jsp").forward(request, response);
			  }else if(statu.equals("finish"))
			  {
				  int res[]=bs.getPageCountAndRowCount_finish();
					int rowCount=res[0];
					int pageCount=res[1];
				  List<Book> list=bs.getAllFinishBooks(pageNow);
					request.setAttribute("result", list);
					request.setAttribute("rowCount", rowCount+"");
					 request.setAttribute("pageCount", pageCount+"");
					 request.setAttribute("pageNow", pageNow+"");
					 request.getRequestDispatcher("admin/book_finish.jsp").forward(request, response);
			  }else if(statu.equals("giveup"))
			  {
				  int res[]=bs.getPageCountAndRowCount_giveup();
					int rowCount=res[0];
					int pageCount=res[1];
				  List<Book> list=bs.getAllGiveupBooks(pageNow);
					request.setAttribute("result", list);
					request.setAttribute("rowCount", rowCount+"");
					 request.setAttribute("pageCount", pageCount+"");
					 request.setAttribute("pageNow", pageNow+"");
					 request.getRequestDispatcher("admin/book_giveup.jsp").forward(request, response);
			  }
			  else if(statu.equals("find"))
			  {
				  //ת�ؿ�����ҳ��
				  request.setAttribute("count", 0);
				  
				  request.setAttribute("result", null);
					 
				  request.getRequestDispatcher("admin/findBook_result.jsp").forward(request, response);
			  }
		  }else if(flag.equals("delete_some"))
		  {
			  //����ɾ��
			  int pageNow=Integer.parseInt(request.getParameter("pageIndex"));
			  String statu=request.getParameter("statu");//����״̬������ҳ���ת
			  String checkBooks[]=request.getParameterValues("checkBooks");//���д�ɾ�������Ķ���������
				 int int_checkBooks[]=new int[checkBooks.length];
				 for(int i=0;i<checkBooks.length;i++)
				 {
					 int_checkBooks[i]=Integer.parseInt(checkBooks[i]);
				 }
				 
				 //��ɾ����������
				  SeqService ss=SeqService.getInstance();
				  ss.deleteSeqBySomeBooks(int_checkBooks);
				  
				  //��ɾ������
				  BookService bs=BookService.getInstance();
				  bs.deleteSomeBooks(int_checkBooks);
				  
				  if(statu.equals("new"))
				  {
					  int res[]=bs.getPageCountAndRowCount();
						int rowCount=res[0];
						int pageCount=res[1];
					  List<Book> list=bs.getAllNewBooks(pageNow);
						request.setAttribute("result", list);
						request.setAttribute("rowCount", rowCount+"");
						 request.setAttribute("pageCount", pageCount+"");
						 request.setAttribute("pageNow", pageNow+"");
						 request.getRequestDispatcher("admin/book_new.jsp").forward(request, response);
				  }else if(statu.equals("recieve"))
				  {
					  int res[]=bs.getPageCountAndRowCount_recieve();
						int rowCount=res[0];
						int pageCount=res[1];
					  List<Book> list=bs.getAllRecieveBooks(pageNow);
						request.setAttribute("result", list);
						request.setAttribute("rowCount", rowCount+"");
						 request.setAttribute("pageCount", pageCount+"");
						 request.setAttribute("pageNow", pageNow+"");
						 request.getRequestDispatcher("admin/book_recieve.jsp").forward(request, response);
				  }else if(statu.equals("finish"))
				  {
					  int res[]=bs.getPageCountAndRowCount_finish();
						int rowCount=res[0];
						int pageCount=res[1];
					  List<Book> list=bs.getAllFinishBooks(pageNow);
						request.setAttribute("result", list);
						request.setAttribute("rowCount", rowCount+"");
						 request.setAttribute("pageCount", pageCount+"");
						 request.setAttribute("pageNow", pageNow+"");
						 request.getRequestDispatcher("admin/book_finish.jsp").forward(request, response);
				  }else if(statu.equals("giveup"))
				  {
					  int res[]=bs.getPageCountAndRowCount_giveup();
						int rowCount=res[0];
						int pageCount=res[1];
					  List<Book> list=bs.getAllGiveupBooks(pageNow);
						request.setAttribute("result", list);
						request.setAttribute("rowCount", rowCount+"");
						 request.setAttribute("pageCount", pageCount+"");
						 request.setAttribute("pageNow", pageNow+"");
						 request.getRequestDispatcher("admin/book_giveup.jsp").forward(request, response);
				  }
				  else if(statu.equals("find"))
				  {
					  //ת�ؿ�����ҳ��
					  request.setAttribute("count", 0);
					  
					  request.setAttribute("result", null);
						 
					  request.getRequestDispatcher("admin/findBook_result.jsp").forward(request, response);
				  }
				 
		  }else if(flag.equals("give_up"))
		  {
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  BookService bs=BookService.getInstance();
			  bs.up_give_up(bookId);//ȡ������
			  request.getRequestDispatcher("UserInfo/right.jsp").forward(request, response);
		  }else if(flag.equals("check_giveup"))
		  {
			//��ҳ��ȡ��ȡ������(���¶�ʱ�併��)
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  BookService bs=BookService.getInstance();
				int res[]=bs.getPageCountAndRowCount_giveup();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllGiveupBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_giveup.jsp").forward(request, response);
		  }else if(flag.equals("show_booksByUser"))
		  {
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  int userId=Integer.parseInt(request.getParameter("userId"));
			  BookService bs=BookService.getInstance();
			  int res[]=bs.getPageCountAndRowCount_user(userId);
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllBooks_user(pageNow, userId);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("UserInfo/showAll_book.jsp").forward(request, response);
			  
		  }else if(flag.equals("show_booksGiveupByUser"))
		  {
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  int userId=Integer.parseInt(request.getParameter("userId"));
			  BookService bs=BookService.getInstance();
			  int res[]=bs.getPageCountAndRowCountGiveup_user(userId);
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllBooksGiveup_user(pageNow, userId);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("UserInfo/showAllGiveup_book.jsp").forward(request, response);
		  }
	}
 
}
