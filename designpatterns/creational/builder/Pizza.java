package designpatterns.creational.builder;

public class Pizza {
    private String crust;
    private String cheese;
    private String chilly;
    private String seasoning;
    private String toppings;

    private Pizza() {
    }

    public static class Builder {
        private String crust;
        private String cheese;
        private String chilly;
        private String seasoning;
        private String toppings;

        public Builder(String crust, String cheese) {
            this.crust = crust;
            this.cheese = cheese;
        }

        public Builder addChilly(String chilly) {
            this.chilly = chilly;
            return this;
        }

        public Builder addSeasoning(String seasoning) {
            this.seasoning = seasoning;
            return this;
        }

        public Builder addTopping(String toppings) {
            this.toppings = toppings;
            return this;
        }

        public Pizza build() {
            Pizza pizza = new Pizza();
            pizza.crust = this.crust;
            pizza.cheese = this.cheese;
            pizza.chilly = this.chilly;
            pizza.seasoning = this.seasoning;
            pizza.toppings = this.toppings;
            return pizza;
        }

    }

    public String toString() {
        return "crust: " + crust + " cheese: " + cheese + " chilly: " + chilly + " seasoning: " + seasoning + " toppings: "
                + toppings;
    }

}
