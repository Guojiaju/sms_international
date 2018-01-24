package com.sms.international.admin.mongodb;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SystemConstant {
	private static final String BUNDLE_NAME = "rabbitconf"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private SystemConstant() {
	}

	/**
	 * get the value from the properties file
	 *
	 * @param key
	 *            the key in the properties file
	 * @return
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
