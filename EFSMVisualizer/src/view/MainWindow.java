package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.EFSM;
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
	JPanel panel = new JPanel();

	JTextArea logArea = new JTextArea();
	JButton btnMerge = new JButton("Merge");
	JLabel instUser = new JLabel("User behavior model");
	JPanel userPane = new JPanel();
	JLabel instMerge = new JLabel("Merged user behavior model");
	final JPanel mergedPane = new JPanel();
	JSplitPane splitPane = new JSplitPane();
	JPanel optionPane = new JPanel();
	JScrollPane scrollPane = new JScrollPane(logArea);

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

	private void addLogArea() {
		
		DBHelper.getInstance().constructModel(new UserLogGetter());
		List<User> users = UserBehaviorModels.getInstance().getAllUsers();

		StringBuilder sb = new StringBuilder();
		for(User user : users) {
			System.out.println(user.toString());
		}
		logArea.setRows(8);
		logArea.setColumns(8);
		logArea.setEditable(false); 
		panel.add(logArea);
		logArea.setText(sb.toString());		

	}
	
	private void addMergeOperation() {
		optionPane.add(btnMerge);
		btnMerge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int k = Integer.valueOf(textField.getText());
				EFSM mergedEFSM = EFSMUtil.getMergedModel(k);
				EFSMView mergedView = generateView(mergedEFSM);
				mergedPane.add(mergedView);
				mergedPane.revalidate();
			}
		});

	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 891, 787);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		scrollPane.setPreferredSize(new Dimension(500,200));	
		frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);
		frame.getContentPane().add(optionPane, BorderLayout.NORTH);

		textField = new JTextField();
		textField.setColumns(10);
		optionPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		optionPane.add(textField);

		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		mergedPane.setLayout(new BoxLayout(mergedPane, BoxLayout.Y_AXIS));
		instMerge.setFont(new Font(instMerge.getFont().getName(), Font.PLAIN, 30));
		mergedPane.add(instMerge);
		mergedPane.add(new EFSMView(getMergedAutomata()));

		userPane.setPreferredSize(new Dimension(400,400));
		instUser.setFont(new Font(instUser.getFont().getName(), Font.PLAIN, 30));
		userPane.add(instUser);
		userPane.add(new ArrowView());
		
		splitPane.setLeftComponent(userPane);
		splitPane.setRightComponent(mergedPane);
		
		addLogArea();
		addMergeOperation();
	}

	private EFSMView generateView(EFSM efsm) {
		return new EFSMView(efsm);
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
