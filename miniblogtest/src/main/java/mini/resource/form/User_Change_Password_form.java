package mini.resource.form;

import javax.ws.rs.FormParam;

import mini.resource.Hash_gen_util;
import mini.resource.System_value;

public class User_Change_Password_form {
	@FormParam("old_password")
	public String old_password;
	@FormParam("new_password")
	public String new_password;
	
	public String Get_encrypted_Old_password() {
		return Encrypt_password(this.old_password);
	}
	public String Get_encrypted_New_password() {
		return Encrypt_password(this.new_password);
	}
	public String Encrypt_password(String password){
		try {
			return Hash_gen_util.generateSHA256(System_value.secret_key+password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
