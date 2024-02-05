package Tetris;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class Block {
	public static final int MAX_Y=4;
	public static final int MAX_X=4;
	private static final int TILE_SIZE = Board.TILE_SIZE;
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	
	public static final int BAR = 0;
	public static final int Z_SHAPE = 1;
	public static final int SQUARE = 2;
	public static final int L_SHAPE = 3;
	public static final int REVERSE_Z_SHAPE = 4;
	public static final int T_SHAPE = 5;
	public static final int REVERSE_L_SHAPE = 6;
	public static final int WALL = 7;
	
	protected int[][] block;
	protected int imageNo;
	protected Point pos;
	protected Board board;
	
	public Block(Board board)
	{
		block = new int[MAX_Y][MAX_X];
		for(int y=0;y<MAX_Y;y++)
		{
			for(int x=0;x<MAX_X;x++)
			{
				block[y][x]=0;
			}
		}
		
		imageNo=6;
		pos = new Point(4,-4);
		
		this.board=board;
	}
	
	public void draw(Graphics g, Image blockImage)
	{
		for(int y=0;y<MAX_Y;y++)
		{
			for(int x=0;x<MAX_X;x++)
			{
				if(block[y][x]==1)
				{
					g.drawImage(blockImage, (pos.x+x) * TILE_SIZE, (pos.y+y) * TILE_SIZE, (pos.x+x)*TILE_SIZE+TILE_SIZE, (pos.y+y)*TILE_SIZE+TILE_SIZE,
                            imageNo * TILE_SIZE, 0, imageNo * TILE_SIZE + TILE_SIZE, TILE_SIZE, null);
				}
			}
		}
	}
	
	public boolean move(int dir)
	{
		switch(dir)
		{
		case LEFT:
			Point newPos = new Point(pos.x-1,pos.y);
			if(board.isMovable(newPos,block))
			{
				pos=newPos;
			}
			break;
		case RIGHT: 
			newPos = new Point(pos.x+1,pos.y);
			if(board.isMovable(newPos,block))
			{
				pos=newPos;
			}
			break;
		case DOWN: 
			newPos = new Point(pos.x,pos.y+1);
			if(board.isMovable(newPos,block))
			{
				pos=newPos;
			}
			else
			{
				board.fixBlock(pos,block,imageNo);
				return true;
			}
			break;
		}
		return false;
	}
	
	public void turn()
	{
		int[][] turnedBlock = new int[MAX_Y][MAX_X];
		
		for(int y=0;y<MAX_Y;y++)
		{
			for(int x=0;x<MAX_X;x++)
			{
				turnedBlock[x][MAX_Y-1-y]=block[y][x];
			}
		}
		
		if(board.isMovable(pos,turnedBlock))
		{
			block=turnedBlock;
		}
	}
}
