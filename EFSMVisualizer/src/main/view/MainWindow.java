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
import main.model.ImageCacher;
import main.model.UserBehaviorModels;
import main.model.entity.EFSM;
import main.model.entity.User;
import main.util.DotUtil;

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
		//mergedPane.add(instMerge);

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

				int k = Integer.valueOf(textField.getText());
				EFSM mergedEFSM = EFSMUtil.getMergedModel(k);
				try {
					DotUtil.dotFileWrite("User_merged", mergedEFSM);
					DotUtil.generateBMImg();

					EFSMView mergedView = new EFSMView("merged");
					mergedPane.setLayout(new BorderLayout());
					mergedPane.add(mergedView, BorderLayout.CENTER);

					mergedPane.revalidate();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
	}


}