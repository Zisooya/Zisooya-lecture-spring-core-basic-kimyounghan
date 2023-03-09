package hello.core.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
    //@MyExcludeComponent가 붙은 것은 컴포넌트 스캔에서 제외하는 컴포넌트 
}
