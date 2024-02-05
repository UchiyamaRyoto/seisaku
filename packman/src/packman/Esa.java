package packman;

public class Esa {
	public Esa()
	{
		
	}
	public boolean isWear(Area area,int manX,int manY)
	{
		if(area.area[manY][manX]==9)
		{
			return true;
		}
		return false;
	}
}
