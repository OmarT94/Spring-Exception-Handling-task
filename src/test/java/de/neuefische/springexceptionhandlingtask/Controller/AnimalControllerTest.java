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
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void when_getAllAnimals_ThenReturnException() throws Exception {
        mockMvc.perform(get("/api/animals"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No Animals found"))
                .andExpect(jsonPath("$.timestamp").exists());

    }



    @Test
    void whenGetAnimalSpeciesNotDog_ThenReturnException() throws Exception {
        mockMvc.perform(get("/api/animals/cat"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Only 'dog' is allowed"))
                .andExpect(jsonPath("$.timestamp").exists());
    }


}