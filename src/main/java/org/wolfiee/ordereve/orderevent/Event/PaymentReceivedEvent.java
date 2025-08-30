package org.wolfiee.ordereve.orderevent.Event;

public class PaymentReceivedEvent extends Event {
    private String orderId;
    private double amountPaid;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }
}
