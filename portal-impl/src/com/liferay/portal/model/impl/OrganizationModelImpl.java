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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationModel;
import com.liferay.portal.kernel.model.OrganizationSoap;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

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
 * The base model implementation for the Organization service. Represents a row in the &quot;Organization_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>OrganizationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OrganizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrganizationImpl
 * @generated
 */
@JSON(strict = true)
public class OrganizationModelImpl
	extends BaseModelImpl<Organization> implements OrganizationModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a organization model instance should use the <code>Organization</code> interface instead.
	 */
	public static final String TABLE_NAME = "Organization_";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"externalReferenceCode", Types.VARCHAR},
		{"organizationId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"parentOrganizationId", Types.BIGINT}, {"treePath", Types.VARCHAR},
		{"name", Types.VARCHAR}, {"type_", Types.VARCHAR},
		{"recursable", Types.BOOLEAN}, {"regionId", Types.BIGINT},
		{"countryId", Types.BIGINT}, {"statusId", Types.BIGINT},
		{"comments", Types.VARCHAR}, {"logoId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("organizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("parentOrganizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("treePath", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("recursable", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("regionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("countryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("comments", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("logoId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Organization_ (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,organizationId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,parentOrganizationId LONG,treePath STRING null,name VARCHAR(100) null,type_ VARCHAR(75) null,recursable BOOLEAN,regionId LONG,countryId LONG,statusId LONG,comments STRING null,logoId LONG)";

	public static final String TABLE_SQL_DROP = "drop table Organization_";

	public static final String ORDER_BY_JPQL =
		" ORDER BY organization.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Organization_.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.Organization"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.Organization"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.Organization"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	public static final long NAME_COLUMN_BITMASK = 4L;

	public static final long ORGANIZATIONID_COLUMN_BITMASK = 8L;

	public static final long PARENTORGANIZATIONID_COLUMN_BITMASK = 16L;

	public static final long TREEPATH_COLUMN_BITMASK = 32L;

	public static final long UUID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Organization toModel(OrganizationSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Organization model = new OrganizationImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setOrganizationId(soapModel.getOrganizationId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setParentOrganizationId(soapModel.getParentOrganizationId());
		model.setTreePath(soapModel.getTreePath());
		model.setName(soapModel.getName());
		model.setType(soapModel.getType());
		model.setRecursable(soapModel.isRecursable());
		model.setRegionId(soapModel.getRegionId());
		model.setCountryId(soapModel.getCountryId());
		model.setStatusId(soapModel.getStatusId());
		model.setComments(soapModel.getComments());
		model.setLogoId(soapModel.getLogoId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Organization> toModels(OrganizationSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Organization> models = new ArrayList<Organization>(
			soapModels.length);

		for (OrganizationSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String MAPPING_TABLE_GROUPS_ORGS_NAME = "Groups_Orgs";

	public static final Object[][] MAPPING_TABLE_GROUPS_ORGS_COLUMNS = {
		{"companyId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"organizationId", Types.BIGINT}
	};

	public static final String MAPPING_TABLE_GROUPS_ORGS_SQL_CREATE =
		"create table Groups_Orgs (companyId LONG not null,groupId LONG not null,organizationId LONG not null,primary key (groupId, organizationId))";

	public static final boolean FINDER_CACHE_ENABLED_GROUPS_ORGS =
		GetterUtil.getBoolean(
			com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.Groups_Orgs"),
			true);

	public static final String MAPPING_TABLE_USERS_ORGS_NAME = "Users_Orgs";

	public static final Object[][] MAPPING_TABLE_USERS_ORGS_COLUMNS = {
		{"companyId", Types.BIGINT}, {"organizationId", Types.BIGINT},
		{"userId", Types.BIGINT}
	};

	public static final String MAPPING_TABLE_USERS_ORGS_SQL_CREATE =
		"create table Users_Orgs (companyId LONG not null,organizationId LONG not null,userId LONG not null,primary key (organizationId, userId))";

	public static final boolean FINDER_CACHE_ENABLED_USERS_ORGS =
		GetterUtil.getBoolean(
			com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.Users_Orgs"),
			true);

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.Organization"));

	public OrganizationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _organizationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOrganizationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _organizationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Organization.class;
	}

	@Override
	public String getModelClassName() {
		return Organization.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Organization, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Organization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Organization, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((Organization)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Organization, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Organization, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Organization)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Organization, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Organization, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Organization>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Organization.class.getClassLoader(), Organization.class,
			ModelWrapper.class);

		try {
			Constructor<Organization> constructor =
				(Constructor<Organization>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Organization, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Organization, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Organization, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Organization, Object>>();
		Map<String, BiConsumer<Organization, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Organization, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getMvccVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"mvccVersion",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object mvccVersionObject) {

					organization.setMvccVersion((Long)mvccVersionObject);
				}

			});
		attributeGetterFunctions.put(
			"uuid",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object uuidObject) {

					organization.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"externalReferenceCode",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getExternalReferenceCode();
				}

			});
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization,
					Object externalReferenceCodeObject) {

					organization.setExternalReferenceCode(
						(String)externalReferenceCodeObject);
				}

			});
		attributeGetterFunctions.put(
			"organizationId",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getOrganizationId();
				}

			});
		attributeSetterBiConsumers.put(
			"organizationId",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object organizationIdObject) {

					organization.setOrganizationId((Long)organizationIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object companyIdObject) {

					organization.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object userIdObject) {

					organization.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object userNameObject) {

					organization.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object createDateObject) {

					organization.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object modifiedDateObject) {

					organization.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"parentOrganizationId",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getParentOrganizationId();
				}

			});
		attributeSetterBiConsumers.put(
			"parentOrganizationId",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization,
					Object parentOrganizationIdObject) {

					organization.setParentOrganizationId(
						(Long)parentOrganizationIdObject);
				}

			});
		attributeGetterFunctions.put(
			"treePath",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getTreePath();
				}

			});
		attributeSetterBiConsumers.put(
			"treePath",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object treePathObject) {

					organization.setTreePath((String)treePathObject);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object nameObject) {

					organization.setName((String)nameObject);
				}

			});
		attributeGetterFunctions.put(
			"type",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getType();
				}

			});
		attributeSetterBiConsumers.put(
			"type",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object typeObject) {

					organization.setType((String)typeObject);
				}

			});
		attributeGetterFunctions.put(
			"recursable",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getRecursable();
				}

			});
		attributeSetterBiConsumers.put(
			"recursable",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object recursableObject) {

					organization.setRecursable((Boolean)recursableObject);
				}

			});
		attributeGetterFunctions.put(
			"regionId",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getRegionId();
				}

			});
		attributeSetterBiConsumers.put(
			"regionId",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object regionIdObject) {

					organization.setRegionId((Long)regionIdObject);
				}

			});
		attributeGetterFunctions.put(
			"countryId",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getCountryId();
				}

			});
		attributeSetterBiConsumers.put(
			"countryId",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object countryIdObject) {

					organization.setCountryId((Long)countryIdObject);
				}

			});
		attributeGetterFunctions.put(
			"statusId",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getStatusId();
				}

			});
		attributeSetterBiConsumers.put(
			"statusId",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object statusIdObject) {

					organization.setStatusId((Long)statusIdObject);
				}

			});
		attributeGetterFunctions.put(
			"comments",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getComments();
				}

			});
		attributeSetterBiConsumers.put(
			"comments",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object commentsObject) {

					organization.setComments((String)commentsObject);
				}

			});
		attributeGetterFunctions.put(
			"logoId",
			new Function<Organization, Object>() {

				@Override
				public Object apply(Organization organization) {
					return organization.getLogoId();
				}

			});
		attributeSetterBiConsumers.put(
			"logoId",
			new BiConsumer<Organization, Object>() {

				@Override
				public void accept(
					Organization organization, Object logoIdObject) {

					organization.setLogoId((Long)logoIdObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
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
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		_columnBitmask |= EXTERNALREFERENCECODE_COLUMN_BITMASK;

		if (_originalExternalReferenceCode == null) {
			_originalExternalReferenceCode = _externalReferenceCode;
		}

		_externalReferenceCode = externalReferenceCode;
	}

	public String getOriginalExternalReferenceCode() {
		return GetterUtil.getString(_originalExternalReferenceCode);
	}

	@JSON
	@Override
	public long getOrganizationId() {
		return _organizationId;
	}

	@Override
	public void setOrganizationId(long organizationId) {
		_columnBitmask |= ORGANIZATIONID_COLUMN_BITMASK;

		if (!_setOriginalOrganizationId) {
			_setOriginalOrganizationId = true;

			_originalOrganizationId = _organizationId;
		}

		_organizationId = organizationId;
	}

	public long getOriginalOrganizationId() {
		return _originalOrganizationId;
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
	public long getParentOrganizationId() {
		return _parentOrganizationId;
	}

	@Override
	public void setParentOrganizationId(long parentOrganizationId) {
		_columnBitmask |= PARENTORGANIZATIONID_COLUMN_BITMASK;

		if (!_setOriginalParentOrganizationId) {
			_setOriginalParentOrganizationId = true;

			_originalParentOrganizationId = _parentOrganizationId;
		}

		_parentOrganizationId = parentOrganizationId;
	}

	public long getOriginalParentOrganizationId() {
		return _originalParentOrganizationId;
	}

	@JSON
	@Override
	public String getTreePath() {
		if (_treePath == null) {
			return "";
		}
		else {
			return _treePath;
		}
	}

	@Override
	public void setTreePath(String treePath) {
		_columnBitmask |= TREEPATH_COLUMN_BITMASK;

		if (_originalTreePath == null) {
			_originalTreePath = _treePath;
		}

		_treePath = treePath;
	}

	public String getOriginalTreePath() {
		return GetterUtil.getString(_originalTreePath);
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
		_columnBitmask = -1L;

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
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_type = type;
	}

	@JSON
	@Override
	public boolean getRecursable() {
		return _recursable;
	}

	@JSON
	@Override
	public boolean isRecursable() {
		return _recursable;
	}

	@Override
	public void setRecursable(boolean recursable) {
		_recursable = recursable;
	}

	@JSON
	@Override
	public long getRegionId() {
		return _regionId;
	}

	@Override
	public void setRegionId(long regionId) {
		_regionId = regionId;
	}

	@JSON
	@Override
	public long getCountryId() {
		return _countryId;
	}

	@Override
	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	@JSON
	@Override
	public long getStatusId() {
		return _statusId;
	}

	@Override
	public void setStatusId(long statusId) {
		_statusId = statusId;
	}

	@JSON
	@Override
	public String getComments() {
		if (_comments == null) {
			return "";
		}
		else {
			return _comments;
		}
	}

	@Override
	public void setComments(String comments) {
		_comments = comments;
	}

	@JSON
	@Override
	public long getLogoId() {
		return _logoId;
	}

	@Override
	public void setLogoId(long logoId) {
		_logoId = logoId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Organization.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Organization.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Organization toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Organization>
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
		OrganizationImpl organizationImpl = new OrganizationImpl();

		organizationImpl.setMvccVersion(getMvccVersion());
		organizationImpl.setUuid(getUuid());
		organizationImpl.setExternalReferenceCode(getExternalReferenceCode());
		organizationImpl.setOrganizationId(getOrganizationId());
		organizationImpl.setCompanyId(getCompanyId());
		organizationImpl.setUserId(getUserId());
		organizationImpl.setUserName(getUserName());
		organizationImpl.setCreateDate(getCreateDate());
		organizationImpl.setModifiedDate(getModifiedDate());
		organizationImpl.setParentOrganizationId(getParentOrganizationId());
		organizationImpl.setTreePath(getTreePath());
		organizationImpl.setName(getName());
		organizationImpl.setType(getType());
		organizationImpl.setRecursable(isRecursable());
		organizationImpl.setRegionId(getRegionId());
		organizationImpl.setCountryId(getCountryId());
		organizationImpl.setStatusId(getStatusId());
		organizationImpl.setComments(getComments());
		organizationImpl.setLogoId(getLogoId());

		organizationImpl.resetOriginalValues();

		return organizationImpl;
	}

	@Override
	public int compareTo(Organization organization) {
		int value = 0;

		value = getName().compareTo(organization.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Organization)) {
			return false;
		}

		Organization organization = (Organization)obj;

		long primaryKey = organization.getPrimaryKey();

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
		OrganizationModelImpl organizationModelImpl = this;

		organizationModelImpl._originalUuid = organizationModelImpl._uuid;

		organizationModelImpl._originalExternalReferenceCode =
			organizationModelImpl._externalReferenceCode;

		organizationModelImpl._originalOrganizationId =
			organizationModelImpl._organizationId;

		organizationModelImpl._setOriginalOrganizationId = false;

		organizationModelImpl._originalCompanyId =
			organizationModelImpl._companyId;

		organizationModelImpl._setOriginalCompanyId = false;

		organizationModelImpl._setModifiedDate = false;

		organizationModelImpl._originalParentOrganizationId =
			organizationModelImpl._parentOrganizationId;

		organizationModelImpl._setOriginalParentOrganizationId = false;

		organizationModelImpl._originalTreePath =
			organizationModelImpl._treePath;

		organizationModelImpl._originalName = organizationModelImpl._name;

		organizationModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Organization> toCacheModel() {
		OrganizationCacheModel organizationCacheModel =
			new OrganizationCacheModel();

		organizationCacheModel.mvccVersion = getMvccVersion();

		organizationCacheModel.uuid = getUuid();

		String uuid = organizationCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			organizationCacheModel.uuid = null;
		}

		organizationCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			organizationCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			organizationCacheModel.externalReferenceCode = null;
		}

		organizationCacheModel.organizationId = getOrganizationId();

		organizationCacheModel.companyId = getCompanyId();

		organizationCacheModel.userId = getUserId();

		organizationCacheModel.userName = getUserName();

		String userName = organizationCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			organizationCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			organizationCacheModel.createDate = createDate.getTime();
		}
		else {
			organizationCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			organizationCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			organizationCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		organizationCacheModel.parentOrganizationId = getParentOrganizationId();

		organizationCacheModel.treePath = getTreePath();

		String treePath = organizationCacheModel.treePath;

		if ((treePath != null) && (treePath.length() == 0)) {
			organizationCacheModel.treePath = null;
		}

		organizationCacheModel.name = getName();

		String name = organizationCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			organizationCacheModel.name = null;
		}

		organizationCacheModel.type = getType();

		String type = organizationCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			organizationCacheModel.type = null;
		}

		organizationCacheModel.recursable = isRecursable();

		organizationCacheModel.regionId = getRegionId();

		organizationCacheModel.countryId = getCountryId();

		organizationCacheModel.statusId = getStatusId();

		organizationCacheModel.comments = getComments();

		String comments = organizationCacheModel.comments;

		if ((comments != null) && (comments.length() == 0)) {
			organizationCacheModel.comments = null;
		}

		organizationCacheModel.logoId = getLogoId();

		return organizationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Organization, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Organization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Organization, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Organization)this));
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
		Map<String, Function<Organization, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Organization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Organization, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Organization)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Organization>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _organizationId;
	private long _originalOrganizationId;
	private boolean _setOriginalOrganizationId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _parentOrganizationId;
	private long _originalParentOrganizationId;
	private boolean _setOriginalParentOrganizationId;
	private String _treePath;
	private String _originalTreePath;
	private String _name;
	private String _originalName;
	private String _type;
	private boolean _recursable;
	private long _regionId;
	private long _countryId;
	private long _statusId;
	private String _comments;
	private long _logoId;
	private long _columnBitmask;
	private Organization _escapedModel;

}