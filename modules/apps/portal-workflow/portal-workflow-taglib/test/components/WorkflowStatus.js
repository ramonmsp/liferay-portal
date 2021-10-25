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

import WorkflowStatus from '../../src/main/resources/META-INF/resources/workflow_status/js/WorkflowStatus';

describe('The WorkflowStatus should', () => {
	const INITIAL_PROPS = {
		helpMessage: 'Help',
		id: '1',
		showHelpMessage: true,
		showInstancetracker: true,
		showStatusLabel: true,
		statusMessage: 'Status Message',
		statusStyle: 'secondary',
		version: '1.0.0',
		workflowInstanceId: '05050',
	};

	afterEach(cleanup);

	it('render with all valid props', () => {
		const {getByText} = render(<WorkflowStatus {...INITIAL_PROPS} />);

		const hasStatus = getByText('status:');

		const hasStatusMessage = getByText('Status Message');

		const hasHelpIcon = document.querySelector(
			'.lexicon-icon-question-circle-full'
		);

		const isLabelSecondaty = document.querySelector('.label-secondary');

		expect(hasStatus).toBeTruthy();

		expect(hasStatusMessage).toBeTruthy();

		expect(hasHelpIcon).toBeTruthy();

		expect(isLabelSecondaty).toBeTruthy();
	});

	it('render without help icon', () => {
		render(
			<WorkflowStatus
				{...{
					...INITIAL_PROPS,
					additionalText: '',
					showHelpMessage: false,
					statusMessage: '',
				}}
			/>
		);

		const hasHelpIcon = document.querySelector(
			'.lexicon-icon-question-circle-full'
		);

		expect(hasHelpIcon).toBeFalsy();
	});

	it('render without a status label', () => {
		render(
			<WorkflowStatus
				{...{
					...INITIAL_PROPS,
					showStatusLabel: false,
				}}
			/>
		);

		const hasStatus = document.querySelector('workflow-label');

		expect(hasStatus).toBeFalsy();
	});
});
