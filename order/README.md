# Order Service

Creates orders. For each order it generates a sequential order id (atomic counter in MongoDB), fetches the user from the user service through Eureka, and stores the order document.

- Port: 9096, Eureka name: ORDER-SERVICE
- Storage: MongoDB (localhost:27017, database orderdb)

| Method | Path | Description |
|---|---|---|
| POST | /orders/createOrder | Create an order from food items, user id and restaurant |

Run: `mvn spring-boot:run` (needs a local MongoDB; start servicediscovery and userinfo first).

Part of [food-delivery-microservices](..).
