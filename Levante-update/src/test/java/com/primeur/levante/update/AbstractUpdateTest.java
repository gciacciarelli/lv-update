package com.primeur.levante.update;


import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class AbstractUpdateTest {

	public static File cemanResourceApps;
	public static File cemanUpdateApps;
	public static File cemanLibertyApps;
	
	
    @BeforeClass
	public static void setUpBeforeClass() throws IOException {
    	String cemanSrcUpdate = Constants.UPDATE_CEMAN_APPS_PATH; 
		String cemanLiberty = ConfigurationManager.getLibertyCemanApspPath();
		
		String resourcesDirectory = "src/test/resources";
		String resourcesCemanApps = resourcesDirectory + File.separator + "apps" + File.separator + "ceman";
		
		/* copy my apps to primeur-liberty so there are old versions that must be changed */
		cemanResourceApps = new File(resourcesCemanApps);
		cemanLibertyApps = new File(cemanLiberty);
		cemanUpdateApps = new File(cemanSrcUpdate);
	}
    
    @Before
    public void setUp() throws IOException {
    	prepareTargetWithSomeDifference();
    }

    @After
    public void tearDown() {
    }

    public static void prepareTargetWithSomeDifference() throws IOException  {
		IoUtils.copyFile(cemanUpdateApps, cemanLibertyApps);
		IoUtils.copyFile(cemanResourceApps, cemanLibertyApps);
    }

  
        
}
