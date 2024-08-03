package com.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.FileService;

public class FilesControllerTest {

    @Mock
    private FileService fileService;

    @InjectMocks
    private FilesController filesController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(filesController).build();
    }

    @Test
    void testUploadFile_Success() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "Test content".getBytes());

        mockMvc.perform(multipart("/files").file(file))
                .andExpect(status().isOk());

        verify(fileService).save(any(MultipartFile.class));
    }

    @Test
    void testUploadFile_Failure() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "Test content".getBytes());

        doThrow(new RuntimeException("Could not upload the file")).when(fileService).save(any(MultipartFile.class));

        mockMvc.perform(multipart("/files").file(file))
                .andExpect(status().isExpectationFailed());

        verify(fileService).save(any(MultipartFile.class));
    }

    @Test
    void testGetFile() throws Exception {
        Resource resource = new UrlResource("file:test.txt");
        when(fileService.load("test.txt")).thenReturn(resource);

      
    }
}
