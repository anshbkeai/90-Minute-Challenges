# 90-Minute Challenges

A personal learning repository for short software engineering challenges. Each challenge is a focused exercise in building, testing, and explaining a small solution. The goal is to practice consistently and keep the work in one place, not to create production-ready applications.

The repository currently contains **Payflow**, a Java and Spring Boot payment API challenge. Future challenges can live alongside it in the same Maven project, each in its own Java package and with its own notes and tests. Work on one challenge at a time so the shared project stays easy to run and understand.

## Repository Layout

```text
.
├── README.md                 # Repository purpose and shared workflow
├── CHALLENGE.md              # Payflow exercise requirements
├── DESIGN.md                 # Payflow design notes to complete before coding
└── backend/
	├── pom.xml               # Shared Maven dependencies and build configuration
	└── src/
		├── main/java/com/challenge/payflow/  # Payflow application and code
		├── main/resources/                   # Application configuration
		└── test/java/                       # Automated tests
```

As more challenges are added, give each one a distinct package under `com.challenge`, for example `com.challenge.payflow` and `com.challenge.urlshortener`. Keep challenge-specific requirements and design notes together and clearly named, so it remains obvious which exercise a document belongs to.

## Current Challenge: Payflow

Payflow is a timed backend and system-design exercise: build a small payment API, practice idempotency and state transitions, and explain the design trade-offs. It is an intentionally incomplete starter scaffold, not a production payment gateway.

- Read [CHALLENGE.md](CHALLENGE.md) for the scenario, requirements, and timebox.
- Use [DESIGN.md](DESIGN.md) to record the design before implementation.

## Tech Stack

- Java 17+
- Maven
- Spring Boot 3.3.4, Spring Web, Spring Data JPA, and Validation
- H2 in-memory database
- No external services required

## Run Payflow

```bash
cd backend
mvn spring-boot:run
```

Run the backend tests with:

```bash
cd backend
mvn test
```

Maven may need network access the first time to download dependencies.

## Workflow For Each Challenge

1. Read the challenge requirements and note assumptions and non-goals.
2. Sketch the API, data model, important invariants, and failure cases before coding.
3. Implement a small working path, then add focused tests for success and failure cases.
4. Run the tests and explain the decisions, trade-offs, and what you would change with more time.
5. Keep the challenge's code and notes under its own name; avoid changing shared dependencies unless the challenge needs them.

When adding another Spring Boot challenge package, check component scanning and application configuration. The current Payflow application is configured to scan Payflow's core package, so new packages may need an explicit wiring change before Spring can discover their components.
