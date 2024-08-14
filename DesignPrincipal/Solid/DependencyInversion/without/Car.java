package DesignPrincipal.Solid.DependencyInversion.without;

public class Car {
    DieselEngine engine;
    // DieselEngine is tightly coupled with Car, so changing the implementation in DieselEngine object might affect the Car object.
    public Car(DieselEngine engine) {
        this.engine = engine;
    }
}
