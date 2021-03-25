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

import {sub} from 'data-engine-js-components-web/js/utils/lang.es';

import {populateFormViewFields, validateSelectedFormViews} from './utils.es';

export const ADD_STEP = 'ADD_STEP';
export const ADD_STEP_ACTION = 'ADD_STEP_ACTION';
export const ADD_STEP_FORM_VIEW = 'ADD_STEP_FORM_VIEW';
export const REMOVE_STEP_EMPTY_FORM_VIEWS = 'REMOVE_STEP_EMPTY_FORM_VIEWS';
export const REMOVE_STEP = 'REMOVE_STEP';
export const REMOVE_STEP_ACTION = 'REMOVE_STEP_ACTION';
export const REMOVE_STEP_FORM_VIEW = 'REMOVE_STEP_FORM_VIEW';
export const UPDATE_CONFIG = 'UPDATE_CONFIG';
export const UPDATE_CURRENT_STEP = 'UPDATE_CURRENT_STEP';
export const UPDATE_DATA_OBJECT = 'UPDATE_DATA_OBJECT';
export const UPDATE_FORM_VIEW = 'UPDATE_FORM_VIEW';
export const UPDATE_LIST_ITEMS = 'UPDATE_LIST_ITEMS';
export const UPDATE_STEP = 'UPDATE_STEP';
export const UPDATE_STEP_ACTION = 'UPDATE_STEP_ACTION';
export const UPDATE_STEP_FORM_VIEW = 'UPDATE_STEP_FORM_VIEW';
export const UPDATE_STEP_FORM_VIEW_READONLY = 'UPDATE_STEP_FORM_VIEW_READONLY';
export const UPDATE_STEP_INDEX = 'UPDATE_STEP_INDEX';
export const UPDATE_TABLE_VIEW = 'UPDATE_TABLE_VIEW';

export const getInitialConfig = () => {
	const defaultLanguageId = themeDisplay.getLanguageId();
	const initialSteps = [
		{
			data: {
				props: {
					initial: true,
					name: Liferay.Language.get('initial-step'),
				},
			},
			id: 0,
			position: {x: 200, y: 100},
			type: 'workflowNode',
		},
		{
			data: {
				props: {
					initial: false,
					name: Liferay.Language.get('final-step'),
				},
			},
			id: 800,
			position: {x: 205, y: 300},
			type: 'workflowNode',
		},
	];

	const transitions = [
		{
			arrowHeadType: 'arrowclosed',
			data: {
				props: {name: Liferay.Language.get('submit'), primary: true},
			},
			id: 'e0-800',
			source: '0',
			target: '800',
			type: 'step',
		},
	];

	return {
		currentStep: initialSteps[0],
		dataObject: {
			availableLanguageIds: [defaultLanguageId],
			defaultLanguageId,
		},
		draftConfig: {},
		formView: {},
		listItems: {
			assigneeRoles: [],
			dataObjects: [],
			fetching: false,
			formViews: [],
			tableViews: [],
		},
		steps: initialSteps,
		tableView: {},
		transitions,
	};
};

