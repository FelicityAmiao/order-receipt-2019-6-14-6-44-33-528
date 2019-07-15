package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order o;

    public OrderReceipt(Order o) {
        this.o = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        appendPrintHeaders(output);
        appendCustomerMessage(output);
        double totalSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : o.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');

            double salesTax = getItemSalesTax(lineItem);
            totalSalesTax += salesTax;
            totalAmount = getLineItemTotalAmount(totalAmount, lineItem, salesTax);
        }
        appendSalesTax(output, totalSalesTax);
        appendTotalAmount(output, totalAmount);
        return output.toString();
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
        return lineItem.totalAmount() * .10;
    }

    private void appendCustomerMessage(StringBuilder output) {
        output.append(o.getCustomerName());
        output.append(o.getCustomerAddress());
    }

    private StringBuilder appendPrintHeaders(StringBuilder output) {
        return output.append("======Printing Orders======\n");
    }
}