package mini.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mini.model.token;
import mini.model.users;
import mini.resource.System_value;
import mini.resource.access_token;
import mini.resource.error_code_util;
import mini.resource.form.User_Change_Password_form;
import mini.resource.form.User_Info_form;
import mini.resource.form.User_Input_form;
import mini.service.Data_Validator;
import mini.service.Token_service;
import mini.service.User_service;

import org.jboss.resteasy.annotations.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/v1/user")
@Produces(MediaType.APPLICATION_JSON)
public class User_controller {

    @Autowired
    private User_service user_service;
    @Autowired
    private Data_Validator data_validator;
    @Autowired
    private Token_service token_service;

    @POST
    @Path("adduser")
    @Consumes("application/x-www-form-urlencoded")
    public Response Create_user(
        @Form User_Input_form data){

        if(!data_validator.Validate_user_signup_data(data)){
            return Response.status(System_value.errorcode_1001)
                .entity(error_code_util.set_error_code(System_value.errorcode_1001))
                .build();}

        if(user_service.Check_User_Exist(data.username)){
            return Response.status(System_value.errorcode_2001)
                .entity(error_code_util.set_error_code(System_value.errorcode_2001))
                .build();}

        if(user_service.Check_Email_Exist(data.email)){
            return Response.status(System_value.errorcode_2002)
                .entity(error_code_util.set_error_code(System_value.errorcode_2002))
                .build();}

        users user = data.set_sign_up_data(new users());

        if(!user_service.Insert_user(user)){
            return Response.status(System_value.errorcode_2003)
                .entity(error_code_util.set_error_code(System_value.errorcode_2003))
                .build();}
        
        return Response.status(System_value.code_200)
            .entity(error_code_util.set_code(
                System_value.code_200, 
                "User account was created successfully.", 
                "Account created success!", 
                user))
            .build();
    }

    @POST
    @Path("login")
    @Consumes("application/x-www-form-urlencoded")
    public Response User_Login(
        @Form User_Input_form data){

        if(!data_validator.Validate_user_login_data(data)){
            return Response.status(System_value.errorcode_1001)
                .entity(error_code_util.set_error_code(System_value.errorcode_1001))
                .build();}

        String access_token_key=user_service.Login_user(data);

        if(access_token_key == null){
            return Response.status(System_value.errorcode_2004)
                .entity(error_code_util.set_error_code(System_value.errorcode_2004))
                .build();}

        access_token obj = new access_token();
        obj.access_token=access_token_key;

        return Response.status(System_value.code_200)
            .entity(error_code_util.set_code(
                System_value.code_200,
                "Login successful!",
                null,
                obj))
            .build();
    }

    @POST
    @Path("logout")
    @Consumes("application/json")
    public Response User_Logout(
        @HeaderParam("access_token") String access_token){

        if(!data_validator.Validate_Access_Token(access_token)){
            return Response.status(System_value.errorcode_1001)
                .entity(error_code_util.set_error_code(System_value.errorcode_1001))
                .build();}

        token token = token_service.Check_access_token(access_token);

        if(token==null){
            return Response.status(System_value.errorcode_2008)
                .entity(error_code_util.set_error_code(System_value.errorcode_2008))
                .build();}

        if(!user_service.Logout_user(token)){
            return Response.status(System_value.errorcode_2005)
                .entity(error_code_util.set_error_code(System_value.errorcode_2005))
                .build();}

        return Response.status(System_value.code_200)
            .entity(error_code_util.set_code(
                System_value.code_200,
            	"Logout successful!",
            	null,
            	null))
            .build();
    }

