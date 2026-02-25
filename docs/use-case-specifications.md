# Use Case Specifications

This document contains use case specifications reverse-engineered from the implemented code and BDD scenarios. Use cases document what was built and normalize flows, terminology, and exception handling.

---

## UC-001: Get Current Business Address

**User Story**: As a customer who has relocated to a new office or work location, I want to update my business address, so that I receive physical correspondence and maintain accurate records for billing and compliance purposes.

**Scope**: This use case covers the first acceptance criterion: displaying the current business address including all components.

**Primary Actor**: Customer (self-service user)

**Stakeholders and Interests**:
- **Customer**: Wants to see their current business address to verify it or before updating it
- **Business**: Needs accurate address records for billing, compliance, and correspondence

---

### Preconditions

- System is operational and database is accessible
- Application server is running

---

### Postconditions

**Success**: The customer receives their current business address with all components (street, city, state/province, postal code, country).

**Alternative Flow A1**: The customer is informed that no business address has been set.

---

### Basic Flow

1. **Customer requests their current business address**: The customer initiates a request to view their business address.

2. **System loads the business address**: The system retrieves the canonical business address for the customer from persistent storage.

3. **System returns the address**: The system presents the business address to the customer, including street, city, state/province, postal code, and country.

---

### Alternative Flows

#### A1: No Business Address Set

- **Trigger**: In step 2, no business address exists for the customer
- **Steps**:
  1. System determines that no business address has been stored for the customer
  2. System returns a not-found response
  3. Customer is informed that no business address is on file

---

### Exception Flows

- **Database Connection Lost**: If the database connection fails while loading the business address, the system propagates the exception and the request fails with an error response.

---

### Business Rules

- Each customer has at most one canonical business address
- All address components (street, city, state/province, postal code, country) are mandatory when an address exists
- Address fields are freeform (no format validation beyond presence)

---

### Notes

- BDD scenarios for this use case: `business_address.feature`
- The "update" capability (changing the address) is part of the same user story but is not yet implemented
