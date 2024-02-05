package packman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainPane extends JPanel implements Runnable,KeyListener{
	public static final int WIDTH=660;
	public static final int HEIGHT=880;
	private int manX;
	private int manY;
	private Image blockImage;
	private Image manImage;
	private Image ghostImage;
	private Image esaImage;
	private boolean isfinish=false;
	private Thread gameLoop;
	
	public MainPane()
	{
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setBackground(Color.BLACK);
		this.setFocusable(true);
		addKeyListener(this);
		loadImageArea("image/block.gif");
		loadImageMan("image/pacman.png");
		loadImageGhost("image/ghost.png");
		loadImageEsa("image/esa.png");
		gameLoop = new Thread(this);
		gameLoop.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Area.draw(g,blockImage,esaImage);
		Ghost.draw(g,ghostImage);
		Man.draw(g,manImage);
	}
	
	public void loadImageEsa(String filename)
	{
		ImageIcon icon = new ImageIcon(getClass().getResource(filename));
		esaImage=icon.getImage();
	}
	
	public void loadImageArea(String filename)
	{
		ImageIcon icon = new ImageIcon(getClass().getResource(filename));
		blockImage=icon.getImage();
	}
	
	public void loadImageMan(String filename)
	{
		ImageIcon icon = new ImageIcon(getClass().getResource(filename));
		manImage=icon.getImage();
	}
	
	public void loadImageGhost(String filename)
	{
		ImageIcon icon = new ImageIcon(getClass().getResource(filename));
		ghostImage=icon.getImage();
	}
	
	private void getmanXY()
	{
		this.manX=Man.manX;
		this.manY=Man.manY;
	}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		while(isfinish == false)
		{
			Ghost.move();
			repaint();
			
			try	{
				Thread.sleep(200);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
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
			Man.move(1);
		}else if(key == KeyEvent.VK_DOWN)
		{
			Man.move(2);
		}else if(key == KeyEvent.VK_RIGHT)
		{
			Man.move(3);
		}else if(key == KeyEvent.VK_UP)
		{
			Man.move(4);
		}
		getmanXY();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
