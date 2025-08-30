# order-event
A Spring Boot service that processes **order lifecycle events** (Created, Payment Received, Shipped, Cancelled) and updates order status accordingly.  
It follows an **Event-driven + Observer pattern**, where changes are logged and alerts are triggered via observers.

---

##  Features
- Process events:
    - **OrderCreatedEvent** → Creates an order with `PENDING` status.
    - **PaymentReceivedEvent** → Updates order to `PAID`, `PARTIALLY_PAID`, `OVERPAID`, or remains `PENDING`.
    - **ShippingScheduledEvent** → Updates order to `SHIPPED`.
    - **OrderCancelledEvent** → Updates order to `CANCELLED` with a reason.
- Maintains **event history** per order.
- Notifies **observers** (`LoggerObserver`, `AlertObserver`) on each update.
- Provides API to **fetch all orders**.

---

## Tech Stack
- **Java 17+**
- **Spring Boot**
- **PostgreSQL**

---

## 🚀 API Endpoints

###
```
POST /api/orders/events

GET /api/orders

