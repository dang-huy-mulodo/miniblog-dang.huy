package mini.service.imp;

import java.util.Calendar;
import java.util.List;

import mini.dao.postDAO;
import mini.dao.userDAO;
import mini.model.posts;
import mini.resource.form.Post_form;
import mini.service.Post_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Post_service_imp implements Post_service{
	
	@Autowired
	private postDAO postdao;
	@Autowired
	private userDAO userdao;
	
	@Transactional
	public int Create_post(posts data) {
		try{
			return postdao.save(data);
		}catch(Exception e){return 0;}
	}
	
	@Transactional
	public boolean Check_post_own(int post_id,int user_id) {
		try{
			posts post = postdao.get(post_id);
			if(post.getUser().getId()!=user_id){
				return false;}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}
	
	@Transactional
	public boolean Edit_post(Post_form data){
		try{
			posts post = postdao.get(data.id);
			post.setTitle(data.title);
			post.setContent(data.content);
			post.setModified_at(Calendar.getInstance().getTime());
			postdao.update(post);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}

	@Transactional
	public boolean Edit_status_post(Post_form data) {
		try{
			posts post = postdao.get(data.id);
			post.setStatus(data.status);
			post.setModified_at(Calendar.getInstance().getTime());
			postdao.update(post);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}
	
	@Transactional
	public boolean Delete_post(Post_form data) {
		try{
			posts post = postdao.get(data.id);
			postdao.delete(post);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List Get_all_post() {
		try{
			return postdao.get_all_posts();
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List Get_all_post_by_userid(int user_id) {
		try{
			return postdao.get_all_posts_by_user_Id(user_id);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

	@Transactional
	public posts Get_post_by_postid(int post_id) {
		try{
			return postdao.get(post_id);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List Get_top_post(int limit) {
		try{
			return postdao.search_posts_by_create_at(limit);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List Search_post_by_title(String keysearch) {
		try{
			return postdao.search_posts_by_title(keysearch);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}
}
