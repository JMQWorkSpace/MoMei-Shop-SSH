 package com.momei.control;

import java.awt.Color;  
import java.awt.Font;  
import java.io.IOException;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartUtilities;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.CategoryAxis;  
import org.jfree.chart.axis.NumberAxis;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.renderer.category.BarRenderer;  
import org.jfree.chart.title.TextTitle;  
import org.jfree.data.category.DefaultCategoryDataset;  

import com.momei.domain.Book;
import com.momei.domain.Goods;
import com.momei.domain.Type_Date_Count;
import com.momei.service.BookService;
import com.momei.service.GoodService;
import com.momei.service.SeqService;
import com.momei.util.Sort_deal;
/** 
 * ��״�ֲ�ͳ��ͼ 
 * �ɹ��������ͳ��
 * @˵��  
 * @author cuisuqiang 
 * @version 1.0 
 * @since 
 */  
@SuppressWarnings("serial") 
  

public class WebChart_Bar extends HttpServlet {

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
		

		 response.setContentType("text/html");  
		 
		      DefaultCategoryDataset Data = new DefaultCategoryDataset();  
		      
		      //��ȡ����
		      /*
		       * ���������������С��ɹ����ɹ�����
		       * 1:��ȡ��������ɶ����Ķ�����
		       * 2�����ݶ����Ż�ȡ�ö����Ŷ�Ӧ���е���Ʒ��������������������
		       */
		      
		      //��ȡ���еĶ���id����
	    	  BookService bs=BookService.getInstance();
	    	  SeqService ss=SeqService.getInstance();
	    	  List<Book> list=bs.getBookId();
	    	  
	    
	    	  //��ȡ���еĸɹ�
	    	  GoodService gs=GoodService.getInstance();
	    	  List<Goods> list_g=gs.getAll_Goods();
	    	  
	    	  //key-�ɹ��� value-����
	    	  HashMap<String,Integer> hm=new HashMap<String,Integer>();
	    	  //key-�ɹ��� value-������
	    	  HashMap<String,String> hm2=new HashMap<String,String>();
	    	  
	    	  for(int i=0;i<list_g.size();i++)
	    	  {
	    		  Goods g=list_g.get(i);
	    		  hm.put(g.getGoodsName(), 0);
	    		  //���ݸɹ�id��ȡ�ɹ�������
	    		  hm2.put(g.getGoodsName(), gs.getTypeName(g.getGoodsId()));
	    	  }
	    	  
	    	  //���ݶ���Id��ȡ�������еĸɹ�������
	    	  for(int i=0;i<list.size();i++)
	    	  {  
	    		    Book b=list.get(i);
	    	        List<Type_Date_Count> res=ss.getType_Date_count(b.getBookId());
	    	        for(int j=0;j<res.size();j++)
	    	          {
	    	        	Type_Date_Count tdc=res.get(j);
	    	        	
	    	        		if(hm.containsKey(tdc.getGoodsName()))
	    	        		{
	    	        			int count=hm.get(tdc.getGoodsName());
	    	        			hm.put(tdc.getGoodsName(), count+tdc.getCount());//�ۼ�
	    	        		}
	    	        	
	    	          }
	    	  }
	    	  
	    	 
	    	  /*
	    	   * ����ȡ����ǰ10�ĸɹ�(������Ϊ0)
	    	   */
	    	  ArrayList<Integer> count_list=new ArrayList<Integer>();
	    	  for(int i=0;i<list_g.size();i++)
	    	  {
	    		  Goods g=list_g.get(i);
	    		  if(hm.containsKey(g.getGoodsName()))
	    		  {
	    			  int count=hm.get(g.getGoodsName());
	    			  count_list.add(count);
	    		  }
	    	  }  
	    	  
	    	  int sort_temp[]=null;
	    	  int count_judge=0; //������10��(������Ϊ0)
	    	  int sort[]=Sort_deal.sort(count_list);
	    	   sort_temp=new int[sort.length];
	    	   for(int i=sort.length-1;i>=0;i--)
		       sort_temp[sort.length-1-i]=sort[i];
	    	   
	    	   if(sort_temp.length<=10)
	    	   {
	    		   for(int i=sort_temp.length-1;i>=0;i--)
	    		   {
	    			   if(sort_temp[i]!=0)
	    			   {
	    				   count_judge=sort_temp[i];
	    				   break;
	    			   }
	    		   }
	    	   }else
	    	   {
	    		   for(int i=9;i>=0;i--)
	    		   {
	    			   if(sort_temp[i]!=0)
	    			   {
	    				   count_judge=sort_temp[i];
	    				   break;
	    			   }
	    		   }
	    	   }
	    	 
	    	  //���ݸ�ֵ
	    	  Iterator it=hm.keySet().iterator();
		  		//hasNext����һ��boolean��̽���Ƿ�����һ��
		  		while(it.hasNext())
		  		{
		  			//ȡ��key
		  			String key=it.next().toString();
		  			//ͨ��keyȡ��value
		  			int count=hm.get(key);
		  			if(count>=count_judge)
		  			{
		  			String goodsTypeName=null;
		  			
		  			//����key(�ɹ���)��ȡ�ɹ�����
		  			if(hm2.containsKey(key))
		  			{
		  				goodsTypeName=hm2.get(key);
		  			}
		  			
		  		   Data.addValue(count, goodsTypeName,key);
		  			}
		  			
		  		}
	    	  
		         try {  
		             DefaultCategoryDataset data = Data;  
		             // ʹ��ChartFactory����3D��״ͼ������ʹ��3D��ֱ��ʹ��createBarChart  
		            JFreeChart chart = ChartFactory.createBarChart3D(  
		            		"�ɹ��������ǰʮͳ��",   
		                     "����/����/�ɹ���",   
		                    "����",   
		                     data,  
		                     PlotOrientation.VERTICAL,   
		                   true,   
		                   false,   
		                    false  
		             );  
		             // ��������ͼƬ�ı���ɫ  
		             chart.setBackgroundPaint(Color.white);  
		             // ����ͼƬ�б߿�  
		            chart.setBorderVisible(true);  
		            Font kfont = new Font("����", Font.PLAIN, 12);    // �ײ�  
		            Font titleFont = new Font("����", Font.BOLD, 20); // ͼƬ����  
		            // ͼƬ����  
		             chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));  
		            // �ײ�  
		             chart.getLegend().setItemFont(kfont);  
		             // �õ�������������������  
		             CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();  
		             categoryplot.setDomainGridlinesVisible(true);  
		             categoryplot.setRangeCrosshairVisible(true);  
		             categoryplot.setRangeCrosshairPaint(Color.blue);  
		           NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
		            numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());  
		            BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();  
		             barrenderer.setBaseItemLabelFont(new Font("����", Font.PLAIN, 12));  
		            barrenderer.setSeriesItemLabelFont(1, new Font("����", Font.PLAIN, 12));  
		           CategoryAxis domainAxis = categoryplot.getDomainAxis();           
		            /*------����X�������ϵ�����-----------*/  
		            domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
		             /*------����X��ı�������------------*/  
		             domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
		             /*------����Y�������ϵ�����-----------*/  
		           numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
		             /*------����Y��ı�������------------*/  
		            numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
		             /*------���������˵ײ��������������-----------*/  
		             chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));    
		             ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,  
		                    chart, 900, 400, null);  
		        } catch (Exception es) {  
		            es.printStackTrace();  
		        }   
	}

}
