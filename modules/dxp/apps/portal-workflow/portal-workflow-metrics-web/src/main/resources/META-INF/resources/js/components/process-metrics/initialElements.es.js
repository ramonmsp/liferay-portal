/* eslint-disable react-hooks/exhaustive-deps */
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

const position = {x: 0, y: 0};
const edgeType = 'smoothstep';

export default [
	{
		data: {label: 'input'},
		id: '1',
		position,
		type: 'input',
	},
	{
		data: {label: 'node 2'},
		id: '2',
		position,
	},
	{
		data: {label: 'node 2a'},
		id: '2a',
		position,
	},
	{
		data: {label: 'node 2b'},
		id: '2b',
		position,
	},
	{
		data: {label: 'node 2c'},
		id: '2c',
		position,
	},
	{
		data: {label: 'node 2d'},
		id: '2d',
		position,
	},
	{
		data: {label: 'node 3'},
		id: '3',
		position,
	},
	{
		data: {label: 'node 4'},
		id: '4',
		position,
	},
	{
		data: {label: 'node 5'},
		id: '5',
		position,
	},
	{
		data: {label: 'output'},
		id: '6',
		position,
		type: 'output',
	},
	{data: {label: 'output'}, id: '7', position, type: 'output'},
	{id: 'e12', source: '1', target: '2', type: edgeType},
	{id: 'e13', source: '1', target: '3', type: edgeType},
	{id: 'e22a', source: '2', target: '2a', type: edgeType},
	{id: 'e22b', source: '2', target: '2b', type: edgeType},
	{id: 'e22c', source: '2', target: '2c', type: edgeType},
	{id: 'e2c2d', source: '2c', target: '2d', type: edgeType},
	{id: 'e45', source: '4', target: '5', type: edgeType},
	{id: 'e56', source: '5', target: '6', type: edgeType},
	{id: 'e57', source: '5', target: '7', type: edgeType},
];
