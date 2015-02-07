package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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

import util.DotUtil;
import model.EFSM;
import model.EFSMUtil;
import model.ImageCacher;
import model.State;
import model.Transition;
import model.User;
import model.UserBehaviorModels;
import model.db.DBHelper;
import model.db.UserLogGetter;
import javax.swing.ScrollPaneConstants;

public class MainWindow {
	private JFrame frame;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		DotUtil.generateBMImg();
		ImageCacher.load();
		
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
		JPanel logPanel = new JPanel();
		frame.getContentPane().add(logPanel, BorderLayout.SOUTH);
		
		/* log view */
		JTextArea logArea = new JTextArea();
		logArea.setRows(8);
		logArea.setColumns(200);
		logArea.setEditable(false);
		logPanel.add(logArea);
		List<User> userLogs = UserBehaviorModels.getInstance().getAllUsers();
		
		StringBuilder sb = new StringBuilder();
		for(User user : userLogs) {
			sb.append(user.toString());
		}
		
		logArea.setText(sb.toString());
		JScrollPane logScrollPane = new JScrollPane(logArea);
		//logScrollPane.setPreferredSize(new Dimension(500,100));
		//frame.getContentPane().add(logScrollPane, BorderLayout.SOUTH);

		/* merge view */
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		final JPanel mergedPane = new JPanel();
		final JLabel instMerge = new JLabel("Merged user behavior model");
		instMerge.setFont(new Font(instMerge.getFont().getName(), Font.PLAIN, 14));
		mergedPane.add(instMerge);

		/* user behavior model view */
		JLabel instUser = new JLabel("User behavior model");
		instUser.setFont(new Font(instUser.getFont().getName(), Font.PLAIN, 14));
		
		JPanel userBMPanel = new JPanel();	
		userBMPanel.setLayout(new BoxLayout(userBMPanel, BoxLayout.Y_AXIS));
		
		List<User> userViews = UserBehaviorModels.getInstance().getAllUsers();
		for(User user : userViews) {
			userBMPanel.add(new EFSMView(String.valueOf(user.getId())));
		}
		
		JScrollPane userBMScroll = new JScrollPane(userBMPanel);
		userBMScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		userBMScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JSplitPane userPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		userPane.add(instUser);
		userPane.add(userBMScroll);
		
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
				EFSMView mergedView = new EFSMView("#");
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