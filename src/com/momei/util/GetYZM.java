package com.momei.util;

//�������6λ������֤��
public class GetYZM {
	
	//������֤�뷽��
	public static String Gy()
	{
		StringBuffer sb=new StringBuffer();
		int temp_num;
		for(int i=0;i<6;i++)
		{
			temp_num=(int)(Math.random()*10);
			sb.append(temp_num);
		}
		
		return sb.toString();
	}
	
}
