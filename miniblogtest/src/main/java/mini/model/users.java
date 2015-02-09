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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="users")
public class users {
	@Id
	@Column(columnDefinition = "INT(16) UNSIGNED")
	@GeneratedValue(strategy=GenerationType.AUTO) //for autonumber
	private int id;
	
	@Column(columnDefinition = "VARCHAR(32)",nullable=false,unique=true)
	private String username;
	
	@Column(columnDefinition = "VARCHAR(72)",nullable=false)
	private String password;
	
	@Column(columnDefinition = "VARCHAR(32)",nullable=false)
	private String lastname;
	
	@Column(columnDefinition = "VARCHAR(32)",nullable=false)
	private String firstname;
	
	@Column(columnDefinition = "VARCHAR(254)",nullable=false,unique=true)
	private String email;
	
	public List<comments> getUser_comments() {
		return user_comments;
	}

	public void setUser_comments(List<comments> user_comments) {
		this.user_comments = user_comments;
	}

	public List<token> getUser_token() {
		return user_token;
	}

	public void setUser_token(List<token> user_token) {
		this.user_token = user_token;
	}

	@Column(columnDefinition = "TIMESTAMP(0) default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_at;
	
	@Column(columnDefinition = "TIMESTAMP(0) default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified_at;
	
	@OneToMany(targetEntity=posts.class,mappedBy="user",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	//@Cascade(org.hibernate.annotations.CascadeType.) funny think inside ^^!
	private List<posts> user_posts;
	
	@OneToMany(targetEntity=comments.class,mappedBy="user",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<comments> user_comments;

	@OneToMany(targetEntity=token.class,mappedBy="user",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<token> user_token;
	
	public users(){}

	public users(int id, String username, String password, String lastname,
			String firstname, String email, Date create_at, Date modified_at,
			List<posts> user_posts) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.create_at = create_at;
		this.modified_at = modified_at;
		this.user_posts = user_posts;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	public List<posts> getUser_posts() {
		return user_posts;
	}

	public void setUser_posts(List<posts> user_posts) {
		this.user_posts = user_posts;
	}
}
