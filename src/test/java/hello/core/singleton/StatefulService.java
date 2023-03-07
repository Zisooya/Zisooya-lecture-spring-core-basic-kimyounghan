package hello.core.singleton;

public class StatefulService {

    // 이런 공유 필드를 조심해야 한다. 무상태로 설계 해야 함.
//    private int price; // 상태를 유지하는 필드 10000 -> 20000

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기가 문제!
        return price;
    }

/*
    public int getPrice() {
        return price;
    }
 */
}