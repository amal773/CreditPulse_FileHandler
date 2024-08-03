package com.example.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FileNotFoundExceptionTest {

    @Test
    void testFileNotFoundExceptionMessage() {
        String expectedMessage = "File not found error";
        FileNotFoundException exception = new FileNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage(), "The message should match the constructor input");
    }

    @Test
    void testFileNotFoundExceptionWithCause() {
        String expectedMessage = "File not found error";
        Throwable expectedCause = new Throwable("Cause of the exception");
        FileNotFoundException exception = new FileNotFoundException(expectedMessage, expectedCause);

        assertEquals(expectedMessage, exception.getMessage(), "The message should match the constructor input");
        assertEquals(expectedCause, exception.getCause(), "The cause should match the constructor input");
    }

    @Test
    void testInheritance() {
        FileNotFoundException exception = new FileNotFoundException("test");
        assertTrue(exception instanceof RuntimeException, "FileNotFoundException should inherit from RuntimeException");
    }
}
