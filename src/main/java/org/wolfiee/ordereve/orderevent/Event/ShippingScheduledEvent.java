package org.wolfiee.ordereve.orderevent.Event;

public class ShippingScheduledEvent extends Event {
    private String orderId;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
}
