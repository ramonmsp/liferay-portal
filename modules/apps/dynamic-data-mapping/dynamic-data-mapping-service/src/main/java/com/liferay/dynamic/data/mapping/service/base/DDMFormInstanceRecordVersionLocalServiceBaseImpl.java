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

package com.liferay.dynamic.data.mapping.service.base;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordVersionLocalService;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceRecordVersionPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the ddm form instance record version local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.mapping.service.impl.DDMFormInstanceRecordVersionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMFormInstanceRecordVersionLocalServiceImpl
 * @generated
 */
public abstract class DDMFormInstanceRecordVersionLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements DDMFormInstanceRecordVersionLocalService,
			   IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DDMFormInstanceRecordVersionLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordVersionLocalServiceUtil</code>.
	 */

	/**
	 * Adds the ddm form instance record version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceRecordVersion the ddm form instance record version
	 * @return the ddm form instance record version that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMFormInstanceRecordVersion addDDMFormInstanceRecordVersion(
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion) {

		ddmFormInstanceRecordVersion.setNew(true);

		return ddmFormInstanceRecordVersionPersistence.update(
			ddmFormInstanceRecordVersion);
	}

	/**
	 * Creates a new ddm form instance record version with the primary key. Does not add the ddm form instance record version to the database.
	 *
	 * @param formInstanceRecordVersionId the primary key for the new ddm form instance record version
	 * @return the new ddm form instance record version
	 */
	@Override
	@Transactional(enabled = false)
	public DDMFormInstanceRecordVersion createDDMFormInstanceRecordVersion(
		long formInstanceRecordVersionId) {

		return ddmFormInstanceRecordVersionPersistence.create(
			formInstanceRecordVersionId);
	}

	/**
	 * Deletes the ddm form instance record version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formInstanceRecordVersionId the primary key of the ddm form instance record version
	 * @return the ddm form instance record version that was removed
	 * @throws PortalException if a ddm form instance record version with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMFormInstanceRecordVersion deleteDDMFormInstanceRecordVersion(
			long formInstanceRecordVersionId)
		throws PortalException {

		return ddmFormInstanceRecordVersionPersistence.remove(
			formInstanceRecordVersionId);
	}

	/**
	 * Deletes the ddm form instance record version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceRecordVersion the ddm form instance record version
	 * @return the ddm form instance record version that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMFormInstanceRecordVersion deleteDDMFormInstanceRecordVersion(
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion) {

		return ddmFormInstanceRecordVersionPersistence.remove(
			ddmFormInstanceRecordVersion);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			DDMFormInstanceRecordVersion.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ddmFormInstanceRecordVersionPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return ddmFormInstanceRecordVersionPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return ddmFormInstanceRecordVersionPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return ddmFormInstanceRecordVersionPersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return ddmFormInstanceRecordVersionPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public DDMFormInstanceRecordVersion fetchDDMFormInstanceRecordVersion(
		long formInstanceRecordVersionId) {

		return ddmFormInstanceRecordVersionPersistence.fetchByPrimaryKey(
			formInstanceRecordVersionId);
	}

	/**
	 * Returns the ddm form instance record version with the primary key.
	 *
	 * @param formInstanceRecordVersionId the primary key of the ddm form instance record version
	 * @return the ddm form instance record version
	 * @throws PortalException if a ddm form instance record version with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceRecordVersion getDDMFormInstanceRecordVersion(
			long formInstanceRecordVersionId)
		throws PortalException {

		return ddmFormInstanceRecordVersionPersistence.findByPrimaryKey(
			formInstanceRecordVersionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			ddmFormInstanceRecordVersionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			DDMFormInstanceRecordVersion.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"formInstanceRecordVersionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			ddmFormInstanceRecordVersionLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			DDMFormInstanceRecordVersion.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"formInstanceRecordVersionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			ddmFormInstanceRecordVersionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			DDMFormInstanceRecordVersion.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"formInstanceRecordVersionId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return ddmFormInstanceRecordVersionLocalService.
			deleteDDMFormInstanceRecordVersion(
				(DDMFormInstanceRecordVersion)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ddmFormInstanceRecordVersionPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns a range of all the ddm form instance record versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceRecordVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance record versions
	 * @param end the upper bound of the range of ddm form instance record versions (not inclusive)
	 * @return the range of ddm form instance record versions
	 */
	@Override
	public List<DDMFormInstanceRecordVersion> getDDMFormInstanceRecordVersions(
		int start, int end) {

		return ddmFormInstanceRecordVersionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of ddm form instance record versions.
	 *
	 * @return the number of ddm form instance record versions
	 */
	@Override
	public int getDDMFormInstanceRecordVersionsCount() {
		return ddmFormInstanceRecordVersionPersistence.countAll();
	}

	/**
	 * Updates the ddm form instance record version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceRecordVersion the ddm form instance record version
	 * @return the ddm form instance record version that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMFormInstanceRecordVersion updateDDMFormInstanceRecordVersion(
		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion) {

		return ddmFormInstanceRecordVersionPersistence.update(
			ddmFormInstanceRecordVersion);
	}

	/**
	 * Returns the ddm form instance record version local service.
	 *
	 * @return the ddm form instance record version local service
	 */
	public DDMFormInstanceRecordVersionLocalService
		getDDMFormInstanceRecordVersionLocalService() {

		return ddmFormInstanceRecordVersionLocalService;
	}

	/**
	 * Sets the ddm form instance record version local service.
	 *
	 * @param ddmFormInstanceRecordVersionLocalService the ddm form instance record version local service
	 */
	public void setDDMFormInstanceRecordVersionLocalService(
		DDMFormInstanceRecordVersionLocalService
			ddmFormInstanceRecordVersionLocalService) {

		this.ddmFormInstanceRecordVersionLocalService =
			ddmFormInstanceRecordVersionLocalService;
	}

	/**
	 * Returns the ddm form instance record version persistence.
	 *
	 * @return the ddm form instance record version persistence
	 */
	public DDMFormInstanceRecordVersionPersistence
		getDDMFormInstanceRecordVersionPersistence() {

		return ddmFormInstanceRecordVersionPersistence;
	}

	/**
	 * Sets the ddm form instance record version persistence.
	 *
	 * @param ddmFormInstanceRecordVersionPersistence the ddm form instance record version persistence
	 */
	public void setDDMFormInstanceRecordVersionPersistence(
		DDMFormInstanceRecordVersionPersistence
			ddmFormInstanceRecordVersionPersistence) {

		this.ddmFormInstanceRecordVersionPersistence =
			ddmFormInstanceRecordVersionPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion",
			ddmFormInstanceRecordVersionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DDMFormInstanceRecordVersionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DDMFormInstanceRecordVersion.class;
	}

	protected String getModelClassName() {
		return DDMFormInstanceRecordVersion.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				ddmFormInstanceRecordVersionPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = DDMFormInstanceRecordVersionLocalService.class)
	protected DDMFormInstanceRecordVersionLocalService
		ddmFormInstanceRecordVersionLocalService;

	@BeanReference(type = DDMFormInstanceRecordVersionPersistence.class)
	protected DDMFormInstanceRecordVersionPersistence
		ddmFormInstanceRecordVersionPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}