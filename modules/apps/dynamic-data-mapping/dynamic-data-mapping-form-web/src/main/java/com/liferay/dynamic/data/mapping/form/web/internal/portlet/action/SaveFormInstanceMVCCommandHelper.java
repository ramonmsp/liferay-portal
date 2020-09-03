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

package com.liferay.dynamic.data.mapping.form.web.internal.portlet.action;

import com.liferay.dynamic.data.mapping.exception.FormInstanceSettingsRedirectURLException;
import com.liferay.dynamic.data.mapping.exception.StructureDefinitionException;
import com.liferay.dynamic.data.mapping.exception.StructureLayoutException;
import com.liferay.dynamic.data.mapping.form.builder.context.DDMFormContextDeserializer;
import com.liferay.dynamic.data.mapping.form.builder.context.DDMFormContextDeserializerRequest;
import com.liferay.dynamic.data.mapping.form.web.internal.portlet.action.util.DDMFormInstanceFieldSettingsValidator;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceSettings;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceService;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.util.DDMFormFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.InetAddressUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.PropsValues;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = SaveFormInstanceMVCCommandHelper.class)
public class SaveFormInstanceMVCCommandHelper {

	public DDMFormInstance saveFormInstance(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		return saveFormInstance(portletRequest, portletResponse, false);
	}

	public DDMFormInstance saveFormInstance(
			PortletRequest portletRequest, PortletResponse portletResponse,
			boolean validateDDMFormFieldSettings)
		throws Exception {

		long formInstanceId = ParamUtil.getLong(
			portletRequest, "formInstanceId");

		if (formInstanceId == 0) {
			return addFormInstance(
				portletRequest, validateDDMFormFieldSettings);
		}

		return updateFormInstance(
			portletRequest, formInstanceId, validateDDMFormFieldSettings);
	}

