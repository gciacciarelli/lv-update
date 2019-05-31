package com.primeur.levante.update;

import java.io.File;
import java.nio.file.Paths;

public class Constants {

	public static final String LV_UPDATE_HOME = Paths.get("").toAbsolutePath().toString();
	public static final String LV_UPDATE_CFG_DIR = LV_UPDATE_HOME + File.separator + "cfg";
	public static final String LV_UPDATE_CFG_FILENAME = "lv.update.properties";
	public static final String LV_UPDATE_CFG_FILEPATHNAME = LV_UPDATE_CFG_DIR + File.separator + LV_UPDATE_CFG_FILENAME;
	
	public static final String UPDATE_CEMAN_APPS_RELPATH = "apps/ceman";
	public static final String UPDATE_CEMAN_APPS_PATH = LV_UPDATE_HOME + File.separator + UPDATE_CEMAN_APPS_RELPATH;
	
	public static final String LIBERTY_CEMAN_APPS_RELPATH = "wlp/usr/servers/ceman/apps";

}
