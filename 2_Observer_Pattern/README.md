# Observer Pattern — Notes and Examples

## Why this pattern

- Separates the object that produces state changes (the Subject) from the objects that react to those changes (Observers).
- Promotes loose coupling: subjects don't need to know concrete observer implementations, only the observer interface.
- Makes it easy to add or remove observers at runtime without changing the subject's code.

## When to use

- When many objects need to be informed about state changes in another object.
- When you want to avoid hard-wired dependencies or large conditional logic connecting producers and consumers.
- When you need runtime subscription/unsubscription semantics (plug-in listeners, UI events, notification systems).

## Basic structure (very basic)

- Subject (Observable): maintains a list of observers and exposes `register`, `unregister`, and `notify` operations.
- Observer (Listener): an interface with a callback method (e.g., `onEvent(Event e)` or `update(Subject s, Event e)`).
- ConcreteObserver: implements the Observer and handles events (e.g., sends email, SMS, updates UI).
- Event/DTO (optional): carries details about the change (type, payload, timestamp).

Minimal interaction:

1. Observers register with a Subject.
2. Subject changes state (e.g., stock increased, restore finished).
3. Subject creates an Event and calls `notifyObservers(event)`.
4. Each Observer receives the event and handles it.

## Real-world example names (lots of variations)

- Subjects / Notifiers / Items:
  - InventoryItem, Product, StockItem, PhoneItem, Device, Order, Package
  - RestockNotifier, BackInStockNotifier, RestoreNotifier, RepairNotifier
  - InventoryService, StockService, ProductCatalog

- Observer interfaces / listeners:
  - InventoryObserver, StockListener, ProductObserver, Subscriber, CustomerObserver
  - NotificationListener, EventListener (generic), RestockListener

- Concrete observers (channels / personas / handlers):
  - EmailSubscriber, SmsSubscriber, PushSubscriber
  - WebhookSubscriber, SlackSubscriber, TelegramSubscriber
  - VipCustomerObserver, RegularCustomerObserver, WarehouseTeamObserver
  - SalesTeamObserver, MarketingObserver, FulfillmentObserver
  - IphoneRestoreObserver, IphoneRestockObserver, PhoneRepairObserver
  - BackOrderObserver, LowStockAlertObserver, ThresholdAlertObserver

- Event / payload types:
  - StockEvent, RestockEvent, RestoreEvent, RepairEvent, LowStockEvent
  - NotificationPayload, InventoryUpdate, ProductStatusEvent

- Helpers / policies / managers:
  - NotificationService, SubscriberRepository, NotificationPolicy
  - ThrottlePolicy, PriorityPolicy, RetryPolicy

## Quick mapping (example flow)

- `InventoryService.restock(productId, qty)` → updates `InventoryItem` → creates `RestockEvent` → `InventoryItem.notifyObservers(RestockEvent)` → `EmailSubscriber.onEvent()` / `SmsSubscriber.onEvent()` → `NotificationService.sendEmail(...)` / `sendSms(...)`.

## Tips and variants

- Use `Event` objects to keep the observer API stable and carry rich data.
- For high-volume systems consider asynchronous delivery (queues, worker threads) and back-pressure.
- Use filtering (observer registers interest in specific event types or SKUs) to avoid unnecessary notifications.

---
Created to accompany examples in this folder. If you want, I can scaffold a small runnable demo (subject + 2 observers) in Java next.
