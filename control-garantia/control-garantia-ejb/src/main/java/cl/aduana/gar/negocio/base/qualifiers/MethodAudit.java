package cl.aduana.gar.negocio.base.qualifiers;



import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;



@Target({ TYPE, METHOD, PARAMETER, FIELD })
@Retention(RUNTIME)
public @interface MethodAudit {

	public enum MethodType{
		INSERT, DELETE, UPDATE,DEFAULT
	}
	
	MethodType type() default MethodType.DEFAULT;	
	
}
