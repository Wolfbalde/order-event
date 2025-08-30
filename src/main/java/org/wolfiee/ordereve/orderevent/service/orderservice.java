package org.wolfiee.ordereve.orderevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wolfiee.ordereve.orderevent.Domain.Order;
import org.wolfiee.ordereve.orderevent.Event.*;
import org.wolfiee.ordereve.orderevent.Observers.AlertObserver;
import org.wolfiee.ordereve.orderevent.Observers.LoggerObserver;
import org.wolfiee.ordereve.orderevent.Observers.OrderObserver;
import org.wolfiee.ordereve.orderevent.repo.orderrepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class orderservice {

    @Autowired
    private orderrepo orderRepository;

    private final List<OrderObserver> observers = new ArrayList<>();

    public orderservice() {
        observers.add(new LoggerObserver());
        observers.add(new AlertObserver());
    }

    private void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.onOrderUpdated(order);
        }
    }

    public String processEvent(Event event) {
        Order updatedOrder = null;

        if (event instanceof OrderCreatedEvent oce) {
            Order order = new Order();
            order.setOrderId(oce.getOrderId());
            order.setCustomerId(oce.getCustomerId());
            order.setItems(oce.getItems());

            double total = oce.getItems().stream()
                    .mapToDouble(i -> i.getQty() * i.getPrice())
                    .sum();
            order.setTotalAmount(total);
            order.setStatus(Order.Status.PENDING);

            order.getEventHistory().add("Order created with PENDING status");
            updatedOrder = orderRepository.save(order);
            notifyObservers(updatedOrder);

        } else if (event instanceof PaymentReceivedEvent pre) {
            updatedOrder = orderRepository.findById(pre.getOrderId()).map(order -> {
                double total = order.getTotalAmount();
                double paid = pre.getAmountPaid();

                if (paid == total) {
                    order.setStatus(Order.Status.PAID);
                } else if (paid > total) {
                    order.setStatus(Order.Status.OVERPAID);
                }
                else if (paid > 0) {
                    order.setStatus(Order.Status.PARTIALLY_PAID);
                } else {
                    order.setStatus(Order.Status.PENDING);
                }

                order.getEventHistory().add("Payment received: " + paid + ", status updated to " + order.getStatus());
                notifyObservers(order);
                return orderRepository.save(order);
            }).orElse(null);

        } else if (event instanceof ShippingScheduledEvent sse) {
            updatedOrder = orderRepository.findById(sse.getOrderId()).map(order -> {
                order.setStatus(Order.Status.SHIPPED);
                order.getEventHistory().add("Shipping scheduled, status updated to SHIPPED");
                notifyObservers(order);
                return orderRepository.save(order);
            }).orElse(null);

        } else if (event instanceof OrderCancelledEvent ocae) {
            updatedOrder = orderRepository.findById(ocae.getOrderId()).map(order -> {
                order.setStatus(Order.Status.CANCELLED);
                order.getEventHistory().add("Order cancelled, status updated to CANCELLED Reason:" + ocae.getReason() );
                String message = orderRepository.save(order) + "Reason: "+ocae.getReason();
                notifyObservers(order);
                return orderRepository.save(order);
            }).orElse(null);
        }

        if (updatedOrder != null) {
            if (event instanceof OrderCancelledEvent ocae) {
                return "Order " + updatedOrder.getOrderId() +
                        " updated to " + updatedOrder.getStatus() +
                        ". Reason: " + ocae.getReason();
            } else {
                return "Order " + updatedOrder.getOrderId() +
                        " updated to " + updatedOrder.getStatus();
            }
        } else {
            return "Order not found or event type unsupported";
        }
    }

    public List<String> processEvents(List<Event> events) {
        List<String> results = new ArrayList<>();
        for (Event event : events) {
            results.add(processEvent(event));
        }
        return results;
    }

    public List<Order> getall() {
        return orderRepository.findAll();
    }
}
