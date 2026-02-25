CREATE TABLE business_address (
    customer_id VARCHAR(255) PRIMARY KEY,
    street       VARCHAR(255) NOT NULL,
    city         VARCHAR(255) NOT NULL,
    state_province VARCHAR(255) NOT NULL,
    postal_code  VARCHAR(255) NOT NULL,
    country      VARCHAR(255) NOT NULL
);
