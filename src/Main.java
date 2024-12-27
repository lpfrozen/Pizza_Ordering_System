import models.Pizza;
import models.User;
import services.AuthService;
import services.OrderService;
import services.PaymentService;
import utils.FeedbackSystem;
import utils.PromotionManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();
        Scanner scanner = new Scanner(System.in);

        User user = null;
        while (user == null) {
            user = authService.login();
        }

        boolean running = true;

        while (running) {
            if (user.getRole().equals("customer")) {
                System.out.println("\nCustomer Menu:");
                System.out.println("1. Create a Custom Pizza");
                System.out.println("2. View Orders");
                System.out.println("3. Save Pizza to Favorites");
                System.out.println("4. View Favorite Pizzas");
                System.out.println("5. Reorder Favorite Pizza");
                System.out.println("6. Make Payment");
                System.out.println("7. Leave Feedback");
                System.out.println("8. View Promotions");
                System.out.println("9. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        orderService.createCustomPizza(user);
                        break;
                    case 2:
                        orderService.viewOrders();
                        break;
                    case 3:
                        System.out.print("Enter Pizza Name to Save: ");
                        String pizzaName = scanner.nextLine();
                        user.addFavoritePizza(new Pizza(pizzaName, "Thick", "Tomato", new String[]{"Cheese"}, "Mozzarella"));
                        System.out.println("Pizza added to favorites.");
                        break;
                    case 4:
                        System.out.println("\n--- Favorite Pizzas ---");
                        if (user.getFavoritePizzas().isEmpty()) {
                            System.out.println("No favorite pizzas found.");
                        } else {
                            for (int i = 0; i < user.getFavoritePizzas().size(); i++) {
                                System.out.println((i + 1) + ". " + user.getFavoritePizzas().get(i));
                            }
                        }
                        break;
                    case 5:
                        System.out.print("Enter the favorite pizza number to reorder: ");
                        int reorderIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (reorderIndex < 1 || reorderIndex > user.getFavoritePizzas().size()) {
                            System.out.println("Invalid choice.");
                        } else {
                            orderService.reorderFavoritePizza(user.getFavoritePizzas().get(reorderIndex - 1));
                        }
                        break;
                    case 6:
                        paymentService.processPayment(user, 15.99);
                        break;
                    case 7:
                        FeedbackSystem.collectFeedback();
                        break;
                    case 8:
                        PromotionManager.displayPromotions();
                        break;
                    case 9:
                        System.out.println("Logging out...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else if (user.getRole().equals("admin")) {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. View Orders");
                System.out.println("2. Update Order Status");
                System.out.println("3. Add Promotion");
                System.out.println("4. View Promotions");
                System.out.println("5. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        orderService.viewOrders();
                        break;
                    case 2:
                        orderService.updateOrderStatus();
                        break;
                    case 3:
                        System.out.print("Enter New Promotion: ");
                        String promo = scanner.nextLine();
                        PromotionManager.addPromotion(promo);
                        break;
                    case 4:
                        PromotionManager.displayPromotions();
                        break;
                    case 5:
                        System.out.println("Logging out...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }

        scanner.close();
        System.out.println("Thank you for using the Pizza Ordering System!");
    }
}
