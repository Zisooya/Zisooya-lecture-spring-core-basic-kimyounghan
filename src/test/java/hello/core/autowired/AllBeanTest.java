package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {
    @Test
    void findAllBean() {
        // AutoAppConfig와 DiscountService 모두 스프링 빈 설정 클래스로 등록.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        // 각 회원의 등급에 따라 할인이 얼마나 되는 지 알아볼 수 있는 서비스
        DiscountService discountService = ac.getBean(DiscountService.class);
        // 회원 등록
        Member member = new Member(1L, "userA", Grade.VIP);

        //  discountService.discount(): 각 회원의 등급에 따라 할인이 얼마나 되는 지 알아볼 수 있는 메소드
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        // 고정액 할인 정책을 따라서 할인금액이 1000원이어야 함.
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        // 정률 할인 정책을 따라서 할인금액이 2000원이어야 함.
        assertThat(rateDiscountPrice).isEqualTo(2000);

    }

    // 테스트용 서비스
    static class DiscountService {

        // 1. 방법(1) - Map으로 모든 DiscountPolicy 받기
        private final Map<String, DiscountPolicy> policyMap;

        // 1. 방법(2) - List로 모든 DiscountPolicy 받기
        private final List<DiscountPolicy> policies;

        // 2. 생성자로 의존 관계 주입받기
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap,
                               List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);
            return discountPolicy.discount(member, price);
        }
    }
}