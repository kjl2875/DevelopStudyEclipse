package com.pug.filebackup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

	public static void displayList(File file) throws IOException {
		if( file.isDirectory() ) {
			File []ls = file.listFiles();
			if( ls != null && ls.length >= 0 ) { // Access 권한관련, 빈폴더 관련
				for( File f : ls ) {
					displayList(f);
				}
			}
			
		} else if( file.isFile() ) {
			try {
				String path = file.getPath();
				String hash = getFileHash("SHA-512", file);
				
				System.out.println(hash + "." + path);
			} catch( NoSuchAlgorithmException e ) {
				e.printStackTrace();
			}
		}
	}

	public static String getFileHash(final String algorithm, final File target) throws NoSuchAlgorithmException, IOException
	{
		// 출처: http://reiphiel.tistory.com/entry/about-security-file-hash-checksum
		
	    final MessageDigest md = MessageDigest.getInstance(algorithm);
	    InputStream is = null;
	    
	    try
	    {
	        is = new FileInputStream(target);
	        byte[] buffer = new byte[1024];
	        int readBytes = 0;
	 
	        while ((readBytes = is.read(buffer)) > -1)
	        {
	            md.update(buffer, 0, readBytes);
	        }
	 
	        StringBuilder builder = new StringBuilder();
	        byte[] digest = md.digest();
	        for (byte b : digest)
	        {
	            builder.append(Integer.toHexString(0xff & b));
	        }
	        
	        return builder.toString();
	        
	    }
	    finally
	    {
	        if (is != null)
	        {
	            try
	            {
	                is.close();
	            }
	            catch (IOException e)
	            {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    /*
    	사용예제
    	getFileHash("SHA-512", new File("D:/FileZilla_Server-0_9_49.exe"));
    	*/
	}


}
