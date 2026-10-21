# Food Catalogue Service

Manages food items per restaurant and returns a combined page of restaurant details plus its food items (calls restaurant service through Eureka with a load-balanced RestTemplate).

- Port: 9093, Eureka name: FOOD-CATALOGUE
- Storage: in-memory H2 via Spring Data JPA
- Tests: MockMvc controller test for the update endpoint

| Method | Path | Description |
|---|---|---|
| POST | /foodCatalogue/addFoodItem | Add a food item |
| PUT | /foodCatalogue/updateFoodItem/{id} | Update a food item |
| GET | /foodCatalogue/fetchRestaurantAndFoodItemsById/{restaurantId} | Restaurant + food items |

Run: `mvn spring-boot:run` (start servicediscovery and restaurant first).

Part of [food-delivery-microservices](..).
