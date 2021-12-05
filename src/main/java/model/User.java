package model;

public class User {
    private String name;
    private UserType type;
    private MoneyType moneyType;

    public User() {
        this.name = null;
        this.type = UserType.UNKNOWN;
        this.moneyType = MoneyType.WON;
    }

    public User(String name, UserType type, MoneyType moneyType) {
        this.name = name;
        this.type = type;
        this.moneyType = moneyType;
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }
}
