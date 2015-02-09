package mini.resource.form;

import mini.model.posts;

public class Post_info_form {
	public int id;
	public String title;
	public boolean status;
	public int user_id;
	public String content;
	
	public Post_info_form(posts post) {
		super();
		this.id = post.getId();
		this.title = post.getTitle();
		this.status = post.isStatus();
		this.user_id = post.getUser().getId();
		this.content = post.getContent();
	}
	
}
