package controller;

/**
 * 결제 시스템
 */
public class PayController {
    public void payByAccount() {
        System.out.println("계좌 이체 삐빅.");
    }

    public void payByCredit() {
        System.out.println("카드 결제 입니다 촤악~");
    }

    public void payByOpenBanking() {
        System.out.println("오픈 뱅킹입니다, 두둥");
    }

    public void payByOJipPay() {
        System.out.println("내년에 찾아뵙겠습니다 ^_^, 다른 결제 수단을 이용해주세요");
    }
}
