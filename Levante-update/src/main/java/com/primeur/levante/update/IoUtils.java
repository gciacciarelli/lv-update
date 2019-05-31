package com.primeur.levante.update;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author Giuseppe Ciacciarelli
 */
public class IoUtils {
	
	private final static Logger log = LogManager.getLogger(UpdateTask.class);
    
    private static void copyDir(File sourceDir, File targetDir) throws IOException {
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
                copyFile(child, new File(targetDir, child.getName()));
            }
        }
    }
    
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
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
                Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
            	throw new IOException("Cannot copy files from " + sourceFile.getAbsolutePath() + " to " + targetFile.getAbsolutePath() + ": " + e.getMessage(), e);
            }
        }
    }

    private static void copyDirIfChanged(File sourceDir, File targetDir) throws IOException, NoSuchAlgorithmException {
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
        	copyDirIfChanged(sourceFile, targetFile);
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
    		log.debug("Copied: {}", targetFile.getName());
    		//System.out.println("copied");
		} catch (FileAlreadyExistsException e) {
        	if (!FileCompare.checkSameContent(sourceFile, targetFile)) {
        		Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);	
        		log.debug("Replaced: {}", targetFile.getName());
        	} else {
        		log.debug("NOT replaced: {}", targetFile.getName());
        	}
		}
    }

}
