package com.momei.service;

public class ScoreGoodsService {
	
private static final ScoreGoodsService instance = new ScoreGoodsService();
	
	/**
	 * ȡ��ʵ�� 
	 * @return ʵ������
	 */
	public static ScoreGoodsService getInstance() {
		return instance;
	}
	
	/**
	 * ���췽�� 
	 */
	private ScoreGoodsService() {
	
	}

}
