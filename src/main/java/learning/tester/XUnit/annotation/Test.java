package learning.tester.XUnit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//적용될 타입
@Retention(RetentionPolicy.RUNTIME)//언제까지 살아남을것인가
public @interface Test {
}
