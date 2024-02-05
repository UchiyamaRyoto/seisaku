package packman;

import java.awt.Graphics;
import java.awt.Image;

public class Man {
	public static final int TILE_SIZE=22;
	public static int manX=13;
	public static int manY=11;
	public Man()
	{
		
	}
	
	static  void draw(Graphics g,Image manImage)
	{
		g.drawImage(manImage,manX*TILE_SIZE, manY*TILE_SIZE,null);
	}
	
	static void move(int a)
	{
		if(a==1)
		{
			if(isfixed(a))
			{
				manX=manX-1;
			}
		}
		else if(a==2)
		{
			if(isfixed(a))
			{
				manY=manY+1;
			}
		}
		else if(a==3)
		{
			if(isfixed(a))
			{
				manX=manX+1;
			}
		}
		else if(a==4)
		{
			if(isfixed(a))
			{
				manY=manY-1;
			}
		}
	}
	
	public static  boolean isfixed(int a)
	{
		if(a==1)
		{
			if(Area.area[manY][manX-1]!=1)
			{
				return true;
			}
		}
		else if(a==2)
		{
			if(Area.area[manY+1][manX]!=1)
			{
				return true;
			}
		}
		else if(a==3)
		{
			if(Area.area[manY][manX+1]!=1)
			{
				return true;
			}
		}
		else if(a==4)
		{
			if(Area.area[manY-1][manX]!=1)
			{
				return true;
			}
		}
		return false;
	}
}
