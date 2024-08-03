package com.example.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

class ErrorDetailsTest {

    @Test
    void testErrorDetailsProperties() {
        Date now = new Date();
        String expectedMessage = "Error occurred";
        String expectedDetails = "Detailed error message here";

        ErrorDetails errorDetails = new ErrorDetails(now, expectedMessage, expectedDetails);

        assertEquals(now, errorDetails.getTimestamp(), "Timestamp should match the constructor input");
        assertEquals(expectedMessage, errorDetails.getMessage(), "Message should match the constructor input");
        assertEquals(expectedDetails, errorDetails.getDetails(), "Details should match the constructor input");
    }
}
