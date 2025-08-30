package org.wolfiee.ordereve.orderevent.Event;

public class OrderCancelledEvent extends Event {
    private String orderId;
    private String reason;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
