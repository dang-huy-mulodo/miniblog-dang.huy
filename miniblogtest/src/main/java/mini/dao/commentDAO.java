package mini.dao;

import java.util.List;

import mini.model.comments;

public interface commentDAO {
	public int save(comments comment);
	public void persist(comments comment);
	public void update(comments comment);
	public void saveorupdate(comments comment);
	public void delete(comments comment);
	
	public comments get(int id);
	public comments load(int id);
	//custom function
	@SuppressWarnings("rawtypes")
	public List get_all_comments_by_userId(int user_id);
	@SuppressWarnings("rawtypes")
	public List get_all_comments_by_postId(int post_id);
	/* not yet
	public List<comments> search_comments_by_comment(String comment_content);
	*/
}
