package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import play.mvc.With;
import actions.TenantAwareAction;
/**
 * 
 * @author Krishna Chaitanya Thota
 *
 */
@With(TenantAwareAction.class)
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TenantAware {

}
