package DesignPrincipal.Solid.LiskovSP.with;

public class RealDog implements IRealDog{

    @Override
    public void makeNoise() {
        System.out.println("Real Dog making noise");
    }

    @Override
    public void eat() {
        System.out.println("Real Dog eating");
    }
    
}
