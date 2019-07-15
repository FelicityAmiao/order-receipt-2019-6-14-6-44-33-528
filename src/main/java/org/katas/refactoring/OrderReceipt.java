package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order o) {
        this.order = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        appendPrintHeaders(output);
        appendCustomerMessage(output);
        order.appendLineItems(output);
        appendSalesTax(output, order.calculateTotalSalesTax());
        appendTotalAmount(output, order.calculateTotalAmount());
        return output.toString();
    }


    private StringBuilder appendTotalAmount(StringBuilder output, double totalAmount) {
        return output.append("Total Amount").append('\t').append(totalAmount);
    }

    private StringBuilder appendSalesTax(StringBuilder output, double totalSalesTax) {
        return output.append("Sales Tax").append('\t').append(totalSalesTax);
    }

    private void appendCustomerMessage(StringBuilder output) {
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
    }

    private StringBuilder appendPrintHeaders(StringBuilder output) {
        return output.append("======Printing Orders======\n");
    }
}