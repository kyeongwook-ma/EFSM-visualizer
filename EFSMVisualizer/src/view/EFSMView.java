package view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class EFSMView extends JPanel {

	List<StateView> stateViews;
	
	public EFSMView(StateView initState) {
		stateViews = new ArrayList<StateView>();
		stateViews.add(initState);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for(StateView v : stateViews) {
		
		}
	
	}
	
}
