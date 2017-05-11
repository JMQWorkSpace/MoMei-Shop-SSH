package com.momei.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.momei.domain.Goods;
import com.momei.service.GoodService;
import com.momei.service.SeqService;
import com.momei.service.StoreService;
import com.momei.util.Tools;

public class GoodsServlet extends HttpServlet {

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
			String goodsName="";
			double price;
			String unit="";
			int count;
			String content="";
			int goodsTypeId;
			String pic="";
			 String [] s=new String[6];//���ܱ����ı�Ԫ�ص�����
			 
			 String path=request.getRealPath("/GoodsPic");
		       
		        DiskFileItemFactory factory=new DiskFileItemFactory();
		        ServletFileUpload sfu=new ServletFileUpload(factory);
		        sfu.setHeaderEncoding("UTF-8");  //������������
		        sfu.setSizeMax(1024*1024);   //�����ļ���С
		        
		        try {
		            List<FileItem> fileItems= sfu.parseRequest(request);  //�������� �õ����б�Ԫ��
		            int i=0;
		            for (FileItem fi : fileItems) {
		                //�п����� �ļ���Ҳ��������ͨ���� 
		                if (fi.isFormField()) { //���ѡ���� ���� 
		                  s[i]=fi.getString();
		                  i++;
		                }else{
		                    // ���ļ�
		                    String fn=fi.getName();	                    
		                     pic=fn;
		                    // fn �ǿ����������� c:\abc\de\tt\fish.jpg
		                     if(fn.length()>0)
		                    fi.write(new File(path,fn));
		                    
		                }  
		               
		                
		            }    
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		        goodsName=Tools.getNewString(s[0]);
		        goodsTypeId=Integer.parseInt(s[1]);
		        unit=Tools.getNewString(s[2]);
		        count=Integer.parseInt(s[3]);
		        price=Double.parseDouble(s[4]);
		        if(s[5]!=null)
		        {
		        content=Tools.getNewString(s[5]);
		        }
		        
		      Goods goods=new Goods();
		      goods.setGoodsName(goodsName);
		      goods.setGoodsTypeId(goodsTypeId);
		      goods.setPrice(price);
		      goods.setPic(pic);
		      goods.setUnit(unit);
		      goods.setCount(count);
		      goods.setContent(content);
		      
		      GoodService gs=GoodService.getInstance();
		      gs.addGoods(goods);
		      
		      request.setAttribute("Msg", "��ӳɹ�!");
				 request.getRequestDispatcher("admin/addgoods.jsp").forward(request, response);
		        
		}else if(flag.equals("up_get"))
		{
			int goodsId=Integer.parseInt(request.getParameter("goodsId"));
			int type=Integer.parseInt(request.getParameter("type"));
			GoodService gs=GoodService.getInstance();
			Goods g=gs.getGoods(goodsId);
			request.setAttribute("g", g);
			request.setAttribute("pageNow", request.getParameter("pageNow"));
			request.setAttribute("type", type);
			request.getRequestDispatcher("admin/upgoods.jsp").forward(request, response);
			
		}
		else if(flag.equals("update"))
		{
			int goodsId;
			String goodsName="";
			double price;
			String unit="";
			int count;
			String content="";
			int goodsTypeId;
			String pic="";
			int pageNow;
			int type;
			 String [] s=new String[9];//���ܱ����ı�Ԫ�ص�����
			 
			 String path=request.getRealPath("/GoodsPic");
		       
		        DiskFileItemFactory factory=new DiskFileItemFactory();
		        ServletFileUpload sfu=new ServletFileUpload(factory);
		        sfu.setHeaderEncoding("UTF-8");  //������������
		        sfu.setSizeMax(1024*1024);   //�����ļ���С
		        
		        try {
		            List<FileItem> fileItems= sfu.parseRequest(request);  //�������� �õ����б�Ԫ��
		            int i=0;
		            for (FileItem fi : fileItems) {
		                //�п����� �ļ���Ҳ��������ͨ���� 
		                if (fi.isFormField()) { //���ѡ���� ���� 
		                  s[i]=fi.getString();
		                  i++;
		                }else{
		                    // ���ļ�
		                    String fn=fi.getName();	                    
		                     pic=fn;
		                    // fn �ǿ����������� c:\abc\de\tt\fish.jpg
		                     if(fn.length()>0)
		                     fi.write(new File(path,fn));
		                   
		                }  
		               
		                
		            }    
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		   
		        goodsId=Integer.parseInt(s[0]);
		        goodsName=Tools.getNewString(s[1]);
		        goodsTypeId=Integer.parseInt(s[2]);
		        unit=Tools.getNewString(s[3]);
		        count=Integer.parseInt(s[4]);
		        price=Double.parseDouble(s[5]);
		        if(s[6]!=null)
		        {
		        content=Tools.getNewString(s[6]);
		        }
		        pageNow=Integer.parseInt(s[7]);
		        type=Integer.parseInt(s[8]);
		      
 		      Goods goods=new Goods();
 		      goods.setGoodsId(goodsId);
 		      goods.setGoodsName(goodsName);
 		      goods.setPrice(price);
 		      goods.setPic(pic);
 		      goods.setUnit(unit);
 		      goods.setCount(count);
 		      goods.setContent(content);
 		      goods.setGoodsTypeId(goodsTypeId);
 		      
 		      GoodService gs=GoodService.getInstance();
 		      gs.UpdateGoods(goods);
 		      
 		      if(type==0)
 		      {
 			int res[]=gs.getPageCountAndRowCount();
 			int rowCount=res[0];
 			int pageCount=res[1];
 			List<Goods> list=gs.getGoodsByPage(pageNow);
 			request.setAttribute("result", list);
 			request.setAttribute("rowCount", rowCount+"");
 			 request.setAttribute("pageCount", pageCount+"");
 			 request.setAttribute("pageNow", pageNow+"");
 			 request.setAttribute("Msg", "�޸ĳɹ�!");
 			 request.getRequestDispatcher("admin/goodsManage.jsp").forward(request, response);
 		      }else
 		      {
 		    	 int res[]=gs.getPageCountAndRowCountByType(goodsTypeId);
 				int rowCount=res[0];
 				int pageCount=res[1];
 				List<Goods> list=gs.getGoodsByPageByType(pageNow, goodsTypeId);
 				request.setAttribute("result", list);
 				request.setAttribute("rowCount", rowCount+"");
 				 request.setAttribute("pageCount", pageCount+"");
 				 request.setAttribute("pageNow", pageNow+"");
 				 request.setAttribute("Msg", "�޸ĳɹ�!");
 				 request.setAttribute("goodsTypeId", goodsTypeId+"");
 				 request.getRequestDispatcher("admin/goodsManager_type.jsp").forward(request, response);
 		      }
 		      
 		  
		      
		}else if(flag.equals("delete_one"))
		{
			 int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			int goodsId=Integer.parseInt(request.getParameter("goodsId"));
			int goodsTypeId=Integer.parseInt(request.getParameter("type"));
			
			//ɾ����Ʒ
			//��ɾ����Ʒ�ղ��Ӽ�¼
			StoreService sts=StoreService.getInstance();
			sts.deleteStoreByGoodsId(goodsId);
			
			//���ж���Ʒ�Ӽ�¼���������Ƿ�Ϊ��
			SeqService ss=SeqService.getInstance();
			List<Integer> list_bookId=ss.getSeqIdByGoodsId(goodsId);
			
			 GoodService gs=GoodService.getInstance();
			 
			 //�޶��������Ӽ�¼�����޼������⣬ɾ����Ʒ
			if(list_bookId.size()==0)
			{
			 gs.deleteGoods(goodsId);
			}else
			{
				//�ж��������Ӽ�¼��Ϊ��Ӱ����ʷ������Ϣ�Ĳ�ѯ����ɾ����Ʒ���޸���Ʒ״̬Ϊ�¼�
				gs.updateGoodsStatu(goodsId);
				
			}
			
			 if(goodsTypeId==0)
			 {
			int res[]=gs.getPageCountAndRowCount();
			int rowCount=res[0];
			int pageCount=res[1];
			List<Goods> list=gs.getGoodsByPage(pageNow);
			request.setAttribute("result", list);
			request.setAttribute("rowCount", rowCount+"");
			 request.setAttribute("pageCount", pageCount+"");
			 request.setAttribute("pageNow", pageNow+"");
			 request.getRequestDispatcher("admin/goodsManage.jsp").forward(request, response);
			 }else
			 {
				    int res[]=gs.getPageCountAndRowCountByType(goodsTypeId);
	 				int rowCount=res[0];
	 				int pageCount=res[1];
	 				List<Goods> list=gs.getGoodsByPageByType(pageNow, goodsTypeId);
	 				request.setAttribute("result", list);
	 				request.setAttribute("rowCount", rowCount+"");
	 				 request.setAttribute("pageCount", pageCount+"");
	 				 request.setAttribute("pageNow", pageNow+"");
	 				 request.setAttribute("goodsTypeId", goodsTypeId+"");
	 				 request.getRequestDispatcher("admin/goodsManager_type.jsp").forward(request, response);
			 }
				
		}
		else if(flag.equals("delete"))
		{
			//����ɾ��
			 int pageNow=Integer.parseInt(request.getParameter("pageIndex"));
			 int goodsTypeId=Integer.parseInt(request.getParameter("goodsTypeId"));
			 String checkGoods[]=request.getParameterValues("checkGoods");
			 int int_checkGoods[]=new int[checkGoods.length];			 
				StoreService sts=StoreService.getInstance();
			 for(int i=0;i<checkGoods.length;i++)
			 {
				 int_checkGoods[i]=Integer.parseInt(checkGoods[i]);
				 sts.deleteStoreByGoodsId(int_checkGoods[i]);//��ɾ����Ʒ�ղ��Ӽ�¼
			 }
			 
			 
			 List<Integer> list_bookId_select=new ArrayList<Integer>();
			 
			 SeqService ss=SeqService.getInstance();
			 GoodService gs=GoodService.getInstance();
			 //�����ж���Ʒ�Ӽ�¼���������Ƿ�Ϊ��
			 for(int i=0;i<int_checkGoods.length;i++)
			 {
				 List<Integer> list_bookId=ss.getSeqIdByGoodsId(int_checkGoods[i]);
				 //�޶��������Ӽ�¼�����޼������⣬ɾ����Ʒ
					if(list_bookId.size()==0)
					{
						list_bookId_select.add(int_checkGoods[i]);
					}else
					{
						//�ж��������Ӽ�¼��Ϊ��Ӱ����ʷ������Ϣ�Ĳ�ѯ����ɾ����Ʒ���޸���Ʒ״̬Ϊ�¼�
						gs.updateGoodsStatu(int_checkGoods[i]);
						
					}
			 }
		
			 int checkGoodsId[]=new int[list_bookId_select.size()];
			 for(int i=0;i<list_bookId_select.size();i++)
			 {
				 checkGoodsId[i]=list_bookId_select.get(i);
			 }
			 
			 if(checkGoodsId.length>0)
			 {
			 gs.deleteGoodsSome(checkGoodsId);
			 }
			 
			 if(goodsTypeId==0)
			 { 
			 int res[]=gs.getPageCountAndRowCount();
				int rowCount=res[0];
				int pageCount=res[1];
				List<Goods> list=gs.getGoodsByPage(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/goodsManage.jsp").forward(request, response);
			 }else
			 {
				 int res[]=gs.getPageCountAndRowCountByType(goodsTypeId);
	 				int rowCount=res[0];
	 				int pageCount=res[1];
	 				List<Goods> list=gs.getGoodsByPageByType(pageNow, goodsTypeId);
	 				request.setAttribute("result", list);
	 				request.setAttribute("rowCount", rowCount+"");
	 				 request.setAttribute("pageCount", pageCount+"");
	 				 request.setAttribute("pageNow", pageNow+"");
	 				 request.setAttribute("goodsTypeId", goodsTypeId+"");
	 				 request.getRequestDispatcher("admin/goodsManager_type.jsp").forward(request, response);
			 }
			 
			 
			 
		}else if(flag.equals("showByPage"))
		{
			int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			 GoodService gs=GoodService.getInstance();
			int res[]=gs.getPageCountAndRowCount();
			int rowCount=res[0];
			int pageCount=res[1];
			List<Goods> list=gs.getGoodsByPage(pageNow);
			request.setAttribute("result", list);
			request.setAttribute("rowCount", rowCount+"");
			 request.setAttribute("pageCount", pageCount+"");
			 request.setAttribute("pageNow", pageNow+"");
			 request.getRequestDispatcher("admin/goodsManage.jsp").forward(request, response);
			
		}else if(flag.equals("showPageType"))
		{
			int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			int goodsTypeId=Integer.parseInt(request.getParameter("goodsTypeId"));
			 GoodService gs=GoodService.getInstance();
			int res[]=gs.getPageCountAndRowCountByType(goodsTypeId);
			int rowCount=res[0];
			int pageCount=res[1];
			List<Goods> list=gs.getGoodsByPageByType(pageNow, goodsTypeId);
			request.setAttribute("result", list);
			request.setAttribute("rowCount", rowCount+"");
			 request.setAttribute("pageCount", pageCount+"");
			 request.setAttribute("pageNow", pageNow+"");
			 request.setAttribute("goodsTypeId", goodsTypeId+"");
			 request.getRequestDispatcher("admin/goodsManager_type.jsp").forward(request, response);
		}else if(flag.equals("find"))
		{
			String goodsName=request.getParameter("goodsName");
			 GoodService gs=GoodService.getInstance();
			 List<Goods> list=gs.getGoodsByName(goodsName);
			 request.setAttribute("result", list);
			 request.getRequestDispatcher("admin/findGoods_result.jsp").forward(request, response);
			
		}else if(flag.equals("show_type"))
		{
			int goodsTypeId=Integer.parseInt(request.getParameter("goodsTypeId"));
			 GoodService gs=GoodService.getInstance();
			List<Goods> list=gs.getGoodsByType(goodsTypeId);
			 request.setAttribute("result", list);
			 request.setAttribute("goodsTypeId", goodsTypeId);
			 request.getRequestDispatcher("UserInfo/GoodsMain.jsp").forward(request, response);
		}else if(flag.equals("find_blur"))
		{
			//��Ʒģ����ѯ
			String goodsName=Tools.getNewString(request.getParameter("goodsName"));
			 GoodService gs=GoodService.getInstance();
			List<Goods> list=gs.getGoodsByFind(goodsName);
			request.setAttribute("result", list);
			request.setAttribute("find", "find_blur");
			 request.getRequestDispatcher("UserInfo/GoodsMain.jsp").forward(request, response);
		}
	}

}
