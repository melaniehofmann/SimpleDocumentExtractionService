/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.document;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hofmann.melanie.document.extraction.ExtractorService;
import com.hofmann.melanie.repository.Dao;
import com.hofmann.melanie.task.Task;
import com.hofmann.melanie.task.TaskStatus;


@Service
public class SimpleDocumentService implements DocumentService {
	final static Logger LOG = LoggerFactory.getLogger(SimpleDocumentService.class);

	@Autowired
	private Dao<Task, TaskStatus> taskDao;

	@Autowired
	private Dao<DocumentResult, String> documentResultDao;

	@Autowired
	private ExtractorService extractorService;

	public String analyseDocument(final byte[] bs) {
		final long startTime = System.currentTimeMillis();

		final String taskId = UUID.randomUUID().toString();
		taskDao.insert(taskId, TaskStatus.STARTED);

		CompletableFuture.runAsync(() -> {
			taskDao.update(taskId, TaskStatus.RUNNING);

			String result;	
			try {
				result = extractorService.extractDocument(bs);

				documentResultDao.insert(taskId, result);

				taskDao.update(taskId, TaskStatus.FINISHED);

				LOG.info(String.format("Document extraction took %d ms", System.currentTimeMillis() - startTime));
			} catch (Exception e) {
				LOG.error("Error occured while analyzing document.", e);
				taskDao.update(taskId, TaskStatus.ABORTED);
			}
		});


		return taskId;
	}

	public Task getTask(String id) {
		return taskDao.getByTaskId(id);
	}

	public DocumentResult getDocumentResult(String id) {
		return documentResultDao.getByTaskId(id);
	}

}
