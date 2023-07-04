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

#### To get [JWT](https://en.wikipedia.org/wiki/JSON_Web_Token) token

```bash
curl -v -u ${username}:${password} http://localhost:8085/login
```

**where:**

- `${username}` is user
- `${password}` is password

See `src/main/resources/application.yml` for more details

#### To get products

```bash
curl -v -H "Authorization: Bearer ${token}" http://localhost:8085/products/
```

**where:**

- `${token}` is JWT token generated from login endpoint