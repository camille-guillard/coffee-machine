package project.drinkmaker.orange;


import project.drinkmaker.DrinkType;
import project.drinkmaker.money.PricedDrink;

public enum OrangeDrinkType implements DrinkType, PricedDrink {

    TEA(1, "Tea","T", 0.4),
    COFFEE(2, "Coffee", "C", 0.6),
    CHOCOLATE(3, "Chocolate", "H", 0.5),
    ORANGE(4, "Orange", "O", 0.6);

    private final int id;
    private final String name;
    private final String logo;
    private final double price;

    OrangeDrinkType(final int id, final String name, final String logo, final double price) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.price = price;
    }

    public static OrangeDrinkType getDrink(String command) {
        for (OrangeDrinkType lEnum : values()) {
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
