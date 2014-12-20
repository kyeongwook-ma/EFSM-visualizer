package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Automata;

public class EFSMView extends JPanel {

	private Automata automata;
	
	public EFSMView(Automata automata) {
		
		this.automata = automata;
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	
	}
	
}
