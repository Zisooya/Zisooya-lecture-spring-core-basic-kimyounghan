package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 회원 찾기 위해 사용할 객체
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 할인 정책을 위해 사용할 객체
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 회원 찾기
        Member member = memberRepository.findById(memberId);

        // 할인 정책(member 정보와 상품 가격 넘기기)
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 최종 생성된 주문 정보 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
