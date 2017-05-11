  package com.momei.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import com.momei.domain.ShoppingCar_seq;

public class ShoppingCarServlet extends HttpServlet {
	
	
	//�����Ʒ�����ﳵ
	public static ArrayList<ShoppingCar_seq> add(ArrayList<ShoppingCar_seq> car_list,int goodsId,String goodsName,double price,int count,int count_kc)
	{
		ShoppingCar_seq se_temp=null;
		int index=0;
		for(int i=0;i<car_list.size();i++)
		{
		   se_temp=car_list.get(i);
		   if(se_temp.getGoodsId()==goodsId)
		   {
			   se_temp.setCount(se_temp.getCount()+count);
               car_list.remove(i);
               car_list.add(se_temp);
               index++;
               break;
		   }
			
		}
		if(index==0)
		{
		ShoppingCar_seq se=new ShoppingCar_seq();
		se.setGoodsId(goodsId);
		se.setGoodsName(goodsName);
		se.setPrice(price);
		se.setCount(count);
		se.setCount_kc(count_kc);
		car_list.add(se);
		}
		return car_list;
		
	}
	
	//�޸���������
	public static ArrayList<ShoppingCar_seq> car_up_add(ArrayList<ShoppingCar_seq> car_list,int goodsId)
	{
		ShoppingCar_seq se_temp=null;
		for(int i=0;i<car_list.size();i++)
		{
		   se_temp=car_list.get(i);
		   if(se_temp.getGoodsId()==goodsId)
		   {
			   if(se_temp.getCount()<se_temp.getCount_kc())
			   {
			   se_temp.setCount(se_temp.getCount()+1);
               car_list.remove(i);
               car_list.add(se_temp);
               break;
			   }
			   break;
		   }
			
	}
		
		return car_list;
		
	}
	
	//�޸���������
	public static ArrayList<ShoppingCar_seq> car_up_red(ArrayList<ShoppingCar_seq> car_list,int goodsId)
	{
		ShoppingCar_seq se_temp=null;
		for(int i=0;i<car_list.size();i++)
		{
		   se_temp=car_list.get(i);
		   if(se_temp.getGoodsId()==goodsId)
		   {
			   if(se_temp.getCount()>1)
			   {
			   se_temp.setCount(se_temp.getCount()-1);
               car_list.remove(i);
               car_list.add(se_temp);
               break;
			   }
			   break;
		   }
		}
		return car_list;
		
	}
	
	
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
		
		response.setContentType("text/html;charset=utf-8");
		
		String flag=request.getParameter("flag");
		
		if(flag.equals("car_add"))
		{
			int goodsId=Integer.parseInt(request.getParameter("goodsId"));
			String goodsName=request.getParameter("goodsName");
			double price=Double.parseDouble(request.getParameter("price"));
			int count=Integer.parseInt(request.getParameter("count"));
			int count_kc=Integer.parseInt(request.getParameter("count_kc"));
			
		   //ȡ��session�еĹ��ﳵ����
			HttpSession session=request.getSession();
			ArrayList<ShoppingCar_seq> car_list=null;
			if(session.getAttribute("car_list")==null)
			{
				ArrayList<ShoppingCar_seq> cl=new ArrayList<ShoppingCar_seq>();
				session.setAttribute("car_list", cl);//��ʼ��,����session�д���յĹ��ﳵ����
				car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
			}else
			{
		        car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
			}
			
			//���빺�ﳵ
			ArrayList<ShoppingCar_seq> carList=ShoppingCarServlet.add(car_list,goodsId, goodsName, price, count, count_kc);
			
			
			//�滻session�еĹ��ﳵ����
			session.setAttribute("car_list", carList);
			
			//��json������ﳵ�б����ݴ��ؿͻ���
			JSONArray jsonobj=JSONArray.fromObject(carList);
			
			response.getWriter().print(jsonobj);
		
			
		}else if(flag.equals("car_add_store"))
		{
			int goodsId=Integer.parseInt(request.getParameter("goodsId"));
			String goodsName=request.getParameter("goodsName");
			double price=Double.parseDouble(request.getParameter("price"));
			int count=Integer.parseInt(request.getParameter("count"));
			int count_kc=Integer.parseInt(request.getParameter("count_kc"));
			
		   //ȡ��session�еĹ��ﳵ����
			HttpSession session=request.getSession();
			ArrayList<ShoppingCar_seq> car_list=null;
			if(session.getAttribute("car_list")==null)
			{
				ArrayList<ShoppingCar_seq> cl=new ArrayList<ShoppingCar_seq>();
				session.setAttribute("car_list", cl);//��ʼ��,����session�д���յĹ��ﳵ����
				car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
			}else
			{
		        car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
			}
			
			//���빺�ﳵ
			ArrayList<ShoppingCar_seq> carList=ShoppingCarServlet.add(car_list,goodsId, goodsName, price, count, count_kc);
			
			
			//�滻session�еĹ��ﳵ����
			session.setAttribute("car_list", carList);
			
			response.getWriter().print("��ӳɹ�!");
		}
		else if(flag.equals("car_setNull"))
		{
			 //�Ƴ�session�й��ﳵ����
			HttpSession session=request.getSession();
		    session.removeAttribute("car_list");
			
			response.getWriter().print("");
		}else if(flag.equals("car_up_add"))
		{
			int goodsId=Integer.parseInt(request.getParameter("goodsId"));
			
			  //ȡ��session�еĹ��ﳵ����
			HttpSession session=request.getSession();
			ArrayList<ShoppingCar_seq> car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
			//�޸�����
			ArrayList<ShoppingCar_seq> carList=ShoppingCarServlet.car_up_add(car_list, goodsId);
			
			//�滻session�еĹ��ﳵ����
			session.setAttribute("car_list", carList);
			
			//��json������ﳵ�б����ݴ��ؿͻ���
			JSONArray jsonobj=JSONArray.fromObject(carList);
			
			response.getWriter().print(jsonobj);
			
		}else if(flag.equals("car_up_red"))
		{
			int goodsId=Integer.parseInt(request.getParameter("goodsId"));
			
			  //ȡ��session�еĹ��ﳵ����
			HttpSession session=request.getSession();
			ArrayList<ShoppingCar_seq> car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
			//�޸�����
			ArrayList<ShoppingCar_seq> carList=ShoppingCarServlet.car_up_red(car_list, goodsId);
			
			//�滻session�еĹ��ﳵ����
			session.setAttribute("car_list", carList);
			
			//��json������ﳵ�б����ݴ��ؿͻ���
			JSONArray jsonobj=JSONArray.fromObject(carList);
			
			response.getWriter().print(jsonobj);
			
		}else if(flag.equals("show_car"))
		{
			  //ȡ��session�еĹ��ﳵ����
			HttpSession session=request.getSession();
			ArrayList<ShoppingCar_seq> car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
	
			if(car_list!=null)
			{
			//��json������ﳵ�б����ݴ��ؿͻ���
			JSONArray jsonobj=JSONArray.fromObject(car_list);
			response.getWriter().print(jsonobj);	
			}else
			{
				List list=new ArrayList();//����һ���ռ���
				//��json������ﳵ�б����ݴ��ؿͻ���
				JSONArray jsonobj=JSONArray.fromObject(list);
				response.getWriter().print(jsonobj);	
			}
	
		}
		
	}

}
