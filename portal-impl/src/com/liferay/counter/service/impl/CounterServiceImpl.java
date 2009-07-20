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

package com.liferay.counter.service.impl;

import com.liferay.counter.service.CounterService;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;

import java.util.List;

public class CounterServiceImpl implements CounterService {

	public List<String> getNames() throws SystemException {
		return CounterUtil.getNames();
	}

	public long increment() throws SystemException {
		return CounterUtil.increment();
	}

	public long increment(String name) throws SystemException {
		return CounterUtil.increment(name);
	}

	public long increment(String name, int size) throws SystemException {
		return CounterUtil.increment(name, size);
	}

	public void rename(String oldName, String newName) throws SystemException {
		CounterUtil.rename(oldName, newName);
	}

	public void reset(String name) throws SystemException {
		CounterUtil.reset(name);
	}

	public void reset(String name, long size) throws SystemException {
		CounterUtil.reset(name, size);
	}

}