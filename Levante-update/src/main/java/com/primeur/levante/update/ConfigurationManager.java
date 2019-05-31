package com.primeur.levante.update;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigurationManager extends Constants {
	
	private static Properties properties = null;
	private static String libertyHome = null;

	/**
	 * Load application configuration if not yet loaded:
	 * <LI> load {@link Properties}.
	 * <LI> set log4j configuration
	 * 
	 * @throws IOException
	 */
	private static void loadConfiguration() throws IOException {
		if (properties == null) {
			loadPropertiesFromFile();
		}
	}

	/**
	 * Load application file properties into {@link Properties}
	 * 
	 * @throws IOException
	 */
	private static void loadPropertiesFromFile() throws IOException {
		properties = new Properties();
		properties.load(new FileInputStream(LV_UPDATE_CFG_FILEPATHNAME));
		return;
	}

	/**
	 * Get the specified configuration parameter. Before load configuration if not yet loaded.
	 * 
	 * @param conf - the configuration key as {@link Enum}
	 * @return the configuration value as {@link String} or <code>null</code> if not found.
	 * @throws IOException
	 */
	public static String get(CONF_KEYS conf) throws IOException {
		loadConfiguration();
		return properties.getProperty(conf.getKey());
	}
	
	public static String get(CONF_KEYS conf, String defaultValue) throws IOException {
		loadConfiguration();
		return properties.getProperty(conf.getKey(), defaultValue);
	}
	
//	/**
//	 * Return configured UPDATE_HOME or UPDATE_HOME_DEFAULT (the installation directory)
//	 * 
//	 * @return UpdateHome
//	 * @throws IOException
//	 */
//	public static String getUpdateHome() throws IOException {
//		if (updateHome == null) {
//			updateHome = ConfigurationManager.get(CONF_KEYS.LV_UPDATE_HOME, LV_UPDATE_HOME);	
//		}
//		return updateHome;
//	}

	public static String getLibertyHome() throws IOException {
		if (libertyHome == null) {
			libertyHome = ConfigurationManager.get(CONF_KEYS.LV_LIBERTY_HOME);	
		}
		return libertyHome;
	}
	
//	public static String getUpdateCfgPathName() throws IOException {
//		return getUpdateHome() + File.separator + File.separator + "cfg" + File.separator + LV_UPDATE_CFG_FILENAME;
//	}
//	
//	public static String getUpdateCemanAppsPath() throws IOException {
//		return getUpdateHome() + File.separator + UPDATE_CEMAN_APPS_RELPATH;
//	}
	
	public static String getLibertyCemanApspPath() throws IOException {
		return getLibertyHome() + File.separator + LIBERTY_CEMAN_APPS_RELPATH;
	}

}
