package com.momei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.momei.domain.Book;
import com.momei.util.DBUtils;
import com.momei.util.DaoException;

public class BookDao {
	
private Connection connection;

private int pageSize=5;
private int rowCount=0;
private int pageCount=0;
	
	
	public  BookDao(Connection connection){
		this.connection = connection;
	}
	

	//��Ӷ���
	public void addBook(int bookId,int userId,String message,String send_time,String pay_way,String phoneNum,String address,Calendar dates_order,int count,double pay)
	{
		String sql = "insert into book (bookId,userId,STATU,DATES_ORDER,ADDRESS,PHONENUM,PAY_WAY,SEND_TIME,MESSAGE,count,pay,COMMENT_STATU) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		    pstmt.setInt(2,userId );
		    pstmt.setString(3,"�������ύ");
		    pstmt.setTime(4, new Time(dates_order.getTimeInMillis()));
		    pstmt.setString(5, address);
		    pstmt.setString(6, phoneNum);
		    pstmt.setString(7, pay_way);
		    pstmt.setString(8, send_time);
		    pstmt.setString(9, message);
		    pstmt.setInt(10, count);
		    pstmt.setDouble(11, pay);
		    pstmt.setString(12, "������");
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on add", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	//��ѯ��ǰ��������id��
	public synchronized int find_Max_bookId()
	{
		int result=999;//Ĭ��999
		
		 String sql ="select max(bookId) from book";
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				 result=rs.getInt("max(bookId)");
				 if(result==0)
				 {
					 result=999;//��ǰ���ݿ����޶�����¼��Ĭ�Ϸ���999�������Ŵ�1000��ʼ������
				 }
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		return result;
			
	}
	
	//��ѯ�û����¶���(�¶�ʱ��ǰ��)
	public List<Book> getBook_Recent(int userId)
	{
		List<Book> list=new ArrayList<Book>();
		String sql="select a1.*,rownum rn from(select * from book where userId=? order by dates_order desc) a1 where rownum<=3";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1,userId);
			
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				b.setStatu(rs.getString("statu"));
				b.setPay_way(rs.getString("pay_way"));
				b.setPay(rs.getDouble("pay"));
				b.setCount(rs.getInt("count"));
				Calendar cal = Calendar.getInstance();
				java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
				 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
				 
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	//���ݶ����Ų�ѯ����
	public Book getBookById(int bookId)
	{
		Book b=new Book();
		String sql="select * from book where bookId=?";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1,bookId);
			
			 rs = pstmt.executeQuery();
			 if(rs.next()){
		
					b.setBookId(rs.getInt("bookId"));
					b.setStatu(rs.getString("statu"));
					b.setPay_way(rs.getString("pay_way"));
					b.setSend_time(rs.getString("send_time"));
					b.setPhoneNum(rs.getString("phoneNum"));
					b.setAddress(rs.getString("address"));
					b.setMessage(rs.getString("message"));
					b.setPay(rs.getDouble("pay"));
					b.setComment_statu(rs.getString("comment_statu"));
				
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return b;
	}
	
	
	/*
	 * ���ݶ����źͶ���״̬��ѯ����
	 */
	public Book getBookById(int bookId,String statu)
	{
		Book b=null;
		String sql="select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and bookId=? and statu=?";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1,bookId);
			 pstmt.setString(2, statu);
			
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				   b=new Book();
				 b.setBookId(rs.getInt("bookId"));
					b.setName(rs.getString("name"));
					b.setUserName(rs.getString("userName"));
					b.setStatu(rs.getString("statu"));
					b.setSend_time(rs.getString("send_time"));
					b.setPay_way(rs.getString("pay_way"));
					Calendar cal = Calendar.getInstance();
					java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
					 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
					b.setPay(rs.getDouble("pay"));
					b.setCount(rs.getInt("count"));
					b.setPhoneNum(rs.getString("phoneNum"));
					b.setAddress(rs.getString("address"));
					b.setMessage(rs.getString("message"));
				
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return b;
	}
	
