package org.katas.refactoring;

public class LineItem {
    private static final double TAX_RATE = 0.1;
    private String description;
    private double price;
    private int quantity;

    public LineItem(String description, double price, int quantity) {
        super();
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    double totalAmount() {
        return price * quantity;
    }

    public double getItemSalesTax() {
        return totalAmount() * TAX_RATE;
    }

    public double getLineItemTotalAmount(double totalAmount) {
        return totalAmount + totalAmount() + getItemSalesTax();
    }
}