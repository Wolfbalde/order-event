package org.wolfiee.ordereve.orderevent.Event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Instant;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "eventType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderCreatedEvent.class, name = "OrderCreatedEvent"),
        @JsonSubTypes.Type(value = PaymentReceivedEvent.class, name = "PaymentReceivedEvent"),
        @JsonSubTypes.Type(value = ShippingScheduledEvent.class, name = "ShippingScheduledEvent"),
        @JsonSubTypes.Type(value = OrderCancelledEvent.class, name = "OrderCancelledEvent")
})
public abstract class Event {
    private String eventId;
    private Instant timestamp;
    private String eventType;

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
}
