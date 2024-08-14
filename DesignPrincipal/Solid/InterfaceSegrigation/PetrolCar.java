package DesignPrincipal.Solid.InterfaceSegrigation;

public class PetrolCar implements IEngine,IAcclerate{

    @Override
    public void acclerate() {
        System.out.println("Accleartion in 20 m /sec sqr");
    }

    @Override
    public void turnOnEngine() {
        System.out.println("Petrol engine is noisy");
    }
    
}
