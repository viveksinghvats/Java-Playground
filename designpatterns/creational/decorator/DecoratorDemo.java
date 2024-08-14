package designpatterns.creational.decorator;

public class DecoratorDemo {
    public static void main(String[] args) {
        Beverage beverage = new DarkRoast();
        beverage = new SoyMilk(beverage);
        beverage = new Mocha(beverage);
        beverage = new Mocha(beverage);
        System.out.println(beverage.getDescription());
        System.out.println(beverage.cost());

    }
}

// Base Class
abstract class Beverage{
   String description = "no description";
   
   public String getDescription(){
    return this.description;
   }

   public abstract double cost();
}

// Concerete Inhetance for concerte classes

class DarkRoast extends Beverage{

    @Override
    public double cost() {
        return 100.0;
    }
    
    public String getDescription(){
        return "Dark Roast coffee";
    }
}

// Behaviour Applier => This should also extend base class

abstract class CoffeeApplier extends Beverage{
    public abstract String getDescription();
}

// Behaviours 

class SoyMilk extends CoffeeApplier{

    Beverage beverage;

    public SoyMilk(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " Soy Milk";
    }

    @Override
    public double cost() {
        return beverage.cost() + 10.0;
    }
    
}

class Mocha extends CoffeeApplier{
    Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " Mocha";
    }

    @Override
    public double cost() {
        return beverage.cost() + 20.0;
    }
    
}