export default (state, action) => {
	switch (action.type) {
		case ADD_STEP: {
			const appWorkflowDataLayoutLinks = [];

			if (state.formView.id) {
				appWorkflowDataLayoutLinks.push({
					...state.formView,
					dataLayoutId: state.formView.id,
					readOnly: true,
				});
			}

			const currentStep = {
				appWorkflowDataLayoutLinks,
				appWorkflowRoleAssignments: [],
				errors: {
					formViews: {
						duplicatedFields: [],
						errorIndexes: [],
					},
				},
				id: state.currentStep.id + 1,
				name: sub(Liferay.Language.get('step-x'), [
					state.steps.length - 1,
				]),
				position: action.stepPosition,
				type: action.stepType,
			};

			let transitions = state.transitions;

			transitions = [
				...transitions,
				{
					arrowHeadType: 'arrowclosed',
					data: {
						props: {
							name: Liferay.Language.get('submit'),
							primary: true,
						},
					},
					id: `e${action.source}-${action.target}`,
					source: action.source,
					target: action.target,
					type: 'step',
				},
			];

			return {
				...state,
				currentStep,
				steps: [...state.steps, currentStep],
				transitions,
			};
		}
		case ADD_STEP_ACTION: {
			state.transitions.push({
				arrowHeadType: 'arrowclosed',
				data: {
					props: {
						name: Liferay.Language.get('secondary-action'),
						primary: false,
					},
				},
				id: `e${action.source}-${action.target}`,
				source: action.source,
				target: state.currentStep.id - 1,
				type: 'step',
			});

			return {...state};
		}
		case ADD_STEP_FORM_VIEW: {
			state.currentStep.appWorkflowDataLayoutLinks.push({
				dataLayoutId: undefined,
				fields: [],
				readOnly: false,
			});

			return {...state};
		}
		case REMOVE_STEP: {
			let currentStep;
			const workflowSteps = [...state.steps];

			const previousStep = workflowSteps[action.id - 1];
			const nextStep = workflowSteps[action.id + 1];

			let transition = state.transitions.find(({id}) => id == action.id);

			transition = {
				...transition,
				id: `e${previousStep.id}-${nextStep.id}`,
				source: previousStep.id,
				target: nextStep.id,
			};

			if(state.transitions.)

			if (nextStep?.appWorkflowTransitions?.length > 1) {
				if (action.id > 1) {


					nextStep.appWorkflowTransitions.forEach((transition) => {
						if (!transition.primary) {
							transition.transitionTo = previousStep.name;
						}
					});
				} else {
					nextStep.appWorkflowTransitions.forEach(
						(transition, index) => {
							if (!transition.primary) {
								nextStep.appWorkflowTransitions.splice(
									index,
									1
								);
							}
						}
					);
				}
			}

			if (state.currentStep.id === action.id) {
				currentStep = previousStep;
			}

			workflowSteps.splice(action.id, 1);

			return {
				...state,
				currentStep,
				steps: [...workflowSteps],
			};
		}
		case REMOVE_STEP_ACTION: {
			state.transitions.pop();

			return {...state};
		}
		case REMOVE_STEP_EMPTY_FORM_VIEWS: {
			const currentStep = state.currentStep;

			currentStep.appWorkflowDataLayoutLinks = currentStep.appWorkflowDataLayoutLinks?.filter(
				({dataLayoutId}) => dataLayoutId !== undefined
			);

			return {...state, currentStep};
		}
		case REMOVE_STEP_FORM_VIEW: {
			const currentStep = state.currentStep;

			currentStep.appWorkflowDataLayoutLinks.splice(action.index, 1);
			currentStep.errors.formViews = validateSelectedFormViews(
				currentStep.appWorkflowDataLayoutLinks
			);

			return {...state, currentStep};
		}
		case UPDATE_CONFIG: {
			return {
				...state,
				...action.config,
				draftConfig: JSON.parse(JSON.stringify(action.config)),
			};
		}
		case UPDATE_CURRENT_STEP: {
			return {
				...state,
				currentStep: state.steps.find(({id}) => id == action.id),
			};
		}
		case UPDATE_DATA_OBJECT: {
			state.steps.forEach((step) => {
				if (step.appWorkflowDataLayoutLinks) {
					step.appWorkflowDataLayoutLinks = [];
				}

				if (step?.errors?.formViews) {
					step.errors.formViews = {
						duplicatedFields: [],
						errorIndexes: [],
					};
				}
			});

			return {
				...state,
				dataObject: action.dataObject,
				formView: {},
				tableView: {},
			};
		}
		case UPDATE_FORM_VIEW: {
			const workflowSteps = [...state.steps];

			const initialStep = workflowSteps.shift();
			const finalStep = workflowSteps.pop();

			const formView = populateFormViewFields(action.formView);

			workflowSteps.forEach((step) => {
				if (step.appWorkflowDataLayoutLinks.length === 0) {
					step.appWorkflowDataLayoutLinks.push({
						...formView,
						dataLayoutId: formView.id,
						readOnly: true,
					});
				}
			});

			return {
				...state,
				formView,
				steps: [initialStep, ...workflowSteps, finalStep],
			};
		}
		case UPDATE_LIST_ITEMS: {
			return {
				...state,
				listItems: {
					...state.listItems,
					...action.listItems,
				},
			};
		}
		case UPDATE_STEP: {
			const step = (state.steps[action.step.id] = action.step);

			return {
				...state,
				currentStep: step,
			};
		}
		case UPDATE_STEP_ACTION: {
			const {name, primary} = action;

			const appWorkflowTransitions =
				state.currentStep.appWorkflowTransitions;

			const transitionIndex = appWorkflowTransitions.findIndex(
				(transition) => transition.primary === primary
			);

			appWorkflowTransitions[transitionIndex].name = name;

			return {...state};
		}
		case UPDATE_STEP_FORM_VIEW: {
			const currentStep = state.currentStep;

			const formViews = currentStep.appWorkflowDataLayoutLinks;

			formViews[action.index] = {
				...formViews[action.index],
				...populateFormViewFields(action.formView),
				dataLayoutId: action.formView.id,
				name: action.formView.name,
			};

			currentStep.errors.formViews = validateSelectedFormViews(formViews);

			return {...state, currentStep};
		}
		case UPDATE_STEP_FORM_VIEW_READONLY: {
			const currentStep = state.currentStep;

			currentStep.appWorkflowDataLayoutLinks[action.index] = {
				...currentStep.appWorkflowDataLayoutLinks[action.index],
				readOnly: action.readOnly,
			};

			return {...state, currentStep};
		}
		case UPDATE_TABLE_VIEW: {
			return {
				...state,
				tableView: action.tableView,
			};
		}
		default: {
			return state;
		}
	}
};
