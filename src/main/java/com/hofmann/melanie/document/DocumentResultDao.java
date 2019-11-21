/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.document;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hofmann.melanie.repository.Dao;
import com.hofmann.melanie.task.Task;

@Repository
public class DocumentResultDao implements Dao<DocumentResult, String> {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public void insert(String taskId, String docResult) {
		DocumentResult documentResult = new DocumentResult(taskId, docResult);

		entityManager.persist(documentResult);
		
	}

	public DocumentResult getByTaskId(String taskId) {
		DocumentResult documentResult = entityManager.createQuery(
				String.format("SELECT t from DocumentResult t WHERE t.taskId = '%s'", taskId), DocumentResult.class).getSingleResult();
		return documentResult;
	}

	@Override
	public Task update(String id, String objToUpdate) {
		throw new UnsupportedOperationException("Update not implemented for document result.");
	}

}
