package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.GoodsDao;
import com.momei.domain.Goods;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class GoodService {
	
private static final GoodService instance = new GoodService();
	
	/**
	 * ȡ��ʵ�� 
	 * @return ʵ������
	 */
	public static GoodService getInstance() {
		return instance;
	}
	
	/**
	 * ���췽�� 
	 */
	private GoodService() {
	
	}
	
	/**
	 * �����Ʒ
	 */
	public void addGoods(Goods goods){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.addGoods(goods);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�����Ʒ����");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * �޸���Ʒ
	 */
	public void UpdateGoods(Goods goods){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.UpdateGoods(goods);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�޸���Ʒ����");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * �޸���Ʒ״̬Ϊ�¼�
	 */
	public void updateGoodsStatu(int goodsId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.updateGoodsStatu(goodsId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�޸���Ʒ����");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * �޸���Ʒ����(����)
	 */
	public void update_count(int goodsId,int number)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.update_count(goodsId, number);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("�޸���Ʒ����");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * ɾ����Ʒ
	 */
	public void deleteGoods(int goodsId){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.deleteGoods(goodsId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("ɾ����Ʒ����");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * ����ɾ����Ʒ
	 */
	public void deleteGoodsSome(int int_checkGoods[]){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.deleteGoodsSome(int_checkGoods);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("����ɾ����Ʒ����");
		}finally{
			DBUtils.closeConnection(conn);
		}
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
			GoodsDao gd = new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			res=gd.getPageCountAndRowCount();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��Ʒ����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * ������Ʒ���ͻ�ȡrowCount��pageCount
	 */
	public int[] getPageCountAndRowCountByType(int goodsTypeId)
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			GoodsDao gd = new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			res=gd.getPageCountAndRowCountByType(goodsTypeId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��Ʒ����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	
	
	
	
	/*
	 * ��Ʒ��ҳ��ѯ
	 */
	public  List<Goods> getGoodsByPage(int pageNow)
	{
		 List<Goods> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				list=gd.getGoodsByPage(pageNow);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ��Ʒ����", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * ������Ʒ���ͷ�ҳ��ѯ
	 */
	public  List<Goods> getGoodsByPageByType(int pageNow,int goodsTypeId)
	{
		 List<Goods> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				list=gd.getGoodsByPageByType(pageNow,goodsTypeId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ��Ʒ����", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * ������Ʒ���Ͳ�ѯ
	 */
	public  List<Goods> getGoodsByType(int goodsTypeId)
	{
		 List<Goods> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				list=gd.getGoodsByType(goodsTypeId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ��Ʒ����", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * ��������id��ȡ������Ʒid
	 */
	public List<Integer> getGoodsIdByType(int goodsTypeId)
	{
		List<Integer> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			GoodsDao gd = new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			list=gd.getGoodsIdByType(goodsTypeId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��Ʒ����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	
	/*
	 * ��ѯ�����ϼ���Ʒ
	 */
	
	 public List<Goods> getAllGoods()
	 {
		 List<Goods> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				list=gd.getAllGoods();
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ��Ʒ����", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	 }
	 
	    /*
		 * ��ѯ������Ʒ
		 */
		 public List<Goods> getAll_Goods()
		 {
			 List<Goods> list=null;
			    Connection conn = null;
			    try{
					conn = DBUtils.getConnection();
					GoodsDao gd = new GoodsDao(conn);
					DBUtils.beginTransaction(conn);
					list=gd.getAll_Goods();
					DBUtils.commit(conn);
				} catch (ServiceException e) {
					throw e;
				} catch (Exception e) {
					DBUtils.rollback(conn);
					throw new ServiceException("��ѯ��Ʒ����", e);
				} finally {
					DBUtils.closeConnection(conn);
				}
				return list;
		 }
	
	/*
	 * ������Ʒid��ѯ��Ʒ
	 */
	
	public Goods getGoods(int goodsId)
	{
		Goods g=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				g=gd.getGoods(goodsId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ��Ʒ����", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return g;
	}
	
	/*
	 * ������Ʒ�������ϼ���Ʒ(��Ʒ����ͬ��)
	 */
	public List<Goods> getGoodsByName(String goodsName)
	{
		List<Goods> list=null;
		  Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				list=gd.getGoodsByName(goodsName);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ��Ʒ����", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}

	/*
	 * ģ����ѯ�ϼ���Ʒ
	 */
	public List<Goods> getGoodsByFind(String goodsName)
	{
		List<Goods> list=null;
		  Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
			    list=gd.getGoodsByFind(goodsName);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ��Ʒ����", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * ������Ʒid��ȡ��Ʒ������
	 */
	
	public String getTypeName(int goodsId)
	{
		String res=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			GoodsDao gd = new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
		    res=gd.getTypeName(goodsId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ��Ʒ����", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
	}
	
}
