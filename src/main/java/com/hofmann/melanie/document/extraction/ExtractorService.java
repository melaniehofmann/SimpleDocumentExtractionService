/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.document.extraction;

public interface ExtractorService {

	/**
	 * Extracting document.
	 * @param bs image as byte
	 * @return the extracted document
	 * @throws Exception 
	 */
	String extractDocument(byte[] bs) throws Exception;
}
