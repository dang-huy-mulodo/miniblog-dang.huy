package mini.service;

import mini.resource.form.Comment_form;
import mini.resource.form.Post_form;
import mini.resource.form.User_Change_Password_form;
import mini.resource.form.User_Input_form;

public interface Data_Validator {
	public boolean Validate_user_signup_data(User_Input_form data);
	public boolean Validate_user_login_data(User_Input_form data);
	public boolean Validate_Access_Token(String access_token);
	public boolean Validate_user_update_data(User_Input_form data);
	public boolean Validate_user_get_info_parameter(String mode);
	public boolean Validate_Change_Password_data(User_Change_Password_form data);
	public boolean Validate_Search_query(String query);
	
	public boolean Validate_post_data(Post_form data);
	public boolean Validate_post_get_parameter(String mode);
	
	public boolean Validate_comment(Comment_form data);
	public boolean Validate_comment_get_parameter(String mode);
}
