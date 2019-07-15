package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order o;
    private static final double TAX_RATE = 0.1;

    public OrderReceipt(Order o) {
        this.o = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        appendPrintHeaders(output);
        appendCustomerMessage(output);
        appendLineItems(output);
        appendSalesTax(output, calculateTotalSalesTax());
        appendTotalAmount(output, calculateTotalAmount());
        return output.toString();
    }

    private double calculateTotalAmount() {
        double totalAmount = 0d;
        for (LineItem lineItem : o.getLineItems()) {
            totalAmount = getLineItemTotalAmount(totalAmount, lineItem, getItemSalesTax(lineItem));
        }
        return totalAmount;
    }

    private double calculateTotalSalesTax() {
        double totalSalesTax = 0d;
        for (LineItem lineItem : o.getLineItems()) {
            totalSalesTax += getItemSalesTax(lineItem);
        }
        return totalSalesTax;
    }

    private void appendLineItems(StringBuilder output) {
        for (LineItem lineItem : o.getLineItems()) {
            appendLineItem(output, lineItem);
        }
    }

    private void appendLineItem(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.getDescription()).append('\t');
        output.append(lineItem.getPrice()).append('\t');
        output.append(lineItem.getQuantity()).append('\t');
        output.append(lineItem.totalAmount()).append('\n');
    }

    private StringBuilder appendTotalAmount(StringBuilder output, double totalAmount) {
        return output.append("Total Amount").append('\t').append(totalAmount);
    }

    private StringBuilder appendSalesTax(StringBuilder output, double totalSalesTax) {
        return output.append("Sales Tax").append('\t').append(totalSalesTax);
    }

    private double getLineItemTotalAmount(double totalAmount, LineItem lineItem, double salesTax) {
        totalAmount += lineItem.totalAmount() + salesTax;
        return totalAmount;
    }

    private double getItemSalesTax(LineItem lineItem) {
        return lineItem.totalAmount() * TAX_RATE;
    }

    private void appendCustomerMessage(StringBuilder output) {
        output.append(o.getCustomerName());
        output.append(o.getCustomerAddress());
    }

    private StringBuilder appendPrintHeaders(StringBuilder output) {
        return output.append("======Printing Orders======\n");
    }
}