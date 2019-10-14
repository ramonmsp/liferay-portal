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

package com.liferay.site.navigation.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuModel;
import com.liferay.site.navigation.model.SiteNavigationMenuSoap;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the SiteNavigationMenu service. Represents a row in the &quot;SiteNavigationMenu&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>SiteNavigationMenuModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SiteNavigationMenuImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SiteNavigationMenuImpl
 * @generated
 */
@JSON(strict = true)
public class SiteNavigationMenuModelImpl
	extends BaseModelImpl<SiteNavigationMenu>
	implements SiteNavigationMenuModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a site navigation menu model instance should use the <code>SiteNavigationMenu</code> interface instead.
	 */
	public static final String TABLE_NAME = "SiteNavigationMenu";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"siteNavigationMenuId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"type_", Types.INTEGER},
		{"auto_", Types.BOOLEAN}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("siteNavigationMenuId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("auto_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SiteNavigationMenu (uuid_ VARCHAR(75) null,siteNavigationMenuId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,type_ INTEGER,auto_ BOOLEAN,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table SiteNavigationMenu";

	public static final String ORDER_BY_JPQL =
		" ORDER BY siteNavigationMenu.siteNavigationMenuId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SiteNavigationMenu.siteNavigationMenuId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.site.navigation.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.site.navigation.model.SiteNavigationMenu"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.site.navigation.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.site.navigation.model.SiteNavigationMenu"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.site.navigation.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.site.navigation.model.SiteNavigationMenu"),
		true);

	public static final long AUTO_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long NAME_COLUMN_BITMASK = 8L;

	public static final long TYPE_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	public static final long SITENAVIGATIONMENUID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SiteNavigationMenu toModel(SiteNavigationMenuSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SiteNavigationMenu model = new SiteNavigationMenuImpl();

		model.setUuid(soapModel.getUuid());
		model.setSiteNavigationMenuId(soapModel.getSiteNavigationMenuId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setType(soapModel.getType());
		model.setAuto(soapModel.isAuto());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SiteNavigationMenu> toModels(
		SiteNavigationMenuSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<SiteNavigationMenu> models = new ArrayList<SiteNavigationMenu>(
			soapModels.length);

		for (SiteNavigationMenuSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.site.navigation.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.site.navigation.model.SiteNavigationMenu"));

	public SiteNavigationMenuModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _siteNavigationMenuId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSiteNavigationMenuId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _siteNavigationMenuId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SiteNavigationMenu.class;
	}

	@Override
	public String getModelClassName() {
		return SiteNavigationMenu.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SiteNavigationMenu, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SiteNavigationMenu, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SiteNavigationMenu, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SiteNavigationMenu)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SiteNavigationMenu, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SiteNavigationMenu, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SiteNavigationMenu)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SiteNavigationMenu, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SiteNavigationMenu, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SiteNavigationMenu>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SiteNavigationMenu.class.getClassLoader(), SiteNavigationMenu.class,
			ModelWrapper.class);

		try {
			Constructor<SiteNavigationMenu> constructor =
				(Constructor<SiteNavigationMenu>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<SiteNavigationMenu, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SiteNavigationMenu, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SiteNavigationMenu, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<SiteNavigationMenu, Object>>();
		Map<String, BiConsumer<SiteNavigationMenu, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<SiteNavigationMenu, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu, Object uuidObject) {

					siteNavigationMenu.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"siteNavigationMenuId",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getSiteNavigationMenuId();
				}

			});
		attributeSetterBiConsumers.put(
			"siteNavigationMenuId",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu,
					Object siteNavigationMenuIdObject) {

					siteNavigationMenu.setSiteNavigationMenuId(
						(Long)siteNavigationMenuIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu,
					Object groupIdObject) {

					siteNavigationMenu.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu,
					Object companyIdObject) {

					siteNavigationMenu.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu,
					Object userIdObject) {

					siteNavigationMenu.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu,
					Object userNameObject) {

					siteNavigationMenu.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu,
					Object createDateObject) {

					siteNavigationMenu.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu,
					Object modifiedDateObject) {

					siteNavigationMenu.setModifiedDate(
						(Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu, Object nameObject) {

					siteNavigationMenu.setName((String)nameObject);
				}

			});
		attributeGetterFunctions.put(
			"type",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getType();
				}

			});
		attributeSetterBiConsumers.put(
			"type",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu, Object typeObject) {

					siteNavigationMenu.setType((Integer)typeObject);
				}

			});
		attributeGetterFunctions.put(
			"auto",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getAuto();
				}

			});
		attributeSetterBiConsumers.put(
			"auto",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu, Object autoObject) {

					siteNavigationMenu.setAuto((Boolean)autoObject);
				}

			});
		attributeGetterFunctions.put(
			"lastPublishDate",
			new Function<SiteNavigationMenu, Object>() {

				@Override
				public Object apply(SiteNavigationMenu siteNavigationMenu) {
					return siteNavigationMenu.getLastPublishDate();
				}

			});
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			new BiConsumer<SiteNavigationMenu, Object>() {

				@Override
				public void accept(
					SiteNavigationMenu siteNavigationMenu,
					Object lastPublishDateObject) {

					siteNavigationMenu.setLastPublishDate(
						(Date)lastPublishDateObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getSiteNavigationMenuId() {
		return _siteNavigationMenuId;
	}

	@Override
	public void setSiteNavigationMenuId(long siteNavigationMenuId) {
		_siteNavigationMenuId = siteNavigationMenuId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (!_setOriginalType) {
			_setOriginalType = true;

			_originalType = _type;
		}

		_type = type;
	}

	public int getOriginalType() {
		return _originalType;
	}

	@JSON
	@Override
	public boolean getAuto() {
		return _auto;
	}

	@JSON
	@Override
	public boolean isAuto() {
		return _auto;
	}

	@Override
	public void setAuto(boolean auto) {
		_columnBitmask |= AUTO_COLUMN_BITMASK;

		if (!_setOriginalAuto) {
			_setOriginalAuto = true;

			_originalAuto = _auto;
		}

		_auto = auto;
	}

	public boolean getOriginalAuto() {
		return _originalAuto;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(SiteNavigationMenu.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), SiteNavigationMenu.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SiteNavigationMenu toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SiteNavigationMenu>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SiteNavigationMenuImpl siteNavigationMenuImpl =
			new SiteNavigationMenuImpl();

		siteNavigationMenuImpl.setUuid(getUuid());
		siteNavigationMenuImpl.setSiteNavigationMenuId(
			getSiteNavigationMenuId());
		siteNavigationMenuImpl.setGroupId(getGroupId());
		siteNavigationMenuImpl.setCompanyId(getCompanyId());
		siteNavigationMenuImpl.setUserId(getUserId());
		siteNavigationMenuImpl.setUserName(getUserName());
		siteNavigationMenuImpl.setCreateDate(getCreateDate());
		siteNavigationMenuImpl.setModifiedDate(getModifiedDate());
		siteNavigationMenuImpl.setName(getName());
		siteNavigationMenuImpl.setType(getType());
		siteNavigationMenuImpl.setAuto(isAuto());
		siteNavigationMenuImpl.setLastPublishDate(getLastPublishDate());

		siteNavigationMenuImpl.resetOriginalValues();

		return siteNavigationMenuImpl;
	}

	@Override
	public int compareTo(SiteNavigationMenu siteNavigationMenu) {
		long primaryKey = siteNavigationMenu.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SiteNavigationMenu)) {
			return false;
		}

		SiteNavigationMenu siteNavigationMenu = (SiteNavigationMenu)obj;

		long primaryKey = siteNavigationMenu.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		SiteNavigationMenuModelImpl siteNavigationMenuModelImpl = this;

		siteNavigationMenuModelImpl._originalUuid =
			siteNavigationMenuModelImpl._uuid;

		siteNavigationMenuModelImpl._originalGroupId =
			siteNavigationMenuModelImpl._groupId;

		siteNavigationMenuModelImpl._setOriginalGroupId = false;

		siteNavigationMenuModelImpl._originalCompanyId =
			siteNavigationMenuModelImpl._companyId;

		siteNavigationMenuModelImpl._setOriginalCompanyId = false;

		siteNavigationMenuModelImpl._setModifiedDate = false;

		siteNavigationMenuModelImpl._originalName =
			siteNavigationMenuModelImpl._name;

		siteNavigationMenuModelImpl._originalType =
			siteNavigationMenuModelImpl._type;

		siteNavigationMenuModelImpl._setOriginalType = false;

		siteNavigationMenuModelImpl._originalAuto =
			siteNavigationMenuModelImpl._auto;

		siteNavigationMenuModelImpl._setOriginalAuto = false;

		siteNavigationMenuModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SiteNavigationMenu> toCacheModel() {
		SiteNavigationMenuCacheModel siteNavigationMenuCacheModel =
			new SiteNavigationMenuCacheModel();

		siteNavigationMenuCacheModel.uuid = getUuid();

		String uuid = siteNavigationMenuCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			siteNavigationMenuCacheModel.uuid = null;
		}

		siteNavigationMenuCacheModel.siteNavigationMenuId =
			getSiteNavigationMenuId();

		siteNavigationMenuCacheModel.groupId = getGroupId();

		siteNavigationMenuCacheModel.companyId = getCompanyId();

		siteNavigationMenuCacheModel.userId = getUserId();

		siteNavigationMenuCacheModel.userName = getUserName();

		String userName = siteNavigationMenuCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			siteNavigationMenuCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			siteNavigationMenuCacheModel.createDate = createDate.getTime();
		}
		else {
			siteNavigationMenuCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			siteNavigationMenuCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			siteNavigationMenuCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		siteNavigationMenuCacheModel.name = getName();

		String name = siteNavigationMenuCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			siteNavigationMenuCacheModel.name = null;
		}

		siteNavigationMenuCacheModel.type = getType();

		siteNavigationMenuCacheModel.auto = isAuto();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			siteNavigationMenuCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			siteNavigationMenuCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return siteNavigationMenuCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SiteNavigationMenu, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SiteNavigationMenu, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SiteNavigationMenu, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((SiteNavigationMenu)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<SiteNavigationMenu, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SiteNavigationMenu, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SiteNavigationMenu, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SiteNavigationMenu)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SiteNavigationMenu>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _siteNavigationMenuId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _originalName;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private boolean _auto;
	private boolean _originalAuto;
	private boolean _setOriginalAuto;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private SiteNavigationMenu _escapedModel;

}