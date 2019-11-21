/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.melanie.utils;

import com.hofmann.melanie.task.PollingURL;
import com.hofmann.melanie.task.TaskStatus;

public class ResponseUtils {

	public static String getPollingURL(TaskStatus taskStatus, String taskId)  {
		return PollingURL.V1.getUrl() + (TaskStatus.FINISHED.equals(taskStatus) ? PollingURL.RESULT.getUrl() : PollingURL.STATUS.getUrl()) + "/" + taskId;
	}
}
