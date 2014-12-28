package view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JPanel;

import model.Automata;
import model.Transition;

public class EFSMView extends JPanel {

	private Automata automata;
	private Image arrowImg = ImageCasher.getImage("arrow.png");

	public EFSMView(Automata automata) {
		this.automata = automata;

		Iterator<Integer> stateSeq = getStateSeq();

		while(stateSeq.hasNext()) {
			Integer stateId = stateSeq.next();
		//	add(new StateView(stateId.intValue()));
		}
	}

	private Iterator<Integer> getStateSeq() {

		TreeSet<Integer> stateSet = new TreeSet<Integer>();

		List<Transition> transitions = automata.getAllTransition();
		for(Transition t : transitions) {
			stateSet.add(t.getSrc().getStateId());
			stateSet.add(t.getDst().getStateId());
		}

		return stateSet.iterator();
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(arrowImg, 0, 0,30, 30, this);
	}

}
