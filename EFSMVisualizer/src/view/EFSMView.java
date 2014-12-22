package view;

import java.awt.Graphics;

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
