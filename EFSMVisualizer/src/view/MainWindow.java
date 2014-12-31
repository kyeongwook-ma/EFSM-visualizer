package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.EFSM;
import model.EFSMStorage;
import model.EFSMUtil;
import model.State;
import model.Transition;
import model.User;
import model.UserBehaviorModels;
import model.db.DBHelper;
import model.db.UserLogGetter;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 891, 787);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		JTextArea logArea = new JTextArea();
		logArea.setRows(8);
		logArea.setColumns(8);
		logArea.setEditable(false); 
		panel.add(logArea);


		DBHelper.getInstance().constructModel(new UserLogGetter());
		List<User> users = UserBehaviorModels.getInstance().getAllUsers();

		StringBuilder sb = new StringBuilder();
		for(User user : users) {
			System.out.println(user.toString());
		}

		logArea.setText(sb.toString());		

		JScrollPane scrollPane = new JScrollPane(logArea);
		scrollPane.setPreferredSize(new Dimension(500,200));	
		frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);

		JPanel optionPane = new JPanel();
		frame.getContentPane().add(optionPane, BorderLayout.NORTH);

		textField = new JTextField();
		textField.setColumns(10);
		optionPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		optionPane.add(textField);

		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel mergedPane = new JPanel();
		mergedPane.add(new EFSMView(getMergedAutomata()));

		JPanel userPane = new JPanel();
		userPane.setLayout(new GridLayout(0, 1, 0, 0));

		splitPane.setLeftComponent(userPane);
		splitPane.setRightComponent(mergedPane);
		
		JButton btnMerge = new JButton("Merge");
		optionPane.add(btnMerge);
		btnMerge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int k = Integer.valueOf(textField.getText());
				EFSM mergedEFSM = merge(k);
				EFSMView mergedView = generateView(mergedEFSM);
				mergedPane.add(mergedView);
			}
		});


	}

	private EFSMView generateView(EFSM efsm) {
		return new EFSMView(efsm);
	}
	
	private EFSM merge(int k) {
		List<User> users = UserBehaviorModels.getInstance().getAllUsers();

		EFSM firstEFSM = users.get(0).getBehaviorModel();

		for(User user : users) {
			try {
				firstEFSM = EFSMUtil.merge(firstEFSM, user.getBehaviorModel(), k);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return firstEFSM;
	}

	private EFSM getMergedAutomata() {

		EFSM mergedAutomata = new EFSM();

		Transition t2 = 
				new Transition.TransitionBuilder(State.newInstance(2), State.newInstance(3))
		.x(30).y(40).event("onTouch").timestamp(100).target("더보기")
		.createTransition();

		Transition t3 = 
				new Transition.TransitionBuilder(State.newInstance(3), State.newInstance(4))
		.x(30).y(40).event("onTouch").timestamp(300).target("이벤트")
		.createTransition();

		mergedAutomata.addStateSeq(t2,t3);	
		return mergedAutomata;
	}

}
