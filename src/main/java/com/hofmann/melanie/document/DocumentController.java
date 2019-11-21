/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.document;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.hofmann.melanie.task.Task;
import com.hofmann.melanie.task.TaskResponse;
import com.hofmann.melanie.task.TaskStatus;
import com.hofmann.melanie.utils.ResponseUtils;

@RestController
public class DocumentController {
	final static Logger LOG = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	private DocumentService documentService;


	@PostMapping("/v1")
	public ResponseEntity<TaskResponse> extractDocument(@RequestParam("document") MultipartFile document) throws IOException {
		if (document != null && document.getContentType().startsWith("image/")) {
			LOG.info("Start document extraction for " + document.getName() + " of type " + document.getContentType());

			String extractionTask = documentService.analyseDocument(document.getBytes());

			return new ResponseEntity<TaskResponse>(
					new TaskResponse(extractionTask, TaskStatus.STARTED, 
							ResponseUtils.getPollingURL(TaskStatus.STARTED, extractionTask)),
					HttpStatus.ACCEPTED);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Document must be of type image.");
		}

	}

	@GetMapping("/v1/status/{id}")
	public ResponseEntity<TaskResponse> getDocumentExtractionStatus(@PathVariable String id) {
		if (!StringUtils.isEmpty(id)) {
			Task extractionTask = documentService.getTask(id);
			if(extractionTask != null) {
				return new ResponseEntity<TaskResponse>(
						new TaskResponse(extractionTask.getTaskId(), extractionTask.getStatus(), 
								ResponseUtils.getPollingURL(extractionTask.getStatus(), 
								extractionTask.getTaskId())), 
						HttpStatus.ACCEPTED);
			} else {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No task found.");
			}

		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Document ID must not be empty.");
		}

	}

	@GetMapping("/v1/result/{id}")
	public ResponseEntity<String> getDocumentExtractionResult(@PathVariable String id) {
		if (!StringUtils.isEmpty(id)) {
			DocumentResult documentResult = documentService.getDocumentResult(id);

			if(documentResult != null) {
				return new ResponseEntity<String>(documentResult.getResult(), HttpStatus.OK);
			} else {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No document found.");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Document ID must not be empty.");
		}

	}
}
