package project.drinkmaker.money;


import project.drinkmaker.DrinkType;

public enum MoneyDrinkType implements DrinkType, PricedDrink {

    TEA(1, "Tea","T", 0.4),
    COFFEE(2, "Coffee", "C", 0.6),
    CHOCOLATE(3, "Chocolate", "H", 0.5);

    private final int id;
    private final String name;
    private final String logo;
    private final double price;

    MoneyDrinkType(final int id, final String name, final String logo, final double price) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.price = price;
    }

    public static MoneyDrinkType getDrink(String command) {
        for (MoneyDrinkType lEnum : values()) {
            if (lEnum.getName().equals(command)) {
                return lEnum;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public double getPrice() {
        return price;
    }
}
