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

import ClayIcon from '@clayui/icon';
import ClayModal, {useModal} from '@clayui/modal';
import {ClayTooltipProvider} from '@clayui/tooltip';
import WorkflowInstanceTracker from '@liferay/portal-workflow-instance-tracker-web/js/components/WorkflowInstanceTracker';
import React, {useState} from 'react';

import InstanceInfos from './components/instance-infos/InstanceInfos';
import TrackableLabel from './components/trackable-label/TrackableLabel';

export default function WorkflowStatus({
	helpMessage,
	id,
	showHelpMessage,
	showInstancetracker,
	showStatusLabel,
	statusMessage,
	statusStyle,
	version,
	workflowInstanceId,
}) {
	const [showInstanceTrackerModal, setShowInstanceTrackerModal] = useState(
		false
	);

	const {observer} = useModal({
		onClose: () => {
			setShowInstanceTrackerModal(false);
		},
	});

	return (
		<>
			<InstanceInfos type="id" value={id} />

			<InstanceInfos type="version" value={version} />

			{showStatusLabel && (
				<span className="mr-2 workflow-label">
					{`${Liferay.Language.get('status')}: `}
				</span>
			)}

			<TrackableLabel
				showInstanceTrackerModal={() =>
					setShowInstanceTrackerModal(true)
				}
				showInstancetracker={showInstancetracker}
				statusMessage={statusMessage}
				statusStyle={statusStyle}
			/>

			{showHelpMessage && (
				<ClayTooltipProvider>
					<ClayIcon
						symbol="question-circle-full"
						title={helpMessage}
					/>
				</ClayTooltipProvider>
			)}

			{showInstanceTrackerModal && (
				<ClayModal observer={observer} size="full-screen">
					<ClayModal.Header>
						{Liferay.Language.get('track-workflow')}
					</ClayModal.Header>

					<ClayModal.Body>
						<WorkflowInstanceTracker
							workflowInstanceId={workflowInstanceId}
						/>
					</ClayModal.Body>
				</ClayModal>
			)}
		</>
	);
}
