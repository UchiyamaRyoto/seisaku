package packman;

import java.awt.Container;

import javax.swing.JFrame;

public class packman extends JFrame{
	static packman frame;
	static Container contentPane;
	static MainPane panel;
	public packman()
	{
		setTitle("パックマン");
		setResizable(false);
		
		panel = new MainPane();
		contentPane = getContentPane();
		contentPane.add(panel);
		pack();
	}
	public static void main(String[]args)
	{
		packman.frame=new packman();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
