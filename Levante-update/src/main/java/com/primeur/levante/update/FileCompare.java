package com.primeur.levante.update;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class FileCompare {

	private static String getFileChecksum(Path pathToFile) throws IOException, NoSuchAlgorithmException {
		byte[] b = Files.readAllBytes(pathToFile);
		byte[] hash = MessageDigest.getInstance("MD5").digest(b);
		return DatatypeConverter.printHexBinary(hash);
	}
	
	public static boolean checkSameContent(File sourceFile, File targetFile) throws IOException, NoSuchAlgorithmException {
		String srcFileChecksum = getFileChecksum(sourceFile.toPath());
		String trgFileChecksum = getFileChecksum(targetFile.toPath());
		return srcFileChecksum.equalsIgnoreCase(trgFileChecksum);
	}
	
}
