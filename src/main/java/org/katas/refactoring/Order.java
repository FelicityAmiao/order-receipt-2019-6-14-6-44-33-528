package org.katas.refactoring;

import java.util.List;

public class Order {
    String customerName;
    String customerAddress;
    List<LineItem> lineItems;

    public Order(String customerName, String customerAddress, List<LineItem> lineItems) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.lineItems = lineItems;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public double calculateTotalAmount() {
        double totalAmount = 0d;
        for (LineItem lineItem : lineItems) {
            totalAmount = lineItem.getLineItemTotalAmount(totalAmount);
        }
        return totalAmount;
    }

    public double calculateTotalSalesTax() {
        double totalSalesTax = 0d;
        for (LineItem lineItem : lineItems) {
            totalSalesTax += lineItem.getItemSalesTax();
        }
        return totalSalesTax;
    }

    public void appendLineItems(StringBuilder output) {
        for (LineItem lineItem : lineItems) {
            lineItem.appendLineItem(output, lineItem);
        }
    }
}
