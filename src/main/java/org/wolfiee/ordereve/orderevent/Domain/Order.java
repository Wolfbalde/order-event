package org.wolfiee.ordereve.orderevent.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    private String orderId;

    private String customerId;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items;

    public enum Status {
        PENDING,
        PAID,
        PARTIALLY_PAID,
        SHIPPED,
        CANCELLED,
        OVERPAID
    }

    @ElementCollection
    private List<String> eventHistory = new ArrayList<>();

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }


    public List<String> getEventHistory() {
        return eventHistory;
    }

    public void setEventHistory(List<String> eventHistory) {
        this.eventHistory = eventHistory;
    }
}
