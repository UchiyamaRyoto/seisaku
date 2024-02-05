package Tetris;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable,KeyListener{
	
	public static final int WIDTH = 192;
	public static final int HEIGHT = 416;
	private Board board;
	private Block block;
	private Block nextBlock;
	private Image blockImage;
	private Thread gameLoop;
	private Random rand;
	
	public MainPanel()
	{
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		loadImage("image/block.gif");
		rand = new Random();
		board = new Board();
		block = createBlock(board);
		addKeyListener(this);
		
		/*
		try {
			WaveEngine.load("se/kachi42.wav");
		}
		catch(UnsupportedAudioFileException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1) 
		{
	            e1.printStackTrace();
	    } 
		catch (LineUnavailableException e1) 
		{
	            e1.printStackTrace();
	    }
	        
	    try {
	            // BGMをロード
	            MidiEngine.load("bgm/tetrisb.mid");
	    } 
	    catch (MidiUnavailableException e) 
	    {
	            e.printStackTrace();
	    } 
	    catch (InvalidMidiDataException e) 
	    {
	            e.printStackTrace();
	    } 
	    catch (IOException e) 
	    {
	            e.printStackTrace();
	    }
	    
	    midiEngine.play(0);
	    */
		
	    gameLoop = new Thread(this);
	    gameLoop.start();
	}
	
	public void run() {
        long lastTime = 0;
        
        while (true) {
            boolean isFixed = block.move(Block.DOWN);
            if (isFixed) { 
                //WaveEngine.play(0);
                nextBlock = createBlock(board);
                block = nextBlock;
            }
            board.deleteLine();
            
            if (board.isStacked()) {
                board = new Board();
                block = createBlock(board);
            }

            //WaveEngine.render();

            // 再描画
            repaint();

            // 休止
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	
	public void paintComponent(Graphics g)
	{
 		super.paintComponent(g);
		board.draw(g,blockImage);
		block.draw(g,blockImage);
	}
	
	public Block createBlock(Board board)
	{
		int blockNo = rand.nextInt(7);
		switch(blockNo)
		{
		case Block.BAR : return new BarBlock(board);
		case Block.Z_SHAPE : return new ZShapeBlock(board);
		case Block.SQUARE : return new SquareBlock(board);
		case Block.L_SHAPE : return new LShapeBlock(board);
		case Block.REVERSE_Z_SHAPE : return new ReverseZShapeBlock(board);
		case Block.T_SHAPE : return new TShapeBlock(board);
		case Block.REVERSE_L_SHAPE : return new ReverseLShapeBlock(board);
		}
		return null;
	}
	
	public void loadImage(String filename)
	{
		ImageIcon icon = new ImageIcon(getClass().getResource(filename));
		blockImage=icon.getImage();
	}
	
	
	
	
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT)
		{
			block.move(Block.LEFT);
		}
		else if(key == KeyEvent.VK_DOWN)
		{
			block.move(Block.DOWN);
		}
		else if(key == KeyEvent.VK_RIGHT)
		{
			block.move(Block.RIGHT);
		}
		else if(key == KeyEvent.VK_SPACE)
		{
			block.turn();
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
