package DesignPrincipal.Solid.DependencyInversion.with;

public class Car {
    // Car is lossely copuled with Engine, so changing anything in DielseEngine class will not affet the Car class.
    IEngine engine;
    public Car(IEngine engine){
        this.engine = engine;
    }
}
