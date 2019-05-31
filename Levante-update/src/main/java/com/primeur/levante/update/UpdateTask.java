package com.primeur.levante.update;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 *
 * @author Giuseppe Ciacciarelli
 */
public class UpdateTask  {
	
	private final static Logger log = LogManager.getLogger(UpdateTask.class);

	public static void main(String[] args) throws IOException {
		UpdateTask updateTask = new UpdateTask();
		
		String cemanSrc = Constants.UPDATE_CEMAN_APPS_PATH; 
		String cemanTrg = ConfigurationManager.getLibertyCemanApspPath();		
		File sourceDir = new File(cemanSrc);
		File targetDir = new File(cemanTrg);
		
		String component = null;
		if (args.length > 0) {
			component = args[0];
			if (component.equals("ceman")) {
				updateTask.apply(sourceDir, targetDir);
			} else {
				log.error("Component not valid (component: {})", component);
			}
		}
	}

	/**
	 * Update files into targetDir with sourceDir files in the following ways:
	 * 
	 * <li><b>copy</b> if there isnt't the same file into targetDir</li> 
	 * <li><b>replace</b> if there is the same file into targetDir with different checksum</li>
	 * <li><b>not replace</b> if there is the same file into targetDir with same checksum</li>  
	 * 
	 * @param sourceDir
	 * @param targetDir
	 * 
	 */
    public void apply(File sourceDir, File targetDir) {
    	log.info("Starting Update Levante ");
		try {
	        if(sourceDir.exists()) {
	            // Recursively copy source files
	            IoUtils.copyFileIfChanged(sourceDir, targetDir);
	        } else { 
	            if(!targetDir.exists() && !targetDir.mkdirs()) {
	            	throw new IOException("Cannot create directory: " + targetDir.getAbsolutePath()); 
	            }
	        }
		} catch (IOException | NoSuchAlgorithmException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
    }

}
