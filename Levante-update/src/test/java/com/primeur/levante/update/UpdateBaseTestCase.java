package com.primeur.levante.update;

import java.io.File;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UpdateBaseTestCase extends AbstractUpdateTest {
	

	@Test
    public void testCopyDir() throws Exception {
		File cemanApps = new File(ceman_apps);
		File cemanAppsNew= new File(ceman_apps_new);
		UpdateTask updateTask = new UpdateTask();
		updateTask.apply(cemanAppsNew, cemanApps);
    	System.out.println(">> testCopyDir OK");
    }


}
