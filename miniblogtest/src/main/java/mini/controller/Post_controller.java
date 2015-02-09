package mini.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mini.model.posts;
import mini.model.token;
import mini.model.users;
import mini.resource.System_value;
import mini.resource.error_code_util;
import mini.resource.form.Post_form;
import mini.resource.form.Post_info_form;
import mini.service.Data_Validator;
import mini.service.Post_service;
import mini.service.Token_service;
import mini.service.User_service;

import org.jboss.resteasy.annotations.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/v1/post")
@Produces(MediaType.APPLICATION_JSON)
public class Post_controller {
	
	@Autowired
	private Data_Validator data_validator;
	
	@Autowired
	private Token_service token_service;
	
	@Autowired
	private Post_service post_service;
	
	@Autowired
	private User_service user_service;
	
	@POST
	@Path("add")
	@Consumes("application/x-www-form-urlencoded")
	public Response Create_Post(
			@HeaderParam("access_token") String access_token,
			@Form Post_form data){
    	if(!data_validator.Validate_Access_Token(access_token) ||
    	   !data_validator.Validate_post_data(data)){
    		return Response.status(System_value.errorcode_1001)
    				.entity(error_code_util.set_error_code(System_value.errorcode_1001))
    				.build();}
    	
    	token token = token_service.Check_access_token(access_token);
    	
    	if(token==null){
    		return Response.status(System_value.errorcode_2008)
    				.entity(error_code_util.set_error_code(System_value.errorcode_2008))
    				.build();}
    	int post_id=post_service.Create_post(data.set_post_data(token.getUser()));
		if(post_id==0){
			return Response.status(System_value.errorcode_3001)
					.entity(error_code_util.set_error_code(System_value.errorcode_3001))
					.build();}
		
		return Response.status(System_value.code_200).entity(error_code_util.set_code(
				System_value.code_200, 
				"post create success", 
				null, 
				post_id
				)).build();
	}
	
	@PUT
	@Path("edit")
	@Consumes("application/x-www-form-urlencoded")
	public Response Edit_Post(
			@HeaderParam("access_token") String access_token,
			@Form Post_form data){
    	if(!data_validator.Validate_Access_Token(access_token) ||
    	   !data_validator.Validate_post_data(data)){
    		return Response.status(System_value.errorcode_1001)
    				.entity(error_code_util.set_error_code(System_value.errorcode_1001))
    				.build();}
    	
    	token token = token_service.Check_access_token(access_token);
    	
    	if(token==null){
    		return Response.status(System_value.errorcode_2008)
    				.entity(error_code_util.set_error_code(System_value.errorcode_2008))
    				.build();}
    	
		if(!post_service.Check_post_own(data.id, token.getUser().getId())){
			return Response.status(System_value.errorcode_3005)
					.entity(error_code_util.set_error_code(System_value.errorcode_3005))
					.build();}
		
		if(!post_service.Edit_post(data)){
			return Response.status(System_value.errorcode_3003)
					.entity(error_code_util.set_error_code(System_value.errorcode_3003))
					.build();}
		
		return Response.status(System_value.code_200).entity(error_code_util.set_code(
				System_value.code_200, 
				"post update success", 
				null, 
				null
				)).build();
	}
	
	@PUT
	@Path("status")
	@Consumes("application/x-www-form-urlencoded")
	public Response Change_status_Post(
			@HeaderParam("access_token") String access_token,
			@Form Post_form data){
    	if(!data_validator.Validate_Access_Token(access_token)){
    	    return Response.status(System_value.errorcode_1001)
    	    		.entity(error_code_util.set_error_code(System_value.errorcode_1001))
    	    		.build();}
    	token token = token_service.Check_access_token(access_token);
    	
    	if(token==null){
    		return Response.status(System_value.errorcode_2008)
    				.entity(error_code_util.set_error_code(System_value.errorcode_2008))
    				.build();}
    	
		if(!post_service.Check_post_own(data.id, token.getUser().getId())){
			return Response.status(System_value.errorcode_3005)
					.entity(error_code_util.set_error_code(System_value.errorcode_3005))
					.build();}
		
		if(!post_service.Edit_status_post(data)){
			return Response.status(System_value.errorcode_3002)
					.entity(error_code_util.set_error_code(System_value.errorcode_3002))
					.build();}
		
		return Response.status(System_value.code_200).entity(error_code_util.set_code(
				System_value.code_200, 
				"post update success", 
				null, 
				null
				)).build();
	}

