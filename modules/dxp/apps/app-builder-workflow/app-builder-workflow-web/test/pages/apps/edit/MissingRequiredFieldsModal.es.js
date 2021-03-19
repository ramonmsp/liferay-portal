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

import '@testing-library/jest-dom/extend-expect';
import {act, fireEvent, render} from '@testing-library/react';
import React, {useState} from 'react';

import MissingRequiredFieldsModal from '../../../../src/main/resources/META-INF/resources/js/pages/apps/edit/MissingRequiredFieldsModal.es';

const MissingRequiredFieldsModalWrapper = ({fieldType}) => {
	const [deployModalVisible, setDeployModalVisible] = useState(true);

	const onCloseMissingRequiredFieldsModal = () =>
		setDeployModalVisible(false);

	return (
		<MissingRequiredFieldsModal
			customActionOnClick={() => {
				setDeployModalVisible(true);

				onCloseMissingRequiredFieldsModal();
			}}
			dataObjectName="User"
			fieldType={fieldType}
			onCloseModal={onCloseMissingRequiredFieldsModal}
			visible={deployModalVisible}
		/>
	);
};

describe('MissingRequiredFieldsModal', () => {
	beforeAll(() => {
		jest.useFakeTimers();
	});

	it('render missing required custom fields', async () => {
		const {baseElement, getByText} = render(
			<MissingRequiredFieldsModalWrapper />
		);

		act(() => {
			jest.runAllTimers();
		});

		const cancelButton = getByText('cancel');
		const deployButton = getByText('deploy');

		expect(cancelButton).toBeEnabled();
		expect(deployButton).toBeEnabled();

		await act(async () => {
			await fireEvent.click(cancelButton);
		});

		const modalOpen = baseElement.querySelector('modal-open');
		expect(modalOpen).toBeNull();
	});

	it('render missing required native fields', async () => {
		const {getByText} = render(
			<MissingRequiredFieldsModalWrapper fieldType="nativeField" />
		);

		act(() => {
			jest.runAllTimers();
		});

		const editButton = getByText('edit-app');

		expect(editButton).toBeEnabled();
	});
});
