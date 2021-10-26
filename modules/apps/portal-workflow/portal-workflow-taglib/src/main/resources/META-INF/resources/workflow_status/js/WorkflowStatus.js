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

import React from 'react';

import ModelInfo from './components/model-info/ModelInfo';
import StatusLabel from './components/status-label/StatusLabel';

export default function WorkflowStatus({
	id,
	instanceId,
	showInstanceTracker,
	showStatusLabel,
	statusMessage,
	statusStyle,
	version,
}) {
	return (
		<>
			<ModelInfo type="id" value={id} />

			<ModelInfo type="version" value={version} />

			{showStatusLabel && (
				<span className="mr-2 workflow-label">
					{`${Liferay.Language.get('status')}: `}
				</span>
			)}

			<StatusLabel
				instanceId={instanceId}
				showInstanceTracker={showInstanceTracker}
				statusMessage={statusMessage}
				statusStyle={statusStyle}
			/>
		</>
	);
}
