package com.momei.service;

import java.sql.Connection;

import com.momei.dao.AdminDao;
import com.momei.domain.Admin;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class AdminService {
	
private static final AdminService instance = new AdminService();
	
	/**
	 * ȡ��ʵ�� 
	 * @return ʵ������
	 */
	public static AdminService getInstance() {
		return instance;
	}
	
	/**
	 * ���췽�� 
	 */
	private AdminService() {
	
	}
	
	/*
	 * ��½��֤
	 */
	public Admin Login(String userName,String passwd){
         Admin admin=null;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			AdminDao ad = new AdminDao(conn);
			DBUtils.beginTransaction(conn);
			admin=ad.Login(userName, passwd);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ�û�����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return admin;
		
	}
	
	/*
	 * �޸�����
	 */
   public void UpdatePasswd(String userName,String passwdNew){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			AdminDao ad=new AdminDao(conn);
			DBUtils.beginTransaction(conn);
		    ad.UpdatePasswd(userName, passwdNew);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�޸��������");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}

}
