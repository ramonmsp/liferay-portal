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

package com.liferay.portal.kernel.resiliency.spi.cache;

import com.liferay.portal.kernel.cache.PortalCacheManager;

import java.io.Serializable;

/**
 * @author     Tina Tian
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 */
@Deprecated
public interface SPIPortalCacheManagerConfigurator {

	public PortalCacheManager<? extends Serializable, ? extends Serializable>
			createSPIPortalCacheManager(
				PortalCacheManager
					<? extends Serializable, ? extends Serializable>
						portalCacheManager)
		throws Exception;

}