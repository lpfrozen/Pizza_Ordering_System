package services;

public class DeliveryService {
    public String getDeliveryEstimate(String address) {
        if (address.toLowerCase().contains("downtown")) {
            return "20 minutes";
        } else {
            return "30-45 minutes";
        }
    }
}
