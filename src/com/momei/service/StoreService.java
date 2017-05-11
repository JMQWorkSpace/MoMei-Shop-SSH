package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.StoreDao;
import com.momei.domain.Store;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class StoreService {

	private static final StoreService instance=new StoreService();
	
	/**
	 * ȡ��ʵ�� 
	 * @return ʵ������
	 */
	public static StoreService getInstance() {
		return instance;
	}
	
	/**
	 * ���췽�� 
	 */
	private StoreService() {
	
	}
	
	/*
	 * ����ղ�
	 */
	
	public void addStore(int goodsId,int userId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			StoreDao sd=new StoreDao(conn);
			DBUtils.beginTransaction(conn);
			sd.addStore(goodsId, userId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�����Ʒ�ղش���");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * �ж��û��Ƿ��Ѿ���ӹ�ĳ��Ʒ�ղ���
	 */
	
	public Store getJudge(int goodsId,int userId)
	{
		Store store=null;
			Connection conn = null;
			try{
				conn = DBUtils.getConnection();
				StoreDao sd=new StoreDao(conn);
				DBUtils.beginTransaction(conn);
				store=sd.getJudge(goodsId, userId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ�ղش���", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return store;
	}
	
	/*
	 * ��ѯ�û�ȫ���ղ���Ʒ
	 */
	public List<Store> getAllStore(int userId)
	{
		List<Store> list=null;
		  Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				StoreDao sd=new StoreDao(conn);
				DBUtils.beginTransaction(conn);
				list=sd.getAllStore(userId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ�û��ղش���", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
		
		return list;
	}
	
	/*
	 * ɾ���ղ�
	 */
	
	public void deleteStore(int storeId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			StoreDao sd=new StoreDao(conn);
			DBUtils.beginTransaction(conn);
			sd.deleteStore(storeId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("ɾ����Ʒ�ղش���");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * ������Ʒidɾ����Ʒ�ղ�
	 */
	
	public void deleteStoreByGoodsId(int goodsId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			StoreDao sd=new StoreDao(conn);
			DBUtils.beginTransaction(conn);
			sd.deleteStoreByGoodsId(goodsId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("ɾ����Ʒ�ղش���");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
}
