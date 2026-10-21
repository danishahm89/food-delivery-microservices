# Restaurant Service

Manages restaurant master data (name, city, address, description).

- Port: 9091, Eureka name: RESTAURANT-SERVICE
- Storage: in-memory H2 via Spring Data JPA (console at /h2-console)
- Mapping: MapStruct

| Method | Path | Description |
|---|---|---|
| GET | /restaurants | List all restaurants |
| GET | /restaurants/fetchById/{restaurantId} | Get one restaurant |
| POST | /restaurants/addRestaurant | Add a restaurant (201) |

Run: `mvn spring-boot:run` (start servicediscovery first).

Part of [food-delivery-microservices](..).
