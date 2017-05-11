package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.SeqDao;
import com.momei.domain.BookSeqence;
import com.momei.domain.Type_Date_Count;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class SeqService {
	
private static final SeqService instance = new SeqService();
	
	/**
	 * ȡ��ʵ�� 
	 * @return ʵ������
	 */
	public static SeqService getInstance() {
		return instance;
	}
	
	/**
	 * ���췽�� 
	 */
	private SeqService() {
	
	}

	//��Ӷ���������
	public void addSeq(int goodsId,int bookId,int count)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				SeqDao sd = new SeqDao(conn);
				DBUtils.beginTransaction(conn);
				sd.addSeq(goodsId, bookId, count);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��Ӷ������д���", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	//���ݶ����Ų�ѯ��������
	public List<BookSeqence> getSeq(int bookId)
	{
		 List<BookSeqence> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				SeqDao sd = new SeqDao(conn);
				DBUtils.beginTransaction(conn);
				list=sd.getSeq(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("��ѯ�������д���", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	//���ݶ�����ɾ����������
	public void deleteSeqByBookId(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				SeqDao sd = new SeqDao(conn);
				DBUtils.beginTransaction(conn);
				sd.deleteSeqByBookId(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("ɾ���������д���", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	//������������������ɾ����������
	public void deleteSeqBySomeBooks(int[] bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				SeqDao sd = new SeqDao(conn);
				DBUtils.beginTransaction(conn);
				sd.deleteSeqBySomeBooks(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("ɾ���������д���", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * ���ݶ���id��ȡ�������еĸɹ�������
	 */
	public List<Type_Date_Count> getType_Date_count(int bookId)
	{
		List<Type_Date_Count> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			SeqDao sd = new SeqDao(conn);
			DBUtils.beginTransaction(conn);
			list=sd.getType_Date_count(bookId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ�������д���", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	 /*
	 * ������Ʒ�ŷ���������еĶ��������к�
	 */
	public List<Integer> getSeqIdByGoodsId(int goodsId)
	{
		List<Integer> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			SeqDao sd = new SeqDao(conn);
			DBUtils.beginTransaction(conn);
			list=sd.getSeqIdByGoodsId(goodsId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("��ѯ�������д���", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}

}
