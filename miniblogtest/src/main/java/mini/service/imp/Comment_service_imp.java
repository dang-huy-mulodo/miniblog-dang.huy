package mini.service.imp;

import java.util.Calendar;
import java.util.List;

import mini.dao.commentDAO;
import mini.model.comments;
import mini.resource.form.Comment_form;
import mini.service.Comment_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class Comment_service_imp implements Comment_service{
	@Autowired
	private commentDAO commentdao;

	@Transactional
	public int Create_comment(comments data) {
		try{
			return commentdao.save(data);
		}catch(Exception e){return 0;}
	}

	@Transactional
	public boolean Check_comment_own(int comment_id, int user_id) {
		try{
			comments comment = commentdao.get(comment_id);
			if(comment.getUser().getId()!=user_id){
				return false;}
			return true;
		}catch(Exception e){return false;}
	}
	
	@Transactional
	public boolean Edit_comment(Comment_form data) {
		try{
			comments comment = commentdao.get(data.id);
			comment.setComment(data.comment);
			comment.setModified_at(Calendar.getInstance().getTime());
			commentdao.update(comment);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}

	@Transactional
	public boolean Delete_comment(Comment_form data) {
		try{
			comments comment = commentdao.get(data.id);
			commentdao.delete(comment);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}

	@Transactional
	public comments Get_comment_by_id(int comment_id) {
		try{
			return commentdao.get(comment_id);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List Get_all_comment_by_userid(int user_id) {
		try{
			return commentdao.get_all_comments_by_userId(user_id);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List Get_all_comment_by_postid(int post_id) {
		try{
			return commentdao.get_all_comments_by_postId(post_id);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

}
