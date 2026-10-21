# Food Delivery Microservices

A practice project that models a small food-delivery backend as a set of Spring Boot microservices registered with a Eureka service registry. I built it to practise service discovery, inter-service communication and per-service data stores. It is a learning/showcase project, not a production system.

## Architecture

§§§
                 +--------------------------+
                 |  servicediscovery :8761  |   Eureka server
                 +------------▲-------------+
                              | register / discover
     +-----------------+------+----------+------------------+
     |                 |                 |                  |
+----+-------+  +------+--------+  +-----+--------+  +------+-------+
| restaurant |  | foodcatalogue |  |   userinfo   |  |    order     |
|   :9091    |◄─|     :9093     |  |    :9092     |◄─|    :9096     |
|  H2 (JPA)  |  |   H2 (JPA)    |  |   H2 (JPA)   |  |   MongoDB    |
+------------+  +---------------+  +--------------+  +--------------+
§§§

| Service | Port | Eureka name | Data store | Responsibility |
|---|---|---|---|---|
| [servicediscovery](servicediscovery) | 8761 | - | - | Eureka service registry |
| [restaurant](restaurant) | 9091 | RESTAURANT-SERVICE | H2 | Restaurant master data |
| [userinfo](userinfo) | 9092 | USER-SERVICE | H2 | User profiles |
| [foodcatalogue](foodcatalogue) | 9093 | FOOD-CATALOGUE | H2 | Food items per restaurant; aggregates restaurant details |
| [order](order) | 9096 | ORDER-SERVICE | MongoDB | Creates orders; looks up the user; auto-incrementing order ids |

## Tech stack

Java 17, Spring Boot 3.3/3.4, Spring Cloud (Netflix Eureka), Spring Data JPA, Spring Data MongoDB, MapStruct, Lombok, H2, JUnit 5 / Mockito / MockMvc, Maven.

## Inter-service calls

- **foodcatalogue -> restaurant**: fetches restaurant details with a load-balanced `RestTemplate` using the Eureka name `RESTAURANT-SERVICE`.
- **order -> userinfo**: fetches user details with a load-balanced `RestTemplate` using the Eureka name `USER-SERVICE`.
- **order**: generates sequential order ids with an atomic `findAndModify` counter document in MongoDB.

## Running locally

Requirements: JDK 17+, Maven, and a local MongoDB on `localhost:27017` (only for the order service).

Start the services in this order, each from its own folder:

§§§bash
cd servicediscovery && mvn spring-boot:run   # http://localhost:8761
cd restaurant       && mvn spring-boot:run
cd userinfo         && mvn spring-boot:run
cd foodcatalogue    && mvn spring-boot:run
cd order            && mvn spring-boot:run
§§§

## Sample endpoints

| Method | URL | Purpose |
|---|---|---|
| POST | `http://localhost:9091/restaurants/addRestaurant` | Add a restaurant |
| GET | `http://localhost:9091/restaurants` | List restaurants |
| POST | `http://localhost:9092/users/adduser` | Add a user |
| GET | `http://localhost:9092/users/fetchUserById/{userId}` | Get a user |
| POST | `http://localhost:9093/foodCatalogue/addFoodItem` | Add a food item |
| PUT | `http://localhost:9093/foodCatalogue/updateFoodItem/{id}` | Update a food item |
| GET | `http://localhost:9093/foodCatalogue/fetchRestaurantAndFoodItemsById/{restaurantId}` | Restaurant + its food items |
| POST | `http://localhost:9096/orders/createOrder` | Place an order |

## Notes

- Databases are in-memory H2 (and a local MongoDB), configured for local development only; no real credentials are used.
- This is a practice project: there is no authentication, API gateway or centralised configuration in this repository.
