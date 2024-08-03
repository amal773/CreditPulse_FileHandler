package com.example.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.exceptions.FileNotFoundException;
import com.example.exceptions.FileStorageException;

@Service
public class FileService {

	@Value("${upload.path}")
	private String uploadPath;

	@PostConstruct
	public void init() {
		try {
			Files.createDirectories(Paths.get(uploadPath));
		} catch (IOException e) {
			throw new FileStorageException("Could not create upload folder!");
		}
	}

	public void save(MultipartFile file) {
		try {
			Path root = Paths.get(uploadPath);
			if (!Files.exists(root)) {
				init();
			}
			Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new FileStorageException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public Resource load(String filename) {
		try {
			Path file = Paths.get(uploadPath).resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new FileNotFoundException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new FileNotFoundException("Error: " + e.getMessage());
		}
	}

}