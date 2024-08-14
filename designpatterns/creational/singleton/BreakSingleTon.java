package designpatterns.creational.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BreakSingleTon {
    public static void main(String[] args) {
        SingleTon instance1 = SingleTon.getInstance();
        SingleTon instance2 = SingleTon.getInstance();

        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());

        instance2 = null;
        for (@SuppressWarnings("rawtypes")
        Constructor cstr : SingleTon.class.getDeclaredConstructors()) {
            cstr.setAccessible(true);
            try {
                instance2 = (SingleTon) cstr.newInstance();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                System.out.println(e);
            }
        }

        System.out.println("After using reflect");

        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());



        System.out.println("Bill Pugh implementation");

        BillPughSingleTon instance3 = BillPughSingleTon.getInstance();
        BillPughSingleTon instance4 = BillPughSingleTon.getInstance();

        System.out.println(instance3.hashCode());
        System.out.println(instance4.hashCode());

        instance4 = null;
        for (@SuppressWarnings("rawtypes")
        Constructor cstr : BillPughSingleTon.class.getDeclaredConstructors()) {
            cstr.setAccessible(true);
            try {
                instance4 = (BillPughSingleTon) cstr.newInstance();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                System.out.println(e);
            }
        }

        System.out.println("After using reflect");

        System.out.println(instance3.hashCode());
        System.out.println(instance4.hashCode());

    }
}
