/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.task;

public class TaskResponse {
	
	private String taskId;
	private TaskStatus status;
	private String url;
		
	public TaskResponse(String taskId, TaskStatus taskStatus, String url) {
		this.taskId = taskId;
		this.status = taskStatus;
		this.url = url;
	}
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
