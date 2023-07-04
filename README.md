Shopping Cart Service
----------------------------------------------

[![GitHub](https://github.com/josdem/shopping-cart-service/actions/workflows/main.yml/badge.svg)](https://github.com/josdem/shopping-cart-service/actions)

Shopping cart is an API service using [Spring Webflux Framework](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html).

#### To run tests

```bash
gradle test
```

#### To run the project

```bash
gradle bootRun
```

#### To consume service no authentication

```bash
curl -v http://localhost:8085/
```

#### To consume service with authentication

```bash
curl -v -u ${username}:${password} http://localhost:8085/products/
```

**where:**
- `${username}` is user
- `${password}` is password

See `src/main/resources/application.yml` for more details