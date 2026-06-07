# Decorator Pattern — Notes and Examples

## Why this pattern

- Lets you add responsibilities to objects dynamically without modifying their code.
- Promotes flexible composition of behavior at runtime using wrappers rather than inheritance.
- Helps adhere to Single Responsibility and Open/Closed principles by separating concerns into decorators.

## When to use

- When you need to add features to individual objects at runtime, not to an entire class.
- When subclassing would lead to an explosion of classes for every feature combination.
- When you want to layer behaviors (logging, caching, compression) transparently around an object.

## Basic structure (very basic)

- Component: the interface or abstract class defining operations (e.g., `render()`, `read()`).
- ConcreteComponent: the core object to which responsibilities can be added.
- Decorator: an abstract class implementing `Component` and holding a `Component` reference.
- ConcreteDecorator: extends `Decorator` and adds behavior before/after delegating to the wrapped component.

Minimal interaction:

1. Client holds a reference to a `Component`.
2. To add behavior, client wraps the `Component` with one or more `ConcreteDecorator` instances.
3. Calls to the component are delegated through decorators which add behavior and forward the call.

## Java-specific notes

- Prefer composition (wrapping) over inheritance for flexible runtime behavior.
- Java I/O (`InputStream`, `FilterInputStream`, `BufferedInputStream`) is a canonical example.

## Real-life examples (quoted)

"Java I/O: BufferedInputStream, DataInputStream, GZIPInputStream"
"GUI: ScrollBarDecorator, BorderDecorator, ShadowDecorator"
"Logging: TimestampLoggerDecorator, EncryptionLoggerDecorator"
"Networking: CompressionDecorator, EncryptionDecorator, RetryDecorator"
"Caching: CachingDecorator, MemoizationDecorator"

## Tips and variants

- Keep decorator interfaces compatible with the component so wrapping is transparent.
- Avoid deep decorator chains for performance-sensitive code; consider combining behaviors when needed.
- Consider using the pattern with functional wrappers or higher-order functions where the language allows.

---
Created as a concise reference README for the Decorator Pattern. Want a small Java demo scaffold next?
