package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.UserDao;
import com.momei.domain.UserInfo;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class UserService {
	
private static final UserService instance = new UserService();
	
	/**
	 * ȡ��ʵ�� 
	 * @return ʵ������
	 */
	public static UserService getInstance() {
		return instance;
	}
	
	/**
	 * ���췽�� 
	 */
	private UserService() {
	
	}
	
	/**
	 * �����û�
	 * user �û���Ϣʵ��
	 */
	public void addUser(UserInfo user){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.addUser(user);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("����û�����");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * ����û�ͷ��
	 */
	
	public void addUserTx(String userName,String pic){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.addUserTx(userName, pic);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("����û�ͷ�����");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * �����û�����
	 */
	public void addScore(int userId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.addScore(userId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("����û����ִ���");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	
	/*
	 * �޸��û��ֻ���
	 */
	
	public void UpdatePh(String userName,String phoneNum){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.UpdatePh(userName, phoneNum);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�޸��û��ֻ��Ŵ���");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * �޸��û�������
	 */
	
	public void UpdateEmail(String userName,String EmailNew){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.UpdateEmail(userName, EmailNew);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�޸��û����������");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * �޸�����
	 */
	
	public void UpdatePasswd(String userName,String passwdNew){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.UpdatePasswd(userName, passwdNew);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�޸��û��������");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	
	/*
	 * ��֤�û����Ƿ����
	 */
	
	public boolean findUser(String username){
        boolean b=false;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			 if(ud.findUser(username)==true)
			 {
				 b=true;
			 }else
			 {
				 b=false;
			 }
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ�û�����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
		
	}
	
	/*
	 * ��֤���ֻ����Ƿ����
	 */
	
	public boolean findPhoneNum(String phoneNum){
        boolean b=false;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			 if(ud.findPhoneNum(phoneNum)==true)
			 {
				 b=true;
			 }else
			 {
				 b=false;
			 }
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ�û�����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
		
	}
	
	/*
	 * ��֤ע�������Ƿ����
	 */
	
	public boolean findEmail(String Email){
        boolean b=false;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			 if(ud.findEmail(Email)==true)
			 {
				 b=true;
			 }else
			 {
				 b=false;
			 }
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ�û�����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
		
	}
	
	/*
	 * ��½��֤---�û������ֻ�������
	 */
	public UserInfo Login(String uName,String passwd){
        UserInfo user=null;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			user=ud.Login(uName, passwd);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ�û�����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return user;
		
	}
	
	/*
	 * ������֤(�޸�����ǰ)
	 */
	public boolean JudgePasswd(String userName,String passwd){
        
		boolean b=false;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			b=ud.JudgePasswd(userName, passwd);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ�û�����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
		
	}
	
	/*
	 * ��ȡrowCount��pageCount
	 */
	public int[] getPageCountAndRowCount()
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			 UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			res=ud.getPageCountAndRowCount();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ�û�����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * �û���ҳ��ѯ
	 */
	public  List<UserInfo> getUsersByPage(int pageNow)
	{
		 List<UserInfo> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				 UserDao ud = new UserDao(conn);
				DBUtils.beginTransaction(conn);
				list=ud.getUsersByPage(pageNow);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ�û�����", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * �����û�����ȡ�û�
	 */
	
	public UserInfo findUserByName(String userName){
        UserInfo user=null;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			user=ud.findUserByName(userName);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ�û�����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return user;
		
	}
	
}
