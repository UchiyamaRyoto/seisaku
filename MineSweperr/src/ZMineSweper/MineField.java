package ZMineSweper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MineField
{
	private Status status = Status.READY;
	private List<Cell> cells = new ArrayList();
	private final int height;
	private final int width;
	private final int minus;
	private int openCount=0;
	
	MineField(Difficulty d)
	{
		this(d.height,d.width,d.minus);
	}
	
	private MineField(int height,int width,int minus)
	{
		this.height=height;
		this.width=width;
		this.minus=minus;
		IntStream.range(0, width*height).forEach(i->cells.add(new Cell(i)));
	}
	
	public enum Difficulty
	{
		BEGINNER(9,9,10),
		INTERMEDIATE(16,16,40),
		ADVANCED(16,30,99);
		private final int height;
		private final int width;
		private final int minus;
		Difficulty(int height,int width,int minus)
		{
			this.height=height;
			this.width=width;
			this.minus=minus;
			
		}
	}
	
	private enum Status
	{
		READY,GENERATED,EXPLOODED,SECURED;
	}
	
	private class Cell
	{
		private static final int MINE=9;
		private final int index;
		private int value=0;
		private Face face = Face.DEFAULT;
		
		private Cell(int index) 
		{
		this.index=index;
		}
		
		private boolean isMine()
		{
			return this.value==MINE;
		}
		
		private List<Cell> aroundCells()
		{
			return IntStream.range(0, 9).filter(i->i!=4)
					.mapToObj(i->new int[]{index%width+i%3-1,index/width+i/3-1})
					.filter(p->p[0]>=0&&p[0]<width&&p[1]<height)
					.mapToInt(p->p[0] + p[1]*width)
					.mapToObj(cells::get)
					.collect(Collectors.toList());
		}
	}
	
	private enum Face
	{
		DEFAULT,FLAGGED,REMOVED;
	}
	
	@SuppressWarnings("unused")
	public enum CellView
	{
		M0,M1,M2,M3,M4,M5,M6,M7,M8,MINE,COVERED,FLAGGED,STILL_COVERED,MISS_FLAGGED
	}
	
	CellView getViewOf(int x,int y)
	{
		return getViewOf(cells.get(x+y*width));
	}
	
	private CellView getViewOf(Cell target)
	{
		if(isFinished()) {
			switch(target.face)
			{
			case DEFAULT:return target.isMine()? CellView.STILL_COVERED: CellView.COVERED;
			case FLAGGED:return target.isMine()? CellView.FLAGGED:CellView.MISS_FLAGGED;
			case REMOVED:return CellView.values()[target.value];			}
		}
		else
		{
			switch(target.face)
			{
			case DEFAULT:return CellView.COVERED;
			case FLAGGED:return CellView.FLAGGED;
			case REMOVED:return CellView.values()[target.value];
			}
		}
		throw new IllegalArgumentException();
	}
	
	CellView[][] getView()
	{
		CellView[][] view = new CellView[height][width];
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				view[i][j]=getViewOf(j,i);
			}
		}
		
		return view;
	}
	
	boolean isFinished()
	{
		return status==Status.EXPLOODED||status==Status.SECURED;
	}
	
	boolean isSecured()
	{
		return status==Status.SECURED;
	}
	
	int getHeight()
	{
		return height;
	}
	int getWidth()
	{
		return width;
	}
	int getMinus()
	{
		return minus;
	}
}