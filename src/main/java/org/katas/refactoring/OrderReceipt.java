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
        double totSalesTx = 0d;
        double tot = 0d;
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
            totSalesTx += salesTax;
            tot = getLineItemTotalAmount(tot, lineItem, salesTax);
        }
        appendSalesTax(output, totSalesTx);
        appendTotalAmount(output, tot);
        return output.toString();
    }

    private StringBuilder appendTotalAmount(StringBuilder output, double tot) {
        return output.append("Total Amount").append('\t').append(tot);
    }

    private StringBuilder appendSalesTax(StringBuilder output, double totSalesTx) {
        return output.append("Sales Tax").append('\t').append(totSalesTx);
    }

    private double getLineItemTotalAmount(double tot, LineItem lineItem, double salesTax) {
        tot += lineItem.totalAmount() + salesTax;
        return tot;
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