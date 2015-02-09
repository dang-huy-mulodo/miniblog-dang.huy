package mini.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mini.dao.tokenDAO;
import mini.dao.userDAO;
import mini.model.token;
import mini.model.users;
import mini.resource.form.Search_result_form;
import mini.resource.form.User_Change_Password_form;
import mini.resource.form.User_Input_form;
import mini.service.Token_service;
import mini.service.User_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User_service_imp implements User_service{
	
	@Autowired
	private userDAO userdao;
	@Autowired
	private tokenDAO tokendao;
	@Autowired
	private Token_service tokenservice;
	
	@Transactional
	public boolean Check_User_Exist(String username) {
		try{
			if(userdao.get_by_username(username)!=null)
				return true;
			else{return false;}
		}catch(Exception e){return false;}
	}
	
	@Transactional
	public boolean Check_Email_Exist(String email) {
		try{
			if(userdao.get_by_email(email)!=null)
				{return true;}
			else{return false;}
		}catch(Exception e){return false;}
	}
	
	@Transactional
	public boolean Insert_user(users user) {
		try{
			userdao.save(user);
			return true;
		}catch(Exception e){return false;}
	}
	
	@Transactional
	public String Login_user(User_Input_form data){
		users user;
		try{
			user = userdao.get_by_username(data.username);
		}catch(Exception e){
			e.printStackTrace();
			return null;}

		if(user==null || user.getPassword().compareTo(data.Encrypt_password())!=0){
			return null;}
		
		return tokenservice.Create_token(user);
	}
	


	@Transactional
	public boolean Logout_user(token token) {
			try{
				tokendao.delete(token);
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;}
	}
	

	
	@Transactional
	public boolean Update_user_info(User_Input_form data,int user_id){
		try{
			users user = userdao.get(user_id);
			user.setFirstname(data.firstname);
			user.setLastname(data.lastname);
			user.setModified_at(Calendar.getInstance().getTime());
			userdao.update(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}

	@Transactional
	public users Get_user_by_id(int id) {
		try{
			return userdao.get(id);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

	@Transactional
	public boolean Change_password(User_Change_Password_form data, int user_id) {
		try{
			users user = userdao.get(user_id);
			if(user.getPassword().compareTo(data.Get_encrypted_Old_password())!=0 || data.Get_encrypted_New_password()==null){
				return false;}
			user.setPassword(data.Get_encrypted_New_password());
			tokenservice.Clear_token_talbe_by_userid(user.getId());
			userdao.update(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}

	@Transactional
	public List<Search_result_form> Search_user_by_username(String query) {
		List<Search_result_form> result = new ArrayList<Search_result_form>();
		for(users user:userdao.search_user_by_username(query)){
			result.add(new Search_result_form(user.getId(),user.getUsername(),user.getEmail()));
		}
		return result ;
	}

	@Transactional
	public boolean Delete_user(String username) {
		try{
			users user = userdao.get_by_username(username);
			if(user!=null){
			userdao.delete(user);}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}

	@Transactional
	public users Get_user_by_username(String username) {
		try{
			return userdao.get_by_username(username);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}
}
