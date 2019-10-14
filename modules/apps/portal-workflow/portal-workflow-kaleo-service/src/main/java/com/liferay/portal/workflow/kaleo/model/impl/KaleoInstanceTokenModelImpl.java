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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceTokenModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the KaleoInstanceToken service. Represents a row in the &quot;KaleoInstanceToken&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>KaleoInstanceTokenModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoInstanceTokenImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceTokenImpl
 * @generated
 */
public class KaleoInstanceTokenModelImpl
	extends BaseModelImpl<KaleoInstanceToken>
	implements KaleoInstanceTokenModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo instance token model instance should use the <code>KaleoInstanceToken</code> interface instead.
	 */
	public static final String TABLE_NAME = "KaleoInstanceToken";

	public static final Object[][] TABLE_COLUMNS = {
		{"kaleoInstanceTokenId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"kaleoDefinitionVersionId", Types.BIGINT},
		{"kaleoInstanceId", Types.BIGINT},
		{"parentKaleoInstanceTokenId", Types.BIGINT},
		{"currentKaleoNodeId", Types.BIGINT},
		{"currentKaleoNodeName", Types.VARCHAR}, {"className", Types.VARCHAR},
		{"classPK", Types.BIGINT}, {"completed", Types.BOOLEAN},
		{"completionDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("kaleoInstanceTokenId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("parentKaleoInstanceTokenId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("currentKaleoNodeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("currentKaleoNodeName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("className", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("completed", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("completionDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table KaleoInstanceToken (kaleoInstanceTokenId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoDefinitionVersionId LONG,kaleoInstanceId LONG,parentKaleoInstanceTokenId LONG,currentKaleoNodeId LONG,currentKaleoNodeName VARCHAR(200) null,className VARCHAR(200) null,classPK LONG,completed BOOLEAN,completionDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table KaleoInstanceToken";

	public static final String ORDER_BY_JPQL =
		" ORDER BY kaleoInstanceToken.kaleoInstanceTokenId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY KaleoInstanceToken.kaleoInstanceTokenId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long COMPLETIONDATE_COLUMN_BITMASK = 2L;

	public static final long KALEODEFINITIONVERSIONID_COLUMN_BITMASK = 4L;

	public static final long KALEOINSTANCEID_COLUMN_BITMASK = 8L;

	public static final long PARENTKALEOINSTANCETOKENID_COLUMN_BITMASK = 16L;

	public static final long KALEOINSTANCETOKENID_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken"));

	public KaleoInstanceTokenModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoInstanceTokenId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoInstanceTokenId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoInstanceTokenId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoInstanceToken.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoInstanceToken.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<KaleoInstanceToken, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<KaleoInstanceToken, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoInstanceToken, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((KaleoInstanceToken)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<KaleoInstanceToken, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<KaleoInstanceToken, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(KaleoInstanceToken)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<KaleoInstanceToken, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<KaleoInstanceToken, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, KaleoInstanceToken>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			KaleoInstanceToken.class.getClassLoader(), KaleoInstanceToken.class,
			ModelWrapper.class);

		try {
			Constructor<KaleoInstanceToken> constructor =
				(Constructor<KaleoInstanceToken>)proxyClass.getConstructor(
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

	private static final Map<String, Function<KaleoInstanceToken, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<KaleoInstanceToken, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<KaleoInstanceToken, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<KaleoInstanceToken, Object>>();
		Map<String, BiConsumer<KaleoInstanceToken, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<KaleoInstanceToken, ?>>();

		attributeGetterFunctions.put(
			"kaleoInstanceTokenId",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getKaleoInstanceTokenId();
				}

			});
		attributeSetterBiConsumers.put(
			"kaleoInstanceTokenId",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object kaleoInstanceTokenIdObject) {

					kaleoInstanceToken.setKaleoInstanceTokenId(
						(Long)kaleoInstanceTokenIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object groupIdObject) {

					kaleoInstanceToken.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object companyIdObject) {

					kaleoInstanceToken.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object userIdObject) {

					kaleoInstanceToken.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object userNameObject) {

					kaleoInstanceToken.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object createDateObject) {

					kaleoInstanceToken.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object modifiedDateObject) {

					kaleoInstanceToken.setModifiedDate(
						(Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"kaleoDefinitionVersionId",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getKaleoDefinitionVersionId();
				}

			});
		attributeSetterBiConsumers.put(
			"kaleoDefinitionVersionId",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object kaleoDefinitionVersionIdObject) {

					kaleoInstanceToken.setKaleoDefinitionVersionId(
						(Long)kaleoDefinitionVersionIdObject);
				}

			});
		attributeGetterFunctions.put(
			"kaleoInstanceId",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getKaleoInstanceId();
				}

			});
		attributeSetterBiConsumers.put(
			"kaleoInstanceId",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object kaleoInstanceIdObject) {

					kaleoInstanceToken.setKaleoInstanceId(
						(Long)kaleoInstanceIdObject);
				}

			});
		attributeGetterFunctions.put(
			"parentKaleoInstanceTokenId",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getParentKaleoInstanceTokenId();
				}

			});
		attributeSetterBiConsumers.put(
			"parentKaleoInstanceTokenId",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object parentKaleoInstanceTokenIdObject) {

					kaleoInstanceToken.setParentKaleoInstanceTokenId(
						(Long)parentKaleoInstanceTokenIdObject);
				}

			});
		attributeGetterFunctions.put(
			"currentKaleoNodeId",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getCurrentKaleoNodeId();
				}

			});
		attributeSetterBiConsumers.put(
			"currentKaleoNodeId",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object currentKaleoNodeIdObject) {

					kaleoInstanceToken.setCurrentKaleoNodeId(
						(Long)currentKaleoNodeIdObject);
				}

			});
		attributeGetterFunctions.put(
			"currentKaleoNodeName",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getCurrentKaleoNodeName();
				}

			});
		attributeSetterBiConsumers.put(
			"currentKaleoNodeName",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object currentKaleoNodeNameObject) {

					kaleoInstanceToken.setCurrentKaleoNodeName(
						(String)currentKaleoNodeNameObject);
				}

			});
		attributeGetterFunctions.put(
			"className",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getClassName();
				}

			});
		attributeSetterBiConsumers.put(
			"className",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object classNameObject) {

					kaleoInstanceToken.setClassName((String)classNameObject);
				}

			});
		attributeGetterFunctions.put(
			"classPK",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getClassPK();
				}

			});
		attributeSetterBiConsumers.put(
			"classPK",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object classPKObject) {

					kaleoInstanceToken.setClassPK((Long)classPKObject);
				}

			});
		attributeGetterFunctions.put(
			"completed",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getCompleted();
				}

			});
		attributeSetterBiConsumers.put(
			"completed",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object completedObject) {

					kaleoInstanceToken.setCompleted((Boolean)completedObject);
				}

			});
		attributeGetterFunctions.put(
			"completionDate",
			new Function<KaleoInstanceToken, Object>() {

				@Override
				public Object apply(KaleoInstanceToken kaleoInstanceToken) {
					return kaleoInstanceToken.getCompletionDate();
				}

			});
		attributeSetterBiConsumers.put(
			"completionDate",
			new BiConsumer<KaleoInstanceToken, Object>() {

				@Override
				public void accept(
					KaleoInstanceToken kaleoInstanceToken,
					Object completionDateObject) {

					kaleoInstanceToken.setCompletionDate(
						(Date)completionDateObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceTokenId;
	}

	@Override
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_columnBitmask = -1L;

		_kaleoInstanceTokenId = kaleoInstanceTokenId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

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

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

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

	@Override
	public long getKaleoDefinitionVersionId() {
		return _kaleoDefinitionVersionId;
	}

	@Override
	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId) {
		_columnBitmask |= KALEODEFINITIONVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalKaleoDefinitionVersionId) {
			_setOriginalKaleoDefinitionVersionId = true;

			_originalKaleoDefinitionVersionId = _kaleoDefinitionVersionId;
		}

		_kaleoDefinitionVersionId = kaleoDefinitionVersionId;
	}

	public long getOriginalKaleoDefinitionVersionId() {
		return _originalKaleoDefinitionVersionId;
	}

	@Override
	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_columnBitmask |= KALEOINSTANCEID_COLUMN_BITMASK;

		if (!_setOriginalKaleoInstanceId) {
			_setOriginalKaleoInstanceId = true;

			_originalKaleoInstanceId = _kaleoInstanceId;
		}

		_kaleoInstanceId = kaleoInstanceId;
	}

	public long getOriginalKaleoInstanceId() {
		return _originalKaleoInstanceId;
	}

	@Override
	public long getParentKaleoInstanceTokenId() {
		return _parentKaleoInstanceTokenId;
	}

	@Override
	public void setParentKaleoInstanceTokenId(long parentKaleoInstanceTokenId) {
		_columnBitmask |= PARENTKALEOINSTANCETOKENID_COLUMN_BITMASK;

		if (!_setOriginalParentKaleoInstanceTokenId) {
			_setOriginalParentKaleoInstanceTokenId = true;

			_originalParentKaleoInstanceTokenId = _parentKaleoInstanceTokenId;
		}

		_parentKaleoInstanceTokenId = parentKaleoInstanceTokenId;
	}

	public long getOriginalParentKaleoInstanceTokenId() {
		return _originalParentKaleoInstanceTokenId;
	}

	@Override
	public long getCurrentKaleoNodeId() {
		return _currentKaleoNodeId;
	}

	@Override
	public void setCurrentKaleoNodeId(long currentKaleoNodeId) {
		_currentKaleoNodeId = currentKaleoNodeId;
	}

	@Override
	public String getCurrentKaleoNodeName() {
		if (_currentKaleoNodeName == null) {
			return "";
		}
		else {
			return _currentKaleoNodeName;
		}
	}

	@Override
	public void setCurrentKaleoNodeName(String currentKaleoNodeName) {
		_currentKaleoNodeName = currentKaleoNodeName;
	}

	@Override
	public String getClassName() {
		if (_className == null) {
			return "";
		}
		else {
			return _className;
		}
	}

	@Override
	public void setClassName(String className) {
		_className = className;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	@Override
	public boolean getCompleted() {
		return _completed;
	}

	@Override
	public boolean isCompleted() {
		return _completed;
	}

	@Override
	public void setCompleted(boolean completed) {
		_completed = completed;
	}

	@Override
	public Date getCompletionDate() {
		return _completionDate;
	}

	@Override
	public void setCompletionDate(Date completionDate) {
		_columnBitmask |= COMPLETIONDATE_COLUMN_BITMASK;

		if (_originalCompletionDate == null) {
			_originalCompletionDate = _completionDate;
		}

		_completionDate = completionDate;
	}

	public Date getOriginalCompletionDate() {
		return _originalCompletionDate;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), KaleoInstanceToken.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoInstanceToken toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, KaleoInstanceToken>
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
		KaleoInstanceTokenImpl kaleoInstanceTokenImpl =
			new KaleoInstanceTokenImpl();

		kaleoInstanceTokenImpl.setKaleoInstanceTokenId(
			getKaleoInstanceTokenId());
		kaleoInstanceTokenImpl.setGroupId(getGroupId());
		kaleoInstanceTokenImpl.setCompanyId(getCompanyId());
		kaleoInstanceTokenImpl.setUserId(getUserId());
		kaleoInstanceTokenImpl.setUserName(getUserName());
		kaleoInstanceTokenImpl.setCreateDate(getCreateDate());
		kaleoInstanceTokenImpl.setModifiedDate(getModifiedDate());
		kaleoInstanceTokenImpl.setKaleoDefinitionVersionId(
			getKaleoDefinitionVersionId());
		kaleoInstanceTokenImpl.setKaleoInstanceId(getKaleoInstanceId());
		kaleoInstanceTokenImpl.setParentKaleoInstanceTokenId(
			getParentKaleoInstanceTokenId());
		kaleoInstanceTokenImpl.setCurrentKaleoNodeId(getCurrentKaleoNodeId());
		kaleoInstanceTokenImpl.setCurrentKaleoNodeName(
			getCurrentKaleoNodeName());
		kaleoInstanceTokenImpl.setClassName(getClassName());
		kaleoInstanceTokenImpl.setClassPK(getClassPK());
		kaleoInstanceTokenImpl.setCompleted(isCompleted());
		kaleoInstanceTokenImpl.setCompletionDate(getCompletionDate());

		kaleoInstanceTokenImpl.resetOriginalValues();

		return kaleoInstanceTokenImpl;
	}

	@Override
	public int compareTo(KaleoInstanceToken kaleoInstanceToken) {
		int value = 0;

		if (getKaleoInstanceTokenId() <
				kaleoInstanceToken.getKaleoInstanceTokenId()) {

			value = -1;
		}
		else if (getKaleoInstanceTokenId() >
					kaleoInstanceToken.getKaleoInstanceTokenId()) {

			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof KaleoInstanceToken)) {
			return false;
		}

		KaleoInstanceToken kaleoInstanceToken = (KaleoInstanceToken)obj;

		long primaryKey = kaleoInstanceToken.getPrimaryKey();

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
		KaleoInstanceTokenModelImpl kaleoInstanceTokenModelImpl = this;

		kaleoInstanceTokenModelImpl._originalCompanyId =
			kaleoInstanceTokenModelImpl._companyId;

		kaleoInstanceTokenModelImpl._setOriginalCompanyId = false;

		kaleoInstanceTokenModelImpl._setModifiedDate = false;

		kaleoInstanceTokenModelImpl._originalKaleoDefinitionVersionId =
			kaleoInstanceTokenModelImpl._kaleoDefinitionVersionId;

		kaleoInstanceTokenModelImpl._setOriginalKaleoDefinitionVersionId =
			false;

		kaleoInstanceTokenModelImpl._originalKaleoInstanceId =
			kaleoInstanceTokenModelImpl._kaleoInstanceId;

		kaleoInstanceTokenModelImpl._setOriginalKaleoInstanceId = false;

		kaleoInstanceTokenModelImpl._originalParentKaleoInstanceTokenId =
			kaleoInstanceTokenModelImpl._parentKaleoInstanceTokenId;

		kaleoInstanceTokenModelImpl._setOriginalParentKaleoInstanceTokenId =
			false;

		kaleoInstanceTokenModelImpl._originalCompletionDate =
			kaleoInstanceTokenModelImpl._completionDate;

		kaleoInstanceTokenModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<KaleoInstanceToken> toCacheModel() {
		KaleoInstanceTokenCacheModel kaleoInstanceTokenCacheModel =
			new KaleoInstanceTokenCacheModel();

		kaleoInstanceTokenCacheModel.kaleoInstanceTokenId =
			getKaleoInstanceTokenId();

		kaleoInstanceTokenCacheModel.groupId = getGroupId();

		kaleoInstanceTokenCacheModel.companyId = getCompanyId();

		kaleoInstanceTokenCacheModel.userId = getUserId();

		kaleoInstanceTokenCacheModel.userName = getUserName();

		String userName = kaleoInstanceTokenCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoInstanceTokenCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoInstanceTokenCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoInstanceTokenCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoInstanceTokenCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoInstanceTokenCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoInstanceTokenCacheModel.kaleoDefinitionVersionId =
			getKaleoDefinitionVersionId();

		kaleoInstanceTokenCacheModel.kaleoInstanceId = getKaleoInstanceId();

		kaleoInstanceTokenCacheModel.parentKaleoInstanceTokenId =
			getParentKaleoInstanceTokenId();

		kaleoInstanceTokenCacheModel.currentKaleoNodeId =
			getCurrentKaleoNodeId();

		kaleoInstanceTokenCacheModel.currentKaleoNodeName =
			getCurrentKaleoNodeName();

		String currentKaleoNodeName =
			kaleoInstanceTokenCacheModel.currentKaleoNodeName;

		if ((currentKaleoNodeName != null) &&
			(currentKaleoNodeName.length() == 0)) {

			kaleoInstanceTokenCacheModel.currentKaleoNodeName = null;
		}

		kaleoInstanceTokenCacheModel.className = getClassName();

		String className = kaleoInstanceTokenCacheModel.className;

		if ((className != null) && (className.length() == 0)) {
			kaleoInstanceTokenCacheModel.className = null;
		}

		kaleoInstanceTokenCacheModel.classPK = getClassPK();

		kaleoInstanceTokenCacheModel.completed = isCompleted();

		Date completionDate = getCompletionDate();

		if (completionDate != null) {
			kaleoInstanceTokenCacheModel.completionDate =
				completionDate.getTime();
		}
		else {
			kaleoInstanceTokenCacheModel.completionDate = Long.MIN_VALUE;
		}

		return kaleoInstanceTokenCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<KaleoInstanceToken, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<KaleoInstanceToken, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoInstanceToken, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((KaleoInstanceToken)this));
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
		Map<String, Function<KaleoInstanceToken, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<KaleoInstanceToken, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoInstanceToken, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((KaleoInstanceToken)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, KaleoInstanceToken>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _kaleoInstanceTokenId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _kaleoDefinitionVersionId;
	private long _originalKaleoDefinitionVersionId;
	private boolean _setOriginalKaleoDefinitionVersionId;
	private long _kaleoInstanceId;
	private long _originalKaleoInstanceId;
	private boolean _setOriginalKaleoInstanceId;
	private long _parentKaleoInstanceTokenId;
	private long _originalParentKaleoInstanceTokenId;
	private boolean _setOriginalParentKaleoInstanceTokenId;
	private long _currentKaleoNodeId;
	private String _currentKaleoNodeName;
	private String _className;
	private long _classPK;
	private boolean _completed;
	private Date _completionDate;
	private Date _originalCompletionDate;
	private long _columnBitmask;
	private KaleoInstanceToken _escapedModel;

}