package co.com.clientauth.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiRestTest {

    ApiRest apiRest = new ApiRest();

    @Test
    void apiRestTest() {
        var response = apiRest.commandName();
        assertEquals("", response);
    }
}
