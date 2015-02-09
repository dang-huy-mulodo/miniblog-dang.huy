package mini.dao;

import java.util.List;

import mini.model.users;

public interface userDAO {
	public void save(users user);
	public void persist(users user);
	public void update(users user);
	public void saveorupdate(users user);
	public void delete(users user);
	
	public users get(int id);
	public users load(int id);
	//custom function
	public List<users> get_all_user();
	public users get_by_username(String username);
	public users get_by_email(String email);
	
	
	public List<users> search_user_by_username(String query);
	/* not yet
	public List<users> search_user_by_firstname(String firstname);
	public List<users> search_user_by_lastname(String lastname);
	*/
}
