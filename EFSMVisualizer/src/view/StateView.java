package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class StateView extends JComponent {

	public void paintComponent(Graphics page) {
		page.setColor(Color.BLACK);
		setSize(100,100);
		drawCircle(50,50,20);
		
	}

	private void drawCircle(int x, int y, int radius) {
		getGraphics().drawOval(x-radius, y-radius, radius*2, radius*2);
	}
}
