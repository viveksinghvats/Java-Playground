package designpatterns.creational.singleton;

// *Objective: Object can only be instantiate once

// Eagerly Initialized
public class SingleTon {
    final static SingleTon instance = new SingleTon();

    private SingleTon() {

    }

    public static SingleTon getInstance() {
        return instance;
    }

}

// * static => loaded when class in loaded in the memory

class StaticSingleTon {
    private static StaticSingleTon instance;

    private StaticSingleTon() {

    }

    static {
        instance = new StaticSingleTon();
    }

    public static StaticSingleTon getInstance() {
        return instance;
    }
}

/*
 * Lazy => Object will be initiliazed when getInstance will be called
 * If one thread already has called the instance the, then other thread should
 * also get the same instance.
 */
class LazySingleTon {
    private static LazySingleTon instance;

    private LazySingleTon() {

    }

    public static LazySingleTon getInstance() {
        if (instance == null) {
            instance = new LazySingleTon();
        }
        return instance;
    }
}

/*
 * Thread Safe implementation => Only one thread can call the getInstance method
 * at a time
 */

class SynchronizedSingleTon {
    private static SynchronizedSingleTon instance;

    private SynchronizedSingleTon() {
    }

    public static synchronized SynchronizedSingleTon getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleTon();
        }
        return instance;
    }
}

/*
 * Optimized => because synchronized is a very heavy operation
 * Thread Safe implementation => Only one thread can call the getInstance method
 * at a time
 */

class OptimizedSynchronizedSingleTon {
    private static OptimizedSynchronizedSingleTon instance;

    private OptimizedSynchronizedSingleTon() {
    }

    public static OptimizedSynchronizedSingleTon getInstance() {
        if (instance == null) {
            synchronized (OptimizedSynchronizedSingleTon.class) {
                instance = new OptimizedSynchronizedSingleTon();
            }
        }
        return instance;
    }
}

class BillPughSingleTon {

    private BillPughSingleTon(){

    }

    private static class SingleTonHelper {
        private static final BillPughSingleTon bill_SingleTon = new BillPughSingleTon();
    }

    public static BillPughSingleTon getInstance(){
        return SingleTonHelper.bill_SingleTon;
    }
}
