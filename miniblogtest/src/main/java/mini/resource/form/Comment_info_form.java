package mini.resource.form;

import mini.model.comments;

public class Comment_info_form {
	public int id;
	public String comment;
	public int userid;
	public Comment_info_form(comments comment) {
		super();
		this.id = comment.getId();
		this.comment = comment.getComment();
		this.userid = comment.getUser().getId();
	}
}
