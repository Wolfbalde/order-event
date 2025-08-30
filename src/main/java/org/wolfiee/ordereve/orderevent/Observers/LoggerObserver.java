package org.wolfiee.ordereve.orderevent.Observers;

import org.wolfiee.ordereve.orderevent.Domain.Order;

public class LoggerObserver implements OrderObserver{

    @Override
    public void onOrderUpdated(Order order) {
        System.out.println("[Logger] Order " + order.getOrderId() + " updated to " + order.getStatus());
    }
}
