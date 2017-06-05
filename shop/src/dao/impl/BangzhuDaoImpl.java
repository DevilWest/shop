package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Bangzhu;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.BangzhuDao;









public class BangzhuDaoImpl extends HibernateDaoSupport implements  BangzhuDao{


	public void deleteBean(Bangzhu Bangzhu) {
		this.getHibernateTemplate().delete(Bangzhu);
		
	}

	public void insertBean(Bangzhu Bangzhu) {
		this.getHibernateTemplate().save(Bangzhu);
		
	}

	@SuppressWarnings("unchecked")
	public Bangzhu selectBean(String where) {
		List<Bangzhu> list = this.getHibernateTemplate().find("from Bangzhu " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Bangzhu "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Bangzhu> selectBeanList(final int start,final int limit,final String where) {
		return (List<Bangzhu>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Bangzhu> list = session.createQuery("from Bangzhu "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Bangzhu Bangzhu) {
		this.getHibernateTemplate().update(Bangzhu);
		
	}
	
	
}
