package core;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadAndWrite {

	public static int getScore()
    {
    	String s="";
    	Scanner in =null;
		try
		{
			in =new Scanner(new File("score.txt"));
		}
		catch(Exception ex)
		{
			System.out.print("文件不存在");
		}
			if(in.hasNext())
			{
				s=in.nextLine().trim();
				in.close();
				if(s.length()<1)
				{
					return 0;
				}
				else
				{
					return Integer.parseInt(s);
				}
			}	
			else
			{
				in.close();
				return 0;
			}
    }
    
    public static void setScore(int score)
    {
    	String s=""+score;
    	try
    	{
    		PrintWriter out=new PrintWriter(new File("score.txt"));
    		out.write(s);
    		out.close();
    	}
    	catch (Exception ex)
    	{
    		System.out.print(ex);
    	}
    	
    }
  }
