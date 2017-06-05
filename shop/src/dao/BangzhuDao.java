package dao;

import java.util.List;

import model.Bangzhu;


public interface BangzhuDao  {
	
	
	
	public void insertBean(Bangzhu Bangzhu);
	
	public void deleteBean(Bangzhu Bangzhu);
	
	public void updateBean(Bangzhu Bangzhu);

	public Bangzhu selectBean(String where);
	
	public List<Bangzhu> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
