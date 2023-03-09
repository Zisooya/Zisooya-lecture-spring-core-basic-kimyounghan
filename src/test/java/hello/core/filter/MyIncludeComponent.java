package hello.core.filter;

import java.lang.annotation.*;
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
    //@MyIncludeComponent가 붙은 것은 컴포넌트 스캔에 추가하는 컴포넌트 
}
