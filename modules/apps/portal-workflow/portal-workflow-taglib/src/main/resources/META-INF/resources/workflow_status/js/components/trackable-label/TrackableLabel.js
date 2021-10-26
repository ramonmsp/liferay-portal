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

import ClayLabel from '@clayui/label';
import ClayLink from '@clayui/link';
import {ClayTooltipProvider} from '@clayui/tooltip';
import React from 'react';

export default function TrackableLabel({
	showInstanceTrackerModal,
	showInstancetracker,
	statusMessage,
	statusStyle,
}) {
	const LinkedLabel = (
		<ClayTooltipProvider>
			<ClayLink
				data-tooltip-align="bottom"
				onClick={showInstanceTrackerModal}
				title={Liferay.Language.get('track-workflow')}
			>
				<ClayLabel displayType={statusStyle}>
					{statusMessage && Liferay.Language.get(statusMessage)}
				</ClayLabel>
			</ClayLink>
		</ClayTooltipProvider>
	);

	const NotLinkedLabel = (
		<ClayLabel displayType={statusStyle}>
			{statusMessage && Liferay.Language.get(statusMessage)}
		</ClayLabel>
	);

	if (!showInstancetracker) {
		return NotLinkedLabel;
	}

	return LinkedLabel;
}
