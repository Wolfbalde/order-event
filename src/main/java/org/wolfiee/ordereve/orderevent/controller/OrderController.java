package org.wolfiee.ordereve.orderevent.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wolfiee.ordereve.orderevent.Domain.Order;
import org.wolfiee.ordereve.orderevent.Event.Event;
import org.wolfiee.ordereve.orderevent.service.orderservice;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private orderservice orderService;

    @PostMapping("/events")
    public ResponseEntity<List<String>> handleMultiple(@RequestBody List<Event> events) {
        return ResponseEntity.ok(orderService.processEvents(events));
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getall();
    }

}
