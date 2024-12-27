package models;

import java.util.Arrays;

public class Pizza {
    private String name;
    private String crust;
    private String sauce;
    private String[] toppings;
    private String cheese;

    public Pizza(String name, String crust, String sauce, String[] toppings, String cheese) {
        this.name = name;
        this.crust = crust;
        this.sauce = sauce;
        this.toppings = toppings;
        this.cheese = cheese;
    }

    // Getter for the name field
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Pizza: " + name + ", Crust: " + crust + ", Sauce: " + sauce +
                ", Cheese: " + cheese + ", Toppings: " + Arrays.toString(toppings);
    }
}
