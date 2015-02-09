package mini.resource.form;

import java.util.Calendar;

import javax.ws.rs.FormParam;

import mini.model.users;
import mini.resource.Hash_gen_util;
import mini.resource.System_value;

public class User_Input_form {
	@FormParam("username")
	public String username;
	@FormParam("password")
	public String password;
	@FormParam("firstname")
	public String firstname;
	@FormParam("lastname")
	public String lastname;
	@FormParam("email")
	public String email;

	public users set_sign_up_data(users user){
		String passwordencrypt = Encrypt_password();
		if(passwordencrypt!=null)
			{user.setPassword(passwordencrypt);}
		else
			{user.setPassword(this.password);}
		user.setUsername(this.username);
		user.setFirstname(this.firstname);
		user.setLastname(this.lastname);
		user.setEmail(this.email);
		user.setCreate_at(Calendar.getInstance().getTime());
		user.setModified_at(Calendar.getInstance().getTime());
		return user;
	}
	
	public String Encrypt_password(){
		try {
			return Hash_gen_util.generateSHA256(System_value.secret_key+this.password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
