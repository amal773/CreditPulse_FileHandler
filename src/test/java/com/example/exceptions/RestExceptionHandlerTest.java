package com.example.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.example.model.UploadResponseMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestExceptionHandlerTest {

    private RestExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new RestExceptionHandler();
    
    }

    @Test
    void testHandleMaxSizeException() {
        MaxUploadSizeExceededException exception = new MaxUploadSizeExceededException(1);

        ResponseEntity<UploadResponseMessage> response = handler.handleMaxSizeException(exception);

        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode(), "HTTP status should be EXPECTATION_FAILED");
        assertEquals("Unable to upload. File is too large!", response.getBody().getResponseMessage(), "The response message should indicate that the file size was too large.");
    }
}
