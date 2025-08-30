package org.wolfiee.ordereve.orderevent.Event;

import org.wolfiee.ordereve.orderevent.Domain.OrderItem;
import java.util.List;

public class OrderCreatedEvent extends Event {
    private String orderId;
    private String customerId;
    private List<OrderItem> items;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}
