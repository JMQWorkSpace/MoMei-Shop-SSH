package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.AddressDao;
import com.momei.domain.Address;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class AddressService {
	
private static final AddressService instance = new AddressService();
	
	/**
	 * ȡ��ʵ�� 
	 * @return ʵ������
	 */
	public static AddressService getInstance() {
		return instance;
	}
	
	/**
	 * ���췽�� 
	 */
	private AddressService() {
	
	}

	/**
	 * �����û���ַ
	 */
	public void addAddress(int userId,String addressName,String phoneNum){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			AddressDao adDao=new AddressDao(conn);
			DBUtils.beginTransaction(conn);
			adDao.addAddress(userId, addressName,phoneNum);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("����û���ַ����");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * �޸��û���ַ
	 */
	public void updateAddress(int addressId,String addressName,String phoneNum){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			AddressDao adDao=new AddressDao(conn);
			DBUtils.beginTransaction(conn);
			adDao.updateAddress(addressId, addressName,phoneNum);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�޸��û���ַ����");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * ɾ���û���ַ
	 */
	public void deleteAddress(int addressId){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			AddressDao adDao=new AddressDao(conn);
			DBUtils.beginTransaction(conn);
			adDao.deleteAddress(addressId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("ɾ���û���ַ����");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * �����û�id��ȡ���е�ַ
	 */
	
	public List<Address> getAllAddressByUser(int userId)
	{
		List<Address> list=null;
		  Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				AddressDao adDao=new AddressDao(conn);
				DBUtils.beginTransaction(conn);
				list=adDao.getAllAddressByUser(userId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ�û���ַ����", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
		
		return list;
	}
}
