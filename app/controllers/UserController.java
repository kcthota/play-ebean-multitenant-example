package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.User;
import annotations.TenantAware;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * 
 * @author Krishna Chaitanya Thota
 *
 */
public class UserController extends Controller {
	
	@TenantAware
	public static Result getUsers() {
		String tenantId = request().getHeader("tenantId");
		//get the Ebean server for passed in tenantId and then query with that server
		List<User> tenantUsers = Ebean.getServer(tenantId).find(User.class).findList();
		
		return ok(Json.toJson(tenantUsers));
		
	}
}
