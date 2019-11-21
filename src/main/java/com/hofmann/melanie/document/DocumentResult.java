/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "document_result")
public class DocumentResult {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String result;
	@Column(unique=true)
	private String taskId;
	
	
	public DocumentResult() {
		
	}


	public DocumentResult(String taskId, String result) {
		this.result = result;
		this.taskId = taskId;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}	
}
