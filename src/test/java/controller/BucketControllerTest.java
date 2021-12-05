package controller;

import model.PayType;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class BucketControllerTest {
    private static BucketController bucketController = new BucketController();
    private static List<Product> list = new ArrayList<>();
    private static List<Product> list2 = new ArrayList<>();
    private static List<Product> list3 = new ArrayList<>();

    private static void setProducts() {
        for (int i = 0; i < 10; i++) {
            list.add(new Product(i, "test" + i, ((i + 1) * 1000), "kh" + i));
        }
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                list2.add(new Product(i, "test" + i, ((i + 1) * 1000), "kh" + i));
            } else {
                list2.add(new Product(100, "test100", 500, "jkh"));
            }
        }
    }

    /**
     * 장바구니에 제품 하나 담기
     */
    private static void testAddProduct() {
        Product product = new Product(1, "test", 1000, "kh");

        System.out.println("단일 제품 추가 테스트");
        bucketController.addProduct(product);
    }

    /**
     * 장바구니에 제품 여러 개 담기
     */
    private static void testAddProducts() {
        System.out.println("여러 제품 추가 테스트");
        bucketController.addProducts(list.toArray(new Product[list.size()]));
    }

    /**
     * 장바구니에 제품 삭제 하기
     */
    private static void testRemoveProduct() {
        System.out.println("제품 제거 테스트");
        bucketController.removeProduct(9);
        bucketController.removeProduct(100);
    }

    /**
     * 장바구니에 제품 전체 금액 확인 하기
     */
    private static void testGetTotalPrice() {
        System.out.println("장바구네 제품 총 가격 반환 테스트");
        System.out.println("Total Price (won) : " + bucketController.getTotalPrice());
    }

    /**
     * 송장 출력 기능 확인
     */
    private static void testPrintSellerSticker() {
        System.out.println("송장 출력 테스트");
//        bucketService.addProducts(list2.toArray(new Product[list2.size()]));
        bucketController.printSellerSticker();
    }

    /**
     * 같은 제품 중복으로 담는 경우 확인
     */
    private static void testAddSameProduct() {
        System.out.println("중복 제품 출력 테스트");
        bucketController.addProducts(list2.toArray(new Product[list2.size()]));
    }

    /**
     * 결제 시스템 확인
     */
    private static void testPayment(PayType payType) {
        System.out.println("결제 시스템 확인 테스트 : " + payType);
        bucketController.payment(payType);
    }

    public static void main(String[] args) {
        setProducts();
        testAddProduct();
        bucketController.printProducts();

        System.out.println("=====================================");

        testAddProducts();
        bucketController.printProducts();

        System.out.println("=====================================");

        testRemoveProduct();
        bucketController.printProducts();

        System.out.println("=====================================");
        testGetTotalPrice();
        bucketController.printProducts();

        System.out.println("=====================================");
        testPrintSellerSticker();

        System.out.println("=====================================");
        testAddSameProduct();
        bucketController.printProducts();

        System.out.println("=====================================");
        testPayment(PayType.ACOOUNT);
        testPayment(PayType.O_JIP_PAY);
        testPayment(PayType.CREDIT_CARD);
        testPayment(PayType.OPEN_BANKING);
        testPayment(PayType.UNKNOWN);

        bucketController.cleanProducts();
        bucketController.printProducts();
    }
}
