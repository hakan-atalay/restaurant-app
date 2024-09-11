package com.anproject.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUploadUtil {

	private static final String UPLOAD_DIR = System.getProperty("user.dir") + File.separator + "images" + File.separator;

	public void saveFile(InputStream uploadedInputStream, String fileName) throws IOException {
		File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs(); 
        }
		String uploadedFileLocation = UPLOAD_DIR + fileName;
		try (FileOutputStream out = new FileOutputStream(new File(uploadedFileLocation))) {
			byte[] bytes = new byte[1024];
			int read;
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		}
	}
	
}
