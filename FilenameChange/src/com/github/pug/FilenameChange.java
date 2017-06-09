package com.github.pug;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FilenameChange
{
	// ����: �ٸ����� ����ϱ����� ����ȯ�� �ߴµ�, �״�� �����̸� �����ϱ� �����Ƽ� �������� �ձ��ڸ� ���� 1,2,3������ �ؼ� ��� ������� �ߴµ� �ᱹ�� �̰� �������� �̸����� ������ �ٲ��ֱⰡ ������ ����� �������� ��鰳�� ���̾��ٴ� �������� ������ �ڵ�� ©���ְٴ� ������ ����� �ؼ� ����.
	// ���α׷� ����: PathA�� �ִ� "1 ~~~, 2 ~~~"ó�� ���ִ� ���ϸ� �״�� ����, PathB�� �ִ� "1, 2"�� ���ִ� �����̸��� ���� ������� �ٲ���.
	// ���� ���� ����: PathA�� �ִ� ���ڵ��� ��ġ�� �ʰ� �����ؾߵȴ�.

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
