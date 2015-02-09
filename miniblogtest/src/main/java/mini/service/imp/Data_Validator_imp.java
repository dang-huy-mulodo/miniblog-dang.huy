package mini.service.imp;

import org.springframework.stereotype.Service;

import mini.resource.System_value;
import mini.resource.form.Comment_form;
import mini.resource.form.Post_form;
import mini.resource.form.User_Change_Password_form;
import mini.resource.form.User_Input_form;
import mini.service.Data_Validator;
@Service
public class Data_Validator_imp  implements Data_Validator{

	@Override
	public boolean Validate_user_signup_data(User_Input_form data) {
		if(
				data.username !=null && 
				data.username.length() <= System_value.users_username_MAX_LENGTH &&
				data.username.length() >= System_value.users_username_MIN_LENGTH && 
				data.username.matches(System_value.users_username_STRING_RANGE)&&
				
				data.password !=null &&
				data.password.length() <= System_value.users_password_MAX_LENGTH &&
				data.password.length() >= System_value.users_password_MIN_LENGTH &&
				data.password.matches(System_value.users_password_STRING_RANGE) &&
				
				data.firstname !=null &&
				data.firstname.length() <= System_value.users_firstname_MAX_LENGTH &&
				data.firstname.matches(System_value.users_firstname_STRING_RANGE) &&
				
				data.lastname !=null &&
				data.lastname.length() <= System_value.users_lastname_MAX_LENGTH &&
				data.lastname.matches(System_value.users_lastname_STRING_RANGE) &&
				
				data.email !=null &&
				data.email.length() <= System_value.users_email_MAX_LENGTH &&
				data.email.matches(System_value.users_email_STRING_RANGE)
			){ return true;}
			else
			{ return false;}
	}

	@Override
	public boolean Validate_user_login_data(User_Input_form data) {
		if(
				data.username !=null && 
				data.username.length() <= System_value.users_username_MAX_LENGTH &&
				data.username.length() >= System_value.users_username_MIN_LENGTH && 
				data.username.matches(System_value.users_username_STRING_RANGE)&&
				
				data.password !=null &&
				data.password.length() <= System_value.users_password_MAX_LENGTH &&
				data.password.length() >= System_value.users_password_MIN_LENGTH &&
				data.password.matches(System_value.users_password_STRING_RANGE)
		){ return true;}
		else
		{ return false;}
	}

	@Override
	public boolean Validate_Access_Token(String access_token) {
		if(access_token!=null && access_token.length()==System_value.access_token_LENGTH)
		{ return true;}
		else
		{ return false;}
	}

	@Override
	public boolean Validate_user_update_data(User_Input_form data) {
		if(
				data.firstname !=null &&
				data.firstname.length() <= System_value.users_firstname_MAX_LENGTH &&
				data.firstname.matches(System_value.users_firstname_STRING_RANGE) &&
				
				data.lastname !=null &&
				data.lastname.length() <= System_value.users_lastname_MAX_LENGTH &&
				data.lastname.matches(System_value.users_lastname_STRING_RANGE)
		){ return true;}
		else
		{ return false;}
	}
	

	@Override
	public boolean Validate_Change_Password_data(User_Change_Password_form data) {
		if(
				data.old_password !=null &&
				data.old_password.length() <= System_value.users_password_MAX_LENGTH &&
				data.old_password.length() >= System_value.users_password_MIN_LENGTH &&
				data.old_password.matches(System_value.users_password_STRING_RANGE) &&
				
				data.new_password !=null &&
				data.new_password.length() <= System_value.users_password_MAX_LENGTH &&
				data.new_password.length() >= System_value.users_password_MIN_LENGTH &&
				data.new_password.matches(System_value.users_password_STRING_RANGE)
		){ return true;}
		else
		{ return false;}
	}

	@Override
	public boolean Validate_user_get_info_parameter(String mode) {
		if(
				mode !=null &&
				(mode.equals("current")||
				mode.equals("id"))
		){ return true;}
		else
		{ return false;}
	}

	@Override
	public boolean Validate_Search_query(String query) {
		if(
				query !=null && 
				query.length() <= System_value.users_username_MAX_LENGTH &&
				query.matches(System_value.users_username_STRING_RANGE)
		){ return true;}
		else
		{ return false;}
	}

	@Override
	public boolean Validate_post_data(Post_form data) {
		if(
				data.title !=null && 
				data.title.length() <= System_value.post_title_MAX_LENGTH &&
				data.title.length() >= System_value.post_title_MIN_LENGTH && 
				
				data.content !=null && 
				data.content.length() <= System_value.post_content_MAX_LENGTH &&
				data.content.length() >= System_value.post_content_MIN_LENGTH
		){ return true;}
		else
		{ return false;}
	}

	@Override
	public boolean Validate_post_get_parameter(String mode) {
		if(
				mode !=null &&
				(mode.equals("all")||
				mode.equals("current")||
				mode.equals("postid")||
				mode.equals("userid")||
				mode.equals("top")||
				mode.equals("title"))
		){ return true;}
		else
		{ return false;}
	}

	@Override
	public boolean Validate_comment(Comment_form data) {
		if(
			data.comment !=null && 
			data.comment.length() <= System_value.comment_MAX_LENGTH &&
			data.comment.length() >= System_value.comment_MIN_LENGTH
		){ return true;}
		else
		{ return false;}
	}

	@Override
	public boolean Validate_comment_get_parameter(String mode) {
		if(
				mode !=null &&
				(mode.equals("id")||
				mode.equals("userid")||
				mode.equals("postid"))
		){ return true;}
		else
		{ return false;}
	}

	
}
