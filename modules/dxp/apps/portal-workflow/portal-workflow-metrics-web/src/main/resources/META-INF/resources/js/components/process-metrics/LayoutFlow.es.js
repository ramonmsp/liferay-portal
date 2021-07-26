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

import dagre from 'dagre';
import React, {useCallback, useState} from 'react';
import ReactFlow, {
	Controls,
	ReactFlowProvider,
	isNode,
} from 'react-flow-renderer';

import FocusNodeButton from './FocusNodeButton';

const json = {
	name: 'wlt_commerce_seller_order_acceptence_workflow_3.0.0',
	nodes: [
		{
			name: 'requireApproval',
			type: 'condition',
		},
		{
			name: 'CheckPrice',
			type: 'condition',
		},
		{
			name: 'Create Files',
			type: 'condition',
		},
		{
			name: 'checkJitterbit',
			type: 'condition',
		},
		{
			name: 'Check for Immedia',
			type: 'condition',
		},
		{
			name: 'Is the user exempt?',
			type: 'condition',
		},
		{
			name: 'Check is Tier Approval?',
			type: 'condition',
		},
		{
			name: 'Check if the user has Tier 1 role',
			type: 'condition',
		},
		{
			name: 'Check if the user has Tier 2 role',
			type: 'condition',
		},
		{
			name: 'Check if the user has Tier 3 role',
			type: 'condition',
		},
		{
			name: 'Check order total Tier 1',
			type: 'condition',
		},
		{
			name: 'Check order total Tier 2',
			type: 'condition',
		},
		{
			name: 'Check order total Tier 3',
			type: 'condition',
		},
		{
			name: 'created',
			type: 'state',
		},
		{
			name: 'approved',
			type: 'state',
		},
		{
			name: 'Order Rejected',
			type: 'state',
		},
		{
			name: 'denied',
			type: 'state',
		},
		{
			name: 'Rejected by Tiered workflow',
			type: 'state',
		},
		{
			name: 'review',
			type: 'task',
		},
		{
			name: 'jitterbitApproval',
			type: 'task',
		},
		{
			name: 'File Failure',
			type: 'task',
		},
		{
			name: 'Awaiting Immedia',
			type: 'task',
		},
		{
			name: 'Tier 1 Approval',
			type: 'task',
		},
		{
			name: 'Tier 2 Approval',
			type: 'task',
		},
		{
			name: 'Tier 3 Approval',
			type: 'task',
		},
	],
	transitions: [
		{
			default: true,
			name: 'review',
			sourceNodeName: 'requireApproval',
			targetNodeName: 'review',
		},
		{
			default: false,
			name: 'passthrough',
			sourceNodeName: 'requireApproval',
			targetNodeName: 'CheckPrice',
		},
		{
			default: true,
			name: 'over',
			sourceNodeName: 'CheckPrice',
			targetNodeName: 'review',
		},
		{
			default: false,
			name: 'under',
			sourceNodeName: 'CheckPrice',
			targetNodeName: 'checkJitterbit',
		},
		{
			default: true,
			name: 'XML Failure',
			sourceNodeName: 'Create Files',
			targetNodeName: 'File Failure',
		},
		{
			default: false,
			name: 'XML File Created',
			sourceNodeName: 'Create Files',
			targetNodeName: 'approved',
		},
		{
			default: true,
			name: 'isJitterbit',
			sourceNodeName: 'checkJitterbit',
			targetNodeName: 'jitterbitApproval',
		},
		{
			default: false,
			name: 'notJitterbit',
			sourceNodeName: 'checkJitterbit',
			targetNodeName: 'Check for Immedia',
		},
		{
			default: true,
			name: 'Immedia Order',
			sourceNodeName: 'Check for Immedia',
			targetNodeName: 'Awaiting Immedia',
		},
		{
			default: false,
			name: 'Normal Order',
			sourceNodeName: 'Check for Immedia',
			targetNodeName: 'Create Files',
		},
		{
			default: true,
			name: 'User is not exempt',
			sourceNodeName: 'Is the user exempt?',
			targetNodeName: 'Check is Tier Approval?',
		},
		{
			default: false,
			name: 'User is exempt',
			sourceNodeName: 'Is the user exempt?',
			targetNodeName: 'checkJitterbit',
		},
		{
			default: true,
			name: 'isNotTierApproval',
			sourceNodeName: 'Check is Tier Approval?',
			targetNodeName: 'requireApproval',
		},
		{
			default: false,
			name: 'isTierApproval',
			sourceNodeName: 'Check is Tier Approval?',
			targetNodeName: 'Check if the user has Tier 1 role',
		},
		{
			default: true,
			name: 'Has tier 1, 2 or 3 role',
			sourceNodeName: 'Check if the user has Tier 1 role',
			targetNodeName: 'Check if the user has Tier 2 role',
		},
		{
			default: false,
			name: 'Does not have tier 1 role',
			sourceNodeName: 'Check if the user has Tier 1 role',
			targetNodeName: 'Check order total Tier 1',
		},
		{
			default: true,
			name: 'Has tier 2 or 3 role',
			sourceNodeName: 'Check if the user has Tier 2 role',
			targetNodeName: 'Check if the user has Tier 3 role',
		},
		{
			default: false,
			name: 'Does not have tier 2 role',
			sourceNodeName: 'Check if the user has Tier 2 role',
			targetNodeName: 'Check order total Tier 2',
		},
		{
			default: true,
			name: 'Does not have tier 3 role',
			sourceNodeName: 'Check if the user has Tier 3 role',
			targetNodeName: 'Check order total Tier 3',
		},
		{
			default: false,
			name: 'Has tier 3 role',
			sourceNodeName: 'Check if the user has Tier 3 role',
			targetNodeName: 'checkJitterbit',
		},
		{
			default: true,
			name: 'Does not have tier 1 price',
			sourceNodeName: 'Check order total Tier 1',
			targetNodeName: 'Check if the user has Tier 2 role',
		},
		{
			default: false,
			name: 'Has tier 1 price',
			sourceNodeName: 'Check order total Tier 1',
			targetNodeName: 'Tier 1 Approval',
		},
		{
			default: true,
			name: 'Does not have tier 2 price',
			sourceNodeName: 'Check order total Tier 2',
			targetNodeName: 'Check if the user has Tier 3 role',
		},
		{
			default: false,
			name: 'Has tier 2 price',
			sourceNodeName: 'Check order total Tier 2',
			targetNodeName: 'Tier 2 Approval',
		},
		{
			default: true,
			name: 'Does not have tier 3 price',
			sourceNodeName: 'Check order total Tier 3',
			targetNodeName: 'checkJitterbit',
		},
		{
			default: false,
			name: 'Has tier 3 price',
			sourceNodeName: 'Check order total Tier 3',
			targetNodeName: 'Tier 3 Approval',
		},
		{
			default: true,
			name: 'evaluate',
			sourceNodeName: 'created',
			targetNodeName: 'Is the user exempt?',
		},
		{
			default: true,
			name: 'reject',
			sourceNodeName: 'review',
			targetNodeName: 'Order Rejected',
		},
		{
			default: false,
			name: 'Approve Order',
			sourceNodeName: 'review',
			targetNodeName: 'checkJitterbit',
		},
		{
			default: true,
			name: 'jitterbitReject',
			sourceNodeName: 'jitterbitApproval',
			targetNodeName: 'denied',
		},
		{
			default: false,
			name: 'jitterbitApprove',
			sourceNodeName: 'jitterbitApproval',
			targetNodeName: 'Check for Immedia',
		},
		{
			default: true,
			name: 'Retry',
			sourceNodeName: 'File Failure',
			targetNodeName: 'Create Files',
		},
		{
			default: false,
			name: 'Approve without Files',
			sourceNodeName: 'File Failure',
			targetNodeName: 'approved',
		},
		{
			default: true,
			name: 'Sent to Immedia',
			sourceNodeName: 'Awaiting Immedia',
			targetNodeName: 'Create Files',
		},
		{
			default: true,
			name: 'Approve Tier 1',
			sourceNodeName: 'Tier 1 Approval',
			targetNodeName: 'Check if the user has Tier 2 role',
		},
		{
			default: false,
			name: 'Reject Order Tier 1',
			sourceNodeName: 'Tier 1 Approval',
			targetNodeName: 'Rejected by Tiered workflow',
		},
		{
			default: true,
			name: 'Approve Tier 2',
			sourceNodeName: 'Tier 2 Approval',
			targetNodeName: 'Check if the user has Tier 3 role',
		},
		{
			default: false,
			name: 'Reject Order Tier 2',
			sourceNodeName: 'Tier 2 Approval',
			targetNodeName: 'Rejected by Tiered workflow',
		},
		{
			default: true,
			name: 'Approve Tier 3',
			sourceNodeName: 'Tier 3 Approval',
			targetNodeName: 'checkJitterbit',
		},
		{
			default: false,
			name: 'Reject Order Tier 3',
			sourceNodeName: 'Tier 3 Approval',
			targetNodeName: 'Rejected by Tiered workflow',
		},
	],
};

