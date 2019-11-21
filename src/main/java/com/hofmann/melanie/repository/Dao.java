/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.repository;

import org.springframework.transaction.annotation.Transactional;

import com.hofmann.melanie.task.Task;

public interface Dao<T, I> {
	
	@Transactional
	public void insert(String id, I objToInsert);
	
	@Transactional
	public T getByTaskId(String id);
	
	@Transactional
	public Task update(String id, I objToUpdate);
	
}
