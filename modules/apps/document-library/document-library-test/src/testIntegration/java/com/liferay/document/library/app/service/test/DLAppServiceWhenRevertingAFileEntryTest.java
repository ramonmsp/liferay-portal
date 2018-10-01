/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.document.library.app.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.sync.constants.DLSyncConstants;
import com.liferay.document.library.workflow.WorkflowHandlerInvocationCounter;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.documentlibrary.service.test.BaseDLAppTestCase;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alexander Chow
 */
@RunWith(Arquillian.class)
public class DLAppServiceWhenRevertingAFileEntryTest extends BaseDLAppTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void shouldCallWorkflowHandler() throws Exception {
		try (WorkflowHandlerInvocationCounter<FileEntry>
				 workflowHandlerInvocationCounter =
				 new WorkflowHandlerInvocationCounter<>(
					 DLFileEntryConstants.getClassName())) {

			FileEntry fileEntry = addFileEntry(
				group.getGroupId(), parentFolder.getFolderId());

			Assert.assertEquals(
				1,
				workflowHandlerInvocationCounter.getCount(
					"updateStatus", int.class, Map.class));

			String version = fileEntry.getVersion();

			updateFileEntry(
				group.getGroupId(), fileEntry.getFileEntryId(),
				RandomTestUtil.randomString(), true);

			Assert.assertEquals(
				2,
				workflowHandlerInvocationCounter.getCount(
					"updateStatus", int.class, Map.class));

			ServiceContext serviceContext =
				ServiceContextTestUtil.getServiceContext(
					group.getGroupId());

			DLAppServiceUtil.revertFileEntry(
				fileEntry.getFileEntryId(), version, serviceContext);

			Assert.assertEquals(
				3,
				workflowHandlerInvocationCounter.getCount(
					"updateStatus", int.class, Map.class));
		}
	}

	@Test
	public void shouldFireSyncEvent() throws Exception {
		AtomicInteger updateCounter =
			registerDLSyncEventProcessorMessageListener(
				DLSyncConstants.EVENT_UPDATE);

		FileEntry fileEntry = addFileEntry(
			group.getGroupId(), parentFolder.getFolderId());

		String version = fileEntry.getVersion();

		Assert.assertEquals(0, updateCounter.get());

		updateFileEntry(
			group.getGroupId(), fileEntry.getFileEntryId(),
			RandomTestUtil.randomString(), true);

		Assert.assertEquals(2, updateCounter.get());

		DLAppServiceUtil.revertFileEntry(
			fileEntry.getFileEntryId(), version,
			ServiceContextTestUtil.getServiceContext(group.getGroupId()));

		Assert.assertEquals(4, updateCounter.get());
	}

}