    @PUT
    @Path("user")
    @Consumes("application/x-www-form-urlencoded")
    public Response User_Update(
        @HeaderParam("access_token") String access_token,
        @Form User_Input_form data){

        if(!data_validator.Validate_Access_Token(access_token)||
           !data_validator.Validate_user_update_data(data)){
                return Response.status(System_value.errorcode_1001)
                    .entity(error_code_util.set_error_code(System_value.errorcode_1001))
                    .build();}

        token token = token_service.Check_access_token(access_token);

        if(token==null){
            return Response.status(System_value.errorcode_2008)
                .entity(error_code_util.set_error_code(System_value.errorcode_2008))
                .build();}

        if(!user_service.Update_user_info(data,token.getUser().getId())){
            return Response.status(System_value.errorcode_2006)
                .entity(error_code_util.set_error_code(System_value.errorcode_2006))
                .build();}

        return Response.status(System_value.code_200)
                .entity(error_code_util.set_code(200, "Update user info successful!", null,null ))
                .build();
    }

    @GET
    @Path("user")
    public Response Get_user_info(
        @HeaderParam("access_token") String access_token,
        @QueryParam("mode") String mode,
        @QueryParam("id")int id){

        users user;

        if(!data_validator.Validate_Access_Token(access_token)||
           !data_validator.Validate_user_get_info_parameter(mode)){
                return Response.status(System_value.errorcode_1001)
                        .entity(error_code_util.set_error_code(System_value.errorcode_1001))
                        .build();}

        token token = token_service.Check_access_token(access_token);

        if(token==null){
            return Response.status(System_value.errorcode_2008)
                    .entity(error_code_util.set_error_code(System_value.errorcode_2008))
                    .build();}

        switch(mode){
        case "current":
            user = user_service.Get_user_by_id(token.getUser().getId());
            if(user==null){
                return Response.status(System_value.errorcode_2009)
                        .entity(error_code_util.set_error_code(System_value.errorcode_2009))
                        .build();}
            return Response.status(System_value.code_200)
                    .entity(error_code_util.set_code(200, "Info of current user", null,new User_Info_form(user) ))
                    .build();

        case "id":
            user = user_service.Get_user_by_id(id);
            if(user==null){
                return Response.status(System_value.errorcode_2009)
                        .entity(error_code_util.set_error_code(System_value.errorcode_2009))
                        .build();}
            
            return Response.status(System_value.code_200)
                    .entity(error_code_util.set_code(200, "Info of user by id", null,new User_Info_form(user) ))
                    .build();

        }
        return Response.status(System_value.code_200)
                .entity(error_code_util.set_code(200,"", null,null ))
                .build();
    }
    
    @PUT
    @Path("pass")
    @Consumes("application/x-www-form-urlencoded")
    public Response Change_user_password(
            @HeaderParam("access_token") String access_token,
            @Form User_Change_Password_form data){
        if(!data_validator.Validate_Access_Token(access_token)||
           !data_validator.Validate_Change_Password_data(data)){
            return Response.status(System_value.errorcode_1001)
                    .entity(error_code_util.set_error_code(System_value.errorcode_1001))
                    .build();}
        
        token token = token_service.Check_access_token(access_token);
        
        if(token==null){
            return Response.status(System_value.errorcode_2008)
                    .entity(error_code_util.set_error_code(System_value.errorcode_2008))
                    .build();}

        if(!user_service.Change_password(data,token.getUser().getId())){
            return Response.status(System_value.errorcode_2007)
                    .entity(error_code_util.set_error_code(System_value.errorcode_2007))
                    .build();}
        
        return Response.status(System_value.code_200)
                .entity(error_code_util.set_code(200, "Password Change successful!", null,null ))
                .build();
    }

    @GET
    @Path("search/{query}")
    public Response Search_user_by_name(
            @HeaderParam("access_token") String access_token,
            @PathParam("query")String query){
        if(!data_validator.Validate_Access_Token(access_token)||
           !data_validator.Validate_Search_query(query)){
                    return Response.status(System_value.errorcode_1001)
                            .entity(error_code_util.set_error_code(System_value.errorcode_1001))
                            .build();}
        
        token token = token_service.Check_access_token(access_token);
        
        if(token==null){
            return Response.status(System_value.errorcode_2008)
                    .entity(error_code_util.set_error_code(System_value.errorcode_2008))
                    .build();}
        
        return Response.status(System_value.code_200)
                .entity(error_code_util.set_code(200, "result return", null,user_service.Search_user_by_username(query)))
                .build();
    }
}
