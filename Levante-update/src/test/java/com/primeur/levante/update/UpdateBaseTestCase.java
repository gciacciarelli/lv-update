package com.primeur.levante.update;

import java.io.File;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UpdateBaseTestCase extends AbstractUpdateTest {
	

	@Test
    public void testApplyUpdate() throws Exception {
		UpdateTask updateTask = new UpdateTask();
		updateTask.apply(cemanUpdateApps, cemanLibertyApps);
    	System.out.println(">> testCopyDir OK");
    }


}
