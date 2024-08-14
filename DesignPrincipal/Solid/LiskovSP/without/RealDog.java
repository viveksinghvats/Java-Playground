package DesignPrincipal.Solid.LiskovSP.without;

public class RealDog implements IDog {

    @Override
    public void makeNoise() {
        System.out.println("Real Dog making noise");
    }

    @Override
    public void eat() {
        System.out.println("Real Dog eating");
    }

}
