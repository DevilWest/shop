package dao;

import java.util.List;

import model.Jiaoyi;


public interface JiaoyiDao  {
	
	
	
	public void insertBean(Jiaoyi Jiaoyi);
	
	public void deleteBean(Jiaoyi Jiaoyi);
	
	public void updateBean(Jiaoyi Jiaoyi);

	public Jiaoyi selectBean(String where);
	
	public List<Jiaoyi> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
