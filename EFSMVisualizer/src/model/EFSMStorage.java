package model;

import java.util.ArrayList;
import java.util.List;

public class EFSMStorage {

	private List<EFSM> EFSMs;
	private static EFSMStorage instance = new EFSMStorage();
		
	private EFSMStorage() { 
		EFSMs = new ArrayList<EFSM>();
	}
	
	public static EFSMStorage getInstance() {
		if(instance == null)
			return new EFSMStorage();
		else
			return instance;
	}
	
	public void addAutomata(EFSM automata) {
		EFSMs.add(automata);
	}
	
	public List<EFSM> getAllAutomatas() {
		return EFSMs;
	}
	
	
}
