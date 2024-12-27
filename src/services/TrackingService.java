package services;

import models.Order;


public class TrackingService {
    public void displayOrderStatus(Order order) {
        if (order != null) {
            System.out.println("\n--- Order Tracking ---");
            System.out.println("Pizza: " + order.getPizza().getName());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Delivery Option: " + order.getDeliveryOption());
            if (order.getDeliveryOption().equalsIgnoreCase("Delivery")) {
                System.out.println("Estimated Time: " + order.getDeliveryEstimate());
            }
        } else {
            System.out.println("No active orders to track.");
        }
    }

    public void updateOrderStatus(Order order, String status) {
        if (order != null) {
            order.setStatus(status);
            System.out.println("Order status updated to: " + status);
        } else {
            System.out.println("No active orders to update.");
        }
    }
}
