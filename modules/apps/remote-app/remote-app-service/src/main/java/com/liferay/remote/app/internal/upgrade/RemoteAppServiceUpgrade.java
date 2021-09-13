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

package com.liferay.remote.app.internal.upgrade;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.remote.app.internal.upgrade.v1_0_1.RemoteAppEntryURLUpgradeProcess;
import com.liferay.remote.app.internal.upgrade.v2_0_0.RemoteAppEntryAddCustomElementFieldsUpgradeProcess;
import com.liferay.remote.app.internal.upgrade.v2_0_0.RemoteAppEntryAddTypeFieldUpgradeProcess;
import com.liferay.remote.app.internal.upgrade.v2_0_0.RemoteAppEntryRenameURLFieldUpgradeProcess;

import org.osgi.service.component.annotations.Component;

/**
 * @author Iván Zaera
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class RemoteAppServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.0.1", new RemoteAppEntryURLUpgradeProcess());

		registry.register(
			"1.0.1", "2.0.0",
			new RemoteAppEntryAddCustomElementFieldsUpgradeProcess());

		registry.register(
			"1.0.1", "2.0.0", new RemoteAppEntryAddTypeFieldUpgradeProcess());

		registry.register(
			"1.0.1", "2.0.0", new RemoteAppEntryRenameURLFieldUpgradeProcess());
	}

}