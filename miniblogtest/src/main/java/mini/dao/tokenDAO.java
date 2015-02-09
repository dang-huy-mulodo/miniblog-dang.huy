package mini.dao;

import java.util.List;

import mini.model.token;

public interface tokenDAO {
	public void save(token token);
	public void persist(token token);
	public void update(token token);
	public void saveorupdate(token token);
	public void delete(token token);
	
	public token get(int id);
	public token load(int id);
	//custom function
	public token get_token_by_access_token(String access_token);
	public List<token> get_token_by_userId(int user_id);
	public void clear_token_by_userId(int user_id);
}
