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

package com.liferay.changeset.model.impl;

import com.liferay.changeset.model.ChangesetEntry;
import com.liferay.changeset.model.ChangesetEntryModel;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the ChangesetEntry service. Represents a row in the &quot;ChangesetEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ChangesetEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ChangesetEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ChangesetEntryImpl
 * @generated
 */
public class ChangesetEntryModelImpl
	extends BaseModelImpl<ChangesetEntry> implements ChangesetEntryModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a changeset entry model instance should use the <code>ChangesetEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "ChangesetEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"changesetEntryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"changesetCollectionId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("changesetEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("changesetCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ChangesetEntry (changesetEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,changesetCollectionId LONG,classNameId LONG,classPK LONG)";

	public static final String TABLE_SQL_DROP = "drop table ChangesetEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY changesetEntry.changesetEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ChangesetEntry.changesetEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.changeset.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.changeset.model.ChangesetEntry"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.changeset.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.changeset.model.ChangesetEntry"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.changeset.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.changeset.model.ChangesetEntry"),
		true);

	public static final long CHANGESETCOLLECTIONID_COLUMN_BITMASK = 1L;

	public static final long CLASSNAMEID_COLUMN_BITMASK = 2L;

	public static final long CLASSPK_COLUMN_BITMASK = 4L;

	public static final long COMPANYID_COLUMN_BITMASK = 8L;

	public static final long GROUPID_COLUMN_BITMASK = 16L;

	public static final long CHANGESETENTRYID_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.changeset.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.changeset.model.ChangesetEntry"));

	public ChangesetEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _changesetEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setChangesetEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _changesetEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ChangesetEntry.class;
	}

	@Override
	public String getModelClassName() {
		return ChangesetEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ChangesetEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ChangesetEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ChangesetEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ChangesetEntry)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ChangesetEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ChangesetEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ChangesetEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ChangesetEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ChangesetEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ChangesetEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ChangesetEntry.class.getClassLoader(), ChangesetEntry.class,
			ModelWrapper.class);

		try {
			Constructor<ChangesetEntry> constructor =
				(Constructor<ChangesetEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ChangesetEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ChangesetEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ChangesetEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ChangesetEntry, Object>>();
		Map<String, BiConsumer<ChangesetEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ChangesetEntry, ?>>();

		attributeGetterFunctions.put(
			"changesetEntryId",
			new Function<ChangesetEntry, Object>() {

				@Override
				public Object apply(ChangesetEntry changesetEntry) {
					return changesetEntry.getChangesetEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"changesetEntryId",
			new BiConsumer<ChangesetEntry, Object>() {

				@Override
				public void accept(
					ChangesetEntry changesetEntry,
					Object changesetEntryIdObject) {

					changesetEntry.setChangesetEntryId(
						(Long)changesetEntryIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<ChangesetEntry, Object>() {

				@Override
				public Object apply(ChangesetEntry changesetEntry) {
					return changesetEntry.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<ChangesetEntry, Object>() {

				@Override
				public void accept(
					ChangesetEntry changesetEntry, Object groupIdObject) {

					changesetEntry.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<ChangesetEntry, Object>() {

				@Override
				public Object apply(ChangesetEntry changesetEntry) {
					return changesetEntry.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<ChangesetEntry, Object>() {

				@Override
				public void accept(
					ChangesetEntry changesetEntry, Object companyIdObject) {

					changesetEntry.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<ChangesetEntry, Object>() {

				@Override
				public Object apply(ChangesetEntry changesetEntry) {
					return changesetEntry.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<ChangesetEntry, Object>() {

				@Override
				public void accept(
					ChangesetEntry changesetEntry, Object userIdObject) {

					changesetEntry.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<ChangesetEntry, Object>() {

				@Override
				public Object apply(ChangesetEntry changesetEntry) {
					return changesetEntry.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<ChangesetEntry, Object>() {

				@Override
				public void accept(
					ChangesetEntry changesetEntry, Object userNameObject) {

					changesetEntry.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<ChangesetEntry, Object>() {

				@Override
				public Object apply(ChangesetEntry changesetEntry) {
					return changesetEntry.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<ChangesetEntry, Object>() {

				@Override
				public void accept(
					ChangesetEntry changesetEntry, Object createDateObject) {

					changesetEntry.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<ChangesetEntry, Object>() {

				@Override
				public Object apply(ChangesetEntry changesetEntry) {
					return changesetEntry.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<ChangesetEntry, Object>() {

				@Override
				public void accept(
					ChangesetEntry changesetEntry, Object modifiedDateObject) {

					changesetEntry.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"changesetCollectionId",
			new Function<ChangesetEntry, Object>() {

				@Override
				public Object apply(ChangesetEntry changesetEntry) {
					return changesetEntry.getChangesetCollectionId();
				}

			});
		attributeSetterBiConsumers.put(
			"changesetCollectionId",
			new BiConsumer<ChangesetEntry, Object>() {

				@Override
				public void accept(
					ChangesetEntry changesetEntry,
					Object changesetCollectionIdObject) {

					changesetEntry.setChangesetCollectionId(
						(Long)changesetCollectionIdObject);
				}

			});
		attributeGetterFunctions.put(
			"classNameId",
			new Function<ChangesetEntry, Object>() {

				@Override
				public Object apply(ChangesetEntry changesetEntry) {
					return changesetEntry.getClassNameId();
				}

			});
		attributeSetterBiConsumers.put(
			"classNameId",
			new BiConsumer<ChangesetEntry, Object>() {

				@Override
				public void accept(
					ChangesetEntry changesetEntry, Object classNameIdObject) {

					changesetEntry.setClassNameId((Long)classNameIdObject);
				}

			});
		attributeGetterFunctions.put(
			"classPK",
			new Function<ChangesetEntry, Object>() {

				@Override
				public Object apply(ChangesetEntry changesetEntry) {
					return changesetEntry.getClassPK();
				}

			});
		attributeSetterBiConsumers.put(
			"classPK",
			new BiConsumer<ChangesetEntry, Object>() {

				@Override
				public void accept(
					ChangesetEntry changesetEntry, Object classPKObject) {

					changesetEntry.setClassPK((Long)classPKObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getChangesetEntryId() {
		return _changesetEntryId;
	}

	@Override
	public void setChangesetEntryId(long changesetEntryId) {
		_changesetEntryId = changesetEntryId;
	}

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
	public long getChangesetCollectionId() {
		return _changesetCollectionId;
	}

	@Override
	public void setChangesetCollectionId(long changesetCollectionId) {
		_columnBitmask |= CHANGESETCOLLECTIONID_COLUMN_BITMASK;

		if (!_setOriginalChangesetCollectionId) {
			_setOriginalChangesetCollectionId = true;

			_originalChangesetCollectionId = _changesetCollectionId;
		}

		_changesetCollectionId = changesetCollectionId;
	}

	public long getOriginalChangesetCollectionId() {
		return _originalChangesetCollectionId;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ChangesetEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ChangesetEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ChangesetEntry>
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
		ChangesetEntryImpl changesetEntryImpl = new ChangesetEntryImpl();

		changesetEntryImpl.setChangesetEntryId(getChangesetEntryId());
		changesetEntryImpl.setGroupId(getGroupId());
		changesetEntryImpl.setCompanyId(getCompanyId());
		changesetEntryImpl.setUserId(getUserId());
		changesetEntryImpl.setUserName(getUserName());
		changesetEntryImpl.setCreateDate(getCreateDate());
		changesetEntryImpl.setModifiedDate(getModifiedDate());
		changesetEntryImpl.setChangesetCollectionId(getChangesetCollectionId());
		changesetEntryImpl.setClassNameId(getClassNameId());
		changesetEntryImpl.setClassPK(getClassPK());

		changesetEntryImpl.resetOriginalValues();

		return changesetEntryImpl;
	}

	@Override
	public int compareTo(ChangesetEntry changesetEntry) {
		long primaryKey = changesetEntry.getPrimaryKey();

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

		if (!(obj instanceof ChangesetEntry)) {
			return false;
		}

		ChangesetEntry changesetEntry = (ChangesetEntry)obj;

		long primaryKey = changesetEntry.getPrimaryKey();

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
		ChangesetEntryModelImpl changesetEntryModelImpl = this;

		changesetEntryModelImpl._originalGroupId =
			changesetEntryModelImpl._groupId;

		changesetEntryModelImpl._setOriginalGroupId = false;

		changesetEntryModelImpl._originalCompanyId =
			changesetEntryModelImpl._companyId;

		changesetEntryModelImpl._setOriginalCompanyId = false;

		changesetEntryModelImpl._setModifiedDate = false;

		changesetEntryModelImpl._originalChangesetCollectionId =
			changesetEntryModelImpl._changesetCollectionId;

		changesetEntryModelImpl._setOriginalChangesetCollectionId = false;

		changesetEntryModelImpl._originalClassNameId =
			changesetEntryModelImpl._classNameId;

		changesetEntryModelImpl._setOriginalClassNameId = false;

		changesetEntryModelImpl._originalClassPK =
			changesetEntryModelImpl._classPK;

		changesetEntryModelImpl._setOriginalClassPK = false;

		changesetEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ChangesetEntry> toCacheModel() {
		ChangesetEntryCacheModel changesetEntryCacheModel =
			new ChangesetEntryCacheModel();

		changesetEntryCacheModel.changesetEntryId = getChangesetEntryId();

		changesetEntryCacheModel.groupId = getGroupId();

		changesetEntryCacheModel.companyId = getCompanyId();

		changesetEntryCacheModel.userId = getUserId();

		changesetEntryCacheModel.userName = getUserName();

		String userName = changesetEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			changesetEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			changesetEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			changesetEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			changesetEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			changesetEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		changesetEntryCacheModel.changesetCollectionId =
			getChangesetCollectionId();

		changesetEntryCacheModel.classNameId = getClassNameId();

		changesetEntryCacheModel.classPK = getClassPK();

		return changesetEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ChangesetEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ChangesetEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ChangesetEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ChangesetEntry)this));
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
		Map<String, Function<ChangesetEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ChangesetEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ChangesetEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ChangesetEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ChangesetEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _changesetEntryId;
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
	private long _changesetCollectionId;
	private long _originalChangesetCollectionId;
	private boolean _setOriginalChangesetCollectionId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _columnBitmask;
	private ChangesetEntry _escapedModel;

}