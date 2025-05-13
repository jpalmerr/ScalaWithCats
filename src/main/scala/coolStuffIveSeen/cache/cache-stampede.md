### Thread‐Safe Storage ≠ Stampede Protection

Using a ConcurrentHashMap (or Caffeine’s default backing) ensures that individual reads/writes are safe, but does not stop multiple threads from concurrently deciding “miss → load” on the same key.

**Cache Stampede** (a.k.a. Cache Thundering Herd)

- Occurs when many threads all observe a cache miss for the same key and simultaneously initiate expensive downstream loads.

R- esults in redundant work, wasted resources, potential system overload, and unpredictable latency spikes.

**Caffeine’s Loader APIs**

Synchronous:

```java
V value = cache.get(key, k -> loadValue(k));
```
- Guarantees at most one load-per-key at a time; other threads block on that load.

Asynchronous:

```java
Copy
Edit
Cache<Key, CompletableFuture<Value>> asyncCache =
Caffeine.newBuilder()
.buildAsync((k, exec) -> loadValueAsync(k));
```

- Deduplicates loads and returns a CompletableFuture that all callers share.

**Error‐Caching vs. Exception‐Logging Trade-Off**

By default, if the loader throws, Caffeine logs a stack trace and does not cache an entry—so future calls will retry (and re-log).

If you instead return an “error object” (no exception), it will cache that object, suppress logs, but then you must handle “cached-error” downstream.

**Custom “Put-On-Success” Pattern**

Use `buildAsync(...)` with your own wrapper that:

- Calls the delegate under a failsafe/circuit-breaker.

- On success: cache.put(key, CompletableFuture.completedFuture(value)).

- On failure: decide whether to propagate, wrap, or suppress.

- Avoids both log spam and permanent caching of transient failures, at the cost of extra glue code.

### Cache Stampede

Definition:
- A cache stampede (also called thundering herd) is a surge of concurrent cache misses on the same key, causing multiple redundant load operations against the backing data store or service.

**How It Arises**

- Cold Cache or Expiration:
A popular item expires or the cache is initially empty.

- Burst of Traffic:
Many requests for the same item arrive in a short window.

- Miss‐Load‐Put Race:
Without coordination, each request sees “no entry” and initiates its own load.

**Impacts**
- Resource Wastage: Multiple identical backend calls.

- Latency Spikes: Slowest load dictates response time for all.

- Backend Overload: Potential cascading failures under load.

**Common Mitigation Strategies**

| Technique                                  | Pros                                                                 | Cons                                                           |
|--------------------------------------------|----------------------------------------------------------------------|----------------------------------------------------------------|
| Singleflight / Request Coalescing          | Exactly one in-flight load per key; others wait.                     | Calls block (sync) or share a future (async).                 |
| Early Revalidation (Leases)                | Short “stale” window; one loader refreshes while others use stale.   | More complex logic; serving stale data briefly.               |
| Probabilistic Early Expiration             | Randomize TTL expiration to spread out loads.                        | Doesn’t prevent a synchronized burst in all cases.            |
| Write-Through or Write-Behind              | Updates cache on every write, avoiding miss for new entries.         | Higher write overhead.                                        |
| Backoff & Jitter on Retries                | On loader failures, adding randomized delays before retry.           | Adds latency; only helpful under repeated retry storms.       |
| Circuit Breakers / Bulkheads               | Prevent hammering backend on repeated errors.                        | Doesn’t eliminate redundant loads on a cold miss.             |
| Dedicated Loader API (e.g., Caffeine)      | Built-in deduplication via `get(key, loader)` or `buildAsync`.       | Must understand exception-vs-return behavior & logging.       |


**Recommended Approach**

- Use a cache library’s built-in loader/coalescing API (e.g. Caffeine’s get(key, loader) or buildAsync(loader)), so you automatically avoid stampedes.

- Layer in error-handling logic (failsafe, circuit breaker) inside the loader function—this keeps cache semantics clean.

- Choose whether to cache failures or allow retries based on your SLA for fresh vs. stale vs. error data, and manage logs accordingly.

- Add observability (metrics for cache misses, load durations, failures) to detect and fine-tune stampede behavior in production.

