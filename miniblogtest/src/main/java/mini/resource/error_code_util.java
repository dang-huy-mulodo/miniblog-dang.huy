package mini.resource;

public class error_code_util {
	public final static return_object set_error_code(int code){
		return_object resp = new return_object();
		switch(code){
		case System_value.errorcode_1001:
			resp.meta.code=System_value.errorcode_1001;
			resp.meta.description="Validate Data Error.";
		break;
		
		case System_value.errorcode_2001:
			resp.meta.code=System_value.errorcode_2001;
			resp.meta.description="Username Already Exist";
		break;
		
		case System_value.errorcode_2002:
			resp.meta.code=System_value.errorcode_2002;
			resp.meta.description="Email Already Exist";
		break;
		
		case System_value.errorcode_2003:
			resp.meta.code=System_value.errorcode_2003;
			resp.meta.description="create user fail";
		break;
		
		case System_value.errorcode_2004:
			resp.meta.code=System_value.errorcode_2004;
			resp.meta.description="Incorrect username or password";
		break;
		
		case System_value.errorcode_2005:
			resp.meta.code=System_value.errorcode_2005;
			resp.meta.description="Logout error";
		break;
		
		case System_value.errorcode_2006:
			resp.meta.code=System_value.errorcode_2006;
			resp.meta.description="userinfo update fail";
		break;
		
		case System_value.errorcode_2007:
			resp.meta.code=System_value.errorcode_2007;
			resp.meta.description="change password fail";
		break;
		
		case System_value.errorcode_2008:
			resp.meta.code=System_value.errorcode_2008;
			resp.meta.description="invalid or expired token";
		break;
		
		case System_value.errorcode_2009:
			resp.meta.code=System_value.errorcode_2009;
			resp.meta.description="user not exist";
		break;
		
		case System_value.errorcode_3001:
			resp.meta.code=System_value.errorcode_3001;
			resp.meta.description="create post error";
		break;
		
		case System_value.errorcode_3002:
			resp.meta.code=System_value.errorcode_3002;
			resp.meta.description="change post status error";
		break;
		
		case System_value.errorcode_3003:
			resp.meta.code=System_value.errorcode_3003;
			resp.meta.description="update post error";
		break;
		
		case System_value.errorcode_3004:
			resp.meta.code=System_value.errorcode_3004;
			resp.meta.description="delete post error";
		break;
		
		case System_value.errorcode_3005:
			resp.meta.code=System_value.errorcode_3005;
			resp.meta.description="post id not valid";
		break;
		
		case System_value.errorcode_4001:
			resp.meta.code=System_value.errorcode_4001;
			resp.meta.description="create comment error";
		break;
		
		case System_value.errorcode_4002:
			resp.meta.code=System_value.errorcode_4002;
			resp.meta.description="edit comment error";
		break;
		
		case System_value.errorcode_4003:
			resp.meta.code=System_value.errorcode_4003;
			resp.meta.description="delete comment error";
		break;
		
		}
		return resp;
	}
	public final static return_object set_code(int code,String description,Object message,Object data){
		return_object resp = new return_object();
		resp.meta.code=code;
		resp.meta.description=description;
		resp.meta.message=message;
		resp.data=data;
		return resp;
	}
}
