package com.github.pug;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FilenameChange
{
	// 개요: 다른기기로 재생하기위해 영상변환은 했는데, 그당시 파일이름 지정하기 귀찮아서 원본파일 앞글자를 따서 1,2,3식으로 해서 몇개를 만들었긴 했는데 결국에 이거 원본파일 이름따서 일일이 바꿔주기가 귀찮고 몇개여서 다행이지 몇백개면 답이없다는 생각끝에 간단히 코드는 짤수있겟다 생각이 들더라 해서 만듦.
	// 프로그램 목적: PathA에 있는 "1 ~~~, 2 ~~~"처럼 되있는 파일명 그대로 따서, PathB에 있는 "1, 2"로 되있는 파일이름을 숫자 순서대로 바꿔줌.
	// 막지 않은 결함: PathA에 있는 숫자들이 겹치지 않게 주의해야된다.

	final static String resPath = "E:\\";
	final static String workPath = "Z:\\";
	
	public static void main(String[] args)
	{
		int n = 1;
		Map<Integer, String> hashTable = new HashMap<Integer, String>();
		File []ls;
		
		ls = new File(resPath).listFiles();
		for( File f : ls )
		{
			String name = f.getName();
			if (name.indexOf(String.format("%d", n)) == 0)
			{
				hashTable.put(n++, name);
			}
		}
		
		ls = new File(workPath).listFiles();
		for( File f : ls )
		{
			String name = f.getName();
			int idx = name.lastIndexOf('.');
			if( idx >= 0 )
			{
				name = name.substring(0, idx);	
			}
			try
			{
				String newFname;
				
				newFname = hashTable.get(Integer.parseInt(name));
				if (newFname != null)
				{
					newFname = f.getParent() + newFname;
					f.renameTo(new File(newFname));
					System.out.println(newFname);
				}
			}
			catch(NumberFormatException e)
			{
				// Nothing work here.
			}
			
		}
		
	}

}
