package mini.service;

import mini.model.token;
import mini.model.users;

public interface Token_service {
	public token Check_access_token(String access_token);
	public String Create_token(users user);
	public boolean Clear_token_talbe_by_userid(int user_id);
}
