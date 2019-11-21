/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hofmann.melanie.repository.Dao;

@Repository
public class TaskDao implements Dao<Task, TaskStatus> {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
	public void insert(String taskId, TaskStatus taskStatus) {
		Task task = new Task(taskId, taskStatus);

		entityManager.persist(task);
	}
	
	@Transactional
	public Task getByTaskId(String taskId) {
		Task task = entityManager.createQuery(String.format("SELECT t from Task t WHERE t.taskId = '%s'", taskId), Task.class).getSingleResult();
		return task;
	}
	
	@Transactional
	public Task update(String taskId, TaskStatus taskStatus) {
		Task task =this.getByTaskId(taskId);
		task.setStatus(taskStatus);
		entityManager.merge(task);
		return task;
	}
}
