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

import EditAppContext from 'app-builder-web/js/pages/apps/edit/EditAppContext.es';
import React, {useContext, useEffect, useState} from 'react';
import ReactFlow from 'react-flow-renderer';

import {ADD_STEP, REMOVE_STEP, UPDATE_CURRENT_STEP} from '../configReducer.es';
import PlusButton from './PlusButton.es';
import WorkflowStep from './WorkflowStep.es';

export default function WorkflowBuilder() {
	const {
		config: {currentStep, dataObject, steps, transitions},
		dispatchConfig,
	} = useContext(EditAppContext);
	const [elements, setElements] = useState([]);

	const badgeLabel = (index) => {
		if (index === 0) {
			return Liferay.Language.get('start');
		} else if (index === steps.length - 1) {
			return Liferay.Language.get('end');
		}

		return index;
	};

	const onClickStep = ({id}) => {
		dispatchConfig({id, type: UPDATE_CURRENT_STEP});
	};

	const stepInfo = [
		[
			{
				...dataObject,
				label: Liferay.Language.get('data-object'),
			},
		],
		...steps
			.filter(({initial}) => initial === undefined)
			.map(({appWorkflowRoleAssignments = []}) =>
				appWorkflowRoleAssignments.length > 0
					? [
							{
								label: Liferay.Language.get('assignee'),
								name: appWorkflowRoleAssignments
									.map(({roleName}) => roleName)
									.reduce((acc, cur) => `${acc}, ${cur}`),
							},
					  ]
					: []
			),
	];

	let customElements = steps.map((step, index) => {
		return {
			...step,
			data: {
				props: {
					actions: [
						{
							label: Liferay.Language.get('delete-step'),
							onClick: () =>
								dispatchConfig({
									id: index,
									type: REMOVE_STEP,
								}),
						},
					],
					badgeLabel: badgeLabel(index),
					errors: step.errors,
					initial: step.initial,
					isInitialOrFinalSteps: step.isInitialOrFinalSteps,
					name: step.name,
					selected: index === currentStep?.id,
					stepInfo: stepInfo[index] ?? [],
				},
			},
			id: `${step.id}`,
		};
	});

	const addStep = () => {
		dispatchConfig({
			id: 1,
			stepPosition: {x: 500, y: 200},
			stepType: 'workflowNode',
			type: ADD_STEP,
		});
	};

	customElements = [
		...customElements,
		{
			data: {addStep},
			id: '500',
			position: {x: 446, y: 200},
			type: 'plusButton',
		},
	];

	useEffect(() => {
		setElements([...customElements, ...transitions]);

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	useEffect(() => {
		if (steps.length > 2) {
			let newNode = steps.find(({id}) => id == currentStep.id);

			newNode = {
				...newNode,
				data: {
					props: {
						actions: [
							{
								label: Liferay.Language.get('delete-step'),
							},
						],
						badgeLabel: badgeLabel(newNode.id),
						errors: newNode.errors,
						initial: newNode?.initial,
						isInitialOrFinalSteps: newNode.isInitialOrFinalSteps,
						name: newNode.name,
						selected: newNode.id === currentStep.id,
						stepInfo: stepInfo[newNode.id] ?? [],
					},
				},
				id: `${newNode.id}`,
			};

			setElements((es) => es.concat(newNode));

			const edge = {
				arrowHeadType: 'arrowclosed',
				id: 'e0-1',
				label: <PlusButton addStep={addStep} />,
				source: '0',
				target: '1',
				type: 'step',
			};

			setElements((es) => es.concat(edge));
		}

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [steps.length]);

	const nodeTypes = {
		plusButton: PlusButton,
		workflowNode: WorkflowStep,
	};
	const onLoad = (reactFlowInstance) => reactFlowInstance.fitView();

	return (
		<div className="app-builder-workflow-app__builder">
			<div className="reactflow-wrapper">
				<ReactFlow
					elements={elements}
					nodeTypes={nodeTypes}
					onElementClick={onClickStep}
					onLoad={onLoad}
					snapToGrid
				/>
			</div>
		</div>
	);
}
