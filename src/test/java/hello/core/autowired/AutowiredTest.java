package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        // 스프링 빈으로 관리되지 않는 Member 클래스를 가지고 테스트.
        @Autowired(required=false) // => 의존관계가 없다면 이 메서드 자체가 호출이 안됨.
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){ // => 호출은 되지만 값이 null로 뜬다.
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) { // => 스프링 빈이 없으면 Optional.empty로 뜬다.
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
