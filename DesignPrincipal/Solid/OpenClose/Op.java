package DesignPrincipal.Solid.OpenClose;

public class Op {
    public static void main(String[] args) {
        FosterAnimal fosterAnimal = new FosterAnimal();
        fosterAnimal.fosterCat();
        fosterAnimal.fosterDog();

        System.out.println("------Open Close-------");
        
        FosterAnimalOp f = new FosterAnimalOp();
        AnimalOp cat = new CatOp("Cat");
        AnimalOp dog = new DogOp("Dog");
        f.fosterAnimal(cat);
        f.fosterAnimal(dog);


    }
}
