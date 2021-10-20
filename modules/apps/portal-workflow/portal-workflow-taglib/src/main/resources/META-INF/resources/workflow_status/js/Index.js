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

import ClayLink from '@clayui/link';
import ClayLabel from '@clayui/label';
import ClayModal, {useModal} from '@clayui/modal';
import WorkflowInstanceTracker from '@liferay/portal-workflow-instance-tracker-web/js/components/WorkflowInstanceTracker';
import React, {useState} from 'react';

export default function Index({workflowInstanceId, statusMessage}) {
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
			<ClayLink onClick={() => setShowInstanceTrackerModal(true)}>
				{Liferay.Language.get('track-workflow')}
			</ClayLink>

			<ClayLabel
				displayType="info"
			>
				{statusMessage}
			</ClayLabel>

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