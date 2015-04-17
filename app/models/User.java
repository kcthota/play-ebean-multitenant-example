package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * 
 * @author Krishna Chaitanya Thota
 *
 */
@Entity
@Table(name="User")
public class User extends Model{

	private static final long serialVersionUID = -4946196727155401208L;

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="name")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
