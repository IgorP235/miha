package com.example.RestService;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.RestService.api.CarController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ControllerTest {

    @Autowired
    private CarController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull(); // Test that the context is creating controller
    }
}
