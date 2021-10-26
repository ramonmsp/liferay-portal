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

import ModelInfo from '../../../src/main/resources/META-INF/resources/workflow_status/js/components/model-info/ModelInfo';

describe('The ModelInfo component should', () => {
	let type, value;

	afterEach(cleanup);

	it('render with ID type', () => {
		type = 'id';
		value = 'Valid ID';

		const {getByText} = render(<ModelInfo type={type} value={value} />);

		const id = getByText(`${Liferay.Language.get(type)}:`);
		const idValue = getByText('Valid ID');

		expect(id).toBeTruthy();
		expect(idValue).toBeTruthy();
	});

	it('render with Version type', () => {
		type = 'version';
		value = 'Valid Version';

		const {getByText} = render(<ModelInfo type={type} value={value} />);

		const version = getByText(`${Liferay.Language.get(type)}:`);
		const versionValue = getByText('Valid Version');

		expect(version).toBeTruthy();
		expect(versionValue).toBeTruthy();
	});
});
