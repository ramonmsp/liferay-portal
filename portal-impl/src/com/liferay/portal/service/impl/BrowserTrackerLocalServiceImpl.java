/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.service.impl;

import com.liferay.portal.NoSuchBrowserTrackerException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BrowserTracker;
import com.liferay.portal.service.base.BrowserTrackerLocalServiceBaseImpl;

public class BrowserTrackerLocalServiceImpl
	extends BrowserTrackerLocalServiceBaseImpl {

	public void deleteUserBrowserTracker(long userId)
		throws SystemException {

		try {
			browserTrackerPersistence.removeByUserId(userId);
		}
		catch (NoSuchBrowserTrackerException nsbte) {
		}
	}

	public BrowserTracker getBrowserTracker(long browserTrackerId)
		throws PortalException, SystemException {

		return browserTrackerPersistence.findByPrimaryKey(browserTrackerId);
	}

	public BrowserTracker getBrowserTracker(long userId, long browserKey)
		throws SystemException {

		BrowserTracker browserTracker = browserTrackerPersistence.fetchByUserId(
			userId);

		if (browserTracker == null) {
			browserTracker = browserTrackerLocalService.updateBrowserTracker(
				userId, browserKey);
		}

		return browserTracker;
	}

	public BrowserTracker updateBrowserTracker(long userId, long browserKey)
		throws SystemException {

		BrowserTracker browserTracker = browserTrackerPersistence.fetchByUserId(
			userId);

		if (browserTracker == null) {
			long browserTrackerId = counterLocalService.increment();

			browserTracker = browserTrackerPersistence.create(browserTrackerId);

			browserTracker.setUserId(userId);
		}

		browserTracker.setBrowserKey(browserKey);

		try {
			browserTrackerPersistence.update(browserTracker, false);
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn("Add failed, fetch {userId=" + userId + "}");
			}

			browserTracker = browserTrackerPersistence.fetchByUserId(userId);

			if (browserTracker == null) {
				throw se;
			}
		}

		return browserTracker;
	}

	private static Log _log =
		LogFactoryUtil.getLog(BrowserTrackerLocalServiceImpl.class);

}