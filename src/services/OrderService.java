package services;

import models.Order;
import models.Pizza;
import services.DeliveryService;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderService {
    private static final String ORDER_FILE = "C:/Users/acer/Downloads/Lahiru_Java/PizzaOrderingSystem/src/utils/orders.txt";
    private DeliveryService deliveryService = new DeliveryService();
    private Scanner scanner = new Scanner(System.in);

    public void createCustomPizza(models.User user) {
        System.out.println("\n--- Create Your Custom Pizza ---");

        System.out.print("Enter Pizza Name: ");
        String name = scanner.nextLine();

        System.out.print("Choose a crust (Thin/Thick/Stuffed): ");
        String crust = scanner.nextLine();

        System.out.print("Choose a sauce (Tomato/BBQ/Pesto): ");
        String sauce = scanner.nextLine();

        System.out.print("Choose cheese (Mozzarella/Cheddar/Vegan): ");
        String cheese = scanner.nextLine();

        System.out.print("Enter toppings (comma-separated): ");
        String[] toppings = scanner.nextLine().split(",");

        Pizza pizza = new Pizza(name, crust, sauce, toppings, cheese);

        System.out.print("Choose delivery option (Pickup/Delivery): ");
        String deliveryOption = scanner.nextLine();

        String deliveryEstimate = deliveryOption.equalsIgnoreCase("Delivery") ? "30-45 minutes" : "N/A";

        Order order = new Order(pizza, deliveryOption, deliveryEstimate);
        saveOrderToFile(order);

        System.out.println("\nOrder placed successfully!");
        System.out.println(order);
    }

    public void reorderFavoritePizza(Pizza pizza) {
        System.out.print("Choose delivery option (Pickup/Delivery): ");
        String deliveryOption = scanner.nextLine();

        String deliveryEstimate = "";
        if (deliveryOption.equalsIgnoreCase("Delivery")) {
            System.out.print("Enter delivery address: ");
            String address = scanner.nextLine();
            deliveryEstimate = deliveryService.getDeliveryEstimate(address);
        }

        Order order = new Order(pizza, deliveryOption, deliveryEstimate);
        saveOrderToFile(order);

        System.out.println("\nReordered successfully!");
        System.out.println(order);
    }

    public void viewOrders() {
        ArrayList<Order> orders = readOrdersFromFile();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("\n--- All Orders ---");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + ". " + orders.get(i));
            }
        }
    }

    public void updateOrderStatus() {
        ArrayList<Order> orders = readOrdersFromFile();
        if (orders.isEmpty()) {
            System.out.println("No orders available to update.");
            return;
        }

        System.out.println("\n--- Update Order Status ---");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". " + orders.get(i));
        }

        System.out.print("Enter the order number to update: ");
        int orderIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (orderIndex < 0 || orderIndex >= orders.size()) {
            System.out.println("Invalid order number.");
            return;
        }

        System.out.print("Enter new status (e.g., Preparing, Out for Delivery, Delivered): ");
        String newStatus = scanner.nextLine();

        orders.get(orderIndex).setStatus(newStatus);
        writeOrdersToFile(orders);

        System.out.println("Order status updated successfully.");
    }

    private void saveOrderToFile(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE, true))) {
            writer.write(order.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    private ArrayList<Order> readOrdersFromFile() {
        ArrayList<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // Skip empty lines
                    try {
                        orders.add(Order.fromString(line));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Skipping malformed order: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading orders: " + e.getMessage());
        }
        return orders;
    }

    private void writeOrdersToFile(ArrayList<Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE))) {
            for (Order order : orders) {
                writer.write(order.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing orders: " + e.getMessage());
        }
    }
}
