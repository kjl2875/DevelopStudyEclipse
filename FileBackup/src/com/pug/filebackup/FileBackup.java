package com.pug.filebackup;

import java.io.File;
import java.io.IOException;

public class FileBackup {

	public static void main(String[] args) throws IOException {
		// Utils.getFileHash("SHA-512", new File("D:/FileZilla_Server-0_9_49.exe"));
		
		final String path = "c:\\users\\xxx\\backup";
		Utils.displayList(new File(path));
	}

}
