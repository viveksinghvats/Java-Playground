package designpatterns.creational.strategy;

class Duck {
    FlyBehaviour flyBehaviour;
    QuackBehaviour quackBehaviour;
    
    public void swim() {
        System.out.println("All ducks can swim");
    }

    public void display() {
        if (flyBehaviour != null) {
            flyBehaviour.fly();
        } else {
            System.out.println("Fly behaviour not set");
        }
        if (quackBehaviour != null) {
            quackBehaviour.quack();
        } else {
            System.out.println("Quack behaviour not set");
        }
    }
}

/* Interface over inheritance */
interface FlyBehaviour {
    void fly();
}

interface QuackBehaviour {
    void quack();
}

class FlyWithWings implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("Flying with wings");
    }
}

class RocketPoweredFlying implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("Flying with rocket speed");
    }
}

class NoFly implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("Cannot fly");
    }
}

class Quack implements QuackBehaviour {
    @Override
    public void quack() {
        System.out.println("Quacking");
    }
}

class Squeak implements QuackBehaviour {
    @Override
    public void quack() {
        System.out.println("Squeaking");
    }
}

class Mute implements QuackBehaviour {
    @Override
    public void quack() {
        System.out.println("Cannot speak");
    }
}

/** Concrete Classes */

class MallardDuck extends Duck {
    public MallardDuck() {
        flyBehaviour = new FlyWithWings();
        quackBehaviour = new Quack();
    }
}

class RubberDuck extends Duck {
    public RubberDuck() {
        flyBehaviour = new NoFly();
        quackBehaviour = new Squeak();
    }
}

class WoodenDuck extends Duck {
    public WoodenDuck() {
        flyBehaviour = new NoFly();
        quackBehaviour = new Mute();
    }
}

public class DuckSPHandler {
    public static void main(String[] args) {
        Duck mDuck = new MallardDuck();
        Duck rDuck = new RubberDuck();
        Duck wDuck = new WoodenDuck();
        System.out.println("Mallard Duck");
        mDuck.display();
        System.out.println("Rubber Duck");
        rDuck.display();
        System.out.println("Wooden Duck");
        wDuck.display();
    }
}
