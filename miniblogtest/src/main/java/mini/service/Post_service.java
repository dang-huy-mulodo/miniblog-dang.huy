package mini.service;

import java.util.List;

import mini.model.posts;
import mini.resource.form.Post_form;

public interface Post_service{
	public int Create_post(posts data);
	public boolean Check_post_own(int post_id,int user_id);
	public boolean Edit_post(Post_form data);
	public boolean Edit_status_post(Post_form data);
	public boolean Delete_post(Post_form data);
	
	@SuppressWarnings("rawtypes")
	public List Get_all_post();
	@SuppressWarnings("rawtypes")
	public List Get_all_post_by_userid(int user_id);
	public posts Get_post_by_postid(int post_id);
	@SuppressWarnings("rawtypes")
	public List Get_top_post(int limit);
	@SuppressWarnings("rawtypes")
	public List Search_post_by_title(String keysearch);
}
