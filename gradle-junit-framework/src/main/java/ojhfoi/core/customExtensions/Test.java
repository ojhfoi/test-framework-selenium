package ojhfoi.core.customExtensions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@org.junit.jupiter.api.Test
public @interface Test {
    public String browserConfig() default "";
    public String testConfig() default "";
}
