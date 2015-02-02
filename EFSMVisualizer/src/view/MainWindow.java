package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
		
		/* log view */
		JTextArea logArea = new JTextArea();
		logArea.setRows(8);
		logArea.setColumns(200);
		logArea.setEditable(false);
		panel.add(logArea);
		DBHelper.getInstance().constructModel(new UserLogGetter());
		List<User> userLogs = UserBehaviorModels.getInstance().getAllUsers();
		StringBuilder sb = new StringBuilder();
		for(User user : userLogs) {
			sb.append(user.toString());
			//System.out.println(user.toString());
		}
		logArea.setText(sb.toString());
		JScrollPane scrollPane = new JScrollPane(logArea);
		scrollPane.setPreferredSize(new Dimension(500,200));
		frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);

		/* merge view */
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		final JPanel mergedPane = new JPanel();
		final JLabel instMerge = new JLabel("Merged user behavior model");
		instMerge.setFont(new Font(instMerge.getFont().getName(), Font.PLAIN, 30));
		mergedPane.add(instMerge);

		/* user behavior model view */
		JPanel userPane = new JPanel();
		userPane.setPreferredSize(new Dimension(400,400));		
		JLabel instUser = new JLabel("User behavior model");
		instUser.setFont(new Font(instUser.getFont().getName(), Font.PLAIN, 30));
		userPane.add(instUser);
		List<User> userViews = UserBehaviorModels.getInstance().getAllUsers();
		for(User user : userViews) {
			EFSM behaviorModel = user.getBehaviorModel();
			userPane.add(new EFSMView(String.valueOf(user.getId()), behaviorModel));
		}

		splitPane.setLeftComponent(userPane);
		splitPane.setRightComponent(mergedPane);

		/* merge menu */
		JPanel mergeMenuPane = new JPanel();
		frame.getContentPane().add(mergeMenuPane, BorderLayout.NORTH);
		textField = new JTextField();
		textField.setColumns(10);
		mergeMenuPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		mergeMenuPane.add(textField);
		JButton btnMerge = new JButton("Merge");
		mergeMenuPane.add(btnMerge);
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mergedPane.removeAll();
				mergedPane.add(instMerge);

				int k = Integer.valueOf(textField.getText());
				EFSM mergedEFSM = EFSMUtil.getMergedModel(k);
				
				
				
				System.out.println(mergedEFSM.generateDot());
				EFSMView mergedView = new EFSMView("#", mergedEFSM);
				mergedPane.add(mergedView);
				mergedPane.revalidate();
			}
		});
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