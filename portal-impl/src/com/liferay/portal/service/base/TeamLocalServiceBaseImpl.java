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

package com.liferay.portal.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.TeamLocalService;
import com.liferay.portal.kernel.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.TeamFinder;
import com.liferay.portal.kernel.service.persistence.TeamPersistence;
import com.liferay.portal.kernel.service.persistence.UserFinder;
import com.liferay.portal.kernel.service.persistence.UserGroupFinder;
import com.liferay.portal.kernel.service.persistence.UserGroupPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the team local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.TeamLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.TeamLocalServiceImpl
 * @generated
 */
public abstract class TeamLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, TeamLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>TeamLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>TeamLocalServiceUtil</code>.
	 */

	/**
	 * Adds the team to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param team the team
	 * @return the team that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Team addTeam(Team team) {
		team.setNew(true);

		return teamPersistence.update(team);
	}

	/**
	 * Creates a new team with the primary key. Does not add the team to the database.
	 *
	 * @param teamId the primary key for the new team
	 * @return the new team
	 */
	@Override
	@Transactional(enabled = false)
	public Team createTeam(long teamId) {
		return teamPersistence.create(teamId);
	}

	/**
	 * Deletes the team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param teamId the primary key of the team
	 * @return the team that was removed
	 * @throws PortalException if a team with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Team deleteTeam(long teamId) throws PortalException {
		return teamPersistence.remove(teamId);
	}

	/**
	 * Deletes the team from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param team the team
	 * @return the team that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Team deleteTeam(Team team) throws PortalException {
		return teamPersistence.remove(team);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return teamPersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			Team.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return teamPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.TeamModelImpl</code>.
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

		return teamPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.TeamModelImpl</code>.
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

		return teamPersistence.findWithDynamicQuery(
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
		return teamPersistence.countWithDynamicQuery(dynamicQuery);
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

		return teamPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Team fetchTeam(long teamId) {
		return teamPersistence.fetchByPrimaryKey(teamId);
	}

	/**
	 * Returns the team matching the UUID and group.
	 *
	 * @param uuid the team's UUID
	 * @param groupId the primary key of the group
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	@Override
	public Team fetchTeamByUuidAndGroupId(String uuid, long groupId) {
		return teamPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the team with the primary key.
	 *
	 * @param teamId the primary key of the team
	 * @return the team
	 * @throws PortalException if a team with the primary key could not be found
	 */
	@Override
	public Team getTeam(long teamId) throws PortalException {
		return teamPersistence.findByPrimaryKey(teamId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(teamLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Team.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("teamId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(teamLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Team.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("teamId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(teamLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Team.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("teamId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Team>() {

				@Override
				public void performAction(Team team) throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, team);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(Team.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return teamPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return teamLocalService.deleteTeam((Team)persistedModel);
	}

	@Override
	public BasePersistence<Team> getBasePersistence() {
		return teamPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return teamPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the teams matching the UUID and company.
	 *
	 * @param uuid the UUID of the teams
	 * @param companyId the primary key of the company
	 * @return the matching teams, or an empty list if no matches were found
	 */
	@Override
	public List<Team> getTeamsByUuidAndCompanyId(String uuid, long companyId) {
		return teamPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of teams matching the UUID and company.
	 *
	 * @param uuid the UUID of the teams
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching teams, or an empty list if no matches were found
	 */
	@Override
	public List<Team> getTeamsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Team> orderByComparator) {

		return teamPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the team matching the UUID and group.
	 *
	 * @param uuid the team's UUID
	 * @param groupId the primary key of the group
	 * @return the matching team
	 * @throws PortalException if a matching team could not be found
	 */
	@Override
	public Team getTeamByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return teamPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.TeamModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of teams
	 */
	@Override
	public List<Team> getTeams(int start, int end) {
		return teamPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of teams.
	 *
	 * @return the number of teams
	 */
	@Override
	public int getTeamsCount() {
		return teamPersistence.countAll();
	}

	/**
	 * Updates the team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TeamLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param team the team
	 * @return the team that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Team updateTeam(Team team) {
		return teamPersistence.update(team);
	}

	/**
	 */
	@Override
	public void addUserTeam(long userId, long teamId) {
		userPersistence.addTeam(userId, teamId);
	}

	/**
	 */
	@Override
	public void addUserTeam(long userId, Team team) {
		userPersistence.addTeam(userId, team);
	}

	/**
	 */
	@Override
	public void addUserTeams(long userId, long[] teamIds) {
		userPersistence.addTeams(userId, teamIds);
	}

	/**
	 */
	@Override
	public void addUserTeams(long userId, List<Team> teams) {
		userPersistence.addTeams(userId, teams);
	}

	/**
	 */
	@Override
	public void clearUserTeams(long userId) {
		userPersistence.clearTeams(userId);
	}

	/**
	 */
	@Override
	public void deleteUserTeam(long userId, long teamId) {
		userPersistence.removeTeam(userId, teamId);
	}

	/**
	 */
	@Override
	public void deleteUserTeam(long userId, Team team) {
		userPersistence.removeTeam(userId, team);
	}

	/**
	 */
	@Override
	public void deleteUserTeams(long userId, long[] teamIds) {
		userPersistence.removeTeams(userId, teamIds);
	}

	/**
	 */
	@Override
	public void deleteUserTeams(long userId, List<Team> teams) {
		userPersistence.removeTeams(userId, teams);
	}

	/**
	 * Returns the userIds of the users associated with the team.
	 *
	 * @param teamId the teamId of the team
	 * @return long[] the userIds of users associated with the team
	 */
	@Override
	public long[] getUserPrimaryKeys(long teamId) {
		return teamPersistence.getUserPrimaryKeys(teamId);
	}

	/**
	 */
	@Override
	public List<Team> getUserTeams(long userId) {
		return userPersistence.getTeams(userId);
	}

	/**
	 */
	@Override
	public List<Team> getUserTeams(long userId, int start, int end) {
		return userPersistence.getTeams(userId, start, end);
	}

	/**
	 */
	@Override
	public List<Team> getUserTeams(
		long userId, int start, int end,
		OrderByComparator<Team> orderByComparator) {

		return userPersistence.getTeams(userId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getUserTeamsCount(long userId) {
		return userPersistence.getTeamsSize(userId);
	}

	/**
	 */
	@Override
	public boolean hasUserTeam(long userId, long teamId) {
		return userPersistence.containsTeam(userId, teamId);
	}

	/**
	 */
	@Override
	public boolean hasUserTeams(long userId) {
		return userPersistence.containsTeams(userId);
	}

	/**
	 */
	@Override
	public void setUserTeams(long userId, long[] teamIds) {
		userPersistence.setTeams(userId, teamIds);
	}

	/**
	 */
	@Override
	public void addUserGroupTeam(long userGroupId, long teamId) {
		userGroupPersistence.addTeam(userGroupId, teamId);
	}

	/**
	 */
	@Override
	public void addUserGroupTeam(long userGroupId, Team team) {
		userGroupPersistence.addTeam(userGroupId, team);
	}

	/**
	 */
	@Override
	public void addUserGroupTeams(long userGroupId, long[] teamIds) {
		userGroupPersistence.addTeams(userGroupId, teamIds);
	}

	/**
	 */
	@Override
	public void addUserGroupTeams(long userGroupId, List<Team> teams) {
		userGroupPersistence.addTeams(userGroupId, teams);
	}

	/**
	 */
	@Override
	public void clearUserGroupTeams(long userGroupId) {
		userGroupPersistence.clearTeams(userGroupId);
	}

	/**
	 */
	@Override
	public void deleteUserGroupTeam(long userGroupId, long teamId) {
		userGroupPersistence.removeTeam(userGroupId, teamId);
	}

	/**
	 */
	@Override
	public void deleteUserGroupTeam(long userGroupId, Team team) {
		userGroupPersistence.removeTeam(userGroupId, team);
	}

	/**
	 */
	@Override
	public void deleteUserGroupTeams(long userGroupId, long[] teamIds) {
		userGroupPersistence.removeTeams(userGroupId, teamIds);
	}

	/**
	 */
	@Override
	public void deleteUserGroupTeams(long userGroupId, List<Team> teams) {
		userGroupPersistence.removeTeams(userGroupId, teams);
	}

	/**
	 * Returns the userGroupIds of the user groups associated with the team.
	 *
	 * @param teamId the teamId of the team
	 * @return long[] the userGroupIds of user groups associated with the team
	 */
	@Override
	public long[] getUserGroupPrimaryKeys(long teamId) {
		return teamPersistence.getUserGroupPrimaryKeys(teamId);
	}

	/**
	 */
	@Override
	public List<Team> getUserGroupTeams(long userGroupId) {
		return userGroupPersistence.getTeams(userGroupId);
	}

	/**
	 */
	@Override
	public List<Team> getUserGroupTeams(long userGroupId, int start, int end) {
		return userGroupPersistence.getTeams(userGroupId, start, end);
	}

	/**
	 */
	@Override
	public List<Team> getUserGroupTeams(
		long userGroupId, int start, int end,
		OrderByComparator<Team> orderByComparator) {

		return userGroupPersistence.getTeams(
			userGroupId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getUserGroupTeamsCount(long userGroupId) {
		return userGroupPersistence.getTeamsSize(userGroupId);
	}

	/**
	 */
	@Override
	public boolean hasUserGroupTeam(long userGroupId, long teamId) {
		return userGroupPersistence.containsTeam(userGroupId, teamId);
	}

	/**
	 */
	@Override
	public boolean hasUserGroupTeams(long userGroupId) {
		return userGroupPersistence.containsTeams(userGroupId);
	}

	/**
	 */
	@Override
	public void setUserGroupTeams(long userGroupId, long[] teamIds) {
		userGroupPersistence.setTeams(userGroupId, teamIds);
	}

	/**
	 * Returns the team local service.
	 *
	 * @return the team local service
	 */
	public TeamLocalService getTeamLocalService() {
		return teamLocalService;
	}

	/**
	 * Sets the team local service.
	 *
	 * @param teamLocalService the team local service
	 */
	public void setTeamLocalService(TeamLocalService teamLocalService) {
		this.teamLocalService = teamLocalService;
	}

	/**
	 * Returns the team persistence.
	 *
	 * @return the team persistence
	 */
	public TeamPersistence getTeamPersistence() {
		return teamPersistence;
	}

	/**
	 * Sets the team persistence.
	 *
	 * @param teamPersistence the team persistence
	 */
	public void setTeamPersistence(TeamPersistence teamPersistence) {
		this.teamPersistence = teamPersistence;
	}

	/**
	 * Returns the team finder.
	 *
	 * @return the team finder
	 */
	public TeamFinder getTeamFinder() {
		return teamFinder;
	}

	/**
	 * Sets the team finder.
	 *
	 * @param teamFinder the team finder
	 */
	public void setTeamFinder(TeamFinder teamFinder) {
		this.teamFinder = teamFinder;
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

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Returns the user group local service.
	 *
	 * @return the user group local service
	 */
	public com.liferay.portal.kernel.service.UserGroupLocalService
		getUserGroupLocalService() {

		return userGroupLocalService;
	}

	/**
	 * Sets the user group local service.
	 *
	 * @param userGroupLocalService the user group local service
	 */
	public void setUserGroupLocalService(
		com.liferay.portal.kernel.service.UserGroupLocalService
			userGroupLocalService) {

		this.userGroupLocalService = userGroupLocalService;
	}

	/**
	 * Returns the user group persistence.
	 *
	 * @return the user group persistence
	 */
	public UserGroupPersistence getUserGroupPersistence() {
		return userGroupPersistence;
	}

	/**
	 * Sets the user group persistence.
	 *
	 * @param userGroupPersistence the user group persistence
	 */
	public void setUserGroupPersistence(
		UserGroupPersistence userGroupPersistence) {

		this.userGroupPersistence = userGroupPersistence;
	}

	/**
	 * Returns the user group finder.
	 *
	 * @return the user group finder
	 */
	public UserGroupFinder getUserGroupFinder() {
		return userGroupFinder;
	}

	/**
	 * Sets the user group finder.
	 *
	 * @param userGroupFinder the user group finder
	 */
	public void setUserGroupFinder(UserGroupFinder userGroupFinder) {
		this.userGroupFinder = userGroupFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.kernel.model.Team", teamLocalService);

		_setLocalServiceUtilService(teamLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.kernel.model.Team");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return TeamLocalService.class.getName();
	}

	@Override
	public CTPersistence<Team> getCTPersistence() {
		return teamPersistence;
	}

	@Override
	public Class<Team> getModelClass() {
		return Team.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<Team>, R, E> updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(teamPersistence);
	}

	protected String getModelClassName() {
		return Team.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = teamPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setLocalServiceUtilService(
		TeamLocalService teamLocalService) {

		try {
			Field field = TeamLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, teamLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = TeamLocalService.class)
	protected TeamLocalService teamLocalService;

	@BeanReference(type = TeamPersistence.class)
	protected TeamPersistence teamPersistence;

	@BeanReference(type = TeamFinder.class)
	protected TeamFinder teamFinder;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;

	@BeanReference(
		type = com.liferay.portal.kernel.service.UserGroupLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserGroupLocalService
		userGroupLocalService;

	@BeanReference(type = UserGroupPersistence.class)
	protected UserGroupPersistence userGroupPersistence;

	@BeanReference(type = UserGroupFinder.class)
	protected UserGroupFinder userGroupFinder;

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}