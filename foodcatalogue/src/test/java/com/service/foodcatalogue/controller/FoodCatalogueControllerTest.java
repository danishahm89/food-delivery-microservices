package com.service.foodcatalogue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.foodcatalogue.dto.FoodItemDto;
import com.service.foodcatalogue.service.FoodCatalogueService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FoodCatalogueController.class)
class FoodCatalogueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodCatalogueService foodCatalogueService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testUpdateFoodItem() throws Exception {
        int foodId = 1;
        FoodItemDto requestDto = new FoodItemDto();
        requestDto.setItemName("Updated Pizza");
        requestDto.setDescription("Cheese Pizza");
        requestDto.setVeg(true);
        requestDto.setPrice(10.99);
        requestDto.setRestaurantId(100);
        requestDto.setQuantity(5);

        FoodItemDto responseDto = new FoodItemDto();
        responseDto.setItemName("Updated Pizza");
        responseDto.setDescription("Cheese Pizza");
        responseDto.setVeg(true);
        responseDto.setPrice(10.99);
        responseDto.setRestaurantId(100);
        responseDto.setQuantity(5);

        Mockito.when(foodCatalogueService.updateFoodItem(Mockito.eq(foodId), Mockito.any(FoodItemDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(put("/foodCatalogue/updateFoodItem/{id}", foodId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value("Updated Pizza"))
                .andExpect(jsonPath("$.description").value("Cheese Pizza"))
                .andExpect(jsonPath("$.veg").value(true))
                .andExpect(jsonPath("$.price").value(10.99))
                .andExpect(jsonPath("$.restaurantId").value(100))
                .andExpect(jsonPath("$.quantity").value(5));
    }

}
