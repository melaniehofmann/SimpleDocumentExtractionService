/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.task;

public enum PollingURL {

	STATUS("/status"), RESULT("/result"), V1("/v1");
	
	private String url;
	
	PollingURL(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
}
