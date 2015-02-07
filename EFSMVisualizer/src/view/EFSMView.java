package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.EFSM;
import model.ImageCacher;
import model.Transition;

public class EFSMView extends JPanel {

	private JLabel usrLabel = new JLabel(); 
	private String usrId;
	
	public EFSMView(String usrId) {

		this.usrId = usrId;
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		usrLabel.setFont(new Font(usrLabel.getFont().getName(), Font.PLAIN, 20));
		usrLabel.setText("User " + this.usrId + " : ");
		add(usrLabel);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			BufferedImage img = ImageCacher.getImage("User_" + this.usrId + "_out.png");
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