const dagreGraph = new dagre.graphlib.Graph();
dagreGraph.setDefaultEdgeLabel(() => ({}));

// In order to keep this example simple the node width and height are hardcoded.
// In a real world app you would use the correct width and height values of
// const nodes = useStoreState(state => state.nodes) and then node.__rf.width, node.__rf.height

const nodeWidth = 172;
const nodeHeight = 36;

const getLayoutedElements = (elements, direction = 'TB') => {
	const isHorizontal = direction === 'LR';
	dagreGraph.setGraph({rankdir: direction});

	elements.forEach((element) => {
		if (isNode(element)) {
			dagreGraph.setNode(element.id, {
				height: nodeHeight,
				width: nodeWidth,
			});
		}
		else {
			dagreGraph.setEdge(element.source, element.target);
		}
	});

	dagre.layout(dagreGraph);

	return elements.map((element) => {
		if (isNode(element)) {
			const nodeWithPosition = dagreGraph.node(element.id);
			element.targetPosition = isHorizontal ? 'left' : 'top';
			element.sourcePosition = isHorizontal ? 'right' : 'bottom';

			// unfortunately we need this little hack to pass a slightly different position
			// to notify react flow about the change. Moreover we are shifting the dagre node position
			// (anchor=center center) to the top left so it matches the react flow node anchor point (top left).

			element.position = {
				x: nodeWithPosition.x - nodeWidth / 2 + Math.random() / 1000,
				y: nodeWithPosition.y - nodeHeight / 2,
			};
		}

		return element;
	});
};

