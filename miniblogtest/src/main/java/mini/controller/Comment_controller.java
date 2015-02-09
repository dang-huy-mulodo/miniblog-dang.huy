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

import mini.model.comments;
import mini.model.posts;
import mini.model.token;
import mini.model.users;
import mini.resource.System_value;
import mini.resource.error_code_util;
import mini.resource.form.Comment_form;
import mini.resource.form.Comment_info_form;
import mini.service.Comment_service;
import mini.service.Data_Validator;
import mini.service.Post_service;
import mini.service.Token_service;
import mini.service.User_service;

import org.jboss.resteasy.annotations.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/v1/comment")
@Produces(MediaType.APPLICATION_JSON)
public class Comment_controller {
	
	@Autowired
	private Data_Validator data_validator;
	
	@Autowired
	private Token_service token_service;
	
	@Autowired
	private Post_service post_service;
	
	@Autowired
	private Comment_service comment_service;
	
	@Autowired
	private User_service user_service;
	
	@POST
	@Path("add")
	@Consumes("application/x-www-form-urlencoded")
	public Response Create_Comment(
			@HeaderParam("access_token") String access_token,
			@Form Comment_form data){
    	if(!data_validator.Validate_Access_Token(access_token)||
    	   !data_validator.Validate_comment(data)){
    	    	return Response.status(System_value.errorcode_1001)
    	    			.entity(error_code_util.set_error_code(System_value.errorcode_1001))
    	    			.build();}
    	    	
    	token token = token_service.Check_access_token(access_token);
    	    	
    	if(token==null){
    	    	return Response.status(System_value.errorcode_2008)
    	    			.entity(error_code_util.set_error_code(System_value.errorcode_2008))
    	    			.build();}
    	posts post = post_service.Get_post_by_postid(data.postid);
    	
    	if(post==null){
				return Response.status(System_value.errorcode_3005)
						.entity(error_code_util.set_error_code(System_value.errorcode_3005))
						.build();}
    	int comment_id=comment_service.Create_comment(data.set_comment_data(token.getUser(), post));
    	if(comment_id==0){
				return Response.status(System_value.errorcode_4001)
						.entity(error_code_util.set_error_code(System_value.errorcode_4001))
						.build();}

		return Response.status(System_value.code_200).entity(error_code_util.set_code(
				System_value.code_200, 
				"comment create success", 
				null, 
				comment_id
				)).build();
		
	}
	
	@PUT
	@Path("edit")
	@Consumes("application/x-www-form-urlencoded")
	public Response Edit_Comment(
		@HeaderParam("access_token") String access_token,
		@Form Comment_form data){
	if(!data_validator.Validate_Access_Token(access_token) ||
	   !data_validator.Validate_comment(data)){
		return Response.status(System_value.errorcode_1001)
				.entity(error_code_util.set_error_code(System_value.errorcode_1001))
				.build();}
	
	token token = token_service.Check_access_token(access_token);
	
	if(token==null){
		return Response.status(System_value.errorcode_2008)
				.entity(error_code_util.set_error_code(System_value.errorcode_2008))
				.build();}
	
	if(!comment_service.Check_comment_own(data.id, token.getUser().getId())){
		return Response.status(System_value.errorcode_4004)
				.entity(error_code_util.set_error_code(System_value.errorcode_4004))
				.build();}
	
	if(!comment_service.Edit_comment(data)){
		return Response.status(System_value.errorcode_4002)
				.entity(error_code_util.set_error_code(System_value.errorcode_4002))
				.build();}
	
	return Response.status(System_value.code_200).entity(error_code_util.set_code(
			System_value.code_200, 
			"post update success", 
			null, 
			null
			)).build();
	}
	
	@POST
	@Path("del")
	@Consumes("application/x-www-form-urlencoded")
	public Response Delete_comment(
			@HeaderParam("access_token") String access_token,
			@Form Comment_form data){
		if(!data_validator.Validate_Access_Token(access_token)){
					return Response.status(System_value.errorcode_1001)
							.entity(error_code_util.set_error_code(System_value.errorcode_1001))
							.build();}
    	token token = token_service.Check_access_token(access_token);
    	
    	if(token==null){
    		return Response.status(System_value.errorcode_2008)
    				.entity(error_code_util.set_error_code(System_value.errorcode_2008))
    				.build();}
    	
		if(!comment_service.Check_comment_own(data.id, token.getUser().getId())){
			return Response.status(System_value.errorcode_4004)
					.entity(error_code_util.set_error_code(System_value.errorcode_4004))
					.build();}
		
		if(!comment_service.Delete_comment(data)){
			return Response.status(System_value.errorcode_4003)
					.entity(error_code_util.set_error_code(System_value.errorcode_4003))
					.build();}
		
		return Response.status(System_value.code_200).entity(error_code_util.set_code(
				System_value.code_200, 
				"post delete success", 
				null, 
				null
				)).build();
	}
	
	@GET
	@Path("get")
	public Response Get_comment(
			@HeaderParam("access_token") String access_token,
    		@QueryParam("mode") String mode,
    		@QueryParam("id")int id){
		users user;
    	if(!data_validator.Validate_Access_Token(access_token)||
    	    	   !data_validator.Validate_comment_get_parameter(mode)){
    	    			return Response.status(System_value.errorcode_1001)
    	    					.entity(error_code_util.set_error_code(System_value.errorcode_1001))
    	    					.build();}
    	
    	token token = token_service.Check_access_token(access_token);
    	
    	if(token==null){
    		return Response.status(System_value.errorcode_2008)
    				.entity(error_code_util.set_error_code(System_value.errorcode_2008))
    				.build();}
    	
    	switch(mode){
    	case "id":
    		comments comment = comment_service.Get_comment_by_id(id);
    		if(comment==null){
    			return Response.status(System_value.errorcode_4004)
    					.entity(error_code_util.set_error_code(System_value.errorcode_4004))
    					.build();}
    		
    		return Response.status(System_value.code_200)
    				.entity(error_code_util.set_code(200, "get comment by id", null,new Comment_info_form(comment)))
    				.build();
    	case "current":
    		user = user_service.Get_user_by_id(token.getUser().getId());
    		if(user==null){
    			return Response.status(System_value.errorcode_2009)
    					.entity(error_code_util.set_error_code(System_value.errorcode_2009))
    					.build();}
    		return Response.status(System_value.code_200)
    				.entity(error_code_util.set_code(200, "get comment by current user", null,comment_service.Get_all_comment_by_userid(user.getId())))
    				.build();
    	case "postid":
    		posts post = post_service.Get_post_by_postid(id);
    		if(post==null){
    			return Response.status(System_value.errorcode_3005)
    					.entity(error_code_util.set_error_code(System_value.errorcode_3005))
    					.build();}
    		return Response.status(System_value.code_200)
    				.entity(error_code_util.set_code(200, "get comment by user id", null,comment_service.Get_all_comment_by_postid(post.getId())))
    				.build();
    	case "userid":
    		user = user_service.Get_user_by_id(id);
    		if(user==null){
    			return Response.status(System_value.errorcode_2009)
    					.entity(error_code_util.set_error_code(System_value.errorcode_2009))
    					.build();}
    		return Response.status(System_value.code_200)
    				.entity(error_code_util.set_code(200, "get comment by user id", null,comment_service.Get_all_comment_by_userid(user.getId())))
    				.build();
    	}
		
		return Response.status(System_value.code_200).entity(error_code_util.set_code(
				System_value.code_200, 
				"post get success", 
				null, 
				null
				)).build();
	}
}
