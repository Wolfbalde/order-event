package org.wolfiee.ordereve.orderevent.Observers;

import org.wolfiee.ordereve.orderevent.Domain.Order;

public class AlertObserver implements OrderObserver {
    @Override
    public void onOrderUpdated(Order order) {
        if (order.getStatus() == Order.Status.CANCELLED) {
            System.out.println("[ALERT] Sending alert: Order " + order.getOrderId() + " was cancelled.");
        }
    }
}