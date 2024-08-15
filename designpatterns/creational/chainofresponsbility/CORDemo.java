package designpatterns.creational.chainofresponsbility;

public class CORDemo {
    public static void main(String[] args) {
        Director director = new Director();
        SeniorManager sm = new SeniorManager();
        sm.setApprover(director);
        Manager manager = new Manager();
        manager.setApprover(sm);

        manager.processRequest(new Purchage(1550));

        System.out.println("--------------------------------------");

        manager.processRequest(new Purchage(980));

        System.out.println("---------------------------------------");

        manager.processRequest(new Purchage(20000));

        System.out.println("---------------------------------------");

        sm.processRequest(new Purchage(2000));
    }

}

class Purchage {
    private int amount;

    public Purchage(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}

abstract class Approver {
    protected Approver nextApprover;

    public void setApprover(Approver approver) {
        this.nextApprover = approver;
    }

    public abstract void processRequest(Purchage request);
}

class Manager extends Approver {

    @Override
    public void processRequest(Purchage request) {
        if (request.getAmount() <= 100)
            System.out.println("manager approved the request");
        else if (nextApprover != null) {
            System.out.println("Manager cannot approve passing request to next");
            nextApprover.processRequest(request);
        } else {
            System.out.println("Request Cannot be approved");
        }
    }

}

class SeniorManager extends Approver {

    @Override
    public void processRequest(Purchage request) {
        if (request.getAmount() <= 1000)
            System.out.println("Senior manager approved the request");
        else if (nextApprover != null) {
            System.out.println("Senior Manager cannot approve passing request to next");
            nextApprover.processRequest(request);
        } else {
            System.out.println("Request cannot be approved");
        }
    }

}

class Director extends Approver {

    @Override
    public void processRequest(Purchage request) {
        if (request.getAmount() <= 10000)
            System.out.println("Director approved the request");
        else if (nextApprover != null) {
            System.out.println("Director cannot approve passing request to next");
            nextApprover.processRequest(request);
        } else {
            System.out.println("Request cannot be approved");
        }
    }

}
