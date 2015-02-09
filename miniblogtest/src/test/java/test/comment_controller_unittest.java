package test;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@SuppressWarnings("deprecation")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class comment_controller_unittest {
	static final String ROOT_URL = "http://localhost:8080/miniblogtest";
	
	static String token_key;
	static int Post_id=24;
	static int comment_id;
	
	@Before
	public void setUp() throws Exception
	{

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
			comment_controller_unittest.token_key = key.get("access_token").toString();
			
		} catch (JSONException e) {
			e.printStackTrace();
			fail("Login_test fail2");
		}
		assertEquals(64,token_key.length());
		response.close();
	}
	
	 @After
	 public void tearDown() throws Exception
	 {
		ClientResponse<String> response = null;
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/user/logout");

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

	@Test
	public void aa_Add_comment_check_postid_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/comment/add");
			
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
					"postid=1&"
					+ "comment=hohoho%2C+nice+man");
		try {
			response = request.post(String.class);
				
		} catch (Exception e) {
			e.printStackTrace();
			fail("aa_Add_comment_check_postid_test fail 1");
		}
			
		assertEquals(3005,response.getStatus());
			
		response.close();
	}
	@Test
	public void ab_Add_comment_success_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/comment/add");
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"postid="+Post_id+"&"
				+ "comment=hohoho%2C+nice+man");
		try {
			response = request.post(String.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("aa_Add_comment_success_test fail 1");
		}
		
		assertEquals(200,response.getStatus());
		
		try {
			JSONObject array = new JSONObject(response.getEntity().toString());
			comment_id =array.getInt("data");
		} catch (JSONException e) {
			e.printStackTrace();
			fail("aa_Add_comment_success_test fail 2");
		}
		
		response.close();
	}
	
	@Test
	public void ba_Edit_comment_check_own_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/comment/edit");
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"id=1&"
				+ "comment=hohoho%2C+nice+man");
		try {
			response = request.put(String.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("ba_Edit_comment_check_own_test fail 1");
		}
		
		assertEquals(4004,response.getStatus());
		
		response.close();
	}
	
	@Test
	public void bb_Edit_comment_success_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/comment/edit");
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"id="+comment_id+"&"
				+ "comment=hohoho%2C+nice+man");
		try {
			response = request.put(String.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("bb_Edit_comment_success_test fail 1");
		}
		
		assertEquals(200,response.getStatus());
		
		response.close();
	}
	
	@Test
	public void ca_Get_comment_success_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/comment/get?mode=id&id="+comment_id);
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		try {
			response = request.get(String.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("ca_Get_comment_success_test fail 1");
		}
		
		assertEquals(200,response.getStatus());
		
		response.close();
	}
	
	@Test
	public void da_Delete_comment_success_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/comment/del");
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"id="+comment_id);
		try {
			response = request.post(String.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("ca_Get_comment_success_test fail 1");
		}
		
		assertEquals(200,response.getStatus());
		
		response.close();
	}
}
