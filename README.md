# NexaBank QA Automation Framework

A professional hybrid UI and API automation framework designed for resume and GitHub portfolio presentation. The framework demonstrates real-world QA automation skills using Selenium WebDriver, Java, TestNG, Maven, Rest Assured, Extent Reports, Log4j2, Jenkins, Git, and the Page Object Model design pattern.

## Project Objective

This project simulates an enterprise-style QA automation framework for a banking application. It includes UI automation, API automation, reusable utilities, reporting, logging, suite-based execution, CI/CD readiness, and clean GitHub documentation.

## Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Rest Assured
- Extent Reports
- Log4j2
- Jenkins Pipeline
- Git / GitHub
- Page Object Model
- Data-driven testing using JSON
- Screenshot capture on failure
- TestNG groups and suite execution

## Framework Highlights

- Page Object Model implementation for maintainable UI automation
- Separate UI and API test packages
- Maven build lifecycle support
- TestNG smoke, regression, and API suites
- Extent Reports integration with screenshots for failed UI tests
- Log4j2 logging for execution traceability
- Config-driven browser, URL, and execution settings
- ThreadLocal WebDriver handling for parallel execution readiness
- Jenkinsfile included for CI/CD pipeline demonstration
- JSON test data handling for API and login scenarios

## Application Coverage

### UI Automation Coverage

The UI tests are based on ParaBank demo banking flows:

- Login page validation
- Valid login flow
- Invalid login validation
- Account overview validation
- Logout flow
- Fund transfer workflow
- Bill payment workflow
- Mandatory field validation

### API Automation Coverage

The API tests use public demo REST APIs and cover:

- GET request validation
- POST request validation
- PUT request validation
- DELETE request validation
- Status code assertions
- Response body assertions
- Payload validation
- Health check validation
- Negative endpoint validation

## Project Structure

```text
NexaBank-QA-Automation-Framework
├── pom.xml
├── testng.xml
├── Jenkinsfile
├── README.md
├── src
│   ├── main
│   │   └── java/com/nexabank
│   │       ├── base
│   │       ├── config
│   │       ├── pages
│   │       ├── reports
│   │       └── utils
│   └── test
│       ├── java/com/nexabank/tests
│       │   ├── api
│       │   ├── base
│       │   └── ui
│       └── resources
│           ├── config.properties
│           ├── log4j2.xml
│           ├── suites
│           └── testdata
├── reports
├── screenshots
└── logs
```

## Prerequisites

Install the following before running the project:

- Java JDK 17+
- Maven 3.8+
- Chrome browser
- Git
- Jenkins, optional for CI/CD execution

## How to Run

### Run complete suite

```bash
mvn clean test
```

### Run smoke suite

```bash
mvn test -DsuiteXmlFile=src/test/resources/suites/smoke.xml
```

### Run regression suite

```bash
mvn test -DsuiteXmlFile=src/test/resources/suites/regression.xml
```

### Run API suite only

```bash
mvn test -DsuiteXmlFile=src/test/resources/suites/api.xml
```

### Run in headless mode

```bash
mvn test -Dheadless=true
```

### Run on Firefox

```bash
mvn test -Dbrowser=firefox
```

## Reports

Extent reports are generated inside:

```text
reports/
```

Screenshots for failed UI tests are stored inside:

```text
screenshots/
```

Execution logs are stored inside:

```text
logs/
```

## Jenkins Integration

The repository includes a `Jenkinsfile` with the following stages:

1. Checkout
2. Clean Build
3. Run Automation Suite
4. Archive Reports, Screenshots, and Logs

## Suggested Resume Entry

**NexaBank QA Automation Framework**  
Designed and developed a hybrid UI/API automation framework using Selenium WebDriver, Java, TestNG, Maven, and Rest Assured. Implemented Page Object Model, reusable utilities, config-driven execution, TestNG suites, JSON-based test data, Extent Reports with screenshots, Log4j2 logging, and Jenkins pipeline integration for CI/CD execution.

## Suggested GitHub Description

Hybrid Selenium Java + Rest Assured automation framework using TestNG, Maven, POM, Extent Reports, Log4j2, Jenkins, and GitHub for UI/API regression testing.

## Notes

This is a portfolio automation framework. Public demo applications may occasionally change UI locators, availability, or API behavior. If any test fails because the public demo site changes, update the relevant locator or endpoint assertion.
