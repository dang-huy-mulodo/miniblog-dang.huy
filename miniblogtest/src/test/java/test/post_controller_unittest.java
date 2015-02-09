package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
public class post_controller_unittest {
	static final String ROOT_URL = "http://localhost:8080/miniblogtest";
	
	static String token_key;
	static int post_id;
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
			post_controller_unittest.token_key = key.get("access_token").toString();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
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
	public void aa_Create_post_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/post/add");
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"title=hello+this+is+my+first+post&"
				+ "content=hello%2C+this+is+unittest+post+content");
		try {
			response = request.post(String.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("aa_Create_post_test fail 1");
		}
		
		assertEquals(200,response.getStatus());
		
		try {
			JSONObject array = new JSONObject(response.getEntity().toString());
			post_controller_unittest.post_id =array.getInt("data");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("aa_Create_post_test fail 2");
		}
		
		response.close();
	}
	
	@Test
	public void ba_Edit_post_checkown_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/post/edit");
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"title=hello+this+is+my+first+post&"
				+ "content=hello%2C+this+is+unittest+post+content&"
				+ "id=3");
		
		try {
			response = request.put(String.class);
			assertEquals(3005,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("ba_Edit_post_checkown_test fail");
		} finally
		{
			response.close();
		}
	}
	@Test
	public void bb_Edit_post_success_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/post/edit");
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"title=hello+this+is+my+first+post&"
				+ "content=hello%2C+this+is+unittest+post+content&"
				+ "id="+post_id);
		
		try {
			response = request.put(String.class);
			assertEquals(200,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("bb_Edit_post_success_test fail");
		} finally
		{
			response.close();
		}
	}
	
	@Test
	public void ca_Change_status_post_success_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/post/status");
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"status=true&"
				+ "id="+post_id);
		
		try {
			response = request.put(String.class);
			assertEquals(200,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail("ca_Change_status_post_success_test fail");
		} finally
		{
			response.close();
		}
	}
	@Test
	public void da_Get_post_test(){
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/post/getpost?mode=postid&id="+post_id);
		
		ClientResponse<String> response = null;
		
		request.header("access_token",token_key);
		try{
			response= request.get(String.class);
		}catch(Exception e){
			e.printStackTrace();
			fail("da_Get_post_test fail");
		}
		
		assertEquals(200,response.getStatus());
		try{
			JSONObject array = new JSONObject(response.getEntity().toString());
			JSONObject data=array.getJSONObject("data");
			assertEquals("hello this is my first post",data.get("title").toString());
		}catch(JSONException e)
		{
			e.printStackTrace();
			fail("da_Get_post_test fail 1");
		}finally
		{
			response.close();
		}
	}
	
	@Test
	public void ea_Delete_post_test() {
		ClientRequest request = new ClientRequest(ROOT_URL+"/v1/post/delete");
		
		ClientResponse<String> response = null;
		request.header("access_token",token_key);
		request.body(MediaType.APPLICATION_FORM_URLENCODED,
				"id="+post_id);
		
		try {
			response = request.post(String.class);
		} catch (Exception e) {
			e.printStackTrace();
			fail("ea_Delete_post_test fail");
		} finally
		{
			response.close();
		}
		assertEquals(200,response.getStatus());
	}
}
