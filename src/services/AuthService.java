package services;

import models.User;

import java.util.Scanner;

public class AuthService {
    private User[] users = {
            new User("customer", "customer123", "customer"),
            new User("admin", "admin123", "admin")
    };

    public User login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Login ---");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login Successful! Welcome, " + user.getRole() + ".");
                return user;
            }
        }

        System.out.println("Invalid credentials. Please try again.");
        return null;
    }
}
