package model;

public class Product {
    private int id;
    private String name;
    private int price;
    private String seller;
    private int count;


    public Product(int id, String name, int price, String seller) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.count = 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void updateProduct() {
        this.price = this.price * 2;
        this.count++;
    }

    public int getCount() {
        return count;
    }
}
