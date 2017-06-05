package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Jiaoyi;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.JiaoyiDao;









public class JiaoyiDaoImpl extends HibernateDaoSupport implements  JiaoyiDao{


	public void deleteBean(Jiaoyi Jiaoyi) {
		this.getHibernateTemplate().delete(Jiaoyi);
		
	}

	public void insertBean(Jiaoyi Jiaoyi) {
		this.getHibernateTemplate().save(Jiaoyi);
		
	}

	@SuppressWarnings("unchecked")
	public Jiaoyi selectBean(String where) {
		List<Jiaoyi> list = this.getHibernateTemplate().find("from Jiaoyi " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Jiaoyi "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Jiaoyi> selectBeanList(final int start,final int limit,final String where) {
		return (List<Jiaoyi>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Jiaoyi> list = session.createQuery("from Jiaoyi "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Jiaoyi Jiaoyi) {
		this.getHibernateTemplate().update(Jiaoyi);
		
	}
	
	
}
