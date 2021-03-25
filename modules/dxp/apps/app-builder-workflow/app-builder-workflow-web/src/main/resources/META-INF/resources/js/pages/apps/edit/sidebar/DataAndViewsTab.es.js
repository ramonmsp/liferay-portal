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

import ClayAlert from '@clayui/alert';
import ClayButton, {ClayButtonWithIcon} from '@clayui/button';
import {ClayRadio, ClayRadioGroup} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import {AppContext} from 'app-builder-web/js/AppContext.es';
import SelectObjects from 'app-builder-web/js/components/select-objects/SelectObjects.es';
import EditAppContext, {
	UPDATE_APP,
	UPDATE_DATA_LIST_VIEW_ID,
} from 'app-builder-web/js/pages/apps/edit/EditAppContext.es';
import classNames from 'classnames';
import {
	getLocalizedValue,
	sub,
} from 'data-engine-js-components-web/js/utils/lang.es';
import {successToast} from 'data-engine-js-components-web/js/utils/toast.es';
import {concatValues} from 'data-engine-js-components-web/js/utils/utils.es';
import {DataDefinitionUtils} from 'data-engine-taglib';
import {openModal} from 'frontend-js-web';
import React, {useContext} from 'react';

import {getTableViews} from '../actions.es';
import {
	ADD_STEP_FORM_VIEW,
	REMOVE_STEP_FORM_VIEW,
	UPDATE_DATA_OBJECT,
	UPDATE_LIST_ITEMS,
	UPDATE_STEP_FORM_VIEW,
	UPDATE_STEP_FORM_VIEW_READONLY,
	UPDATE_TABLE_VIEW,
} from '../configReducer.es';
import SelectFormView from './SelectFormView.es';
import SelectTableView from './SelectTableView.es';

const NoObjectEmptyState = () => (
	<div className="taglib-empty-result-message">
		<div className="text-center">
			<div className="taglib-empty-result-message-header" />

			<h3>{Liferay.Language.get('no-object-selected')}</h3>

			<p className="empty-message-color taglib-empty-result-message-description">
				{Liferay.Language.get(
					'select-a-data-object-to-start-gathering-business-data'
				)}
			</p>
		</div>
	</div>
);

export const OpenButton = (props) => (
	<ClayButtonWithIcon
		className="ml-2 px-2 tap-ahead-icon-wrapper"
		data-tooltip-align="bottom-right"
		data-tooltip-delay="0"
		displayType="secondary"
		symbol="tap-ahead"
		title={Liferay.Language.get('open')}
		{...props}
	/>
);

