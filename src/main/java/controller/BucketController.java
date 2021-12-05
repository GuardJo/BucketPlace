package controller;

import model.*;
import service.BucketService;

/**
 * 장바구니 시스템
 */
public class BucketController {
    private static final String USER_ID = "test";
    private static final String USER_PWD = "test1";

    private BucketService bucketService;
    private PayController payController;

    public BucketController() {
        this.bucketService = new BucketService();
        this.payController = new PayController();
    }

    /**
     * 장바구니에 제품을 담는 기능
     *
     * @param product 장바구니에 담을 제품
     */
    public void addProduct(Product product) {
        bucketService.addProduct(product);
    }

    /**
     * 장바구니에 제품 여러 개를 담는 기능
     * @param products 여러 제품들
     */
    public void addProducts(Product... products) {
        bucketService.addProducts(products);
    }

    /**
     * 주어진 id의 제품을 장바구니에서 제거한다
     * 장바구니에 해당 제품이 없을 경우 없다는 메시지 출력
     * @param id 삭제할 제품 id
     */
    public void removeProduct(int id) {
        bucketService.removeProduct(id);
    }

    /**
     * 장바구니에 포함된 제품들의 전체 금액 반환
     * @return 사용자의 화폐 종류에 따른 총합 반환
     */
    public float getTotalPrice() {
        return bucketService.getTotalPrice();
    }

    /**
     * 판매자 별로 송장 출력
     */
    public void printSellerSticker() {
        bucketService.printSellerSticker();
    }

    /**
     * 결제 수단 선택 및 결제 진행
     * @param payType 결제 수단
     */
    public void payment(PayType payType) {
        switch (payType) {
            case ACOOUNT:
                payController.payByAccount();
                break;
            case CREDIT_CARD:
                payController.payByCredit();
                break;
            case O_JIP_PAY:
                payController.payByOJipPay();
                break;
            case OPEN_BANKING:
                payController.payByOpenBanking();
                break;
            default:
                System.out.println("올바른 결제 수단이 아닙니다.");
        }
    }

    /**
     * 로그인 성공 시 기존 장바구니 정보 가져오기
     * @param id 로그인 시도 id
     * @param pwd 로그인 시도 비밀번호
     */
    public void login(String id, String pwd) {
        if (id.equals(USER_ID)) {
            if (pwd.equals(USER_PWD)) {
                System.out.println("Login Success");
                User user = new User("kyeongho", UserType.USER, MoneyType.DALLER);
                bucketService = new BucketService(user, bucketService.getProducts() ,bucketService.getTotalPrice());
            }
            else {
                System.out.println("Login Fail, Not Currect password");
            }
        }
        else {
            System.out.println("Login Fail, Not Found Id");
        }
    }

    /**
     * 장바구니를 비운다
     */
    public void cleanProducts() {
        bucketService.cleanProducts();
    }

    /**
     * 현재 장바구니의 제품들을 출력
     */
    public void printProducts() {
        bucketService.printAllProducts();
    }
}
