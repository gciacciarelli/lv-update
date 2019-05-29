package com.primeur.levante.update;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;


/**
 * @author Giuseppe Ciacciarelli
 */
public class IoUtils {

    public static byte[] NO_CONTENT = new byte[0];

    private static void copyDir(File sourceDir, File targetDir) throws IOException, NoSuchAlgorithmException {
        if (targetDir.exists()) {
            if (!targetDir.isDirectory()) {
            	throw new IOException("Not a directory: " + targetDir.getAbsolutePath()); 
            }
        } else if (!targetDir.mkdirs()) {
        	throw new IOException("Cannot create directory: " + targetDir.getAbsolutePath()); 
        }

        File[] children = sourceDir.listFiles();
        if (children != null) {
            for (File child : children) {
                copyFileIfChanged(child, new File(targetDir, child.getName()));
            }
        }
    }
    
    /**
     * Copy sourceFile to targetFile only if content id different.
     * 
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static void copyFileIfChanged(File sourceFile, File targetFile) throws IOException, NoSuchAlgorithmException {
        if (sourceFile.isDirectory()) {
            copyDir(sourceFile, targetFile);
        } else {
            File parent = targetFile.getParentFile();
            if (!parent.exists()) {
                if (!parent.mkdirs()) {
                    throw new IOException("Cannot create directory: " + parent.getAbsolutePath()); 
                }
            }
            try {
            	copyOrReplaceIfDifferent(sourceFile, targetFile);
            } catch (IOException e) {
                throw new IOException("Cannot copy files from " + sourceFile.getAbsolutePath() + " to " + targetFile.getAbsolutePath() + ": " + e.getMessage(), e); 
            }
        }
    }

    private static void copyOrReplaceIfDifferent(File sourceFile, File targetFile) throws IOException, NoSuchAlgorithmException {
    	
    	try {
    		Files.copy(sourceFile.toPath(), targetFile.toPath());
    		System.out.println("copied");
		} catch (FileAlreadyExistsException e) {
        	if (!FileCompare.checkSameContent(sourceFile, targetFile)) {
        		Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);	
        		System.out.println("copied #2");
        	} else {
            	System.out.println("NOT copied");        		
        	}
		}
    }

}
