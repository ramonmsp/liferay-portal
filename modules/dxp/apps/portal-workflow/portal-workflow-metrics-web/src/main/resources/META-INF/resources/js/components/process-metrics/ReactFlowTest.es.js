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
import React, {useRef} from 'react';
import ReactFlow, {Controls, ReactFlowProvider} from 'react-flow-renderer';

import initialElements from './initialElements.es';

export default function ReactFlowTest() {
	// const dagreGraph = new dagre.graphlib.Graph();
	// dagreGraph.setDefaultEdgeLabel(() => ({}));

	// const nodeWidth = 172;
	// const nodeHeight = 36;

	// const getLayoutedElements = (elements, direction = 'TB') => {
	// 	const isHorizontal = direction === 'LR';
	// 	dagreGraph.setGraph({rankdir: direction});

	// 	elements.forEach((element) => {
	// 		if (isNode(element)) {
	// 			dagreGraph.setNode(element.id, {
	// 				height: nodeHeight,
	// 				width: nodeWidth,
	// 			});
	// 		} else {
	// 			dagreGraph.setEdge(element.source, element.target);
	// 		}
	// 	});

	// 	dagre.layout(dagreGraph);

	// 	return elements.map((element) => {
	// 		if (isNode(element)) {
	// 			const nodeWithPosition = dagreGraph.node(element.id);
	// 			element.targetPosition = isHorizontal ? 'left' : 'top';
	// 			element.sourcePosition = isHorizontal ? 'right' : 'bottom';

	// 			// unfortunately we need this little hack to pass a slightly different position
	// 			// to notify react flow about the change. Moreover we are shifting the dagre node position
	// 			// (anchor=center center) to the top left so it matches the react flow node anchor point (top left).

	// 			element.position = {
	// 				x:
	// 					nodeWithPosition.x -
	// 					nodeWidth / 2 +
	// 					Math.random() / 1000,
	// 				y: nodeWithPosition.y - nodeHeight / 2,
	// 			};
	// 		}

	// 		return element;
	// 	});
	// };

	// const layoutedElements = getLayoutedElements(initialElements);

	// const onLayout = useCallback(
	// 	(direction) => {
	// 		const layoutedElements = getLayoutedElements(elements, direction);
	// 		setElements(layoutedElements);
	// 	},
	// 	// eslint-disable-next-line react-hooks/exhaustive-deps
	// 	[elements]
	// );

	const reactFlowWrapper = useRef(null);

	const onDragOver = (event) => {
		event.preventDefault();
		event.dataTransfer.dropEffect = 'move';
	};

	return (
		<div style={{height: '800px', width: '800px'}}>
			<span>Modal Content</span>

			<div className="dndflow">
				<ReactFlowProvider>
					<div className="reactflow-wrapper" ref={reactFlowWrapper}>
						<ReactFlow
							elements={initialElements}
							onDragOver={onDragOver}
						>
							<Controls />
						</ReactFlow>
					</div>
				</ReactFlowProvider>
			</div>
		</div>
	);
}
