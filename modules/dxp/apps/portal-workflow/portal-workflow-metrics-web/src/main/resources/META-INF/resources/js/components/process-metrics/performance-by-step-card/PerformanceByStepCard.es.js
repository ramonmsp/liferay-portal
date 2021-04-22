/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import ClayLayout from '@clayui/layout';
import ClayPanel from '@clayui/panel';
import React, {useMemo} from 'react';

import PanelHeaderWithOptions from '../../../shared/components/panel-header-with-options/PanelHeaderWithOptions.es';
import PromisesResolver from '../../../shared/components/promises-resolver/PromisesResolver.es';
import {useFetch} from '../../../shared/hooks/useFetch.es';
import {useFilter} from '../../../shared/hooks/useFilter.es';
import TimeRangeFilter from '../../filter/TimeRangeFilter.es';
import {getTimeRangeParams} from '../../filter/util/timeRangeUtil.es';
import {Body, Footer} from './PerformanceByStepCardBody.es';

function Header({disableFilters, prefixKey, totalCount}) {
	return (
		<PanelHeaderWithOptions
			className="dashboard-panel-header"
			description={Liferay.Language.get(
				'performance-by-step-description'
			)}
			title={Liferay.Language.get('performance-by-step')}
		>
			<ClayLayout.ContentCol className="m-0 management-bar management-bar-light navbar">
				<ul className="navbar-nav">
					<TimeRangeFilter
						disabled={!totalCount || disableFilters}
						options={{position: 'right'}}
						prefixKey={prefixKey}
					/>
				</ul>
			</ClayLayout.ContentCol>
		</PanelHeaderWithOptions>
	);
}

function PerformanceByStepCard({routeParams}) {
	const {processId} = routeParams;
	const filterKeys = ['timeRange'];
	const prefixKey = 'step';
	const prefixKeys = [prefixKey];

	const {
		filterValues: {stepDateEnd, stepDateStart, stepTimeRange: [key] = []},
		filtersError,
	} = useFilter({
		filterKeys,
		prefixKeys,
	});

	const timeRange = useMemo(
		() => getTimeRangeParams(stepDateStart, stepDateEnd),
		[stepDateEnd, stepDateStart]
	);

	const {data, fetchData} = useFetch({
		params: {
			completed: true,
			page: 1,
			pageSize: 10,
			sort: 'durationAvg:desc',
			...timeRange,
		},
		url: `/processes/${processId}/nodes/metrics`,
	});

	const promises = useMemo(() => {
		if (timeRange.dateEnd && timeRange.dateStart) {
			return [fetchData()];
		}

		return [new Promise((_, reject) => reject(filtersError))];
	}, [fetchData, filtersError, timeRange.dateEnd, timeRange.dateStart]);

	return (
		<ClayPanel className="dashboard-card mt-4">
			<PromisesResolver promises={promises}>
				<PerformanceByStepCard.Header
					disableFilters={filtersError}
					prefixKey={prefixKey}
					totalCount={data.totalCount}
				/>

				<PerformanceByStepCard.Body {...data} />

				{data.totalCount > 0 && (
					<PerformanceByStepCard.Footer
						processId={processId}
						timeRange={{key, ...timeRange}}
						totalCount={data.totalCount}
					/>
				)}
			</PromisesResolver>
		</ClayPanel>
	);
}

PerformanceByStepCard.Body = Body;
PerformanceByStepCard.Footer = Footer;
PerformanceByStepCard.Header = Header;

export default PerformanceByStepCard;
