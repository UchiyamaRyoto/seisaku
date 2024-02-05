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
	
	static void draw(Graphics g,Image manImage)
	{
		g.drawImage(manImage,manX*TILE_SIZE, manY*TILE_SIZE,null);
	}
	
	public void move(Area area,String a)
	{
		if(a=="LEFT")
		{
			if(isfixed(area,a))
			{
				manX=manX-1;
			}
		}
		else if(a=="DOWN")
		{
			if(isfixed(area,a))
			{
				manY=manY+1;
			}
		}
		else if(a=="RIGHT")
		{
			if(isfixed(area,a))
			{
				manX=manX+1;
			}
		}
		else if(a=="UP")
		{
			if(isfixed(area,a))
			{
				manY=manY-1;
			}
		}
	}
	
	private boolean isfixed(Area area,String a)
	{
		if(a=="LEFT")
		{
			if(area.area[manY][manX-1]!=1)
			{
				return true;
			}
		}
		else if(a=="DOWN")
		{
			if(area.area[manY+1][manX]!=1)
			{
				return true;
			}
		}
		else if(a=="RIGHT")
		{
			if(area.area[manY][manX+1]!=1)
			{
				return true;
			}
		}
		else if(a=="UP")
		{
			if(area.area[manY-1][manX]!=1)
			{
				return true;
			}
		}
		return false;
	}
}
