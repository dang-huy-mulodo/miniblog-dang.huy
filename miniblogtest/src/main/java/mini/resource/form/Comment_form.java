package mini.resource.form;

import java.util.Calendar;

import javax.ws.rs.FormParam;

import mini.model.comments;
import mini.model.posts;
import mini.model.users;

public class Comment_form {
	@FormParam("id")
	public int id;
	@FormParam("postid")
	public int postid;
	@FormParam("comment")
	public String comment;
	
	public comments set_comment_data(users user,posts post){
		comments comment = new comments();
		
		comment.setUser(user);
		comment.setPost(post);
		comment.setComment(this.comment);
		comment.setCreate_at(Calendar.getInstance().getTime());
		comment.setModified_at(Calendar.getInstance().getTime());
		
		return comment;
	}
}
