package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

/*
// DIP, OCP를 위반하는 설계
    // 회원 찾기 위해 사용할 객체
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 할인 정책을 위해 사용할 객체 - 1. 1000원 고정 할인 금액
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 할인 정책을 위해 사용할 객체 - 2. 10% 할인율 적용
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
*/

// DIP, OCP를 위반하지 않는 설계    
    // 회원 찾기 위해 사용할 객체 - 인터페이스에만 의존하도록 설계.
    private MemberRepository memberRepository;

    // 할인 정책을 위해 사용할 객체 - 인터페이스에만 의존하도록 설계.
    private DiscountPolicy discountPolicy;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    // 생성자를 통해 각 인터페이스의 구현체로 무엇이 들어갈 지를 결정.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 회원 찾기
        Member member = memberRepository.findById(memberId);

        // 할인 정책(member 정보와 상품 가격 넘기기)
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 최종 생성된 주문 정보 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
