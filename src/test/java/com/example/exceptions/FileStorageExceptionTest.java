package com.example.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FileStorageExceptionTest {

    @Test
    void testFileStorageExceptionMessage() {
        // Test to ensure the message is correctly passed to the exception
        String expectedMessage = "Error storing file";
        FileStorageException exception = new FileStorageException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage(), "The message should be correctly passed and retrieved");
    }

    @Test
    void testFileStorageExceptionWithCause() {
        // Test to ensure the message and cause are correctly passed to the exception
        String expectedMessage = "Error storing file";
        Throwable expectedCause = new Throwable("Specific cause of the failure");
        FileStorageException exception = new FileStorageException(expectedMessage, expectedCause);

        assertEquals(expectedMessage, exception.getMessage(), "The message should be correctly passed and retrieved");
        assertEquals(expectedCause, exception.getCause(), "The cause should be correctly passed and retrieved");
    }

    @Test
    void testInheritance() {
        // Test to check that FileStorageException inherits from RuntimeException
        FileStorageException exception = new FileStorageException("test");
        assertTrue(exception instanceof RuntimeException, "FileStorageException should inherit from RuntimeException");
    }
}
