package utils;

import java.util.ArrayList;
import java.util.Scanner;

public class FeedbackSystem {
    private static ArrayList<String> feedbackList = new ArrayList<>();

    public static void collectFeedback() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nRate your order (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Leave additional comments: ");
        String comments = scanner.nextLine();

        feedbackList.add("Rating: " + rating + ", Comments: " + comments);
        System.out.println("Thank you for your feedback!");
    }

    public static void displayFeedback() {
        System.out.println("\n--- Customer Feedback ---");
        for (String feedback : feedbackList) {
            System.out.println(feedback);
        }
    }
}
