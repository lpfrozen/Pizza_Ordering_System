package models;

public class Order {
    private Pizza pizza;
    private String status; // e.g., "Preparing", "Out for Delivery", "Delivered"
    private String deliveryOption; // "Pickup" or "Delivery"
    private String deliveryEstimate; // Mocked delivery time

    public Order(Pizza pizza, String deliveryOption, String deliveryEstimate) {
        this.pizza = pizza;
        this.status = "Preparing";
        this.deliveryOption = deliveryOption;
        this.deliveryEstimate = deliveryEstimate;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }

    public String getDeliveryEstimate() {
        return deliveryEstimate;
    }

    @Override
    public String toString() {
        return pizza.toString() + ", Delivery Option: " + deliveryOption +
                ", Delivery Estimate: " + deliveryEstimate + ", Status: " + status;
    }

    public static Order fromString(String orderString) {
        try {
            // Split the input string into parts
            String[] parts = orderString.split(", ");
            if (parts.length < 8) {
                throw new IllegalArgumentException("Malformed order string: " + orderString);
            }

            // Extract pizza details
            String name = getValue(parts[0]);
            String crust = getValue(parts[1]);
            String sauce = getValue(parts[2]);
            String cheese = getValue(parts[3]);
            String[] toppings = getValue(parts[4]).replace("[", "").replace("]", "").split(", ");

            Pizza pizza = new Pizza(name, crust, sauce, toppings, cheese);

            // Extract delivery and status details
            String deliveryOption = getValue(parts[5]);
            String deliveryEstimate = parts.length > 6 ? getValue(parts[6]) : "N/A"; // Default to "N/A" if missing
            String status = parts.length > 7 ? getValue(parts[7]) : "Unknown";      // Default to "Unknown" if missing

            // Create and return the order
            Order order = new Order(pizza, deliveryOption, deliveryEstimate);
            order.setStatus(status);
            return order;

        } catch (Exception e) {
            System.out.println("Error parsing order string: " + orderString);
            throw e; // Rethrow for debugging/logging
        }
    }

    // Utility method to safely extract the value after ": "
    private static String getValue(String part) {
        String[] split = part.split(": ", 2); // Split into exactly 2 parts
        return split.length > 1 ? split[1].trim() : ""; // Return value or empty string if missing
    }
}
