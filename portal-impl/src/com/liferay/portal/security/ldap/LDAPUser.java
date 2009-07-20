/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.security.ldap;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;
import com.liferay.util.ldap.DummyDirContext;

import java.util.Properties;

import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

public class LDAPUser extends DummyDirContext {

	public LDAPUser() {
		super();
	}

	public User getUser() {
		return _user;
	}

	public void setUser(User user) throws Exception {
		_user = user;

		Properties userMappings = PortalLDAPUtil.getUserMappings(
			_user.getCompanyId());

		_attrs = new BasicAttributes(true);

		// Required attributes

		Attribute objectClass = new BasicAttribute("objectclass");

		String[] defaultObjectClasses = PropsUtil.getArray(
			PropsKeys.LDAP_USER_DEFAULT_OBJECT_CLASSES);

		for (int i = 0; i < defaultObjectClasses.length; i++) {
			objectClass.add(defaultObjectClasses[i]);
		}

		_attrs.put(objectClass);

		_attrs.put(userMappings.getProperty("firstName"), _user.getFirstName());
		_attrs.put(userMappings.getProperty("lastName"), _user.getLastName());

		if (Validator.isNotNull(_user.getPasswordUnencrypted())) {
			_attrs.put(
				userMappings.getProperty("password"),
				_user.getPasswordUnencrypted());
		}

		if (Validator.isNotNull(_user.getEmailAddress())) {
			_attrs.put(
				userMappings.getProperty("emailAddress"),
				_user.getEmailAddress());
		}

		// Optional attributes

		String fullNameMapping = userMappings.getProperty("fullName");

		if (Validator.isNotNull(fullNameMapping)) {
			_attrs.put(fullNameMapping, _user.getFullName());
		}

		String jobTitleMapping = userMappings.getProperty("jobTitle");

		if (Validator.isNotNull(jobTitleMapping) &&
			Validator.isNotNull(_user.getJobTitle())) {

			_attrs.put(jobTitleMapping, _user.getJobTitle());
		}
	}

	public Attributes getAttributes() {
		return _attrs;
	}

	public Attributes getAttributes(String name) throws NamingException {
		if (Validator.isNotNull(name)) {
			throw new NameNotFoundException();
		}

		return (Attributes)_attrs.clone();
	}

	public Attributes getAttributes(Name name) throws NamingException {
		return getAttributes(name.toString());
	}

	public Attributes getAttributes(String name, String[] ids)
		throws NamingException {

		if (Validator.isNotNull(name)) {
			throw new NameNotFoundException();
		}

		Attributes attrs = new BasicAttributes(true);

		for (int i = 0; i < ids.length; i++) {
			Attribute attr = _attrs.get(ids[i]);

			if (attr != null) {
				attrs.put(attr);
			}
		}

		return attrs;
	}

	public Attributes getAttributes(Name name, String[] ids)
		throws NamingException {

		return getAttributes(name.toString(), ids);
	}

	private User _user;
	private Attributes _attrs;

}