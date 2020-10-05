package Display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	int width, height;
	
	public Display(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		frame = new JFrame(title);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		frame.add(canvas);
		frame.pack();
		
	}
	public JFrame getFrame()
	{
		return frame;
	}
	public Canvas getCanvas()
	{
		return canvas;
	}
	
}
