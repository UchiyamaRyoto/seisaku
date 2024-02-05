package Tetris;

public class BarBlock extends Block{
	public BarBlock(Board board)
	{
		super(board);
		block[0][1]=1;
		block[1][1]=1;
		block[2][1]=1;
		block[3][1]=1;
		imageNo=Block.BAR;
	}
}
