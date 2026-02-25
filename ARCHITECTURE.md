# Architecture Overview

The CRM Self-Service application follows a **hexagonal (ports and adapters)** architecture.

## Documentation

- **[4+1 Architecture View](docs/architecture-4plus1.md)** – Logical, Process, Development, and Physical views with Mermaid diagrams
- **[Use Case Specifications](docs/use-case-specifications.md)** – Reverse-engineered use cases documenting implemented behavior

## Summary

- **Domain**: `BusinessAddress`, `Customer` – pure business concepts, no external dependencies
- **Application**: Use cases and outbound ports (`LoadBusinessAddressPort`, `SaveBusinessAddressPort`)
- **Adapters**: REST controller (inbound), JPA persistence (outbound)
- **Dependency rule**: Dependencies point inward; domain is isolated from ports and adapters
