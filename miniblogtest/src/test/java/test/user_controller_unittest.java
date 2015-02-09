package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.ws.rs.core.MediaType;

import mini.resource.return_object;
import mini.service.User_service;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@SuppressWarnings("deprecation")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class user_controller_unittest {
	
	static final String ROOT_URL = "http://localhost:8080/miniblogtest";
	
	@Autowired
	private User_service user_service;
	
	static String token_key;
	
//	@Before
//	public void setUp() throws Exception
//	{
//		user_service.Delete_user("ledanghuytest");
//	}
	
	@Test
	public void aa_Adduser_validate_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/adduser");
		
		ClientResponse<return_object> response = null;

		request.body(MediaType.APPLICATION_FORM_URLENCODED, "username=huy&password=ew");
		
		try {
			response = request.post(return_object.class);
			assertEquals(1001,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Adduser_validate_test fail");
		} finally
		{
			response.close();
		}
	}
	
	@Test
	public void ab_Adduser_userexist_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/adduser");
		
		ClientResponse<return_object> response = null;

		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"username=ledanghuy&"
				+ "password=123456&"				
				+ "firstname=le&"
				+ "lastname=huy&"
				+ "email=huytest@mulodo.com");
		
		try {
			response = request.post(return_object.class);
			assertEquals(2001,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Adduser_userexist_test fail");
		} finally
		{
			response.close();
		}
	}
	
	@Test
	public void ac_Adduser_emailexist_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/adduser");
		
		ClientResponse<return_object> response = null;

		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"username=ledanghuytestpro&"
				+ "password=123456&"				
				+ "firstname=le&"
				+ "lastname=huy&"
				+ "email=dang.huy@mulodo.com");
		
		try {
			response = request.post(return_object.class);
			assertEquals(2002,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Adduser_emailexist_test fail");
		} finally
		{
			response.close();
		}
	}
	
	@Test
	public void ad_Adduser_success_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/adduser");
		
		ClientResponse<return_object> response = null;
		user_service.Delete_user("ledanghuytest");
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"username=ledanghuytest&"
				+ "password=123456&"				
				+ "firstname=le&"
				+ "lastname=huy&"
				+ "email=dang.huy.test@mulodo.com");
		
		try {
			response = request.post(return_object.class);
			assertEquals(200,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Adduser_success_test fail");
		} finally
		{
			response.close();
		}
	}
	
	@Test
	public void ba_Login_validate_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/login");
		
		ClientResponse<return_object> response = null;
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"username=le&"
				+ "password=a");
		
		try {
			response = request.post(return_object.class);
			assertEquals(1001,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Login_validate_test fail");
		} finally
		{
			response.close();
		}
	}

	@Test
	public void bb_Login_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/login");
		
		ClientResponse<String> response = null;

		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"username=ledanghuy&"
				+ "password=abcd1234");
		
		try {
			response = request.post(String.class);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Login_test fail");
		}
		assertEquals(200,response.getStatus());
		try {
			JSONObject array = new JSONObject(response.getEntity().toString());
			JSONObject key=array.getJSONObject("data");
			user_controller_unittest.token_key = key.get("access_token").toString();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Login_test fail2");
		}
		
		assertEquals(64,token_key.length());
		response.close();
		
	}
	
	@Test
	public void ca_update_validate_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/user");
		
		ClientResponse<return_object> response = null;
		request.header("access_token", "asd");
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"firstname=&"
				+ "lastname=huy");
		
		try {
			response = request.put(return_object.class);
			assertEquals(1001,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("ca_update_validate_test fail");
		} finally
		{
			response.close();
		}
	}
	
	@Test
	public void cb_update_token_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/user");
		
		ClientResponse<return_object> response = null;
		request.header("access_token", "1ih2io1hiio1nioc3iasdfjabsiodfhasdiofhaiduhfuadhfaiosdfdfauiosdd");
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"firstname=sd&"
				+ "lastname=huy");
		
		try {
			response = request.put(return_object.class);
			assertEquals(2008,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("cb_update_token_test fail");
		} finally
		{
			response.close();
		}
	}
	
	@Test
	public void cc_update_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/user");
		
		ClientResponse<return_object> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"firstname=sd&"
				+ "lastname=huy");
		
		try {
			response = request.put(return_object.class);
			assertEquals(200,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("cb_update_test fail");
		} finally
		{
			response.close();
		}
	}
	
	@Test
	public void da_user_get_info_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/user?mode=current");
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		try {
			response = request.get(String.class);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("ea_user_get_info_test fail");
		}
		
		assertEquals(200,response.getStatus());
		
		try {
			JSONObject array = new JSONObject(response.getEntity().toString());
			JSONObject data=array.getJSONObject("data");
			assertEquals("ledanghuy",data.get("username").toString());
			assertEquals("dang.huy@mulodo.com",data.get("email").toString());
		} catch (JSONException e) {
			e.printStackTrace();
			fail("da_user_get_info_test fail 1");
		}	
		response.close();
	}
	
	@Test
	public void ea_search_user_by_username_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/search/ledanghuy");
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		try {
			response = request.get(String.class);
		} catch (Exception e1) {
			e1.printStackTrace();
			fail("ea_search_user_by_username_test fail");
		}
		
		assertEquals(200,response.getStatus());
		
		try {
			JSONObject array = new JSONObject(response.getEntity().toString());
			JSONArray data=array.getJSONArray("data");
			assertEquals(3,data.length());
			JSONObject result = data.getJSONObject(0);
			assertEquals("ledanghuy",result.get("username").toString());
			assertEquals("dang.huy@mulodo.com",result.get("email").toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("ea_search_user_by_username_test fail 1");
		}	
		response.close();
	}
	
	@Test
	public void fa_user_change_password_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/pass");
		
		ClientResponse<return_object> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"old_password=abcd1234&"
				+ "new_password=abcd1234");
		
		try {
			response = request.put(return_object.class);
			assertEquals(200,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("da_user_change_password_test fail");
		} finally
		{
			response.close();
		}
	}
	
	@Test
	public void ga_user_logout_test() {
	ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/login");
	
	ClientResponse<String> response = null;

	request.body(MediaType.APPLICATION_FORM_URLENCODED,
			"username=ledanghuy&"
			+ "password=abcd1234");
	
	try {
		response = request.post(String.class);

	} catch (Exception e) {
		e.printStackTrace();
		fail("Login_test fail");
	}
	assertEquals(200,response.getStatus());
	try {
		JSONObject array = new JSONObject(response.getEntity().toString());
		JSONObject key=array.getJSONObject("data");
		user_controller_unittest.token_key = key.get("access_token").toString();
		
	} catch (JSONException e) {
		e.printStackTrace();
		fail("Login_test fail2");
	}
	
	assertEquals(64,token_key.length());
	response.close();
	request = new ClientRequest(ROOT_URL+"/v1/user/logout");
	request.header("access_token",token_key);
	request.body(MediaType.APPLICATION_JSON,
			"");
	try {
		response = request.post(String.class);

	} catch (Exception e) {
		e.printStackTrace();
		fail("fa_user_logout_test fail");
	}
	assertEquals(200,response.getStatus());
	response.close();
	}
}