	/*
	 * ��ȡrowCount��pageCount
	 */
	public int[] getPageCountAndRowCount()
	{
		int res[]=new int[2];
		String sql="select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='�������ύ' order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	
	/*
	 * ��ȡrowCount��pageCount(�ѽӶ���)
	 * 
	 */
	public int[] getPageCountAndRowCount_recieve()
	{
		int res[]=new int[2];
		String sql="select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='�̼��ѽӵ�' order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	

	/*
	 * ��ȡrowCount��pageCount(����ɶ���)
	 */
	public int[] getPageCountAndRowCount_finish()
	{
		int res[]=new int[2];
		String sql="select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='���������' order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	
	/*
	 * ��ȡrowCount��pageCount(��ȡ������)
	 */
	public int[] getPageCountAndRowCount_giveup()
	{
		int res[]=new int[2];
		String sql="select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='������ȡ��' order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	
	/*
	 * ��ȡrowCount��pageCount(�����û������ж���)
	 */
	public int[] getPageCountAndRowCount_user(int userId)
	{
		int res[]=new int[2];
		String sql="select * from book where userId=? order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 pstmt.setInt(1, userId);
			 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	
	/*
	 * ��ȡrowCount��pageCount(�����û���������ȡ������)
	 */
	public int[] getPageCountAndRowCountGiveup_user(int userId)
	{
		int res[]=new int[2];
		String sql="select * from book where userId=? and statu='������ȡ��' order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 pstmt.setInt(1, userId);
			 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	
	//����Ա��ҳ��ȡ���¶���(���¶�ʱ��Ľ���)
	public List<Book> getAllNewBooks(int pageNow)
	{
		List<Book> list=new ArrayList<Book>();
		
		String sql="select * from (select a1.*,rownum rn from (select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='�������ύ' order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
		
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				b.setName(rs.getString("name"));
				b.setUserName(rs.getString("userName"));
				b.setStatu(rs.getString("statu"));
				b.setSend_time(rs.getString("send_time"));
				b.setPay_way(rs.getString("pay_way"));
				Calendar cal = Calendar.getInstance();
				java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
				 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
				b.setPay(rs.getDouble("pay"));
				b.setCount(rs.getInt("count"));
				b.setPhoneNum(rs.getString("phoneNum"));
				b.setAddress(rs.getString("address"));
				b.setMessage(rs.getString("message"));
				 
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	//����Ա��ҳ��ȡ�ѽ��ն���(���¶�ʱ��Ľ���)
	public List<Book> getAllRecieveBooks(int pageNow)
	{

		List<Book> list=new ArrayList<Book>();
		
		String sql="select * from (select a1.*,rownum rn from (select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='�̼��ѽӵ�' order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
		
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				b.setName(rs.getString("name"));
				b.setUserName(rs.getString("userName"));
				b.setStatu(rs.getString("statu"));
				b.setSend_time(rs.getString("send_time"));
				b.setPay_way(rs.getString("pay_way"));
				Calendar cal = Calendar.getInstance();
				java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
				 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
				b.setPay(rs.getDouble("pay"));
				b.setCount(rs.getInt("count"));
				b.setPhoneNum(rs.getString("phoneNum"));
				b.setAddress(rs.getString("address"));
				b.setMessage(rs.getString("message"));
				 
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	//����Ա��ҳ��ȡ����ɶ���(���¶�ʱ��Ľ���)
	public List<Book> getAllFinishBooks(int pageNow)
	{
         List<Book> list=new ArrayList<Book>();
		
		String sql="select * from (select a1.*,rownum rn from (select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='���������' order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
		
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				b.setName(rs.getString("name"));
				b.setUserName(rs.getString("userName"));
				b.setStatu(rs.getString("statu"));
				b.setSend_time(rs.getString("send_time"));
				b.setPay_way(rs.getString("pay_way"));
				Calendar cal = Calendar.getInstance();
				java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
				 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
				b.setPay(rs.getDouble("pay"));
				b.setCount(rs.getInt("count"));
				b.setPhoneNum(rs.getString("phoneNum"));
				b.setAddress(rs.getString("address"));
				b.setMessage(rs.getString("message"));
				 
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	//����Ա��ҳ��ȡ��ȡ������(���¶�ʱ��Ľ���)
	public List<Book> getAllGiveupBooks(int pageNow)
	{
		 List<Book> list=new ArrayList<Book>();
			
			String sql="select * from (select a1.*,rownum rn from (select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='������ȡ��' order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
			
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					Book b=new Book();
					
					b.setBookId(rs.getInt("bookId"));
					b.setName(rs.getString("name"));
					b.setUserName(rs.getString("userName"));
					b.setStatu(rs.getString("statu"));
					b.setSend_time(rs.getString("send_time"));
					b.setPay_way(rs.getString("pay_way"));
					Calendar cal = Calendar.getInstance();
					java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
					 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
					b.setPay(rs.getDouble("pay"));
					b.setCount(rs.getInt("count"));
					b.setPhoneNum(rs.getString("phoneNum"));
					b.setAddress(rs.getString("address"));
					b.setMessage(rs.getString("message"));
					 
					
					 list.add(b);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting book", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
	}
	
	//�û���ҳ��ȡ���ж���(���¶�ʱ��Ľ���)
	public List<Book> getAllBooks_user(int pageNow,int userId)
	{
		 List<Book> list=new ArrayList<Book>();
			
			String sql="select * from (select a1.*,rownum rn from (select * from book where userId=? order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
				 pstmt.setInt(1, userId);
			
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					Book b=new Book();
					
					b.setBookId(rs.getInt("bookId"));
					b.setStatu(rs.getString("statu"));
					b.setPay_way(rs.getString("pay_way"));
					b.setPay(rs.getDouble("pay"));
					b.setCount(rs.getInt("count"));
					Calendar cal = Calendar.getInstance();
					java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
					 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
					 
					
					 list.add(b);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting book", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
	}
	
	//�û���ҳ��ȡ������ȡ������(���¶�ʱ��Ľ���)
	public List<Book> getAllBooksGiveup_user(int pageNow,int userId)
	{
		List<Book> list=new ArrayList<Book>();
		
		String sql="select * from (select a1.*,rownum rn from (select * from book where userId=? and statu='������ȡ��' order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1, userId);
		
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				b.setStatu(rs.getString("statu"));
				b.setPay_way(rs.getString("pay_way"));
				b.setPay(rs.getDouble("pay"));
				b.setCount(rs.getInt("count"));
				Calendar cal = Calendar.getInstance();
				java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
				 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
				 
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	/*
	 * �޸Ķ���״̬Ϊ�ѽӵ�
	 */
	public void upbook_recieve(int bookId)
	{
		String sql = "update book set statu='�̼��ѽӵ�' where bookId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * �޸Ķ���״̬Ϊ�����
	 */
	public void upbook_finish(int bookId)
	{
		String sql = "update book set statu='���������' where bookId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * ȡ������
	 */
	
	public void up_give_up(int bookId)
	{
		String sql = "update book set statu='������ȡ��' where bookId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * �޸Ķ�������״̬Ϊ������
	 */
	
	public void up_comment(int bookId)
	{
		String sql = "update book set comment_statu='������' where bookId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}

	/*
	 * ɾ������
	 */
	
	public void deleteBook(int bookId)
	{
		String sql = "delete from book where bookId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on delete", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * ����ɾ������
	 */
	
	public void deleteSomeBooks(int[] bookId)
	{
		String BooksAll="";
		 for(int i=0;i<bookId.length-1;i++)  
		  {  
			 BooksAll = BooksAll + bookId[i] + ","  ;  
		  }
		 BooksAll = BooksAll + bookId[bookId.length-1];  
		
		String sql="delete from book where bookId in("+BooksAll+")";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
		
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on delete book", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * �������ڻ�ȡ����id����
	 */
	
	public List<Book> getBookIdByDate(String date_str)
	{
     List<Book> list=new ArrayList<Book>();
		
		String sql="select * from  book where statu='���������' and to_char(dates_order,'yyyy-mm-dd')=?";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setString(1, date_str);
		
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	/*
	 * �����·ݻ�ȡ����id����
	 */
	
	public List<Book> getBookIdByMonth(String month_str)
	{
		 List<Book> list=new ArrayList<Book>();
			
			String sql="select * from  book where statu='���������' and to_char(dates_order,'yyyy-mm')=?";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
				 pstmt.setString(1, month_str);
			
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					Book b=new Book();
					
					b.setBookId(rs.getInt("bookId"));
					
					 list.add(b);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting book", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
	}
	
	/*
	 * ��ȡ����id����
	 */
	
	public List<Book> getBookId()
	{
		 List<Book> list=new ArrayList<Book>();
			
			String sql="select * from  book where statu='���������'";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
				
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					Book b=new Book();
					
					b.setBookId(rs.getInt("bookId"));
					
					 list.add(b);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting book", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
	}
	
}
