package com.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class UploadResponseMessageTest {

    @Test
    void testGetResponseMessage() {
        String expected = "File uploaded successfully";
        UploadResponseMessage message = new UploadResponseMessage(expected);
        assertEquals(expected, message.getResponseMessage(), "The response message should match the set value");
    }
}
