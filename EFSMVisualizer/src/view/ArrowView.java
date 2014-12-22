package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

public class ArrowView extends JComponent {
	
	private Image img;
	
	public ArrowView() {
		img = ImageCasher.getImage("arrow.png");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
	
}
