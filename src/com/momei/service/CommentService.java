package com.momei.service;

import java.sql.Connection;
import java.util.Calendar;

import com.momei.dao.CommentDao;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class CommentService {
	
private static final CommentService instance = new CommentService();
	
	/**
	 * ȡ��ʵ�� 
	 * @return ʵ������
	 */
	public static CommentService getInstance() {
		return instance;
	}
	
	/**
	 * ���췽�� 
	 */
	private CommentService() {
	
	}
	
	//�������
	
	public void add(int userId,int bookId,String content)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			CommentDao cd=new CommentDao(conn);
			DBUtils.beginTransaction(conn);
			cd.add(userId, bookId,content);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("��Ӷ������۴���");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}

}
