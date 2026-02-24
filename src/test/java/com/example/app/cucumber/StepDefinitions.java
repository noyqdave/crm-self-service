package com.example.app.cucumber;

import com.example.app.Application;
import io.cucumber.java.en.Given;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Placeholder step definitions for Cucumber. No business behaviour.
 */
@SpringBootTest(classes = Application.class)
public class StepDefinitions {

    @Given("the application is running")
    public void theApplicationIsRunning() {
        // Placeholder: no implementation
    }
}
