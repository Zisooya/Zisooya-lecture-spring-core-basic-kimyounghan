package hello.core.order;

public interface OrderService {
    // OrderService는 클라이언트에게 주문을 받아서 주문 결과를 반환하는 역할만 한다.
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
