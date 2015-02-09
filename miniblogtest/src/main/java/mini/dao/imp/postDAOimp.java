package mini.dao.imp;

import java.util.List;

import mini.dao.postDAO;
import mini.model.posts;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class postDAOimp implements postDAO{
	
	@Autowired
	private SessionFactory session;
	
	public SessionFactory getSession() {
		return session;
	}

	@Override
	public int save(posts post) {
		return (int) session.getCurrentSession().save(post);
	}

	@Override
	public void persist(posts post) {
		session.getCurrentSession().persist(post);
	}

	@Override
	public void update(posts post) {
		session.getCurrentSession().update(post);
	}

	@Override
	public void saveorupdate(posts post) {
		session.getCurrentSession().saveOrUpdate(post);
	}

	@Override
	public void delete(posts post) {
		session.getCurrentSession().delete(post);
	}

	@Override
	public posts get(int id) {
		return (posts) session.getCurrentSession().get(posts.class, id);
	}

	@Override
	public posts load(int id) {
		return (posts) session.getCurrentSession().load(posts.class, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List get_all_posts_by_user_Id(int user_id) {
		List<Object[]> list = session.getCurrentSession().createSQLQuery(
			    "SELECT id,title,status FROM `posts` WHERE user_id=:user_id")
			    .setParameter("user_id", user_id)
			    .list(); 
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List get_all_posts() {
		List<Object[]> list = session.getCurrentSession().createSQLQuery(
			    "SELECT id,title,status FROM `posts` WHERE 1")
			    .list(); 
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List search_posts_by_create_at(int limit) {
		List<Object[]> list = session.getCurrentSession().createSQLQuery(
			    "SELECT id,title,status FROM `posts` ORDER BY `create_at` DESC LIMIT :limit")
			    .setParameter("limit", limit)
			    .list(); 
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List search_posts_by_title(String keysearch) {
		List<Object[]> list = session.getCurrentSession().createSQLQuery(
			    "SELECT id,title,status FROM `posts` WHERE `title` LIKE :keysearch")
			    .setParameter("keysearch", "%"+keysearch+"%")
			    .list(); 
		return list;
	}
}
