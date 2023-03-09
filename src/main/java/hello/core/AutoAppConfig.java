package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(

//        basePackages = "hello.core",
//        basePackageClasses = AutoAppConfig.class,
        // 예제 에러나지 않도록 애노테이션 빈 등록에서 뺄 타입을 정해주기.
        // (AppConfig는 @Configuration에 의해 빈을 수동으로 등록하는데, @Configuration에는 @Component가 포함되어 있기 때문. )
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {
}