	@POST
	@Path("delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response Delete_Post(
			@HeaderParam("access_token") String access_token,
			@Form Post_form data){
    	token token = token_service.Check_access_token(access_token);
    	if(!data_validator.Validate_Access_Token(access_token)){
    	    return Response.status(System_value.errorcode_1001)
    	    		.entity(error_code_util.set_error_code(System_value.errorcode_1001))
    	    		.build();}
    	if(token==null){
    		return Response.status(System_value.errorcode_2008)
    				.entity(error_code_util.set_error_code(System_value.errorcode_2008))
    				.build();}
    	
		if(!post_service.Check_post_own(data.id, token.getUser().getId())){
			return Response.status(System_value.errorcode_3005)
					.entity(error_code_util.set_error_code(System_value.errorcode_3005))
					.build();}
		
		if(!post_service.Delete_post(data)){
			return Response.status(System_value.errorcode_3004)
					.entity(error_code_util.set_error_code(System_value.errorcode_3004))
					.build();}
		
		return Response.status(System_value.code_200).entity(error_code_util.set_code(
				System_value.code_200, 
				"post delete success", 
				null, 
				null
				)).build();
	}
	
    @GET
    @Path("getpost")
    public Response Get_post(
    		@HeaderParam("access_token") String access_token,
    		@QueryParam("mode") String mode,
    		@QueryParam("id")int id,
    		@QueryParam("limit")int limit,
    		@QueryParam("keysearch")String keysearch){
    	
    	users user;
    	if(!data_validator.Validate_Access_Token(access_token)||
    	   !data_validator.Validate_post_get_parameter(mode)){
    			return Response.status(System_value.errorcode_1001)
    					.entity(error_code_util.set_error_code(System_value.errorcode_1001))
    					.build();}
    	
    	token token = token_service.Check_access_token(access_token);
    	
    	if(token==null){
    		return Response.status(System_value.errorcode_2008)
    				.entity(error_code_util.set_error_code(System_value.errorcode_2008))
    				.build();}

    	switch(mode){
    	case "all":
    		return Response.status(System_value.code_200)
    				.entity(error_code_util.set_code(200, "Get all post", null,post_service.Get_all_post() ))
    				.build();

    	case "current":
    		user = user_service.Get_user_by_id(token.getUser().getId());
    		if(user==null){
    			return Response.status(System_value.errorcode_2009)
    					.entity(error_code_util.set_error_code(System_value.errorcode_2009))
    					.build();}
    		return Response.status(System_value.code_200)
    				.entity(error_code_util.set_code(200, "all post current user", null,post_service.Get_all_post_by_userid(user.getId())))
    				.build();

    	case "userid":
    		user = user_service.Get_user_by_id(id);
    		if(user==null){
    			return Response.status(System_value.errorcode_2009)
    					.entity(error_code_util.set_error_code(System_value.errorcode_2009))
    					.build();}
    		
    		return Response.status(System_value.code_200)
    				.entity(error_code_util.set_code(200, "all post of user by id", null,post_service.Get_all_post_by_userid(user.getId())))
    				.build();
    		
    	case "postid":
    		posts post = post_service.Get_post_by_postid(id);
    		if(post==null){
    			return Response.status(System_value.errorcode_3005)
    					.entity(error_code_util.set_error_code(System_value.errorcode_3005))
    					.build();}
    		
    		return Response.status(System_value.code_200)
    				.entity(error_code_util.set_code(200, "all post of user by id", null,new Post_info_form(post)))
    				.build();
    		
    	case "top":
    		return Response.status(System_value.code_200)
    				.entity(error_code_util.set_code(200, "top post", null,post_service.Get_top_post(limit)))
    				.build();
    	case "title":
    		return Response.status(System_value.code_200)
    				.entity(error_code_util.set_code(200, "search title", null,post_service.Search_post_by_title(keysearch)))
    				.build();

    	}
    	return Response.status(System_value.code_200)
    			.entity(error_code_util.set_code(200,"invalid", null,null ))
    			.build();
    }
}
