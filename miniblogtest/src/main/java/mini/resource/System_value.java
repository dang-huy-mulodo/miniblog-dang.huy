package mini.resource;

public class System_value {
	//validation value
	public final static int users_username_MIN_LENGTH=6;
	public final static int users_username_MAX_LENGTH=32;
	public final static String users_username_STRING_RANGE="[a-z0-9 ]*";
	
	public final static int users_password_MIN_LENGTH=6;
	public final static int users_password_MAX_LENGTH=72;
	public final static String users_password_STRING_RANGE="[A-Za-z0-9 ]*";
	
	public final static int users_firstname_MAX_LENGTH=32;
	public final static String users_firstname_STRING_RANGE="[A-Za-z0-9 ]*";
	
	public final static int users_lastname_MAX_LENGTH=32;
	public final static String users_lastname_STRING_RANGE="[A-Za-z0-9 ]*";
	
	public final static int users_email_MAX_LENGTH=254;
	public final static String users_email_STRING_RANGE="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	public final static int access_token_LENGTH=64;
	
	public final static int post_title_MIN_LENGTH=10;
	public final static int post_title_MAX_LENGTH=100;
	
	public final static int post_content_MIN_LENGTH=10;
	public final static int post_content_MAX_LENGTH=2048;

	public final static int comment_MAX_LENGTH=254;
	public final static int comment_MIN_LENGTH=1;
	//System Secret key
	public final static String secret_key="XfLTaYmtz9";
	public final static int token_expire_time=1;
	
//==================Error code list================================
	public final static int code_200=200;
	//Validate data error.
	public final static int errorcode_1001=1001;
	
	//username already exist
	public final static int errorcode_2001=2001;
	//email already exist
	public final static int errorcode_2002=2002;
	//create user fail
	public final static int errorcode_2003=2003;
	//Incorrect username or password
	public final static int errorcode_2004=2004;
	//logout fail
	public final static int errorcode_2005=2005;
	//userinfo update fail
	public final static int errorcode_2006=2006;
	//change password fail
	public final static int errorcode_2007=2007;
	//invalid or expired token
	public final static int errorcode_2008=2008;
	//username not exist
	public final static int errorcode_2009=2009;
	
	
	//post create error
	public final static int errorcode_3001=3001;
	//change post status error
	public final static int errorcode_3002=3002;
	//update post error
	public final static int errorcode_3003=3003;
	//delete post error
	public final static int errorcode_3004=3004;
	//post id not exist
	public final static int errorcode_3005=3005;

	//create comment error
	public final static int errorcode_4001=4001;
	//edit comment error
	public final static int errorcode_4002=4002;
	//delete comment error
	public final static int errorcode_4003=4003;
	//invalid comment id
	public final static int errorcode_4004=4004;

}
