package codeInterView.ood.tax;

import java.math.BigDecimal;

public class SimpleShoppingCart {

    public static void main(String[] args) {
        Book book1 = new Book("Java Basics", 2, BigDecimal.valueOf(39.95));

        // use log4j instead of println
        System.out.println(book1.getExtendedTax());
        System.out.println(book1.getExtendedTaxedPrice());

        CD cd1 = new CD("Java in 30 Days", 2, BigDecimal.valueOf(59.00));
        System.out.println(cd1.getExtendedTax());
        System.out.println(cd1.getExtendedTaxedPrice());

        Cosmetics cosmetics1 = new Cosmetics("Facials", 2, BigDecimal.valueOf(20.00));
        System.out.println(cosmetics1.getExtendedTax());
        System.out.println(cosmetics1.getExtendedTaxedPrice());

        CD cd2 = new CD("Hadoop in 30 Days", 2, BigDecimal.valueOf(10.00));
        cd2.setImported(true);
        System.out.println(cd2.getExtendedTax());
        System.out.println(cd2.getExtendedTaxedPrice());
    }

}

class TaxCalculatorImpl implements TaxCalculator {

    // Stay away from hard coding values. Read from a “.properties” file
    public static final BigDecimal SALES_TAX = BigDecimal.valueOf(0.10); // 10%
    public static final BigDecimal IMPORT_TAX = BigDecimal.valueOf(0.05); // 5%

    public BigDecimal getCalculatedTax(boolean isTaxable, boolean isImported, BigDecimal price) {

        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }

        BigDecimal salesTax = BigDecimal.ZERO;
        BigDecimal importTax = BigDecimal.ZERO;

        if (isTaxable) {
            salesTax = price.multiply(SALES_TAX);
        }
        if (isImported) {
            importTax = price.multiply(IMPORT_TAX);
        }
        return salesTax.add(importTax);
    }
}

interface TaxCalculator {
    abstract BigDecimal getCalculatedTax(boolean isTaxable, boolean isImported, BigDecimal price);
}

interface Item {
    // abstract by default
    double getExtendedTax();

    double getExtendedTaxedPrice();

    void setImported(boolean b);

    String getDescription();
}

// must be abstract when at least a single method is abstract
abstract class Goods implements Item {

    // define attributes
    private String description;
    private int qty;
    private BigDecimal price;

    private TaxCalculator tax = new TaxCalculatorImpl(); // composition
                                                         // can be set at
                                                         // runtime via a
                                                         // setter method
                                                         // different
                                                         // implementations
                                                         // can be assigned
                                                         // to the interface

    public Goods(String description, int qty, BigDecimal price) {
        this.description = description;
        this.qty = qty;
        this.price = price;
    }

    protected abstract boolean isTaxed(); // abstract method

    protected abstract boolean isImported(); // abstract method

    public double getExtendedTax() {
        BigDecimal calculatedTax = tax.getCalculatedTax(isTaxed(), isImported(), price);
        BigDecimal extTax = calculatedTax.multiply(BigDecimal.valueOf(qty));
        return extTax.doubleValue();
    }

    public double getExtendedTaxedPrice() {
        if (tax == null) {
            throw new IllegalArgumentException("Tax should be calculated first:");
        }

        BigDecimal calculatedTax = tax.getCalculatedTax(isTaxed(), isImported(), price);
        BigDecimal extPrice = BigDecimal.valueOf(qty)
            .multiply(calculatedTax.add(price));
        return extPrice.doubleValue();
    }

    // getters and setters go here for attributes like description etc
    public String getDescription() {
        return description;
    }

    public String toString() {
        return qty + " " + description + " : ";
    }
}

class Book extends Goods {

    // default values
    private boolean isTaxed = false;
    private boolean isImported = false;

    public Book(String description, int qty, BigDecimal price) {
        super(description, qty, price);
    }

    public boolean isTaxed() {
        return isTaxed;
    }

    public boolean isImported() {
        return isImported;
    }

    // default can be overridden
    public void setImported(boolean b) {
        throw new UnsupportedOperationException("Books Cannot be imported");
    }
}

class CD extends Goods {

    // default values
    private boolean isTaxed = true;
    private boolean isImported = false;

    public CD(String description, int qty, BigDecimal price) {
        super(description, qty, price);
    }

    public boolean isTaxed() {
        return isTaxed;
    }

    public boolean isImported() {
        return isImported;
    }

    // default can be overridden
    public void setImported(boolean b) {
        isImported = b;
    }
}

class Cosmetics extends Goods {

    // defaults
    private boolean isTaxed = true;
    private boolean isImported = false;

    public Cosmetics(String description, int qty, BigDecimal price) {
        super(description, qty, price);
    }

    public boolean isTaxed() {
        return isTaxed;
    }

    public boolean isImported() {
        return isImported;
    }

    // default can be overridden
    public void setImported(boolean b) {
        throw new UnsupportedOperationException("Cosmetics Cannot be imported");
    }

}
