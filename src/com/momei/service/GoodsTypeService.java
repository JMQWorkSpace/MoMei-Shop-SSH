package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.GoodsTypeDao;
import com.momei.domain.GoodsType;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class GoodsTypeService {
	
private static final GoodsTypeService instance = new GoodsTypeService();
	
	/**
	 * ȡ��ʵ�� 
	 * @return ʵ������
	 */
	public static GoodsTypeService getInstance() {
		return instance;
	}
	
	/**
	 * ���췽�� 
	 */
	private GoodsTypeService() {
	
	}


	/**
	 * �����Ʒ����
	 */
	public void addGoodsType(GoodsType gt){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsTypeDao gd=new GoodsTypeDao(conn);
			DBUtils.beginTransaction(conn);
			gd.addGoodsType(gt);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�����Ʒ�������");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * �޸���Ʒ����
	 */
	
	public void uptype(GoodsType gt)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsTypeDao gd=new GoodsTypeDao(conn);
			DBUtils.beginTransaction(conn);
			gd.uptype(gt);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�޸���Ʒ�������");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * �޸���Ʒ����״̬Ϊ�¼�
	 */
	public void updateTypeStatu(int goodsTypeId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsTypeDao gd=new GoodsTypeDao(conn);
			DBUtils.beginTransaction(conn);
			gd.updateTypeStatu(goodsTypeId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�޸���Ʒ�������");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * ɾ����Ʒ����
	 */
	
	  public void deltype(int goodsTypeId)
	  {
		  Connection conn=null;
			try{
				conn=DBUtils.getConnection();
				GoodsTypeDao gd=new GoodsTypeDao(conn);
				DBUtils.beginTransaction(conn);
				gd.deltype(goodsTypeId);
				DBUtils.commit(conn);
			}catch(ServiceException e){
				throw e;
			}catch(Exception e){
				DBUtils.rollback(conn);
				throw new ServiceException("ɾ����Ʒ�������");
			}finally{
				DBUtils.closeConnection(conn);
			}
	  }
	
	/*
	 * ���������Ʒ����
	 */
	
	public  List<GoodsType> getGoodsType()
	{
		 List<GoodsType> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsTypeDao gd=new GoodsTypeDao(conn);
				DBUtils.beginTransaction(conn);
			    list=gd.getGoodsType();
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ��Ʒ�������", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * ��������ϼ���Ʒ����
	 */
	public  List<GoodsType> getGoods_Type()
	{
		 List<GoodsType> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsTypeDao gd=new GoodsTypeDao(conn);
				DBUtils.beginTransaction(conn);
			    list=gd.getGoods_Type();
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ��Ʒ�������", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
}
