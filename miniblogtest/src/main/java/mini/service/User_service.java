package mini.service;

import java.util.List;

import mini.model.token;
import mini.model.users;
import mini.resource.form.Search_result_form;
import mini.resource.form.User_Change_Password_form;
import mini.resource.form.User_Input_form;

public interface User_service {

	public boolean Check_User_Exist(String username);
	public boolean Check_Email_Exist(String email);

	public users Get_user_by_username(String username);
	public boolean Delete_user(String username);
	
	public boolean Insert_user(users user);	
	public String Login_user(User_Input_form data);
	public boolean Logout_user(token token);	
	public boolean Update_user_info(User_Input_form data,int user_id);
	public users Get_user_by_id(int id);
	public boolean Change_password(User_Change_Password_form data,int user_id);
	public List<Search_result_form> Search_user_by_username(String query);
}
