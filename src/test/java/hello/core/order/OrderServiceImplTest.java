package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class OrderServiceImplTest {
    @Test
    void createOrder() {
        // 프레임워크 없이 순수한 자바 코드를 단위 테스트 하는 경우
/*
        // 1. 수정자 주입: createOrder()을 실행하려면 memberRepository와 discountPolicy 객체가 필요한데 누락됨.
        // => NullPoint 예외 뜸.
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.createOrder(1L, "itemA", 10000);
*/
        // 2. 생성자 주입
        // => 순수 자바 코드로 단위 테스트 시에는 생성자 주입을 사용하는 것이 안전.
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        org.assertj.core.api.Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}