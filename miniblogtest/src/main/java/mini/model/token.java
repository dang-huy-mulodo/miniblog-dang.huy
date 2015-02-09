package mini.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@Entity
public class token {
	@ManyToOne
	//@JoinColumn(nullable = false)
	@ForeignKey(name="fk_token_users")
	private users user;
	
	@Id
	@Column(columnDefinition = "INT(16) UNSIGNED")
	@GeneratedValue(strategy=GenerationType.AUTO) //for autonumber
	private int id;
	
	@Column(columnDefinition = "VARCHAR(64)",nullable=false,unique=true)
	private String access_token;
	
	@Column(columnDefinition = "TIMESTAMP(0) default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_at;
	
	@Column(columnDefinition = "TIMESTAMP(0) default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expired;

	public users getUser() {
		return user;
	}

	public void setUser(users user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}
}