export default function DataAndViewsTab({
	config: {
		currentStep,
		dataObject,
		formView,
		listItems: {fetching, formViews, tableViews},
		tableView,
	},
	dispatch,
	dispatchConfig,
}) {
	const {objectsPortletURL} = useContext(AppContext);
	const {openFormViewModal, updateFormView} = useContext(EditAppContext);
	const {
		appWorkflowDataLayoutLinks: stepFormViews = [],
		errors: {
			formViews: {duplicatedFields = [], errorIndexes = []} = {},
		} = {},
	} = currentStep;

	const availableFormViews = formViews.map((formView) => ({
		...formView,
		disabled:
			stepFormViews.findIndex(
				({dataLayoutId}) => dataLayoutId === formView.id
			) > -1,
	}));

	const mainFormViews = formViews.map((formView) => ({
		...formView,
		disabled: formView.missingRequiredFields?.nativeField,
	}));

	const addStepFormView = () => {
		dispatchConfig({
			type: ADD_STEP_FORM_VIEW,
		});
	};

	const getWarningIcon = (formView) => {
		const {
			missingRequiredFields: {customField, nativeField} = {},
		} = formView;

		let warningIcon;

		if (customField || nativeField) {
			warningIcon = {
				className: classNames(
					'mr-2 tooltip-popover-icon',
					nativeField ? 'error' : 'info'
				),
				fontSize: '26px',
				symbol: nativeField ? 'exclamation-full' : 'info-circle',
			};
		}

		return warningIcon;
	};

	const removeStepFormView = (index) => {
		dispatchConfig({
			index,
			type: REMOVE_STEP_FORM_VIEW,
		});
	};

	const updateDataObject = (newDataObject) => {
		if (newDataObject.id !== dataObject.id) {
			dispatchConfig({
				dataObject: newDataObject,
				type: UPDATE_DATA_OBJECT,
			});

			dispatch({
				app: {
					dataDefinitionId: newDataObject.id,
					dataLayoutId: null,
					dataListViewId: null,
				},
				type: UPDATE_APP,
			});
		}
	};

	const updateStepFormView = (formView, index) => {
		dispatchConfig({
			formView,
			index,
			type: UPDATE_STEP_FORM_VIEW,
		});
	};

	const updateStepFormViewReadOnly = (readOnly, index) => {
		dispatchConfig({
			index,
			readOnly,
			type: UPDATE_STEP_FORM_VIEW_READONLY,
		});
	};

	const updateTableView = (tableView) => {
		dispatchConfig({
			tableView,
			type: UPDATE_TABLE_VIEW,
		});

		dispatch({
			...tableView,
			type: UPDATE_DATA_LIST_VIEW_ID,
		});
	};

	const openTableViewModal = (
		dataDefinitionId,
		defaultLanguageId,
		dataListViewId
	) => {
		const event = window.top?.Liferay.once(
			'newTableViewCreated',
			({newTableView}) => {
				successToast(
					Liferay.Language.get(
						'the-table-view-was-saved-successfully'
					)
				);

				getTableViews(dataDefinitionId, defaultLanguageId).then(
					(tableViews) => {
						dispatchConfig({
							listItems: {
								fetching: false,
								tableViews,
							},
							type: UPDATE_LIST_ITEMS,
						});
					}
				);

				updateTableView({
					...newTableView,
					name: getLocalizedValue(
						defaultLanguageId,
						newTableView.name
					),
				});
			}
		);

		openModal({
			onClose: () => event?.detach(),
			title: dataListViewId
				? Liferay.Language.get('edit-table-view')
				: Liferay.Language.get('new-table-view'),
			url: `${Liferay.Util.PortletURL.createRenderURL(objectsPortletURL, {
				p_p_state: 'pop_up',
			})}#/custom-object/${dataDefinitionId}/table-views/${
				dataListViewId ?? 'add'
			}`,
		});
	};

	const onCreateObject = (newObject) => {
		const {defaultLanguageId, id, name} = newObject;

		successToast(
			Liferay.Language.get('the-object-was-created-successfully')
		);

		updateDataObject({
			...newObject,
			name: getLocalizedValue(defaultLanguageId, name),
			type: 'custom',
		});

		openFormViewModal(id, defaultLanguageId, updateFormView);
	};

	const AddButton = (props) => (
		<ClayButtonWithIcon
			className="btn btn-monospaced btn-secondary mr-2 nav-btn nav-btn-monospaced"
			data-tooltip-align="bottom-right"
			data-tooltip-delay="0"
			displayType="secondary"
			symbol="plus"
			{...props}
		/>
	);

	const addFormViewButton = (selectFormView) => (
		<AddButton
			onClick={() =>
				openFormViewModal(
					dataObject.id,
					dataObject.defaultLanguageId,
					selectFormView
				)
			}
			title={Liferay.Language.get('new-form-view')}
		/>
	);

	const duplicatedFieldsMessage =
		duplicatedFields.length === 1
			? Liferay.Language.get(
					'the-field-x-is-present-in-multiple-form-views'
			  )
			: Liferay.Language.get(
					'the-fields-x-are-present-in-multiple-form-views'
			  );

	const fieldsLabels = duplicatedFields.map((field) =>
		DataDefinitionUtils.getFieldLabel(dataObject, field)
	);

	const parsedFieldsLabels = fieldsLabels
		.slice(0, 5)
		.map((label) => `"${label}"`);

	if (fieldsLabels.length > 5) {
		parsedFieldsLabels.push(`others*`);
	}

	return (
		<>
			{!currentStep?.initial ? (
				<>
					{duplicatedFields.length > 0 && (
						<ClayAlert
							className="fields-alert-container mt-2"
							displayType="danger"
							title={`${Liferay.Language.get('error')}:`}
						>
							{`${sub(duplicatedFieldsMessage, [
								concatValues(parsedFieldsLabels),
							])} `}

							{duplicatedFields.length > 5 && (
								<a
									className="text-primary"
									data-tooltip-align="bottom"
									data-tooltip-delay={0}
									title={fieldsLabels
										.slice(5, fieldsLabels.length)
										.join('\n')}
								>
									{'*'}
									{Liferay.Language.get('see-other-fields')}
								</a>
							)}
						</ClayAlert>
					)}

					{stepFormViews.map(
						({dataLayoutId, name, readOnly}, index) => (
							<div
								className={classNames(
									'step-form-view',
									errorIndexes.includes(index) &&
										'border-error'
								)}
								key={index}
							>
								<label id="form-view-label">
									{Liferay.Language.get('form-view')}
								</label>

								<SelectFormView
									addButton={addFormViewButton((formView) =>
										updateStepFormView(formView, index)
									)}
									ariaLabelId="form-view-label"
									items={availableFormViews}
									onSelect={(formView) =>
										updateStepFormView(formView, index)
									}
									openButtonProps={{
										disabled: !name,
										onClick: () =>
											openFormViewModal(
												dataObject.id,
												dataObject.defaultLanguageId,
												(formView) =>
													updateStepFormView(
														formView,
														index
													),
												dataLayoutId
											),
									}}
									selectedValue={name}
								/>

								<div className="d-flex justify-content-between mt-2">
									<ClayRadioGroup
										className="pt-1"
										inline
										onSelectedValueChange={(readOnly) =>
											updateStepFormViewReadOnly(
												readOnly,
												index
											)
										}
										selectedValue={readOnly}
									>
										<ClayRadio
											disabled={!dataLayoutId}
											label={Liferay.Language.get(
												'editable'
											)}
											value={false}
										/>

										<ClayRadio
											disabled={!dataLayoutId}
											label={Liferay.Language.get(
												'read-only'
											)}
											value={true}
										/>
									</ClayRadioGroup>

									{stepFormViews.length > 1 && (
										<ClayButtonWithIcon
											borderless
											data-tooltip-align="bottom"
											data-tooltip-delay="0"
											displayType="secondary"
											onClick={() =>
												removeStepFormView(index)
											}
											small
											symbol="trash"
											title={Liferay.Language.get(
												'remove'
											)}
										/>
									)}
								</div>
							</div>
						)
					)}

					<ClayButton
						className="mt-3 w-100"
						displayType="secondary"
						onClick={addStepFormView}
					>
						<span className="text-secondary">
							{Liferay.Language.get('add-new-form-view')}
						</span>
					</ClayButton>
				</>
			) : (
				<>
					<div className="main-section">
						<label id="select-object-label">
							{Liferay.Language.get('main-data-object')}
						</label>

						<ClayIcon
							className="ml-2 text-muted tooltip-icon"
							data-tooltip-align="top"
							data-tooltip-delay="0"
							symbol="question-circle-full"
							title={Liferay.Language.get(
								'a-data-object-stores-your-business-data-and-is-composed-by-data-fields'
							)}
						/>

						<SelectObjects
							label={Liferay.Language.get('select-object')}
							onCreateObject={onCreateObject}
							onSelect={updateDataObject}
							selectedValue={dataObject}
						/>
					</div>

					{dataObject.name ? (
						<div className="py-3">
							<h5 className="text-secondary text-uppercase">
								{Liferay.Language.get('gather-data')}
							</h5>

							<label id="form-view-label">
								{Liferay.Language.get('form-view')}
							</label>

							<SelectFormView
								addButton={addFormViewButton(updateFormView)}
								ariaLabelId="form-view-label"
								dropdownButtonProps={{
									className: classNames(
										'clearfix w-100',
										formView.missingRequiredFields
											?.nativeField && 'border-error'
									),
								}}
								isLoading={fetching}
								items={mainFormViews}
								onSelect={updateFormView}
								openButtonProps={{
									disabled: !formView.name,
									onClick: () =>
										openFormViewModal(
											dataObject.id,
											dataObject.defaultLanguageId,
											updateFormView,
											formView.id
										),
								}}
								selectedValue={formView.name}
								warningIcon={getWarningIcon(formView)}
							>
								{SelectFormView.Item}
							</SelectFormView>

							<h5 className="mt-3 text-secondary text-uppercase">
								{Liferay.Language.get('display-data')}
							</h5>

							<label id="table-view-label">
								{Liferay.Language.get('table-view')}
							</label>

							<SelectTableView
								addButton={
									<AddButton
										onClick={() =>
											openTableViewModal(
												dataObject.id,
												dataObject.defaultLanguageId
											)
										}
										title={Liferay.Language.get(
											'new-table-view'
										)}
									/>
								}
								ariaLabelId="table-view-label"
								isLoading={fetching}
								items={tableViews}
								onSelect={updateTableView}
								openButtonProps={{
									disabled: !tableView.name,
									onClick: () =>
										openTableViewModal(
											dataObject.id,
											dataObject.defaultLanguageId,
											tableView.id
										),
								}}
								selectedValue={tableView.name}
							/>
						</div>
					) : (
						<NoObjectEmptyState />
					)}
				</>
			)}
		</>
	);
}
