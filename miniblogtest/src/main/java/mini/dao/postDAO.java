package mini.dao;

import java.util.List;

import mini.model.posts;

public interface postDAO {
	public int save(posts post);
	public void persist(posts post);
	public void update(posts post);
	public void saveorupdate(posts post);
	public void delete(posts post);
	
	public posts get(int id);
	public posts load(int id);
	//custom function
	@SuppressWarnings("rawtypes")
	public List get_all_posts();
	@SuppressWarnings("rawtypes")
	public List get_all_posts_by_user_Id(int user_id);

	@SuppressWarnings("rawtypes")
	public List search_posts_by_create_at(int limit);
	@SuppressWarnings("rawtypes")
	public List search_posts_by_title(String keysearch);
	/* not yet
	*/
}
