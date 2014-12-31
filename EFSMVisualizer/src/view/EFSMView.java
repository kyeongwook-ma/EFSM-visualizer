package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.EFSM;
import model.Transition;

public class EFSMView extends JPanel {

	private EFSM automata;
	private Image arrowImg = ImageCasher.getImage("arrow.png");

	public EFSMView(EFSM automata) {
		this.automata = automata;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		Iterator<Integer> stateSeq = getStateSeq();
		while(stateSeq.hasNext()) {
			Integer stateId = stateSeq.next();
			add(new StateView(stateId.intValue()));
			add(new ArrowView());
			repaint();
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



}
