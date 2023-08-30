Shopping Cart Service
----------------------------------------------

[![GitHub](https://github.com/josdem/shopping-cart-service/actions/workflows/main.yml/badge.svg)](https://github.com/josdem/shopping-cart-service/actions)
[![Jenkins](https://jenkins.josdem.io/job/shopping-cart-service/badge/icon)](https://jenkins.josdem.io/job/shopping-cart-service/)

Shopping cart is an API service
using [Spring Webflux Framework](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html)
.

#### To run tests

```bash
gradle test
```

#### To run the project

```bash
gradle bootRun
```

#### Deployment

https://github.com/josdem/shopping-cart-service/wiki/Deployments-using-Jenkins

#### To run tests with Jacoco and Sonarqube

```bash
gradle jacocoTestReport sonar test
```

#### Swagger

http://localhost:8085/swagger-ui.html