package Tetris;

public class TShapeBlock extends Block{
	public TShapeBlock(Board board)
	{
		super(board);
		block[1][1]=1;
		block[2][1]=1;
		block[2][2]=1;
		block[3][1]=1;
		imageNo=Block.T_SHAPE;
	}
}
