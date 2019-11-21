/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.document.extraction;

import java.nio.ByteBuffer;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;
import com.amazonaws.services.textract.model.Block;
import com.amazonaws.services.textract.model.DetectDocumentTextRequest;
import com.amazonaws.services.textract.model.DetectDocumentTextResult;
import com.amazonaws.services.textract.model.Document;

@Service
public class TextExtractorService implements ExtractorService {

	
	public String extractDocument(byte[] bs) throws Exception {
		final AmazonTextract client = AmazonTextractClientBuilder.defaultClient();

		ByteBuffer imageBytes = ByteBuffer.wrap(bs);

		DetectDocumentTextRequest request = new DetectDocumentTextRequest()
				.withDocument(new Document()
						.withBytes(imageBytes));


		DetectDocumentTextResult result = client.detectDocumentText(request);

		StringBuffer buffer = new StringBuffer();
		List<Block> blocks = result.getBlocks();
		for (Block block : blocks) {
			if ("WORD".equals(block.getBlockType())) {
				buffer.append(block.getText());
				buffer.append(" ");
			}
		}
		return buffer.toString();
	}

}
