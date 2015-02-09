package mini.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="comments")
public class comments {
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@ForeignKey(name="fk_comments_users")
	private users user;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@ForeignKey(name="fk_comments_posts")
	private posts post;
	
	@Id
	@Column(columnDefinition = "INT(20) UNSIGNED", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO) //for autonumber
	private int id;
	
	@Column(columnDefinition = "VARCHAR(254)",nullable=false)
	private String comment;

	@Column(columnDefinition = "TIMESTAMP(0)")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_at;
	
	@Column(columnDefinition = "TIMESTAMP(0)")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified_at;
	
	public comments(){}

	public users getUser() {
		return user;
	}

	public void setUser(users user) {
		this.user = user;
	}

	public posts getPost() {
		return post;
	}

	public void setPost(posts post) {
		this.post = post;
	}

	public comments(int id, String comment, String posts_id, String users_id,
			Date create_at, Date modified_at) {
		super();
		this.id = id;
		this.comment = comment;
		this.create_at = create_at;
		this.modified_at = modified_at;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public Date getModified_at() {
		return modified_at;
	}

	public void setModified_at(Date modified_at) {
		this.modified_at = modified_at;
	}
	
	
}
