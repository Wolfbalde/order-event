package org.wolfiee.ordereve.orderevent.Observers;

import org.wolfiee.ordereve.orderevent.Domain.Order;

public interface OrderObserver {
    void onOrderUpdated(Order order);
}
