package Tetris;

import java.awt.Container;

import javax.swing.JFrame;

public class Tetris extends JFrame{
	private static Tetris frame;
	public Tetris()
	{
		setTitle("テトリス");
		setResizable(false);
		
		MainPanel panel = new MainPanel();
		Container contentPane = getContentPane();
		contentPane.add(panel);
		pack();
	}
	public static void main(String[] args)
	{
		Tetris.frame = new Tetris();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	;}
}
