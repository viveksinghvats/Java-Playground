package DesignPrincipal.Solid.LiskovSP.without;

public class DogHandler {
    public static void main(String[] args) {
        RealDog realDog = new RealDog();
        DummyDog dummyDog = new DummyDog();
        realDog.eat();
        realDog.makeNoise();

        dummyDog.eat();
        dummyDog.makeNoise();
    }
}
