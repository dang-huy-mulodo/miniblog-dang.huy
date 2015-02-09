package mini.resource.form;

import java.util.Calendar;

import javax.ws.rs.FormParam;

import mini.model.posts;
import mini.model.users;

public class Post_form {
	@FormParam("id")
	public int id;
	@FormParam("title")
	public String title;
	@FormParam("content")
	public String content;
	@FormParam("status")
	public boolean status;
	
	public posts set_post_data(users user){
		posts post = new posts();
		
		post.setTitle(this.title);
		post.setContent(this.content);
		post.setUser(user);
		post.setStatus(true);
		post.setCreate_at(Calendar.getInstance().getTime());
		post.setModified_at(Calendar.getInstance().getTime());
		
		return post;
	}
}
