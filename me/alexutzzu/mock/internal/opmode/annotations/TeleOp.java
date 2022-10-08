package me.alexutzzu.mock.internal.opmode.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.TYPE)
public @interface TeleOp {
    String name();
    String group() default "";


}
