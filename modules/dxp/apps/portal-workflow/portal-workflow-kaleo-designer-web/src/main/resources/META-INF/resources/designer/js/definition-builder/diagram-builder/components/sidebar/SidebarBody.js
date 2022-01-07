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

import PropTypes from 'prop-types';
import React, {useContext, useEffect, useState} from 'react';

import {DiagramBuilderContext} from '../../DiagramBuilderContext';
import {nodeDescription, nodeTypes} from '../nodes/utils';

const onDragStart = (event, nodeType) => {
	event.dataTransfer.setData('application/reactflow', nodeType);
	event.dataTransfer.effectAllowed = 'move';
};

export default function SidebarBody({children, displayDefaultContent = true}) {
	const {setCollidingElements} = useContext(DiagramBuilderContext);
	const [isDragging, setIsDragging] = useState(false);

	useEffect(() => {
		window.addEventListener('drop', (event) => {
			console.log('event', event);
		});
	}, []);


	//find a reliable way to query the node using event
	//set at node the node-disabled class
	//put the code that is in useEffect on a separated function and call it. 

	return (
		<div className="sidebar-body">
			{displayDefaultContent
				? Object.entries(nodeTypes).map(([key, Component], index) => (
						<Component
							className={
								isDragging ? 'node-disabled' : `${key}-node`
							}
							descriptionSidebar={nodeDescription[key]}
							draggable
							key={index}
							onDragEnd={() => {
								setCollidingElements(null);
								console.log('new-end');
							}}
							onDragStart={(event) => {
								onDragStart(event, key);
								setIsDragging(true);
								console.log('drag start');
							}}
						/>
				  ))
				: children}
		</div>
	);
}

SidebarBody.protoTypes = {
	children: PropTypes.any,
	displayDefaultContent: PropTypes.bool,
};
