# User Info Service

Stores and serves user profiles. Used by the order service.

- Port: 9092, Eureka name: USER-SERVICE
- Storage: in-memory H2 via Spring Data JPA (table user_info)

| Method | Path | Description |
|---|---|---|
| POST | /users/adduser | Add a user |
| GET | /users/fetchUserById/{userId} | Get a user by id |

Run: `mvn spring-boot:run` (start servicediscovery first).

Practice project: passwords are stored as plain text for demo purposes only.

Part of [food-delivery-microservices](..).
