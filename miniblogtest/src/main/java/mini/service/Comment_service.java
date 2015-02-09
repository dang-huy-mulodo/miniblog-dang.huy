package mini.service;

import java.util.List;

import mini.model.comments;
import mini.resource.form.Comment_form;

public interface Comment_service {
	public int Create_comment(comments data);
	public boolean Check_comment_own(int comment_id,int user_id);
	public boolean Edit_comment(Comment_form data);
	public boolean Delete_comment(Comment_form data);
	
	public comments Get_comment_by_id(int comment_id);
	@SuppressWarnings("rawtypes")
	public List Get_all_comment_by_userid(int user_id);
	@SuppressWarnings("rawtypes")
	public List Get_all_comment_by_postid(int post_id);
	
}
