package models;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String role; // "customer" or "admin"
    private int loyaltyPoints; // Tracks loyalty points
    private ArrayList<Pizza> favoritePizzas; // Stores favorite pizzas
    private String name; // Stores the user's name

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.loyaltyPoints = 0;
        this.favoritePizzas = new ArrayList<>();
        this.name = username; // Default name is the username
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    public ArrayList<Pizza> getFavoritePizzas() {
        return favoritePizzas;
    }

    public void addFavoritePizza(Pizza pizza) {
        favoritePizzas.add(pizza);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
