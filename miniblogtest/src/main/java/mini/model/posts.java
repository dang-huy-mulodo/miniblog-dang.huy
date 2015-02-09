package mini.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

@Entity
@Table(name="posts")
public class posts {
	@ManyToOne
	@JoinColumn(nullable = false)
	@ForeignKey(name="fk_posts_users")
	private users user;
	
	public List<comments> getPosts_comments() {
		return posts_comments;
	}

	public void setPosts_comments(List<comments> posts_comments) {
		this.posts_comments = posts_comments;
	}

	@OneToMany(targetEntity=comments.class,mappedBy="post",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<comments> posts_comments;
	
	@Id
	@Column(columnDefinition = "INT(20) UNSIGNED")
	@GeneratedValue(strategy=GenerationType.AUTO) //for autonumber
	private int id;
	
	@Column(columnDefinition = "VARCHAR(100)",nullable=false)
	private String title;
	
	@Column(columnDefinition = "VARCHAR(2048)",nullable=false)
	private String content;

	@Column(columnDefinition = "TIMESTAMP(0) default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_at;
	
	@Column(columnDefinition = "TIMESTAMP(0) default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified_at;
	
	@Column(columnDefinition = "TINYINT(1) default TRUE",nullable=false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean status;
	
	public posts(){}

	public posts(users user, int id, String title, String content,
			Date create_at, Date modified_at, boolean status) {
		super();
		this.user = user;
		this.id = id;
		this.title = title;
		this.content = content;
		this.create_at = create_at;
		this.modified_at = modified_at;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}	

	public users getUser() {
		return user;
	}

	public void setUser(users user) {
		this.user = user;
	}
	
}
