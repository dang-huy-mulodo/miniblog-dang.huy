package mini.dao.imp;

import java.util.List;

import mini.dao.tokenDAO;
import mini.model.token;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class tokenDAOimp implements tokenDAO {
	
	@Autowired
	private SessionFactory session;
	
	public SessionFactory getSession() {
		return session;
	}
	
	@Override
	public void save(token token) {
		session.getCurrentSession().save(token);
	}

	@Override
	public void persist(token token) {
		session.getCurrentSession().persist(token);
	}

	@Override
	public void update(token token) {
		session.getCurrentSession().update(token);
	}

	@Override
	public void saveorupdate(token token) {
		session.getCurrentSession().saveOrUpdate(token);
	}

	@Override
	public void delete(token token) {
		session.getCurrentSession().delete(token);
	}

	@Override
	public token get(int id) {
		return (token) session.getCurrentSession().get(token.class, id);
	}

	@Override
	public token load(int id) {
		return (token) session.getCurrentSession().load(token.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public token get_token_by_access_token(String access_token) {
		Criteria criteria = session.getCurrentSession().createCriteria(token.class);
		criteria.add(Restrictions.eq("access_token", access_token));
		List<token> result = criteria.list();
		if(result.size()!=1){ return null;}
		else{return result.get(0);}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<token> get_token_by_userId(int user_id) {
		try{
			return session.getCurrentSession()
			.createQuery("select token from token as token where token.user_id=:user_id")
			.setParameter("user_id",user_id).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void clear_token_by_userId(int user_id) {
			session.getCurrentSession()
			.createQuery("delete from token where user_id=:user_id")
			.setParameter("user_id",user_id)
			.executeUpdate();
	}

}
