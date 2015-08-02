package main.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import javax.swing.ScrollPaneConstants;

import main.model.EFSMUtil;
import main.model.UserBehaviorModels;
import main.model.entity.EFSM;
import main.model.entity.User;
import main.util.DotUtil;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class MainWindow {
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//DotUtil.generateBMImg();
		//ImageCacher.load();

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

		/* merge view */
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		final JLabel instMerge = new JLabel("Merged user behavior model");
		instMerge.setFont(new Font(instMerge.getFont().getName(), Font.PLAIN, 14));
		splitPane.setResizeWeight(0.5);
		
		/* user behavior model view */
		JLabel instUser = new JLabel("User behavior model");
		instUser.setHorizontalAlignment(SwingConstants.CENTER);
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

		JSplitPane leftPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		leftPane.add(instUser);
		leftPane.add(userBMScroll);

		splitPane.setLeftComponent(leftPane);
		splitPane.setResizeWeight(0.5);
		
		JSplitPane rightPane = new JSplitPane();
		rightPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(rightPane);
		
		JPanel transitionPane = new JPanel();
		rightPane.setRightComponent(transitionPane);
		transitionPane.setLayout(null);
		
		JPanel statePane = new JPanel();
		rightPane.setLeftComponent(statePane);
		


	}


}