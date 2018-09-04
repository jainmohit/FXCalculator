package com.anz.fxcalculator.parser;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.anz.fxcalculator.exceptions.ApplicationException;

/**
 * This is the basic file parser which check for the file available in the<br>
 * class path and return the absolute path of the same file.
 * 
 * @author Mohit Jain
 * @version 1.0
 *
 */
public class FileConfigParser {
	private String configFileName;

	/**
	 * Getter for configfileName
	 * 
	 * @return String
	 */
	public String getConfigFileName() {
		return configFileName;
	}

	/**
	 * Setter for configFileName
	 * 
	 * @param configFileName
	 */
	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	/**
	 * {@code getCofigFile} will check for the specific file name provided and
	 * return the absolute path of the same if it is present in the class path.
	 * 
	 * @return File
	 * @throws UnsupportedEncodingException
	 * @throws ApplicationException
	 */
	public File getConfigFile() throws UnsupportedEncodingException, ApplicationException {
		ClassLoader classLoader = getClass().getClassLoader();
		if (classLoader.getResource(getConfigFileName()) == null) {
			throw new ApplicationException(getConfigFileName() + " file not found in the classpath");
		}
		String absolutePath = classLoader.getResource(getConfigFileName()).getFile();
		return new File(URLDecoder.decode(absolutePath, "UTF-8"));
	}
}
