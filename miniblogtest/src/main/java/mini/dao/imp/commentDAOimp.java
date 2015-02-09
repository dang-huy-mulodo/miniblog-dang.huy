package mini.dao.imp;

import java.util.List;

import mini.dao.commentDAO;
import mini.model.comments;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class commentDAOimp implements commentDAO{
	
	@Autowired
	private SessionFactory session;
	
	public SessionFactory getSession() {
		return session;
	}

	@Override
	public int save(comments comment) {
		return (int) session.getCurrentSession().save(comment);
	}

	@Override
	public void persist(comments comment) {
		session.getCurrentSession().persist(comment);
	}

	@Override
	public void update(comments comment) {
		session.getCurrentSession().update(comment);
	}

	@Override
	public void saveorupdate(comments comment) {
		session.getCurrentSession().saveOrUpdate(comment);
	}

	@Override
	public void delete(comments comment) {
		session.getCurrentSession().delete(comment);
	}

	@Override
	public comments get(int id) {
		return (comments) session.getCurrentSession().get(comments.class,id);
	}

	@Override
	public comments load(int id) {
		return (comments) session.getCurrentSession().load(comments.class,id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List get_all_comments_by_userId(int user_id) {
		List<Object[]> list = session.getCurrentSession().createSQLQuery(
			    "SELECT id,comment,user_id FROM `comments` WHERE `user_id` user_id=:user_id")
			    .setParameter("user_id", user_id)
			    .list(); 
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List get_all_comments_by_postId(int post_id) {
		List<Object[]> list = session.getCurrentSession().createSQLQuery(
			    "SELECT id,comment,user_id FROM `comments` WHERE `post_id` post_id=:post_id")
			    .setParameter("post_id", post_id)
			    .list(); 
		return list;
	}

}
