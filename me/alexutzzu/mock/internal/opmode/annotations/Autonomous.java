package me.alexutzzu.mock.internal.opmode.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(java.lang.annotation.ElementType.TYPE)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Autonomous {
    String name();

    String group() default "";
}
