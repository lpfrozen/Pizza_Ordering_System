package services;

import models.User;

import java.util.Scanner;

public class PaymentService {
    private Scanner scanner = new Scanner(System.in);

    public void processPayment(User user, double amount) {
        System.out.println("\n--- Payment Processing ---");
        System.out.println("Order Amount: $" + amount);
        System.out.println("Select Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Digital Wallet");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter Credit Card Number: ");
                scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter Digital Wallet ID: ");
                scanner.nextLine();
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }

        System.out.println("Payment successful!");
        user.addLoyaltyPoints(10); // Add loyalty points
        System.out.println("You earned 10 loyalty points! Total points: " + user.getLoyaltyPoints());
    }
}
