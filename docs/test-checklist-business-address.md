# Test checklist: Business address (get current)

Inside-out unit and integration test ideas to pass the acceptance scenario: *current business address is displayed including all components (street, city, state/province, postal code, country)* and *no address → not found*.

---

## Domain (innermost)

- [x] 1. **Business address value object** – Create with all five fields (street, city, stateProvince, postalCode, country); creation succeeds and holds values.
- [ ] 2. **Business address value object** – Two addresses with same field values are equal (and same hashCode if used in collections).
- [ ] 3. **Business address value object** – Two addresses with different values are not equal.
- [ ] 4. **Customer / address holder** – Can have no business address (null/optional).
- [x] 5. **Customer / address holder** – Can have one business address; returning it yields the same value.
- [ ] 6. **Customer / address holder** – (Optional) Replacing the address overwrites the previous one.

---

## Application / use cases (ports)

- [x] 7. **Get current business address use case** – When outbound port returns an address, use case returns that address (success).
- [ ] 8. **Get current business address use case** – When outbound port returns no address, use case returns not found.
- [ ] 9. **Get current business address use case** – Use case calls outbound port with correct customer/identifier (verify via mock).

---

## Adapters

### REST controller (inbound)

- [x] 10. **Controller** – When use case returns an address, response status is 200 and body is JSON with street, city, stateProvince, postalCode, country.
- [ ] 11. **Controller** – When use case returns not found, response status is 404.
- [ ] 12. **Controller** – Controller calls use case with correct identity (e.g. current customer id; verify via mock).

### Persistence (outbound)

- [x] 13. **Repository** – Save a business address for a customer; load returns the same address (integration with DB or in-memory).
- [ ] 14. **Repository** – Load for a customer with no saved address returns empty/not found.
- [ ] 15. **Repository** – Save then overwrite address for same customer; load returns the new address (one canonical address per customer).

---

## Integration

- [ ] 16. **Slice/integration** – Given a customer with a stored business address (e.g. 100 Main St, Springfield, Illinois, 62701, United States), GET endpoint returns 200 and JSON with those five fields.
- [ ] 17. **Slice/integration** – Given a customer with no stored address, GET endpoint returns 404.

---

## Acceptance

- [x] 18. **Cucumber** – Scenario: Current business address is displayed with all components (Given address, When request, Then customer sees their business address).
- [x] 19. **Cucumber** – Scenario: No business address returns not found (Given no address, When request, Then response status is 404).
