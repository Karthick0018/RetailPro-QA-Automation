# RetailPro QA Automation

UI + API test automation framework for a retail application.

- **UI:** Selenium WebDriver, Cucumber BDD, TestNG, Page Object Model (target: https://www.saucedemo.com)
- **API:** REST Assured (target: https://fakestoreapi.com)
- **Build:** Maven (Java 17), Surefire runs `testng.xml`

## Structure

```
src/test/java/com/retailpro
  api/              REST Assured tests
  hooks/            Cucumber Before/After (driver, screenshot on failure)
  pages/            Page objects (Base, Login, Inventory, Cart)
  runners/          Cucumber TestNG runner
  stepdefinitions/  Step definitions
  utils/            ConfigReader, DriverFactory (ThreadLocal)
src/test/resources
  features/         login.feature, cart.feature
  config.properties URLs, credentials, defaults
testng.xml          UI and API suites
```

## Run

```bash
mvn clean test                                  # defaults: chrome, headed
mvn clean test -Dbrowser=chrome -Dheadless=true # headless
mvn clean test -Dbrowser=firefox
mvn clean test -Dcucumber.filter.tags="@login"  # only login scenarios
```

## Reports

- Cucumber HTML: `target/cucumber-reports/report.html`
- Surefire/TestNG: `target/surefire-reports/`

Failed UI scenarios attach a screenshot to the report.
