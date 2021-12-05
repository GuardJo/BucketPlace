package service;

import model.Product;
import model.User;

import java.util.*;

/**
 * 장바구니에서 제공하는 서비스
 */
public class BucketService {
    private User user;
    private Map<Integer, Product> products;
    private float totalPrice;

    public BucketService() {
        this.user = new User();
        this.products = new HashMap<>();
        this.totalPrice = 0;
    }

    public BucketService(User user, Map<Integer, Product> products, float totalPrice) {
        this.user = user;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    /**
     * 장바구니에 제품을 담는 기능
     * @param product 장바구니에 담을 제품
     */
    public void addProduct(Product product) {
        if (products.isEmpty() || !products.containsKey(product.getId())) {
            products.put(product.getId(), product);
        }
        else if (products.containsKey(product.getId())) {
            products.get(product.getId()).updateProduct();
        }

        totalPrice += product.getPrice();
    }

    /**
     * 장바구니에 제품 여러 개를 담는 기능
     * @param products 여러 제품들
     */
    public void addProducts(Product... products) {
        for (Product p : products) {
            addProduct(p);
        }
    }

    /**
     * 주어진 id의 제품을 장바구니에서 제거한다
     * 장바구니에 해당 제품이 없을 경우 없다는 메시지 출력
     * @param productId 삭제할 제품 id
     */
    public void removeProduct(int productId) {
        if (products.containsKey(productId)) {
            totalPrice -= products.remove(productId).getPrice();
            System.out.println("remove Product, id : " + productId);
        }
        else {
            System.out.println("Not Found this Product");
        }
    }

    /**
     * 장바구니에 포함된 제품들의 전체 금액 반환
     * @return 사용자의 화폐 종류에 따른 총합 반환
     */
    public float getTotalPrice() {
        float total = 0;
        switch (user.getMoneyType()) {
            case WON:
                total = this.totalPrice;
                break;
            case DALLER:
                total = this.totalPrice / 1000;
                break;
            default:
                System.out.println("알 수 없는 화폐 단위입니다. 원화로 출력됩니다.");
                total = this.totalPrice;
        }
        return total;
    }

    /**
     * 판매자 별로 송장 출력
     */
    public void printSellerSticker() {
        Map<String, List<Product>> sticker = new HashMap<>();

        products.forEach((id, product) -> {
            if (sticker.isEmpty() || !sticker.containsKey(product.getSeller())) {
                List<Product> tmpList = new ArrayList<>();
                tmpList.add(product);
                sticker.put(product.getSeller(), tmpList);
            }
            else if (sticker.containsKey(product.getSeller())) {
                sticker.get(product.getSeller()).add(product);
            }
        });

        sticker.forEach((id, products) -> {
            for (Product product : products) {
                System.out.println("product : " + product.getName() + " " + product.getSeller() + " " + product.getCount() + " " + product.getPrice());
            }
        });
    }

    /**
     * 장바구니를 비운다
     */
    public void cleanProducts() {
        products.clear();
    }

    /**
     * 장바구니에 저장된 제품 내역 출력
     */
    public void printAllProducts() {
        products.forEach((id, p) -> {
            System.out.println("id : " + id + " , product : " + p.getName() + " " + p.getSeller() + " " + p.getPrice() + " " + p.getCount());
        });
    }

    public Map<Integer, Product> getProducts() {
        return this.products;
    }
}
