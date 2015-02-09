package mini.resource.form;

import java.util.Date;

import mini.model.users;

public class User_Info_form {

	public String username;

	public String email;
	
	public String firstname;
	
	public String lastname;
	
	public Date create_at;
	
	public Date modified_at;

//	public user_info_form(String username, String email, String firstname,
//			String lastname, Date create_at, Date modified_at) {
//		super();
//		this.username = username;
//		this.email = email;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.create_at = create_at;
//		this.modified_at = modified_at;
//	}
	
	public User_Info_form(users user){
		this.username=user.getUsername();
		this.email=user.getEmail();
		this.firstname=user.getFirstname();
		this.lastname=user.getLastname();
		this.create_at=user.getCreate_at();
		this.modified_at=user.getModified_at();
	}
	
}
