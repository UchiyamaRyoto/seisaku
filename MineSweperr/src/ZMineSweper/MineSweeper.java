package ZMineSweper;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class MineSweeper extends MouseAdapter implements ActionListener{
	private static final int CELL_SIZE=30;
	private MineField.Difficulty difficulty;
	private MineField.CellView[][] viewLog;
	private MineField game;
	private int height;
	private int width;
	private JFrame frame;
	private JPanel upper;
	private JPanel field;
	private JPanel lower;
	private JLabel[][] cells;
	private JComboBox<MineField.Difficulty> comboBox;
	private JButton resetButton;
	private JButton cheatButton;
	private JLabel timeCounter;
	private JLabel mineCounter;
	private final Object locker = new Object();
	private boolean timer = false;
	private int currentX=0;
	private int currentY=0;
	
	private MineSweeper(MineField.Difficulty difficulty)
	{
		this.difficulty=difficulty;
		this.game=new MineField(difficulty);
		this.viewLog=game.getView();
		this.height=game.getHeight();
		this.width=game.getWidth();
		frame=new JFrame("Mine Sweeper");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		initializeFrame();
		}
	
	/*
	 private void removeComponents() 
	 */
	private void initializeFrame()
	{
		frame.getContentPane().setPreferredSize(new Dimension(CELL_SIZE*width,CELL_SIZE*(2+height)));
		 frame.pack();
	        upper = new JPanel();
	        upper.setSize(CELL_SIZE*width, CELL_SIZE);
	        upper.setLocation(0, 0);
	        comboBox = new JComboBox<>(MineField.Difficulty.values());
	        comboBox.setSelectedItem(difficulty);
	        resetButton = new JButton("RESET");
	        resetButton.addActionListener(this);
	        cheatButton = new JButton("CHEAT");
	        cheatButton.addActionListener(this);
	        upper.add(comboBox);
	        upper.add(resetButton);
	        upper.add(cheatButton);
	        field = new JPanel();
	        field.setVisible(true);
	        field.setLayout(new GridLayout(height, width));
	        field.setSize(CELL_SIZE*width, CELL_SIZE*height);
	        field.setLocation(0, CELL_SIZE);
	        cells = new JLabel[height][width];
	        for(int i = 0;i<height;i++){
	            for(int j = 0;j<width;j++){
	                cells[i][j] = new JLabel();
	                cells[i][j].setOpaque(true);
	                cells[i][j].setVisible(true);
	                cells[i][j].addMouseListener(this);
	                cells[i][j].setHorizontalAlignment(SwingConstants.CENTER);
	                updateViewOf(j, i);
	                field.add(cells[i][j]);
	            }
	        }
	        lower = new JPanel();
	        lower.setSize(CELL_SIZE*width,CELL_SIZE);
	        lower.setLocation(0,CELL_SIZE*(1+height));
	        timeCounter=new JLabel("0");
	        mineCounter=new JLabel(""+game.getMinus());
	        lower.add(new JLabel("TIME:"));
	        lower.add(timeCounter);
	        lower.add(new JLabel("MINE:"));
	        lower.add(mineCounter);
	        field.add(upper);
	        field.add(field);
	        field.add(lower);
	        frame.setVisible(true);
	        frame.repaint();
	}
	
	private static final Color ENTERED_COLOR = new Color(70,160,70);
	private static final Color COVERED_COLOR = new Color(30,120,30);
	class Style{
		private String text;
		private Border border;
		private Color background;
		private Color foreground;
		Style(String text,Color color,boolean covered)
		{
			this.text=text;
			this.border=new BevelBorder(covered? BevelBorder.RAISED:BevelBorder.LOWERED);
			this.background=covered? COVERED_COLOR: new Color(140,200,140);
			this.foreground=color;
		}
	}
	
	private Style getStyleOf(MineField.CellView view)
	{
		switch(view)
		{
		  case M0: return new Style("　", Color.BLACK, false);
          case M1: return new Style("１", new Color(51, 102, 204), false);
          case M2: return new Style("２", new Color(0, 153, 51), false);
          case M3: return new Style("３", new Color(204, 51, 0), false);
          case M4: return new Style("４", new Color(0, 51, 153), false);
          case M5: return new Style("５", new Color(102, 0, 0), false);
          case M6: return new Style("６", new Color(31, 184, 133), false);
          case M7: return new Style("７", new Color(204, 0, 51), false);
          case M8: return new Style("８", new Color(30, 0, 0), false);
          case MINE: return new Style("※", Color.RED, false);
          case COVERED: return new Style("　", Color.BLACK, true);
          case STILL_COVERED: return new Style("☓", game.isSecured()?Color.BLACK:Color.WHITE, true);
          case FLAGGED: return new Style("▼", game.isSecured()?Color.BLACK:Color.RED, true);
          case MISS_FLAGGED: return new Style("▽", Color.RED, true);
          default: throw new IllegalArgumentException();
		}
	}
	
	private void updateViewOf(int x,int y)
	{
		viewLog[y][x] = game.getViewOf(x, y);
		Style style = getStyleOf(viewLog[y][x]);
		cells[y][x].setText(style.text);
		cells[y][x].setBorder(style.border);
		cells[y][x].setForeground(style.foreground);
		cells[y][x].setBackground(style.background);
	}
	
	public static void main(String[] args)
	{
		new MineSweeper(MineField.Difficulty.INTERMEDIATE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
