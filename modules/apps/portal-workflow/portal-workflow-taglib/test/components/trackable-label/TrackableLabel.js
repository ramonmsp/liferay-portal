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

import '@testing-library/jest-dom/extend-expect';
import {cleanup, render} from '@testing-library/react';
import React from 'react';

import TrackableLabel from '../../../src/main/resources/META-INF/resources/workflow_status/js/components/trackable-label/TrackableLabel';

describe('The WorkflowStatus should', () => {
	const INITIAL_PROPS = {
		showInstancetracker: true,
		statusMessage: 'Status Message',
		statusStyle: 'info',
	};

	beforeEach(cleanup);

	it('render as Linked Label', () => {
		render(<TrackableLabel {...INITIAL_PROPS} />);

		const hasLink = document.querySelector('a');

		expect(hasLink).toBeTruthy();

		expect(hasLink).toHaveAttribute(
			'title',
			Liferay.Language.get('track-workflow')
		);
	});

	it('render as not Linked Label', () => {
		render(
			<TrackableLabel
				{...{...INITIAL_PROPS, showInstancetracker: false}}
			/>
		);

		const hasLink = document.querySelector('a');

		expect(hasLink).not.toBeInTheDocument();
	});
});
