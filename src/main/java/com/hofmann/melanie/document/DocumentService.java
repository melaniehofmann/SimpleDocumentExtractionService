/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.document;

import com.hofmann.melanie.task.Task;

public interface DocumentService {
	String analyseDocument(final byte[] bs);

	Task getTask(String id);

	DocumentResult getDocumentResult(String id);
}
