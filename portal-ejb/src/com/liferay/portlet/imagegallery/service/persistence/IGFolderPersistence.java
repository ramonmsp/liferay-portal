/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.imagegallery.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.spring.hibernate.HibernateUtil;

import com.liferay.portlet.imagegallery.NoSuchFolderException;
import com.liferay.portlet.imagegallery.model.IGFolder;
import com.liferay.portlet.imagegallery.model.impl.IGFolderImpl;

import com.liferay.util.dao.hibernate.QueryUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

/**
 * <a href="IGFolderPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class IGFolderPersistence extends BasePersistence {
	public IGFolder create(String folderId) {
		IGFolder igFolder = new IGFolderImpl();
		igFolder.setNew(true);
		igFolder.setPrimaryKey(folderId);

		return igFolder;
	}

	public IGFolder remove(String folderId)
		throws NoSuchFolderException, SystemException {
		Session session = null;

		try {
			session = openSession();

			IGFolder igFolder = (IGFolder)session.get(IGFolderImpl.class,
					folderId);

			if (igFolder == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No IGFolder exists with the primary key " +
						folderId);
				}

				throw new NoSuchFolderException(
					"No IGFolder exists with the primary key " + folderId);
			}

			return remove(igFolder);
		}
		catch (NoSuchFolderException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public IGFolder remove(IGFolder igFolder) throws SystemException {
		Session session = null;

		try {
			session = openSession();
			session.delete(igFolder);
			session.flush();

			return igFolder;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public com.liferay.portlet.imagegallery.model.IGFolder update(
		com.liferay.portlet.imagegallery.model.IGFolder igFolder)
		throws SystemException {
		return update(igFolder, false);
	}

	public com.liferay.portlet.imagegallery.model.IGFolder update(
		com.liferay.portlet.imagegallery.model.IGFolder igFolder,
		boolean saveOrUpdate) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (saveOrUpdate) {
				session.saveOrUpdate(igFolder);
			}
			else {
				if (igFolder.isNew()) {
					session.save(igFolder);
				}
			}

			session.flush();
			igFolder.setNew(false);

			return igFolder;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public IGFolder findByPrimaryKey(String folderId)
		throws NoSuchFolderException, SystemException {
		IGFolder igFolder = fetchByPrimaryKey(folderId);

		if (igFolder == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No IGFolder exists with the primary key " +
					folderId);
			}

			throw new NoSuchFolderException(
				"No IGFolder exists with the primary key " + folderId);
		}

		return igFolder;
	}

	public IGFolder fetchByPrimaryKey(String folderId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (IGFolder)session.get(IGFolderImpl.class, folderId);
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findByGroupId(long groupId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portlet.imagegallery.model.IGFolder WHERE ");
			query.append("groupId = ?");
			query.append(" ");
			query.append("ORDER BY ");
			query.append("folderId ASC").append(", ");
			query.append("name ASC");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, groupId);

			return q.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findByGroupId(long groupId, int begin, int end)
		throws SystemException {
		return findByGroupId(groupId, begin, end, null);
	}

	public List findByGroupId(long groupId, int begin, int end,
		OrderByComparator obc) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portlet.imagegallery.model.IGFolder WHERE ");
			query.append("groupId = ?");
			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}
			else {
				query.append("ORDER BY ");
				query.append("folderId ASC").append(", ");
				query.append("name ASC");
			}

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, groupId);

			return QueryUtil.list(q, getDialect(), begin, end);
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public IGFolder findByGroupId_First(long groupId, OrderByComparator obc)
		throws NoSuchFolderException, SystemException {
		List list = findByGroupId(groupId, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();
			msg.append("No IGFolder exists with the key ");
			msg.append(StringPool.OPEN_CURLY_BRACE);
			msg.append("groupId=");
			msg.append(groupId);
			msg.append(StringPool.CLOSE_CURLY_BRACE);
			throw new NoSuchFolderException(msg.toString());
		}
		else {
			return (IGFolder)list.get(0);
		}
	}

	public IGFolder findByGroupId_Last(long groupId, OrderByComparator obc)
		throws NoSuchFolderException, SystemException {
		int count = countByGroupId(groupId);
		List list = findByGroupId(groupId, count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();
			msg.append("No IGFolder exists with the key ");
			msg.append(StringPool.OPEN_CURLY_BRACE);
			msg.append("groupId=");
			msg.append(groupId);
			msg.append(StringPool.CLOSE_CURLY_BRACE);
			throw new NoSuchFolderException(msg.toString());
		}
		else {
			return (IGFolder)list.get(0);
		}
	}

	public IGFolder[] findByGroupId_PrevAndNext(String folderId, long groupId,
		OrderByComparator obc) throws NoSuchFolderException, SystemException {
		IGFolder igFolder = findByPrimaryKey(folderId);
		int count = countByGroupId(groupId);
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portlet.imagegallery.model.IGFolder WHERE ");
			query.append("groupId = ?");
			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}
			else {
				query.append("ORDER BY ");
				query.append("folderId ASC").append(", ");
				query.append("name ASC");
			}

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, groupId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, igFolder);
			IGFolder[] array = new IGFolderImpl[3];
			array[0] = (IGFolder)objArray[0];
			array[1] = (IGFolder)objArray[1];
			array[2] = (IGFolder)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findByG_P(long groupId, String parentFolderId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portlet.imagegallery.model.IGFolder WHERE ");
			query.append("groupId = ?");
			query.append(" AND ");

			if (parentFolderId == null) {
				query.append("parentFolderId IS NULL");
			}
			else {
				query.append("parentFolderId = ?");
			}

			query.append(" ");
			query.append("ORDER BY ");
			query.append("folderId ASC").append(", ");
			query.append("name ASC");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, groupId);

			if (parentFolderId != null) {
				q.setString(queryPos++, parentFolderId);
			}

			return q.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findByG_P(long groupId, String parentFolderId, int begin,
		int end) throws SystemException {
		return findByG_P(groupId, parentFolderId, begin, end, null);
	}

	public List findByG_P(long groupId, String parentFolderId, int begin,
		int end, OrderByComparator obc) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portlet.imagegallery.model.IGFolder WHERE ");
			query.append("groupId = ?");
			query.append(" AND ");

			if (parentFolderId == null) {
				query.append("parentFolderId IS NULL");
			}
			else {
				query.append("parentFolderId = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}
			else {
				query.append("ORDER BY ");
				query.append("folderId ASC").append(", ");
				query.append("name ASC");
			}

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, groupId);

			if (parentFolderId != null) {
				q.setString(queryPos++, parentFolderId);
			}

			return QueryUtil.list(q, getDialect(), begin, end);
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public IGFolder findByG_P_First(long groupId, String parentFolderId,
		OrderByComparator obc) throws NoSuchFolderException, SystemException {
		List list = findByG_P(groupId, parentFolderId, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();
			msg.append("No IGFolder exists with the key ");
			msg.append(StringPool.OPEN_CURLY_BRACE);
			msg.append("groupId=");
			msg.append(groupId);
			msg.append(", ");
			msg.append("parentFolderId=");
			msg.append(parentFolderId);
			msg.append(StringPool.CLOSE_CURLY_BRACE);
			throw new NoSuchFolderException(msg.toString());
		}
		else {
			return (IGFolder)list.get(0);
		}
	}

	public IGFolder findByG_P_Last(long groupId, String parentFolderId,
		OrderByComparator obc) throws NoSuchFolderException, SystemException {
		int count = countByG_P(groupId, parentFolderId);
		List list = findByG_P(groupId, parentFolderId, count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();
			msg.append("No IGFolder exists with the key ");
			msg.append(StringPool.OPEN_CURLY_BRACE);
			msg.append("groupId=");
			msg.append(groupId);
			msg.append(", ");
			msg.append("parentFolderId=");
			msg.append(parentFolderId);
			msg.append(StringPool.CLOSE_CURLY_BRACE);
			throw new NoSuchFolderException(msg.toString());
		}
		else {
			return (IGFolder)list.get(0);
		}
	}

	public IGFolder[] findByG_P_PrevAndNext(String folderId, long groupId,
		String parentFolderId, OrderByComparator obc)
		throws NoSuchFolderException, SystemException {
		IGFolder igFolder = findByPrimaryKey(folderId);
		int count = countByG_P(groupId, parentFolderId);
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portlet.imagegallery.model.IGFolder WHERE ");
			query.append("groupId = ?");
			query.append(" AND ");

			if (parentFolderId == null) {
				query.append("parentFolderId IS NULL");
			}
			else {
				query.append("parentFolderId = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}
			else {
				query.append("ORDER BY ");
				query.append("folderId ASC").append(", ");
				query.append("name ASC");
			}

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, groupId);

			if (parentFolderId != null) {
				q.setString(queryPos++, parentFolderId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, igFolder);
			IGFolder[] array = new IGFolderImpl[3];
			array[0] = (IGFolder)objArray[0];
			array[1] = (IGFolder)objArray[1];
			array[2] = (IGFolder)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findWithDynamicQuery(DynamicQueryInitializer queryInitializer)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findWithDynamicQuery(DynamicQueryInitializer queryInitializer,
		int begin, int end) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);
			query.setLimit(begin, end);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List findAll(int begin, int end) throws SystemException {
		return findAll(begin, end, null);
	}

	public List findAll(int begin, int end, OrderByComparator obc)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portlet.imagegallery.model.IGFolder ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}
			else {
				query.append("ORDER BY ");
				query.append("folderId ASC").append(", ");
				query.append("name ASC");
			}

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			return QueryUtil.list(q, getDialect(), begin, end);
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public void removeByGroupId(long groupId) throws SystemException {
		Iterator itr = findByGroupId(groupId).iterator();

		while (itr.hasNext()) {
			IGFolder igFolder = (IGFolder)itr.next();
			remove(igFolder);
		}
	}

	public void removeByG_P(long groupId, String parentFolderId)
		throws SystemException {
		Iterator itr = findByG_P(groupId, parentFolderId).iterator();

		while (itr.hasNext()) {
			IGFolder igFolder = (IGFolder)itr.next();
			remove(igFolder);
		}
	}

	public void removeAll() throws SystemException {
		Iterator itr = findAll().iterator();

		while (itr.hasNext()) {
			remove((IGFolder)itr.next());
		}
	}

	public int countByGroupId(long groupId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append("SELECT COUNT(*) ");
			query.append(
				"FROM com.liferay.portlet.imagegallery.model.IGFolder WHERE ");
			query.append("groupId = ?");
			query.append(" ");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, groupId);

			Iterator itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = (Long)itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countByG_P(long groupId, String parentFolderId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append("SELECT COUNT(*) ");
			query.append(
				"FROM com.liferay.portlet.imagegallery.model.IGFolder WHERE ");
			query.append("groupId = ?");
			query.append(" AND ");

			if (parentFolderId == null) {
				query.append("parentFolderId IS NULL");
			}
			else {
				query.append("parentFolderId = ?");
			}

			query.append(" ");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, groupId);

			if (parentFolderId != null) {
				q.setString(queryPos++, parentFolderId);
			}

			Iterator itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = (Long)itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countAll() throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append("SELECT COUNT(*) ");
			query.append("FROM com.liferay.portlet.imagegallery.model.IGFolder");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			Iterator itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = (Long)itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected void initDao() {
	}

	private static Log _log = LogFactory.getLog(IGFolderPersistence.class);
}