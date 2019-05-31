package com.primeur.levante.update;

public enum CONF_KEYS {
	LV_LIBERTY_HOME("lv.liberty.home"),
	LV_UPDATE_HOME("lv.update.home"),
	;
	
	public String key;
    
    private CONF_KEYS(String key) {
        this.key = key;
    }
    
	public String getKey() {
		return key;
	}
}
