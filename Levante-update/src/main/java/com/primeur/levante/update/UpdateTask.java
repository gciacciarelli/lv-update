package com.primeur.levante.update;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;



/**
 *
 * @author Giuseppe Ciacciarelli
 */
public class UpdateTask  {


    public void apply(File sourceDir, File targetDir) throws IOException, NoSuchAlgorithmException {

        if(sourceDir.exists()) {
            // Recursively copy source files
            IoUtils.copyFileIfChanged(sourceDir, targetDir);
        } else { 
            if(!targetDir.exists() && ! targetDir.mkdirs()) {
            	throw new IOException("Cannot create directory: " + targetDir.getAbsolutePath()); 
            }
        }

    }

}
