package LldProblems;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Actors:
 * Product(name, price, quantity)
 * VendingMachine(DespenseProduct, Restocking the product, List<Products>, AcceptDenomination,  ReturnDenomination, List<Denomination>,  collect money)
 * implement buy fuction(name, quanitity, List<Denomination>)
 */
public class VendingDemo {
public static void main(String[] args) {
    Product product1 = new Product("ABC", 10, 10);
    Product product2 = new Product("ABCD", 15, 20);
    Denomination five = new FiveCoin(100);
    Denomination ten = new TenNote(100);
    List<Product> products = new ArrayList<>();
    products.add(product1);
    products.add(product2);
    List<Denomination> denominations = new ArrayList<>();
    denominations.add(five);
    denominations.add(ten);
    VendingMachine vendingMachine = new VendingMachine(products, denominations);
    Denomination buyDenomination = new TenNote(10);
    List<Denomination> buyCash = new ArrayList<>();
    buyCash.add(buyDenomination);
    vendingMachine.buyProduct("ABC", 9, buyCash);
    vendingMachine.buyProduct("ABCD", 50, buyCash);
    System.out.println(vendingMachine.revenue);
}
}

class VendingMachine {
    Map<String, Product> products;
    Map<DenominationType, Denomination> denominations;
    double revenue;

    public VendingMachine(List<Product> products, List<Denomination> denominations) {
        this.products = new ConcurrentHashMap<>();
        this.denominations = new ConcurrentHashMap<>();
        revenue = 0;
        for (Denomination denomination : denominations) {
            this.denominations.put(denomination.type, denomination);
        }
        for (Product product : products) {
            this.products.put(product.name, product);
        }
    }

    public void restocking(Product product) {
        if (products.containsKey(product.name)) {
            Product oldProduct = products.get(product.name);
            oldProduct.quantity += product.quantity;
            oldProduct.price = product.price;
            products.put(product.name, oldProduct);
        } else {
            products.put(product.name, product);
        }
    }

    public boolean buyProduct(String name, int quanitity, List<Denomination> denominations) {
        if (!products.containsKey(name))
            return false;
        Product product = products.get(name);
        if (product.quantity < quanitity)
            return false;
        final double requiredValue = product.price * quanitity;
        double total = 0;
        for (Denomination denomination : denominations) {
            total += denomination.value * denomination.unit;
        }
        if (total < requiredValue)
            return false;
        boolean canOfferChange = canDespenseCash(total - requiredValue, true);
        if (!canOfferChange)
            return false;
        despenseProduct(name, quanitity, total - requiredValue);
        revenue += requiredValue;
        return true;
    }

    private void despenseProduct(String name, int quanitity, double change) {
        Product oldProduct = products.get(name);
        oldProduct.quantity -= quanitity;
        products.put(name, oldProduct);
        canDespenseCash(change, false);
    }

    private boolean canDespenseCash(double value, boolean check) {
        for (DenominationType denominationType : denominations.keySet()) {
            if (value == 0)
                return true;
            int change = (int) (Math.round(value) / denominations.get(denominationType).value);
            value -= (denominations.get(denominationType).value * change);
            if (!check) {
                Denomination olDenomination = denominations.get(denominationType);
                olDenomination.unit -= change;
                denominations.put(denominationType, olDenomination);
            }
        }
        return value == 0;
    }

}

class Product {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

abstract class Denomination {
    DenominationType type;
    int value;
    int unit;

    protected Denomination(DenominationType type, int value, int unit) {
        this.type = type;
        this.value = value;
        this.unit = unit;
    }

    protected void addDenomination(int unit) {
        this.unit += unit;
    }

    protected boolean removeDenomination(int unit) {
        if (this.unit < unit)
            return false;
        this.unit -= unit;
        return true;
    }
}

class FiveCoin extends Denomination {

    protected FiveCoin(int unit) {
        super(DenominationType.FIVECOIN, 5, unit);
    }

}

class TenNote extends Denomination {
    protected TenNote(int unit) {
        super(DenominationType.TENNOTE, 10, unit);
    }
}

enum DenominationType {
    FIVECOIN, TENNOTE, TWENTYNOTE, FIFTYNOTE, HUNDREDNOTE, FIVEHUNDREDNOTE
}