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

package com.liferay.sync.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.sync.model.SyncDLFileVersionDiff;
import com.liferay.sync.model.SyncDLFileVersionDiffModel;

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
 * The base model implementation for the SyncDLFileVersionDiff service. Represents a row in the &quot;SyncDLFileVersionDiff&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>SyncDLFileVersionDiffModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SyncDLFileVersionDiffImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLFileVersionDiffImpl
 * @generated
 */
public class SyncDLFileVersionDiffModelImpl
	extends BaseModelImpl<SyncDLFileVersionDiff>
	implements SyncDLFileVersionDiffModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a sync dl file version diff model instance should use the <code>SyncDLFileVersionDiff</code> interface instead.
	 */
	public static final String TABLE_NAME = "SyncDLFileVersionDiff";

	public static final Object[][] TABLE_COLUMNS = {
		{"syncDLFileVersionDiffId", Types.BIGINT},
		{"fileEntryId", Types.BIGINT}, {"sourceFileVersionId", Types.BIGINT},
		{"targetFileVersionId", Types.BIGINT},
		{"dataFileEntryId", Types.BIGINT}, {"size_", Types.BIGINT},
		{"expirationDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("syncDLFileVersionDiffId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sourceFileVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("targetFileVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dataFileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("size_", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SyncDLFileVersionDiff (syncDLFileVersionDiffId LONG not null primary key,fileEntryId LONG,sourceFileVersionId LONG,targetFileVersionId LONG,dataFileEntryId LONG,size_ LONG,expirationDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table SyncDLFileVersionDiff";

	public static final String ORDER_BY_JPQL =
		" ORDER BY syncDLFileVersionDiff.syncDLFileVersionDiffId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SyncDLFileVersionDiff.syncDLFileVersionDiffId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.sync.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.sync.model.SyncDLFileVersionDiff"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.sync.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.sync.model.SyncDLFileVersionDiff"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.sync.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.sync.model.SyncDLFileVersionDiff"),
		true);

	public static final long EXPIRATIONDATE_COLUMN_BITMASK = 1L;

	public static final long FILEENTRYID_COLUMN_BITMASK = 2L;

	public static final long SOURCEFILEVERSIONID_COLUMN_BITMASK = 4L;

	public static final long TARGETFILEVERSIONID_COLUMN_BITMASK = 8L;

	public static final long SYNCDLFILEVERSIONDIFFID_COLUMN_BITMASK = 16L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.sync.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.sync.model.SyncDLFileVersionDiff"));

	public SyncDLFileVersionDiffModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _syncDLFileVersionDiffId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSyncDLFileVersionDiffId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _syncDLFileVersionDiffId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SyncDLFileVersionDiff.class;
	}

	@Override
	public String getModelClassName() {
		return SyncDLFileVersionDiff.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SyncDLFileVersionDiff, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SyncDLFileVersionDiff, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SyncDLFileVersionDiff, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SyncDLFileVersionDiff)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SyncDLFileVersionDiff, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SyncDLFileVersionDiff, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SyncDLFileVersionDiff)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SyncDLFileVersionDiff, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SyncDLFileVersionDiff, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SyncDLFileVersionDiff>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SyncDLFileVersionDiff.class.getClassLoader(),
			SyncDLFileVersionDiff.class, ModelWrapper.class);

		try {
			Constructor<SyncDLFileVersionDiff> constructor =
				(Constructor<SyncDLFileVersionDiff>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SyncDLFileVersionDiff, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SyncDLFileVersionDiff, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SyncDLFileVersionDiff, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<SyncDLFileVersionDiff, Object>>();
		Map<String, BiConsumer<SyncDLFileVersionDiff, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<SyncDLFileVersionDiff, ?>>();

		attributeGetterFunctions.put(
			"syncDLFileVersionDiffId",
			new Function<SyncDLFileVersionDiff, Object>() {

				@Override
				public Object apply(
					SyncDLFileVersionDiff syncDLFileVersionDiff) {

					return syncDLFileVersionDiff.getSyncDLFileVersionDiffId();
				}

			});
		attributeSetterBiConsumers.put(
			"syncDLFileVersionDiffId",
			new BiConsumer<SyncDLFileVersionDiff, Object>() {

				@Override
				public void accept(
					SyncDLFileVersionDiff syncDLFileVersionDiff,
					Object syncDLFileVersionDiffIdObject) {

					syncDLFileVersionDiff.setSyncDLFileVersionDiffId(
						(Long)syncDLFileVersionDiffIdObject);
				}

			});
		attributeGetterFunctions.put(
			"fileEntryId",
			new Function<SyncDLFileVersionDiff, Object>() {

				@Override
				public Object apply(
					SyncDLFileVersionDiff syncDLFileVersionDiff) {

					return syncDLFileVersionDiff.getFileEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"fileEntryId",
			new BiConsumer<SyncDLFileVersionDiff, Object>() {

				@Override
				public void accept(
					SyncDLFileVersionDiff syncDLFileVersionDiff,
					Object fileEntryIdObject) {

					syncDLFileVersionDiff.setFileEntryId(
						(Long)fileEntryIdObject);
				}

			});
		attributeGetterFunctions.put(
			"sourceFileVersionId",
			new Function<SyncDLFileVersionDiff, Object>() {

				@Override
				public Object apply(
					SyncDLFileVersionDiff syncDLFileVersionDiff) {

					return syncDLFileVersionDiff.getSourceFileVersionId();
				}

			});
		attributeSetterBiConsumers.put(
			"sourceFileVersionId",
			new BiConsumer<SyncDLFileVersionDiff, Object>() {

				@Override
				public void accept(
					SyncDLFileVersionDiff syncDLFileVersionDiff,
					Object sourceFileVersionIdObject) {

					syncDLFileVersionDiff.setSourceFileVersionId(
						(Long)sourceFileVersionIdObject);
				}

			});
		attributeGetterFunctions.put(
			"targetFileVersionId",
			new Function<SyncDLFileVersionDiff, Object>() {

				@Override
				public Object apply(
					SyncDLFileVersionDiff syncDLFileVersionDiff) {

					return syncDLFileVersionDiff.getTargetFileVersionId();
				}

			});
		attributeSetterBiConsumers.put(
			"targetFileVersionId",
			new BiConsumer<SyncDLFileVersionDiff, Object>() {

				@Override
				public void accept(
					SyncDLFileVersionDiff syncDLFileVersionDiff,
					Object targetFileVersionIdObject) {

					syncDLFileVersionDiff.setTargetFileVersionId(
						(Long)targetFileVersionIdObject);
				}

			});
		attributeGetterFunctions.put(
			"dataFileEntryId",
			new Function<SyncDLFileVersionDiff, Object>() {

				@Override
				public Object apply(
					SyncDLFileVersionDiff syncDLFileVersionDiff) {

					return syncDLFileVersionDiff.getDataFileEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"dataFileEntryId",
			new BiConsumer<SyncDLFileVersionDiff, Object>() {

				@Override
				public void accept(
					SyncDLFileVersionDiff syncDLFileVersionDiff,
					Object dataFileEntryIdObject) {

					syncDLFileVersionDiff.setDataFileEntryId(
						(Long)dataFileEntryIdObject);
				}

			});
		attributeGetterFunctions.put(
			"size",
			new Function<SyncDLFileVersionDiff, Object>() {

				@Override
				public Object apply(
					SyncDLFileVersionDiff syncDLFileVersionDiff) {

					return syncDLFileVersionDiff.getSize();
				}

			});
		attributeSetterBiConsumers.put(
			"size",
			new BiConsumer<SyncDLFileVersionDiff, Object>() {

				@Override
				public void accept(
					SyncDLFileVersionDiff syncDLFileVersionDiff,
					Object sizeObject) {

					syncDLFileVersionDiff.setSize((Long)sizeObject);
				}

			});
		attributeGetterFunctions.put(
			"expirationDate",
			new Function<SyncDLFileVersionDiff, Object>() {

				@Override
				public Object apply(
					SyncDLFileVersionDiff syncDLFileVersionDiff) {

					return syncDLFileVersionDiff.getExpirationDate();
				}

			});
		attributeSetterBiConsumers.put(
			"expirationDate",
			new BiConsumer<SyncDLFileVersionDiff, Object>() {

				@Override
				public void accept(
					SyncDLFileVersionDiff syncDLFileVersionDiff,
					Object expirationDateObject) {

					syncDLFileVersionDiff.setExpirationDate(
						(Date)expirationDateObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getSyncDLFileVersionDiffId() {
		return _syncDLFileVersionDiffId;
	}

	@Override
	public void setSyncDLFileVersionDiffId(long syncDLFileVersionDiffId) {
		_syncDLFileVersionDiffId = syncDLFileVersionDiffId;
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		_columnBitmask |= FILEENTRYID_COLUMN_BITMASK;

		if (!_setOriginalFileEntryId) {
			_setOriginalFileEntryId = true;

			_originalFileEntryId = _fileEntryId;
		}

		_fileEntryId = fileEntryId;
	}

	public long getOriginalFileEntryId() {
		return _originalFileEntryId;
	}

	@Override
	public long getSourceFileVersionId() {
		return _sourceFileVersionId;
	}

	@Override
	public void setSourceFileVersionId(long sourceFileVersionId) {
		_columnBitmask |= SOURCEFILEVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalSourceFileVersionId) {
			_setOriginalSourceFileVersionId = true;

			_originalSourceFileVersionId = _sourceFileVersionId;
		}

		_sourceFileVersionId = sourceFileVersionId;
	}

	public long getOriginalSourceFileVersionId() {
		return _originalSourceFileVersionId;
	}

	@Override
	public long getTargetFileVersionId() {
		return _targetFileVersionId;
	}

	@Override
	public void setTargetFileVersionId(long targetFileVersionId) {
		_columnBitmask |= TARGETFILEVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalTargetFileVersionId) {
			_setOriginalTargetFileVersionId = true;

			_originalTargetFileVersionId = _targetFileVersionId;
		}

		_targetFileVersionId = targetFileVersionId;
	}

	public long getOriginalTargetFileVersionId() {
		return _originalTargetFileVersionId;
	}

	@Override
	public long getDataFileEntryId() {
		return _dataFileEntryId;
	}

	@Override
	public void setDataFileEntryId(long dataFileEntryId) {
		_dataFileEntryId = dataFileEntryId;
	}

	@Override
	public long getSize() {
		return _size;
	}

	@Override
	public void setSize(long size) {
		_size = size;
	}

	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		_columnBitmask |= EXPIRATIONDATE_COLUMN_BITMASK;

		if (_originalExpirationDate == null) {
			_originalExpirationDate = _expirationDate;
		}

		_expirationDate = expirationDate;
	}

	public Date getOriginalExpirationDate() {
		return _originalExpirationDate;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, SyncDLFileVersionDiff.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SyncDLFileVersionDiff toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SyncDLFileVersionDiff>
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
		SyncDLFileVersionDiffImpl syncDLFileVersionDiffImpl =
			new SyncDLFileVersionDiffImpl();

		syncDLFileVersionDiffImpl.setSyncDLFileVersionDiffId(
			getSyncDLFileVersionDiffId());
		syncDLFileVersionDiffImpl.setFileEntryId(getFileEntryId());
		syncDLFileVersionDiffImpl.setSourceFileVersionId(
			getSourceFileVersionId());
		syncDLFileVersionDiffImpl.setTargetFileVersionId(
			getTargetFileVersionId());
		syncDLFileVersionDiffImpl.setDataFileEntryId(getDataFileEntryId());
		syncDLFileVersionDiffImpl.setSize(getSize());
		syncDLFileVersionDiffImpl.setExpirationDate(getExpirationDate());

		syncDLFileVersionDiffImpl.resetOriginalValues();

		return syncDLFileVersionDiffImpl;
	}

	@Override
	public int compareTo(SyncDLFileVersionDiff syncDLFileVersionDiff) {
		long primaryKey = syncDLFileVersionDiff.getPrimaryKey();

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

		if (!(obj instanceof SyncDLFileVersionDiff)) {
			return false;
		}

		SyncDLFileVersionDiff syncDLFileVersionDiff =
			(SyncDLFileVersionDiff)obj;

		long primaryKey = syncDLFileVersionDiff.getPrimaryKey();

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
		SyncDLFileVersionDiffModelImpl syncDLFileVersionDiffModelImpl = this;

		syncDLFileVersionDiffModelImpl._originalFileEntryId =
			syncDLFileVersionDiffModelImpl._fileEntryId;

		syncDLFileVersionDiffModelImpl._setOriginalFileEntryId = false;

		syncDLFileVersionDiffModelImpl._originalSourceFileVersionId =
			syncDLFileVersionDiffModelImpl._sourceFileVersionId;

		syncDLFileVersionDiffModelImpl._setOriginalSourceFileVersionId = false;

		syncDLFileVersionDiffModelImpl._originalTargetFileVersionId =
			syncDLFileVersionDiffModelImpl._targetFileVersionId;

		syncDLFileVersionDiffModelImpl._setOriginalTargetFileVersionId = false;

		syncDLFileVersionDiffModelImpl._originalExpirationDate =
			syncDLFileVersionDiffModelImpl._expirationDate;

		syncDLFileVersionDiffModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SyncDLFileVersionDiff> toCacheModel() {
		SyncDLFileVersionDiffCacheModel syncDLFileVersionDiffCacheModel =
			new SyncDLFileVersionDiffCacheModel();

		syncDLFileVersionDiffCacheModel.syncDLFileVersionDiffId =
			getSyncDLFileVersionDiffId();

		syncDLFileVersionDiffCacheModel.fileEntryId = getFileEntryId();

		syncDLFileVersionDiffCacheModel.sourceFileVersionId =
			getSourceFileVersionId();

		syncDLFileVersionDiffCacheModel.targetFileVersionId =
			getTargetFileVersionId();

		syncDLFileVersionDiffCacheModel.dataFileEntryId = getDataFileEntryId();

		syncDLFileVersionDiffCacheModel.size = getSize();

		Date expirationDate = getExpirationDate();

		if (expirationDate != null) {
			syncDLFileVersionDiffCacheModel.expirationDate =
				expirationDate.getTime();
		}
		else {
			syncDLFileVersionDiffCacheModel.expirationDate = Long.MIN_VALUE;
		}

		return syncDLFileVersionDiffCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SyncDLFileVersionDiff, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SyncDLFileVersionDiff, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SyncDLFileVersionDiff, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((SyncDLFileVersionDiff)this));
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
		Map<String, Function<SyncDLFileVersionDiff, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SyncDLFileVersionDiff, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SyncDLFileVersionDiff, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((SyncDLFileVersionDiff)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SyncDLFileVersionDiff>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _syncDLFileVersionDiffId;
	private long _fileEntryId;
	private long _originalFileEntryId;
	private boolean _setOriginalFileEntryId;
	private long _sourceFileVersionId;
	private long _originalSourceFileVersionId;
	private boolean _setOriginalSourceFileVersionId;
	private long _targetFileVersionId;
	private long _originalTargetFileVersionId;
	private boolean _setOriginalTargetFileVersionId;
	private long _dataFileEntryId;
	private long _size;
	private Date _expirationDate;
	private Date _originalExpirationDate;
	private long _columnBitmask;
	private SyncDLFileVersionDiff _escapedModel;

}