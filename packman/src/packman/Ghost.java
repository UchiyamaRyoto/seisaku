package packman;

import java.awt.Graphics;
import java.awt.Image;

public class Ghost {
	public Image ghost;
	public static int ghostX = 1;
	public static int ghostY = 1;
	public static final int TILE_SIZE = 22;
	public String move="";
	static int manX;
	static int manY;
	
	public Ghost()
	{
		
	}
	
	static void draw(Graphics g,Image ghostImage)
	{
		g.drawImage(ghostImage,ghostX*TILE_SIZE, ghostY*TILE_SIZE,null);
	}
	
	public void move()
	{
		int up=999,left=999,right=999,down=999;
		getXY();
		if(Area.area[ghostY-1][ghostX]!=1)
		{
			up=Math.abs((ghostY-1)-manY)+Math.abs(ghostX-manX);
		}
		if(Area.area[ghostY+1][ghostX]!=1)
		{
			down=Math.abs((ghostY+1)-manY)+Math.abs(ghostX-manX);
		}
		if(Area.area[ghostY][ghostX-1]!=1)
		{
			left=Math.abs(ghostY-manY)+Math.abs((ghostX-1)-manX);
		}
		if(Area.area[ghostY][ghostX+1]!=1)
		{
			right=Math.abs(ghostY-manY)+Math.abs((ghostX+1)-manX);
		}
		
		//<にして=を別に作る
		if(up < left && up <= right && up <= down && move != "UP")
		{
			ghostY=ghostY-1;
			move="DOWN";
		}
		else if(down <= left && down <= right && down <= up && move!="DOWN")
		{
			ghostY=ghostY+1;
			move="UP";
		}
		else if(left <= down && left <= right && left <= up && move!="LEFT")
		{
			ghostX=ghostX-1;
			move="RIGHT";
		}
		else if(right <= left && right <= down && right <= up && move!="RIGHT")
		{
			ghostX=ghostX+1;
			move="LEFT";
		}
		
		else if(move=="DOWN" && Area.area[ghostY-1][ghostX]!=1)
		{
			ghostY=ghostY-1;
		}
		else if(move=="UP" && Area.area[ghostY+1][ghostX]!=1)
		{
			ghostY=ghostY+1;
		}
		else if(move=="LEFT" && Area.area[ghostY][ghostX-1]!=1)
		{
			ghostX=ghostX+1;
		}
		else if(move=="RIGHT" && Area.area[ghostY][ghostX+1]!=1)
		{
			ghostX=ghostX-1;
		}
	}
	
	static void getXY()
	{
		Ghost.manX=Man.manX;
		Ghost.manY=Man.manY;
	}
}
