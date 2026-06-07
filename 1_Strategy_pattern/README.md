
# Strategy Pattern — Notes and Examples

## Why this pattern

- Encapsulates interchangeable algorithms behind a common interface so the client can switch behavior at runtime.
- Reduces conditional branching in clients by delegating algorithm choice to separate strategy classes.
- Keeps algorithm implementations isolated, making them easier to test and extend.

## When to use

- When multiple algorithms or policies can be applied to the same task and selection must be flexible.
- When you want to vary behavior independently from the class that uses it.
- When you need to make algorithms pluggable, configurable, or replaceable without changing client code.

## Basic structure (very basic)

- Strategy: an interface declaring an operation (e.g., `execute(...)`).
- ConcreteStrategy: a class implementing `Strategy` with a particular algorithm.
- Context: holds a `Strategy` reference and delegates work to it; provides a setter to change the strategy at runtime.

Minimal interaction:

1. Client creates a `Context` with a chosen `Strategy`.
2. Client calls context operation; context delegates to the active strategy.
3. Client can swap the strategy when different behavior is needed.

## Java-specific notes (short)

- Java 8 added `default` and `static` methods in interfaces; these provide optional implementations and utilities but do not change the core pattern.
- On Java 8 compile/run: use `javac` then `java`. Single-file `java File.java` is Java 11+ only.

## Real-life examples (quoted)

"Payment processing: CreditCardStrategy, PayPalStrategy, ApplePayStrategy"
"Sorting: QuickSortStrategy, MergeSortStrategy, InsertionSortStrategy"
"Compression: ZipCompressionStrategy, GzipCompressionStrategy, BrotliCompressionStrategy"
"Authentication: OAuthStrategy, SAMLStrategy, JwtStrategy"
"Pricing: FixedPriceStrategy, DiscountStrategy, SurgePricingStrategy"

---
Rephrased to match the concise pattern used in the observer README. Let me know if you want the short Java demo classes added.
---
Updated to clarify Java compatibility and interface method behavior.
