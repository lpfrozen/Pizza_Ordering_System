package utils;

import java.util.ArrayList;

public class PromotionManager {
    private static ArrayList<String> promotions = new ArrayList<>();

    static {
        promotions.add("Buy 1 Get 1 Free on Medium Pizzas!");
        promotions.add("20% Off Orders Above $30!");
    }

    public static void displayPromotions() {
        System.out.println("\n--- Current Promotions ---");
        for (String promo : promotions) {
            System.out.println("- " + promo);
        }
    }

    public static void addPromotion(String promo) {
        promotions.add(promo);
        System.out.println("Promotion added successfully: " + promo);
    }
}
