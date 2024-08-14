package designpatterns.creational.strategy;

class Duck {

    public void swim() {
        System.out.println("Duck is swimming");
    }

    public void quack() {
        System.out.println("Duck is Quacking");
    }

    public void fly() {
        System.out.println("Duck is flying");
    }
}

class MarlinDuck extends Duck {
  public void display(){
    System.out.println("I am Marlin Duck");
  }
  
}

class RubberDuck extends Duck {
    public void display(){
        System.out.println("I am a rubber duck");
    }
}

public class DuckHandler {
    public static void main(String[] args) {
      MarlinDuck mDuck = new MarlinDuck();
      RubberDuck rDuck = new RubberDuck();
      mDuck.swim();
      mDuck.quack();
      mDuck.fly();

      System.out.println("---------------");

      rDuck.swim();
      rDuck.quack();
      rDuck.fly();   // Wrong: A rubber duck cannot fly
    }
}