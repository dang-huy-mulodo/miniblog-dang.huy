package mini.service.imp;

import java.util.Calendar;
import java.util.Date;

import mini.dao.tokenDAO;
import mini.dao.userDAO;
import mini.model.token;
import mini.model.users;
import mini.resource.Hash_gen_util;
import mini.resource.System_value;
import mini.service.Token_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Token_service_imp implements Token_service{
	@Autowired
	private tokenDAO tokendao;
	@Autowired
	private userDAO userdao;

	@Transactional
	public String Create_token(users user) {
		String access_token=null;
		while(access_token==null){
			try {
				access_token = Hash_gen_util.generateSHA256(System_value.secret_key+"@"+user.getId()+"@"+Calendar.getInstance().getTime());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("hash error");}
		}
		token token = new token();
		token.setAccess_token(access_token);
		token.setUser(user);
	    Calendar cal = Calendar.getInstance();

		token.setCreate_at(cal.getTime());
		
	    cal.setTime(new Date());
	    cal.add(Calendar.HOUR_OF_DAY,System_value.token_expire_time);
	    
	    token.setExpired(cal.getTime());
	    
		user.getUser_token().add(token);
		try{
			userdao.save(user);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
		return access_token;
	}

	@Transactional
	public token Check_access_token(String access_token) {
		try{
		token token = tokendao.get_token_by_access_token(access_token);
		if(token!=null){
			if(Check_expire_time(token.getExpired())>=0){
				tokendao.delete(token);
				return null;
			}else{
				Calendar cal = Calendar.getInstance();
			    cal.setTime(new Date());
			    cal.add(Calendar.HOUR_OF_DAY,System_value.token_expire_time);
			    
			    token.setExpired(cal.getTime());
			    tokendao.update(token);
				return token;
			}
		}else{return null;}
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}
	
	private int Check_expire_time(Date expire){
		Calendar cal = Calendar.getInstance();
		cal.setTime(expire);
		return Calendar.getInstance().compareTo(cal);
	}

//	@Transactional
//	public boolean Clear_token_talbe_by_userid(int user_id) {
//		try{
//			tokendao.clear_token_by_userId(user_id);
//			return true;
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}	
//	}
	@Transactional
	public boolean Clear_token_talbe_by_userid(int user_id) {
		users user = userdao.get(user_id);
		try{
			token token = new token();
			token.setUser(user);
			tokendao.delete(token);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}

}
