package DesignPrincipal.Solid.LiskovSP.with;

public class DummyDog implements IDog {

    @Override
    public void makeNoise() {
        System.out.println("Dummy Dog making noise");
    }

}
