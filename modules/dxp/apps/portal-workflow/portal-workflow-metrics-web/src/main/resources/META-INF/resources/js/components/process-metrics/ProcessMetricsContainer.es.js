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
import React, {useContext} from 'react';
import {Route, Switch} from 'react-router-dom';

import HeaderKebab from '../../shared/components/header/HeaderKebab.es';
import NavbarTabs from '../../shared/components/navbar-tabs/NavbarTabs.es';
import {parse, stringify} from '../../shared/components/router/queryString.es';
import {
	getPathname,
	withParams,
} from '../../shared/components/router/routerUtil.es';
import {useProcessTitle} from '../../shared/hooks/useProcessTitle.es';
import {AppContext} from '../AppContext.es';
import {useTimeRangeFetch} from '../filter/hooks/useTimeRangeFetch.es';
import SLAInfo from './SLAInfo.es';
import CompletionVelocityCard from './completion-velocity/CompletionVelocityCard.es';
import PerformanceByAssigneeCard from './performance-by-assignee-card/PerformanceByAssigneeCard.es';
import PerformanceByStepCard from './performance-by-step-card/PerformanceByStepCard.es';
import CompletedItemsCard from './process-items/CompletedItemsCard.es';
import PendingItemsCard from './process-items/PendingItemsCard.es';
import WorkloadByAssigneeCard from './workload-by-assignee-card/WorkloadByAssigneeCard.es';
import WorkloadByStepCard from './workload-by-step-card/WorkloadByStepCard.es';

function DashboardTab(props) {
	return (
		<ClayLayout.ContainerFluid>
			<ClayLayout.Row>
				<ClayLayout.Col className="p-0" md="9">
					<ClayLayout.ContainerFluid>
						<PendingItemsCard {...props} />

						<WorkloadByStepCard {...props} />
					</ClayLayout.ContainerFluid>
				</ClayLayout.Col>

				<ClayLayout.Col className="p-0" md="3">
					<ClayLayout.ContainerFluid>
						<WorkloadByAssigneeCard {...props} />
					</ClayLayout.ContainerFluid>
				</ClayLayout.Col>
			</ClayLayout.Row>
		</ClayLayout.ContainerFluid>
	);
}

function PerformanceTab(props) {
	useTimeRangeFetch();

	return (
		<ClayLayout.ContainerFluid>
			<CompletedItemsCard {...props} />
			<CompletionVelocityCard {...props} />
			<PerformanceByStepCard {...props} />
			<PerformanceByAssigneeCard {...props} />
		</ClayLayout.ContainerFluid>
	);
}

export default function ProcessMetricsContainer({history, processId, query}) {
	const {defaultDelta} = useContext(AppContext);

	useProcessTitle(processId);

	const tabs = {
		dashboard: {
			key: 'dashboard',
			name: Liferay.Language.get('dashboard'),
			params: {
				page: 1,
				pageSize: defaultDelta,
				processId,
				sort: 'overdueInstanceCount:asc',
			},
			path: '/metrics/:processId/dashboard/:pageSize/:page/:sort',
		},
		performance: {
			key: 'performance',
			name: Liferay.Language.get('performance'),
			params: {processId},
			path: '/metrics/:processId/performance',
		},
	};

	if (history.location.pathname === `/metrics/${processId}`) {
		const pathname = getPathname(
			tabs.dashboard.params,
			tabs.dashboard.path
		);

		const search = stringify({
			...parse(query),
			filters: {taskNames: ['allSteps']},
		});

		history.replace({pathname, search});
	}

	return (
		<div className="workflow-process-dashboard">
			<HeaderKebab
				kebabItems={[
					{
						label: Liferay.Language.get('sla-settings'),
						link: `/sla/${processId}/list/${defaultDelta}/1`,
					},
				]}
			/>

			<NavbarTabs tabs={Object.values(tabs)} />

			<SLAInfo processId={processId} />

			<Switch>
				<Route
					exact
					path={tabs.dashboard.path}
					render={withParams(DashboardTab)}
				/>

				<Route
					exact
					path={tabs.performance.path}
					render={withParams(PerformanceTab)}
				/>
			</Switch>
		</div>
	);
}
