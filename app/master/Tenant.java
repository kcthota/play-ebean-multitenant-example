package master;

import java.util.UUID;

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
@Table(name = "Tenant")
public class Tenant extends Model {
	
	private static final long serialVersionUID = 7882249930953370082L;

	@Id
	@Column(name="tenantId")
	private UUID tenantId;
	
	@Column(name="db_name")
	private String databaseName;
	
	@Column(name="db_user")
	private String databaseUser;
	
	@Column(name="db_password")
	private String databasePassword;

	public UUID getTenantId() {
		return tenantId;
	}

	public void setTenantId(UUID tenantId) {
		this.tenantId = tenantId;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabaseUser() {
		return databaseUser;
	}

	public void setDatabaseUser(String databaseUser) {
		this.databaseUser = databaseUser;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}
	
	public static Finder<String,Tenant> finder = new Finder<String,Tenant>(String.class, Tenant.class); 
	
	
}