	protected DDMFormInstance addFormInstance(
			PortletRequest portletRequest, boolean validateFormFieldsSettings)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDMFormInstance.class.getName(), portletRequest);

		long groupId = ParamUtil.getLong(portletRequest, "groupId");
		String name = ParamUtil.getString(portletRequest, "name");
		String description = ParamUtil.getString(portletRequest, "description");
		DDMForm ddmForm = getDDMForm(portletRequest, serviceContext);
		DDMFormLayout ddmFormLayout = getDDMFormLayout(portletRequest);

		Map<Locale, String> nameMap = getNameMap(
			ddmForm, name, "untitled-form");
		Map<Locale, String> descriptionMap = getLocalizedMap(
			description, ddmForm.getAvailableLocales(),
			ddmForm.getDefaultLocale());

		if (ParamUtil.getBoolean(portletRequest, "saveAsDraft")) {
			serviceContext.setAttribute(
				"status", WorkflowConstants.STATUS_DRAFT);
		}

		if (validateFormFieldsSettings) {
			formInstanceFieldSettingsValidator.validate(
				portletRequest, ddmForm);
		}

		DDMFormValues settingsDDMFormValues = getSettingsDDMFormValues(
			portletRequest);

		validateRedirectURL(
			settingsDDMFormValues,
			_portal.getHttpServletRequest(portletRequest));

		return formInstanceService.addFormInstance(
			groupId, nameMap, descriptionMap, ddmForm, ddmFormLayout,
			settingsDDMFormValues, serviceContext);
	}

	protected DDMForm getDDMForm(
			PortletRequest portletRequest, ServiceContext serviceContext)
		throws PortalException {

		try {
			String serializedFormBuilderContext = ParamUtil.getString(
				portletRequest, "serializedFormBuilderContext");

			return ddmFormBuilderContextToDDMForm.deserialize(
				DDMFormContextDeserializerRequest.with(
					serializedFormBuilderContext));
		}
		catch (PortalException portalException) {
			throw new StructureDefinitionException(portalException);
		}
	}

	protected DDMFormLayout getDDMFormLayout(PortletRequest portletRequest)
		throws PortalException {

		try {
			String serializedFormBuilderContext = ParamUtil.getString(
				portletRequest, "serializedFormBuilderContext");

			return ddmFormBuilderContextToDDMFormLayout.deserialize(
				DDMFormContextDeserializerRequest.with(
					serializedFormBuilderContext));
		}
		catch (PortalException portalException) {
			throw new StructureLayoutException(portalException);
		}
	}

	protected Map<Locale, String> getLocalizedMap(
			String value, Set<Locale> availableLocales, Locale defaultLocale)
		throws PortalException {

		Map<Locale, String> localizedMap = new HashMap<>();

		JSONObject jsonObject = jsonFactory.createJSONObject(value);

		String defaultValueString = jsonObject.getString(
			LocaleUtil.toLanguageId(defaultLocale));

		for (Locale availableLocale : availableLocales) {
			String valueString = jsonObject.getString(
				LocaleUtil.toLanguageId(availableLocale), defaultValueString);

			localizedMap.put(availableLocale, valueString);
		}

		return localizedMap;
	}

	protected Map<Locale, String> getNameMap(
			DDMForm ddmForm, String name, String defaultName)
		throws PortalException {

		Locale defaultLocale = ddmForm.getDefaultLocale();

		Map<Locale, String> nameMap = getLocalizedMap(
			name, ddmForm.getAvailableLocales(), defaultLocale);

		if (nameMap.isEmpty() || Validator.isNull(nameMap.get(defaultLocale))) {
			nameMap.put(
				defaultLocale,
				LanguageUtil.get(
					getResourceBundle(defaultLocale), defaultName));
		}

		return nameMap;
	}

	protected ResourceBundle getResourceBundle(Locale locale) {
		Class<?> clazz = getClass();

		return ResourceBundleUtil.getBundle(
			"content.Language", locale, clazz.getClassLoader());
	}

	protected DDMFormValues getSettingsDDMFormValues(
			PortletRequest portletRequest)
		throws PortalException {

		String settingsContext = ParamUtil.getString(
			portletRequest, "serializedSettingsContext");

		return ddmFormTemplateContextToDDMFormValues.deserialize(
			DDMFormContextDeserializerRequest.with(
				DDMFormFactory.create(DDMFormInstanceSettings.class),
				settingsContext));
	}

	protected DDMFormInstance updateFormInstance(
			PortletRequest portletRequest, long formInstanceId,
			boolean validateFormFieldsSettings)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDMFormInstance.class.getName(), portletRequest);

		String name = ParamUtil.getString(portletRequest, "name");
		String description = ParamUtil.getString(portletRequest, "description");
		DDMForm ddmForm = getDDMForm(portletRequest, serviceContext);
		DDMFormLayout ddmFormLayout = getDDMFormLayout(portletRequest);

		Map<Locale, String> nameMap = getNameMap(
			ddmForm, name, "untitled-form");
		Map<Locale, String> descriptionMap = getLocalizedMap(
			description, ddmForm.getAvailableLocales(),
			ddmForm.getDefaultLocale());

		if (ParamUtil.getBoolean(portletRequest, "saveAsDraft")) {
			serviceContext.setAttribute(
				"status", WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		if (validateFormFieldsSettings) {
			formInstanceFieldSettingsValidator.validate(
				portletRequest, ddmForm);
		}

		DDMFormValues settingsDDMFormValues = getSettingsDDMFormValues(
			portletRequest);

		validateRedirectURL(
			settingsDDMFormValues,
			_portal.getHttpServletRequest(portletRequest));

		return formInstanceService.updateFormInstance(
			formInstanceId, nameMap, descriptionMap, ddmForm, ddmFormLayout,
			settingsDDMFormValues, serviceContext);
	}

	protected void validateRedirectURL(
			DDMFormValues settingsDDMFormValues,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		Map<String, List<DDMFormFieldValue>> ddmFormFieldValuesMap =
			settingsDDMFormValues.getDDMFormFieldValuesMap();

		if (!ddmFormFieldValuesMap.containsKey("redirectURL")) {
			return;
		}

		List<DDMFormFieldValue> ddmFormFieldValues = ddmFormFieldValuesMap.get(
			"redirectURL");

		DDMFormFieldValue ddmFormFieldValue = ddmFormFieldValues.get(0);

		Value value = ddmFormFieldValue.getValue();

		for (Locale availableLocale : value.getAvailableLocales()) {
			String valueString = value.getString(availableLocale);

			if (Validator.isNotNull(valueString)) {
				String escapedRedirect = _portal.escapeRedirect(valueString);

				if (Validator.isNull(escapedRedirect)) {
					URI uri = _getURI(valueString);

					if (uri != null) {
						StringBundler sb = new StringBundler(4);

						sb.append("the-specified-redirect-url-is-not-allowed-");
						sb.append("due-to-installation-settings.-add-x-into-");
						sb.append("the-property-x-in-the-file-portal-");
						sb.append("ext.properties-to-fix-it");

						String securityMode =
							PropsValues.REDIRECT_URL_SECURITY_MODE;

						String host = uri.getHost();

						if (securityMode.equals("domain")) {
							List<String> allowedDomains = Arrays.asList(
								PropsValues.REDIRECT_URL_DOMAINS_ALLOWED);

							if (!allowedDomains.contains(host)) {
								throw new FormInstanceSettingsRedirectURLException(
									LanguageUtil.format(
										httpServletRequest, sb.toString(),
										new String[] {
											host,
											PropsKeys.
												REDIRECT_URL_DOMAINS_ALLOWED
										}));
							}
						}
						else if (securityMode.equals("ip")) {
							try {
								List<String> allowedIps = Arrays.asList(
									PropsValues.REDIRECT_URL_IPS_ALLOWED);

								InetAddress inetAddress =
									InetAddressUtil.getInetAddressByName(host);

								String hostAddress =
									inetAddress.getHostAddress();

								if (!allowedIps.contains(hostAddress)) {
									throw new FormInstanceSettingsRedirectURLException(
										LanguageUtil.format(
											httpServletRequest, sb.toString(),
											new String[] {
												hostAddress,
												PropsKeys.
													REDIRECT_URL_IPS_ALLOWED
											}));
								}
							}
							catch (UnknownHostException unknownHostException) {
								if (_log.isDebugEnabled()) {
									_log.debug(
										unknownHostException,
										unknownHostException);
								}
							}
						}
					}

					throw new FormInstanceSettingsRedirectURLException(
						LanguageUtil.get(
							httpServletRequest,
							"the-specified-redirect-url-is-not-allowed"));
				}
			}
		}
	}

	@Reference(
		target = "(dynamic.data.mapping.form.builder.context.deserializer.type=form)"
	)
	protected DDMFormContextDeserializer<DDMForm>
		ddmFormBuilderContextToDDMForm;

	@Reference(
		target = "(dynamic.data.mapping.form.builder.context.deserializer.type=formLayout)"
	)
	protected DDMFormContextDeserializer<DDMFormLayout>
		ddmFormBuilderContextToDDMFormLayout;

	@Reference(
		target = "(dynamic.data.mapping.form.builder.context.deserializer.type=formValues)"
	)
	protected DDMFormContextDeserializer<DDMFormValues>
		ddmFormTemplateContextToDDMFormValues;

	@Reference
	protected volatile DDMFormInstanceFieldSettingsValidator
		formInstanceFieldSettingsValidator;

	@Reference
	protected DDMFormInstanceService formInstanceService;

	@Reference
	protected JSONFactory jsonFactory;

	private URI _getURI(String uriString) {
		try {
			return new URI(uriString.trim());
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}

			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SaveFormInstanceMVCCommandHelper.class);

	@Reference
	private Portal _portal;

}