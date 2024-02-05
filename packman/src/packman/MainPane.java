package packman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPane extends JPanel implements Runnable,KeyListener{
	public static final int WIDTH=660;
	public static final int HEIGHT=880;
	private int manX;
	private int manY;
	private int score =0;
	private int key;
	private String move="";
	private Image blockImage;
	private Image manImage;
	private Image ghostImage;
	private Image esaImage;
	private boolean isfinish=false;
	private Thread gameLoop;
	private Area area;
	private Ghost ghost;
	private Man man;
	private int Gesa=0;
	private JPanel lower;
	private JLabel syuryo;
	
	public MainPane()
	{
		area = new Area();
		Gesa=area.getGesa();
		ghost = new Ghost();
		man = new Man();
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
		area.draw(g,blockImage,esaImage);
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
		this.manX=man.manX;
		this.manY=man.manY;
	}
	
	private void isEsa()
	{
		if(area.area[manY][manX]==9)
		{
			area.area[manY][manX]=0;
			score=score+50;
			Gesa--;
			////////////////////
			System.out.println(score);
		}
	}
	
	private boolean clear()
	{
		if(Gesa==0) {return true;}
		return false;
	}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		while(isfinish == false)
		{
			ghost.move();
			man.move(area, move);
			getmanXY();
			isEsa();
			repaint();
			
			if(clear())
			{
				isfinish=true;
				lower = new JPanel();
				lower.setSize(660,880);
				lower.setLocation(0,0);
				syuryo = new JLabel("クリア!");
				syuryo.setSize(660,880);
				syuryo.setLocation(0,0);
				syuryo.setFont(new Font( "ＭＳ ゴシック" , Font.BOLD, 80));
				syuryo.setSize(syuryo.getPreferredSize());
				lower.add(syuryo);
				packman.panel.add(lower);
				
			}
			
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
			key = e.getKeyCode();
			if(key == KeyEvent.VK_LEFT)
			{
				move="LEFT";
			}else if(key == KeyEvent.VK_DOWN)
			{
				move="DOWN";
			}else if(key == KeyEvent.VK_RIGHT)
			{
				move="RIGHT";
			}else if(key == KeyEvent.VK_UP)
			{
				move="UP";
			}
		}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}

