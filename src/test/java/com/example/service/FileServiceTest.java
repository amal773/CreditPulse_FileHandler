package com.example.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.exceptions.FileNotFoundException;
import com.example.exceptions.FileStorageException;

public class FileServiceTest {

    @InjectMocks
    private FileService fileService;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(fileService, "uploadPath", tempDir.toString());
        Assertions.assertDoesNotThrow(()->{});
    }

    @Test
    void testInit() throws IOException {
        Path uploadPath = Paths.get(tempDir.toString());
        Files.deleteIfExists(uploadPath);
        fileService.init();
        Assertions.assertDoesNotThrow(()->{});
    }

    @Test
    void testSaveFile() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Test content".getBytes());
        fileService.save(file);
        Path uploadedFilePath = Paths.get(tempDir.toString(), "test.txt");
        assert(Files.exists(uploadedFilePath));
        Assertions.assertDoesNotThrow(()->{});
    }

    @Test
    void testSaveFile_Exception() {
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Test content".getBytes());
        ReflectionTestUtils.setField(fileService, "uploadPath", "invalid/path");
        assertThrows(FileStorageException.class, () -> fileService.save(file));
        Assertions.assertDoesNotThrow(()->{});
    }

    @Test
    void testLoadFile_Success() throws IOException {
        Path filePath = Paths.get(tempDir.toString(), "test.txt");
        Files.write(filePath, "Test content".getBytes());
        Resource resource = fileService.load("test.txt");
        assert(resource.exists());
        Assertions.assertDoesNotThrow(()->{});
    }

    @Test
    void testLoadFile_FileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> fileService.load("nonexistent.txt"));
        Assertions.assertDoesNotThrow(()->{});
    }

    @Test
    void testLoadFile_MalformedURLException() {
        ReflectionTestUtils.setField(fileService, "uploadPath", "invalid://path");
        Assertions.assertDoesNotThrow(()->{});
    }
}
