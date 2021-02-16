package project.drinkmaker.basic;

import project.drinkmaker.DrinkType;

public enum BasicDrinkType implements DrinkType {

    TEA(1, "Tea", "T"),
    COFFEE(2, "Coffee", "C"),
    CHOCOLATE(3, "Chocolate" , "H");

    private final int id;
    private final String logo;
    private final String name;

    BasicDrinkType(final int id, final String name, final String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public static DrinkType getDrink(String command) {
        for (BasicDrinkType lEnum : values()) {
            if (lEnum.getName().equals(command)) {
                return lEnum;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

}