const position = {x: 0, y: 0};

const JSONnodes = json.nodes.map((node) => {
	return {
		data: {label: node.name},
		id: node.name,
		position,
	};
});

const JSONtransitions = json.transitions.map((transition) => {
	return {
		id: transition.name,
		source: transition.sourceNodeName,
		target: transition.targetNodeName,
		type: 'step',
	};
});

const JSONelements = JSONnodes.concat(JSONtransitions);

const layoutedElements = getLayoutedElements(JSONelements);

const LayoutFlow = () => {
	const [elements, setElements] = useState(layoutedElements);

	const onLayout = useCallback(
		(direction) => {
			const layoutedElements = getLayoutedElements(elements, direction);
			setElements(layoutedElements);
		},
		// eslint-disable-next-line react-hooks/exhaustive-deps
		[elements]
	);

	const onLoad = (reactFlowInstance) => {
		reactFlowInstance.fitView();
	};

	return (
		<div className="layoutflow">
			<ReactFlowProvider>
				<ReactFlow
					connectionLineType="smoothstep"
					elements={elements}
					minZoom="0.1"
					onLoad={onLoad}
				/>
				<div className="controls">
					<button
						onClick={() => {
							onLayout('TB');
						}}
					>
						vertical layout
					</button>
					<button onClick={() => onLayout('LR')}>
						horizontal layout
					</button>
					<FocusNodeButton nodeToBeFocusedId="requireApproval" />
				</div>
				<Controls />
			</ReactFlowProvider>
		</div>
	);
};

export default LayoutFlow;
