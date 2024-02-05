package packman;

import java.awt.Container;

import javax.swing.JFrame;

public class packman extends JFrame{
	static packman frame;
	public packman()
	{
		setTitle("パックマン");
		setResizable(false);
		
		MainPane panel = new MainPane();
		Container contentPane = getContentPane();
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
