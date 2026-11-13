# Design notes — fill this in before coding

Keep this document short. Bullet points and diagrams are enough.

## 1. Requirements
- Functional requirements:
- Explicit non-goals:
- Assumptions:

## 2. State model
Write allowed transitions. For example:
`PENDING -> AUTHORIZED | DECLINED`
`AUTHORIZED -> CAPTURED`
`CAPTURED -> REFUNDED` (only if your implementation supports it)

Which transitions are forbidden?

## 3. Data model
For each table/entity, list fields, types, constraints, and indexes.

- Payment:
- Idempotency record / key:
- Optional outbox:

What database constraint protects the most important invariant?

## 4. API contract
For each endpoint: method, path, request, response, and error cases.

## 5. Transaction boundaries
Which method is transactional? What must be committed atomically? What must not happen inside a long-running database transaction?

## 6. Failure scenarios
Describe behaviour for:
- same request submitted twice
- same idempotency key with different request data
- client timeout after commit
- downstream acquirer timeout
- two concurrent capture/refund requests
- process crash after state change but before webhook delivery

## 7. System evolution
How would the design change with:
- multiple application instances
- PostgreSQL/MySQL
- asynchronous dispatch / message broker
- metrics, logs, tracing, secrets, and rate limiting

## 8. Trade-offs
What did you deliberately leave out in the 90-minute version, and why?
