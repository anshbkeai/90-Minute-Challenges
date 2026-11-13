# 90-Minute Challenge: Payflow — a mini payment gateway

## Scenario
You are building the core of a payment gateway. A merchant creates a payment, Payflow asks a downstream acquirer to authorize it, the merchant can capture an authorized payment, and the gateway can issue a refund. The gateway must remain correct when clients retry requests or two requests arrive concurrently.

Build a small, understandable vertical slice. You are not expected to implement a production-ready gateway in 90 minutes.

## Functional requirements
### Required
1. Create a payment for a positive amount and a known merchant.
2. Accept an idempotency key on payment creation. Repeating the same request with the same key must not create a second payment.
3. Store payment state durably (H2 is sufficient for this exercise).
4. Provide an endpoint to retrieve a payment by ID.
5. Keep state transitions explicit. Invalid transitions must return a clear client error.
6. Add tests for at least one happy path and one failure path.

### Choose one stretch task
- Capture an authorized payment.
- Add a fake acquirer that can approve or decline an authorization.
- Add a refund flow with a maximum refundable amount.
- Add a transactional outbox record when a payment state changes.

## Non-functional requirements
- Validate inputs at the API boundary.
- Use `BigDecimal` for monetary values; do not use `double`.
- Define transaction boundaries intentionally.
- Avoid leaking stack traces or internal exception details to API clients.
- Explain what happens if the client times out after the server commits.
- Explain how your design would change with multiple application instances and PostgreSQL/MySQL.
- Explain how webhook delivery could be retried safely.

## API contract (minimum)
`POST /api/payments`
```json
{
  "merchantId": "acme",
  "amount": 125.50,
  "currency": "INR"
}
```
Header: `Idempotency-Key: order-123-payment-1`

Success: `201 Created` with a response containing `id`, `merchantId`, `amount`, `currency`, and `status`.

`GET /api/payments/{id}`
- `200 OK` if found
- `404 Not Found` if missing

Suggested status values: `PENDING`, `AUTHORIZED`, `DECLINED`, `CAPTURED`, `REFUNDED`. Do not implement every status unless you have time.

## 90-minute timebox
- 0–15 min: requirements, state diagram, data model, API contracts, failure cases.
- 15–25 min: inspect starter code and decide boundaries.
- 25–55 min: implement the smallest correct vertical slice.
- 55–70 min: tests and edge cases.
- 70–80 min: one stretch feature if core behaviour is correct.
- 80–90 min: review, explain trade-offs, identify production gaps.

## Review questions (answer after the timer)
1. What invariant prevents duplicate payments for one idempotency key?
2. Why is an in-memory lock insufficient when the service has multiple instances?
3. Which operations need a database transaction?
4. What happens if a client retries after a timeout?
5. How would you implement safe retries for a downstream acquirer?
6. Why should webhook delivery normally be asynchronous?
7. What should be monitored and logged without recording sensitive payment data?
8. What would you change before deploying this to production?
