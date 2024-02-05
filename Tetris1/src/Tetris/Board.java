package Tetris;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class Board {
	public static final int MAX_X = 12;
	public static final int MAX_Y = 26;
	public static final int TILE_SIZE = 16;
	private int[][] board;
	private int[][] boardImage;
	
	public Board()
	{
		board = new int[MAX_Y][MAX_X];
		boardImage = new int[MAX_Y][MAX_X];
		init();
	}
	
	public void init()
	{
		for(int y=0;y<MAX_Y;y++)
		{
			for(int x=0;x<MAX_X;x++)
			{
				if(x==0||x==MAX_X - 1)
				{
					board[y][x]=1;
					boardImage[y][x]=Block.WALL;
				}
				else if(y==MAX_Y - 1)
				{
					board[y][x] = 1;
					boardImage[y][x]=Block.WALL;
				}
				else
				{
					board[y][x]=0;
				}
			}
		}
	}
	
	public void draw(Graphics g,Image blockImage)
	{
		for(int y=0;y<MAX_Y;y++)
		{
			for(int x=0;x<MAX_X;x++)
			{
				if(board[y][x]==1)
				{
				g.drawImage(blockImage, x*TILE_SIZE, y*TILE_SIZE, x*TILE_SIZE+TILE_SIZE,y*TILE_SIZE+TILE_SIZE 
						,boardImage[y][x]*TILE_SIZE, 0, boardImage[y][x]*TILE_SIZE+TILE_SIZE,TILE_SIZE , null);
				}
			}
		}
	}
	
	public boolean isMovable(Point newPos,int[][] block)
	{
		for(int y=0;y<Block.MAX_Y;y++)
		{
			for(int x=0;x<Block.MAX_X;x++)
			{
				if(block[y][x]==1)
				{
					if(newPos.y+y<0)
					{
						if(newPos.x+x<=0||newPos.x+x>=MAX_X -1)
						{
							return false;
						}
					}
					else if(board[newPos.y+y][newPos.x+x]==1)
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void deleteLine()
	{
		for(int y=0;y<MAX_Y-1;y++)
		{
			int count=0;
			for(int x=1;x<MAX_X-1;x++)
			{
			if(board[y][x]==1) count++;	
			}
			if(count==Board.MAX_X-2)
			{
				for(int x=1;x<MAX_X-1;x++)
				{
					board[y][x]=0;
				}
				for(int ty=y;ty>0;ty--)
				{
					for(int tx=1;tx<MAX_X;tx++)
					{
						board[ty][tx]=board[ty-1][tx];
						boardImage[ty][tx]=boardImage[ty-1][tx];
					}
				}
			}
		}
	}
	
	public void fixBlock(Point pos,int[][] block, int imageNo)
	{
		for(int y=0;y<Block.MAX_Y;y++)
		{
			for(int x=0;x<Block.MAX_X;x++)
			{
				if(block[y][x] == 1)
				{
					if(pos.y+y<0)continue;
					board[pos.y+y][pos.x+x]=1;
					boardImage[pos.y+y][pos.x+x]=imageNo;
				}
			}
		}
	}
	
	public boolean isStacked()
	{
		for(int x=1;x<MAX_X-1;x++)
		{
			if(board[0][x] == 1)
			{
				return true;
			}
		}
		return false;
	}
	
}
