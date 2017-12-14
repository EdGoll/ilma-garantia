package cl.aduana.gar.negocio.base.qualifiers;



import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ TYPE })
@Retention(RUNTIME)
public @interface EntityAuditable {

	boolean auditable() default true;	
	
}
