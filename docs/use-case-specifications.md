# Use Case Specifications

This document contains use case specifications reverse-engineered from the implemented code and observed behavior. Use cases document what was built and normalize flows, terminology, and exception handling.

---

## UC-001: Get Current Business Address

**Description**: The customer requests to view their current business address. The system looks up and displays the address (street, city, state/province, postal code, country), or informs the customer when no address is on file. Accurate address records support billing, compliance, and correspondence.

**Primary Actor**: Customer (self-service user)

---

### Preconditions

- System is operational and database is accessible

---

### Postconditions

**Success**: The customer sees their current business address with all components (street, city, state/province, postal code, country).

**Alternative Flow A1**: The customer is informed that no business address is on file.

---

### Basic Flow

1. **Customer requests their current business address**: The customer asks to view their business address.

2. **System looks up the customer's business address**: The system retrieves the business address for the customer.

3. **System shows the address to the customer**: The system presents the business address, including street, city, state/province, postal code, and country.

---

### Alternative Flows

#### A1: No Business Address On File

- **Trigger**: In step 2, no business address exists for the customer
- **Steps**:
  1. System determines that no business address has been stored for the customer
  2. System informs the customer that no business address is on file

---

### Exception Flows

- **Database Connection Lost**: If the database connection fails while looking up the business address, the request fails and the customer receives an error.

---

### Business Rules

- Each customer has at most one canonical business address
- All address components (street, city, state/province, postal code, country) are mandatory when an address exists
- Address fields are freeform (no format validation beyond presence)
