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

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.kernel.dao.orm.Dialect;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PropsValues;

import java.sql.Connection;

import org.hibernate.engine.SessionFactoryImplementor;

public class SessionFactoryImpl implements SessionFactory {

	public void closeSession(Session session) throws ORMException {
		if (!PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED) {
			session.close();
		}
	}

	public Dialect getDialect() throws ORMException {
		return new DialectImpl(_sessionFactoryImplementor.getDialect());
	}

	public SessionFactoryImplementor getSessionFactoryImplementor() {
		return _sessionFactoryImplementor;
	}

	public Session openNewSession(Connection connection) throws ORMException {
		Session session = new SessionImpl(
			_sessionFactoryImplementor.openSession(connection));

		return session;
	}

	public Session openSession() throws ORMException {
		org.hibernate.Session session = null;

		if (PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED) {
			session = _sessionFactoryImplementor.getCurrentSession();
		}
		else {
			session = _sessionFactoryImplementor.openSession();
		}

		if (_log.isDebugEnabled()) {
			LiferayClassicSession classicSession =
				(LiferayClassicSession)session;

			org.hibernate.impl.SessionImpl sessionImpl =
				(org.hibernate.impl.SessionImpl)
					classicSession.getHibernateClassicSession();

			_log.debug(
				"Session is using connection release mode " +
					sessionImpl.getConnectionReleaseMode());
		}

		return new SessionImpl(session);
	}

	public void setSessionFactoryImplementor(
		SessionFactoryImplementor sessionFactoryImplementor) {

		_sessionFactoryImplementor = sessionFactoryImplementor;
	}

	private static Log _log = LogFactoryUtil.getLog(SessionFactoryImpl.class);

	private SessionFactoryImplementor _sessionFactoryImplementor;

}