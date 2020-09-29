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

package com.liferay.dispatch.service.persistence;

import com.liferay.dispatch.exception.NoSuchTriggerException;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the dispatch trigger service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see DispatchTriggerUtil
 * @generated
 */
@ProviderType
public interface DispatchTriggerPersistence
	extends BasePersistence<DispatchTrigger> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DispatchTriggerUtil} to access the dispatch trigger persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the dispatch triggers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findByCompanyId(long companyId);

	/**
	 * Returns a range of all the dispatch triggers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @return the range of matching dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the dispatch triggers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator);

	/**
	 * Returns an ordered range of all the dispatch triggers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first dispatch trigger in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dispatch trigger
	 * @throws NoSuchTriggerException if a matching dispatch trigger could not be found
	 */
	public DispatchTrigger findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
				orderByComparator)
		throws NoSuchTriggerException;

	/**
	 * Returns the first dispatch trigger in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dispatch trigger, or <code>null</code> if a matching dispatch trigger could not be found
	 */
	public DispatchTrigger fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator);

	/**
	 * Returns the last dispatch trigger in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dispatch trigger
	 * @throws NoSuchTriggerException if a matching dispatch trigger could not be found
	 */
	public DispatchTrigger findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
				orderByComparator)
		throws NoSuchTriggerException;

	/**
	 * Returns the last dispatch trigger in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dispatch trigger, or <code>null</code> if a matching dispatch trigger could not be found
	 */
	public DispatchTrigger fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator);

	/**
	 * Returns the dispatch triggers before and after the current dispatch trigger in the ordered set where companyId = &#63;.
	 *
	 * @param dispatchTriggerId the primary key of the current dispatch trigger
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dispatch trigger
	 * @throws NoSuchTriggerException if a dispatch trigger with the primary key could not be found
	 */
	public DispatchTrigger[] findByCompanyId_PrevAndNext(
			long dispatchTriggerId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
				orderByComparator)
		throws NoSuchTriggerException;

	/**
	 * Returns all the dispatch triggers that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching dispatch triggers that the user has permission to view
	 */
	public java.util.List<DispatchTrigger> filterFindByCompanyId(
		long companyId);

	/**
	 * Returns a range of all the dispatch triggers that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @return the range of matching dispatch triggers that the user has permission to view
	 */
	public java.util.List<DispatchTrigger> filterFindByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the dispatch triggers that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dispatch triggers that the user has permission to view
	 */
	public java.util.List<DispatchTrigger> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator);

	/**
	 * Returns the dispatch triggers before and after the current dispatch trigger in the ordered set of dispatch triggers that the user has permission to view where companyId = &#63;.
	 *
	 * @param dispatchTriggerId the primary key of the current dispatch trigger
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dispatch trigger
	 * @throws NoSuchTriggerException if a dispatch trigger with the primary key could not be found
	 */
	public DispatchTrigger[] filterFindByCompanyId_PrevAndNext(
			long dispatchTriggerId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
				orderByComparator)
		throws NoSuchTriggerException;

	/**
	 * Removes all the dispatch triggers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of dispatch triggers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching dispatch triggers
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns the number of dispatch triggers that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching dispatch triggers that the user has permission to view
	 */
	public int filterCountByCompanyId(long companyId);

	/**
	 * Returns the dispatch trigger where companyId = &#63; and name = &#63; or throws a <code>NoSuchTriggerException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching dispatch trigger
	 * @throws NoSuchTriggerException if a matching dispatch trigger could not be found
	 */
	public DispatchTrigger findByC_N(long companyId, String name)
		throws NoSuchTriggerException;

	/**
	 * Returns the dispatch trigger where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching dispatch trigger, or <code>null</code> if a matching dispatch trigger could not be found
	 */
	public DispatchTrigger fetchByC_N(long companyId, String name);

	/**
	 * Returns the dispatch trigger where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching dispatch trigger, or <code>null</code> if a matching dispatch trigger could not be found
	 */
	public DispatchTrigger fetchByC_N(
		long companyId, String name, boolean useFinderCache);

	/**
	 * Removes the dispatch trigger where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the dispatch trigger that was removed
	 */
	public DispatchTrigger removeByC_N(long companyId, String name)
		throws NoSuchTriggerException;

	/**
	 * Returns the number of dispatch triggers where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching dispatch triggers
	 */
	public int countByC_N(long companyId, String name);

	/**
	 * Returns all the dispatch triggers where companyId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @return the matching dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findByC_T(
		long companyId, String taskType);

	/**
	 * Returns a range of all the dispatch triggers where companyId = &#63; and taskType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @return the range of matching dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findByC_T(
		long companyId, String taskType, int start, int end);

	/**
	 * Returns an ordered range of all the dispatch triggers where companyId = &#63; and taskType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findByC_T(
		long companyId, String taskType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator);

	/**
	 * Returns an ordered range of all the dispatch triggers where companyId = &#63; and taskType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findByC_T(
		long companyId, String taskType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first dispatch trigger in the ordered set where companyId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dispatch trigger
	 * @throws NoSuchTriggerException if a matching dispatch trigger could not be found
	 */
	public DispatchTrigger findByC_T_First(
			long companyId, String taskType,
			com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
				orderByComparator)
		throws NoSuchTriggerException;

	/**
	 * Returns the first dispatch trigger in the ordered set where companyId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dispatch trigger, or <code>null</code> if a matching dispatch trigger could not be found
	 */
	public DispatchTrigger fetchByC_T_First(
		long companyId, String taskType,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator);

	/**
	 * Returns the last dispatch trigger in the ordered set where companyId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dispatch trigger
	 * @throws NoSuchTriggerException if a matching dispatch trigger could not be found
	 */
	public DispatchTrigger findByC_T_Last(
			long companyId, String taskType,
			com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
				orderByComparator)
		throws NoSuchTriggerException;

	/**
	 * Returns the last dispatch trigger in the ordered set where companyId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dispatch trigger, or <code>null</code> if a matching dispatch trigger could not be found
	 */
	public DispatchTrigger fetchByC_T_Last(
		long companyId, String taskType,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator);

	/**
	 * Returns the dispatch triggers before and after the current dispatch trigger in the ordered set where companyId = &#63; and taskType = &#63;.
	 *
	 * @param dispatchTriggerId the primary key of the current dispatch trigger
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dispatch trigger
	 * @throws NoSuchTriggerException if a dispatch trigger with the primary key could not be found
	 */
	public DispatchTrigger[] findByC_T_PrevAndNext(
			long dispatchTriggerId, long companyId, String taskType,
			com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
				orderByComparator)
		throws NoSuchTriggerException;

	/**
	 * Returns all the dispatch triggers that the user has permission to view where companyId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @return the matching dispatch triggers that the user has permission to view
	 */
	public java.util.List<DispatchTrigger> filterFindByC_T(
		long companyId, String taskType);

	/**
	 * Returns a range of all the dispatch triggers that the user has permission to view where companyId = &#63; and taskType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @return the range of matching dispatch triggers that the user has permission to view
	 */
	public java.util.List<DispatchTrigger> filterFindByC_T(
		long companyId, String taskType, int start, int end);

	/**
	 * Returns an ordered range of all the dispatch triggers that the user has permissions to view where companyId = &#63; and taskType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dispatch triggers that the user has permission to view
	 */
	public java.util.List<DispatchTrigger> filterFindByC_T(
		long companyId, String taskType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator);

	/**
	 * Returns the dispatch triggers before and after the current dispatch trigger in the ordered set of dispatch triggers that the user has permission to view where companyId = &#63; and taskType = &#63;.
	 *
	 * @param dispatchTriggerId the primary key of the current dispatch trigger
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dispatch trigger
	 * @throws NoSuchTriggerException if a dispatch trigger with the primary key could not be found
	 */
	public DispatchTrigger[] filterFindByC_T_PrevAndNext(
			long dispatchTriggerId, long companyId, String taskType,
			com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
				orderByComparator)
		throws NoSuchTriggerException;

	/**
	 * Removes all the dispatch triggers where companyId = &#63; and taskType = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 */
	public void removeByC_T(long companyId, String taskType);

	/**
	 * Returns the number of dispatch triggers where companyId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @return the number of matching dispatch triggers
	 */
	public int countByC_T(long companyId, String taskType);

	/**
	 * Returns the number of dispatch triggers that the user has permission to view where companyId = &#63; and taskType = &#63;.
	 *
	 * @param companyId the company ID
	 * @param taskType the task type
	 * @return the number of matching dispatch triggers that the user has permission to view
	 */
	public int filterCountByC_T(long companyId, String taskType);

	/**
	 * Caches the dispatch trigger in the entity cache if it is enabled.
	 *
	 * @param dispatchTrigger the dispatch trigger
	 */
	public void cacheResult(DispatchTrigger dispatchTrigger);

	/**
	 * Caches the dispatch triggers in the entity cache if it is enabled.
	 *
	 * @param dispatchTriggers the dispatch triggers
	 */
	public void cacheResult(java.util.List<DispatchTrigger> dispatchTriggers);

	/**
	 * Creates a new dispatch trigger with the primary key. Does not add the dispatch trigger to the database.
	 *
	 * @param dispatchTriggerId the primary key for the new dispatch trigger
	 * @return the new dispatch trigger
	 */
	public DispatchTrigger create(long dispatchTriggerId);

	/**
	 * Removes the dispatch trigger with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dispatchTriggerId the primary key of the dispatch trigger
	 * @return the dispatch trigger that was removed
	 * @throws NoSuchTriggerException if a dispatch trigger with the primary key could not be found
	 */
	public DispatchTrigger remove(long dispatchTriggerId)
		throws NoSuchTriggerException;

	public DispatchTrigger updateImpl(DispatchTrigger dispatchTrigger);

	/**
	 * Returns the dispatch trigger with the primary key or throws a <code>NoSuchTriggerException</code> if it could not be found.
	 *
	 * @param dispatchTriggerId the primary key of the dispatch trigger
	 * @return the dispatch trigger
	 * @throws NoSuchTriggerException if a dispatch trigger with the primary key could not be found
	 */
	public DispatchTrigger findByPrimaryKey(long dispatchTriggerId)
		throws NoSuchTriggerException;

	/**
	 * Returns the dispatch trigger with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dispatchTriggerId the primary key of the dispatch trigger
	 * @return the dispatch trigger, or <code>null</code> if a dispatch trigger with the primary key could not be found
	 */
	public DispatchTrigger fetchByPrimaryKey(long dispatchTriggerId);

	/**
	 * Returns all the dispatch triggers.
	 *
	 * @return the dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findAll();

	/**
	 * Returns a range of all the dispatch triggers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @return the range of dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the dispatch triggers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator);

	/**
	 * Returns an ordered range of all the dispatch triggers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of dispatch triggers
	 */
	public java.util.List<DispatchTrigger> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DispatchTrigger>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the dispatch triggers from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of dispatch triggers.
	 *
	 * @return the number of dispatch triggers
	 */
	public int countAll();

}