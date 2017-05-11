package com.momei.service;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import com.momei.dao.BookDao;
import com.momei.domain.Book;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;


public class BookService {
	
private static final BookService instance = new BookService();
	
	/**
	 * ȡ��ʵ�� 
	 * @return ʵ������
	 */
	public static BookService getInstance() {
		return instance;
	}
	
	/**
	 * ���췽�� 
	 */
	private BookService() {
	
	}

	//��Ӷ���
	public void addBook(int bookId,int userId,String message,String send_time,String pay_way,String phoneNum,String address,Calendar dates_order,int count,double pay)
	{
			 Connection conn = null;
			 try{
					conn = DBUtils.getConnection();
					BookDao bd = new BookDao(conn);
					DBUtils.beginTransaction(conn);
					bd.addBook(bookId,userId, message, send_time, pay_way, phoneNum, address, dates_order,count,pay);
					DBUtils.commit(conn);
				} catch (ServiceException e) {
					throw e;
				} catch (Exception e) {
					DBUtils.rollback(conn);
					throw new ServiceException("��Ӷ�������", e);
				} finally {
					DBUtils.closeConnection(conn);
				}
	}
	
	//��ѯ����Id���ֵ
	public int find_Max_bookId()
	{
		int result=999;//Ĭ��999
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    result=bd.find_Max_bookId();
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ��������", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
		
		return result;
	}
	
	//��ѯ�û����¶���(�¶�ʱ��ǰ��)
	public List<Book> getBook_Recent(int userId)
	{
		 List<Book> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
				list=bd.getBook_Recent(userId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ��������", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	//���ݶ����Ų�ѯ����
	public Book getBookById(int bookId)
	{
		Book b=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			b=bd.getBookById(bookId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
	}
	
	/*
	 * ���ݶ����źͶ���״̬��ѯ����
	 */
	public Book getBookById(int bookId,String statu)
	{
		Book b=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			b=bd.getBookById(bookId, statu);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
	}
	
	
	/*
	 * ��ȡrowCount��pageCount(���¶���)
	 */
	public int[] getPageCountAndRowCount()
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCount();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
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
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCount_recieve();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * ��ȡrowCount��pageCount(����ɶ���)
	 */
	public int[] getPageCountAndRowCount_finish()
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCount_finish();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * ��ȡrowCount��pageCount(��ȡ������)
	 */
	public int[] getPageCountAndRowCount_giveup()
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCount_giveup();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * ��ȡrowCount��pageCount(�����û������ж���)
	 */
	public int[] getPageCountAndRowCount_user(int userId)
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCount_user(userId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * ��ȡrowCount��pageCount(�����û���������ȡ������)
	 */
	public int[] getPageCountAndRowCountGiveup_user(int userId)
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCountGiveup_user(userId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	
	
	//����Ա��ҳ��ȡ���¶���(���¶�ʱ��Ľ���)
	public List<Book> getAllNewBooks(int pageNow)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllNewBooks(pageNow);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	//����Ա��ҳ��ȡ�ѽ��ն���(���¶�ʱ��Ľ���)
	public List<Book> getAllRecieveBooks(int pageNow)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllRecieveBooks(pageNow);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	//����Ա��ҳ��ȡ����ɶ���(���¶�ʱ��Ľ���)
	public List<Book> getAllFinishBooks(int pageNow)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllFinishBooks(pageNow);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	
	//����Ա��ҳ��ȡ��ȡ������(���¶�ʱ��Ľ���)
	public List<Book> getAllGiveupBooks(int pageNow)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllGiveupBooks(pageNow);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	//�û���ҳ��ȡ���ж���(���¶�ʱ��Ľ���)
	public List<Book> getAllBooks_user(int pageNow,int userId)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllBooks_user(pageNow, userId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	//�û���ҳ��ȡ������ȡ������(���¶�ʱ��Ľ���)
	public List<Book> getAllBooksGiveup_user(int pageNow,int userId)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllBooksGiveup_user(pageNow, userId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	/*
	 * �޸Ķ���״̬Ϊ�ѽӵ�
	 */
	public void upbook_recieve(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.upbook_recieve(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("�޸Ķ�������", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * �޸Ķ���״̬Ϊ�����
	 */
	public void upbook_finish(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.upbook_finish(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("�޸Ķ�������", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * ȡ������
	 */
	
	public void up_give_up(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.up_give_up(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("�޸Ķ�������", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	
	/*
	 * �޸Ķ�������״̬Ϊ������
	 */
	
	public void up_comment(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.up_comment(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("�޸Ķ�������״̬����", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * ɾ������
	 */
	
	public void deleteBook(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.deleteBook(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("ɾ����������", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * ����ɾ������
	 */
	
	public void deleteSomeBooks(int[] bookId)
	{
		Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.deleteSomeBooks(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("ɾ����������", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * �������ڻ�ȡ����id����
	 */
	
	public List<Book> getBookIdByDate(String date_str)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getBookIdByDate(date_str);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	/*
	 * �����·ݻ�ȡ����id����
	 */
	
	public List<Book> getBookIdByMonth(String month_str)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getBookIdByMonth(month_str);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	/*
	 * ��ȡ����id����
	 */
	
	public List<Book> getBookId()
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getBookId();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��������", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	

}
