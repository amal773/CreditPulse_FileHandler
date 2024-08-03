package com.example.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class FileDataTest {

    @Test
    void testSetAndGetFilename() {
        String expected = "example.txt";
        FileData fileData = new FileData();
        fileData.setFilename(expected);
        assertEquals(expected, fileData.getFilename(), "The filename should match the set value");
    }

    @Test
    void testSetAndGetUrl() {
        String expected = "http://example.com/example.txt";
        FileData fileData = new FileData();
        fileData.setUrl(expected);
        assertEquals(expected, fileData.getUrl(), "The URL should match the set value");
    }

    @Test
    void testSetAndGetSize() {
        Long expected = 1024L;
        FileData fileData = new FileData();
        fileData.setSize(expected);
        assertEquals(expected, fileData.getSize(), "The size should match the set value");
    }
}

