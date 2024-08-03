package com.example.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.UploadResponseMessage;
import com.example.service.FileService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("files")
public class FilesController {

	private FileService fs;

	public FilesController(FileService fs) {
		this.fs = fs;
	}

	@PostMapping
	public ResponseEntity<UploadResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

		try {
			fs.save(file);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new UploadResponseMessage("Could not upload the file: " + file.getOriginalFilename() + "!"));
		}
	}

	@GetMapping("{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = fs.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}