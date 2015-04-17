package actions;

import java.util.HashSet;
import java.util.Set;

import master.Tenant;
import models.User;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;
import annotations.TenantAware;

import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
/**
 * 
 * @author Krishna Chaitanya Thota
 *
 */
public class TenantAwareAction extends Action<TenantAware> {
	
	private static Set<String> ebeanConfiguredTenants = new HashSet<String>();
	
	@Override
	public Promise<Result> call(Context ctx) throws Throwable {
		String tenantId = ctx.request().getHeader("tenantId");
		
		//check for tenantId header. This helps identify the tenant for this call
		if(tenantId==null) {
			throw new RuntimeException("TenantId not found");
		}
		
		
		checkOrConfigureEbeanServer(tenantId);
		return delegate.call(ctx);
	}
	
	/**
	 * checks if there is an ebean server registered for the tenantId, if not register one
	 * @param tenantId
	 */
	private void checkOrConfigureEbeanServer(String tenantId) {
		if(!ebeanConfiguredTenants.contains(tenantId)) {
			Tenant tenant = Tenant.finder.byId(tenantId);
			
			if(tenant==null) {
				throw new RuntimeException("Tenant not found with Id: "+tenantId);
			}
			registerEbeanServer(tenant);
		}
	}
	
	/**
	 * Registers a new EbeanServer for the passed in tenant.
	 * @param tenant
	 */
	private void registerEbeanServer(Tenant tenant) {
		ServerConfig config = new ServerConfig();
		config.setName(tenant.getTenantId().toString());
		
		DataSourceConfig dsConfig = new DataSourceConfig();
		
		dsConfig.setDriver("com.mysql.jdbc.Driver");
		dsConfig.setUsername(tenant.getDatabaseUser());
		dsConfig.setPassword(tenant.getDatabasePassword());
		dsConfig.setUrl(String.format("jdbc:mysql://localhost:3306/%s", tenant.getDatabaseName()));
		dsConfig.setHeartbeatSql("select 1");
		
		config.setDataSourceConfig(dsConfig);
		config.setDdlGenerate(false);  
		config.setDdlRun(false);
		config.setRegister(true);
		
		config.addClass(User.class);
		
		EbeanServerFactory.create(config);
	}

}
