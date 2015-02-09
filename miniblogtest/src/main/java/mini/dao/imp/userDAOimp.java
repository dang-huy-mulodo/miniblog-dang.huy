package mini.dao.imp;

import java.util.List;

import mini.dao.userDAO;
import mini.model.users;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class userDAOimp implements userDAO{
	
	@Autowired
	private SessionFactory session;

	@Override
	public void save(users user) {
		session.getCurrentSession().save(user);
	}

	@Override
	public void persist(users user) {
		session.getCurrentSession().persist(user);
	}

	@Override
	public void update(users user) {
		session.getCurrentSession().update(user);
	}

	@Override
	public void saveorupdate(users user) {
		session.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public void delete(users user) {
		session.getCurrentSession().delete(user);
	}

	@Override
	public users get(int id) {
		return (users) session.getCurrentSession().get(users.class, id);
	}

	@Override
	public users load(int id) {
		return (users) session.getCurrentSession().load(users.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<users> get_all_user() {
		return session.getCurrentSession().createQuery("from users").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public users get_by_username(String username) {
		Criteria criteria = session.getCurrentSession().createCriteria(users.class);
		criteria.add(Restrictions.eq("username", username));
		List<users> result = criteria.list();
		if(result.size()!=1){ return null;}
		else{return result.get(0);}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public users get_by_email(String email) {
		Criteria criteria = session.getCurrentSession().createCriteria(users.class);
		criteria.add(Restrictions.eq("email", email));
		List<users> result = criteria.list();
		if(result.size()!=1){ return null;}
		else{return result.get(0);}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<users> search_user_by_username(String query) {
		Criteria criteria = session.getCurrentSession().createCriteria(users.class);
		criteria.add(Restrictions.like("username", query,MatchMode.ANYWHERE));
		return criteria.list();
	}
}
