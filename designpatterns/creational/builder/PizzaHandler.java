package designpatterns.creational.builder;

public class PizzaHandler {
    public static void main(String[] args) {
        Pizza pizza1 = new Pizza.Builder("Flour", "Melted Cheese")
                .addChilly("Red Chilly")
                .build();
        Pizza pizza2 = new Pizza.Builder("Bread", "Melted Cheese")
                .addChilly("Red Chilly")
                .addTopping("Pepperoni")
                .addSeasoning("Oregano")
                .build();
        System.out.println(pizza1.toString());
        System.out.println(pizza2.toString());
    }
}
