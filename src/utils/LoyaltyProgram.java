package utils;

import models.User;

public class LoyaltyProgram {
    public static void addPoints(User user) {
        System.out.println("Loyalty points added for " + user.getName() + "!");
    }
}
