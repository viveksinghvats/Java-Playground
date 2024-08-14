package DesignPrincipal.Solid.LiskovSP.without;

public class DummyDog implements IDog {

    @Override
    public void makeNoise() {
        System.out.println("Dummy Dog making noise");
    }

    @Override
    public void eat() {
        System.out.println("Unimplemented method 'eat'");
    }

}
