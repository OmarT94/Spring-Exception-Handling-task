package de.neuefische.springexceptionhandlingtask.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void whenGetAllCars_thenReturnExceptionHandled() throws Exception {
        mockMvc.perform(get("/api/cars"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No Cars found"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void whenGetCarBrandNotPorsche_thenReturnExceptionHandled() throws Exception {
        mockMvc.perform(get("/api/cars/ford"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Only 'porsche' allowed"))
                .andExpect(jsonPath("$.timestamp").exists());
    }
}