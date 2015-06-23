package main.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.model.ImageCacher;

public class EFSMView extends JPanel {

	private JLabel usrLabel = new JLabel(); 
	private String usrId;
	
	public EFSMView(String usrId) {

		this.usrId = usrId;
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		usrLabel.setFont(new Font(usrLabel.getFont().getName(), Font.PLAIN, 14));
		usrLabel.setText("User " + this.usrId + " : ");
		add(usrLabel);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			BufferedImage img = ImageCacher.getImage("User_" + this.usrId + "_out.png");
			//this.setSize(new Dimension(img.getWidth(), img.getHeight()));
			g.drawImage(img, 0, 0,  null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
