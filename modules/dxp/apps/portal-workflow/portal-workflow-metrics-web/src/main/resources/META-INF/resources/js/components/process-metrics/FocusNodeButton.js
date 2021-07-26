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

import React from 'react';
import {useStore, useZoomPanHelper} from 'react-flow-renderer';

export default ({nodeToBeFocusedId}) => {
	const store = useStore();

	const {setCenter} = useZoomPanHelper();

	const focusNode = () => {
		const {nodes} = store.getState();

		if (nodes.length) {
			const node = nodes.find((node) => node.id == nodeToBeFocusedId);

			const x = node.__rf.position.x + node.__rf.width / 2;
			const y = node.__rf.position.y + node.__rf.height / 2;

			const zoom = 1.85;

			setCenter(x, y, zoom);
		}
	};

	return <button onClick={focusNode}>focus node</button>;
};
