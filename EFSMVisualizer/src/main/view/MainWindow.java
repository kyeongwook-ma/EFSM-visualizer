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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;

public class MainWindow {
	private JFrame frame;
	private JTextField screenText;
	private JTextField actionText;
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
		rightPane.setResizeWeight(0.5);
		splitPane.setRightComponent(rightPane);
		
		JPanel transitionPane = new JPanel();
		rightPane.setRightComponent(transitionPane);
		transitionPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Transition");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		transitionPane.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel transitionContentPane = new JPanel();
		transitionPane.add(transitionContentPane, BorderLayout.CENTER);
		GridBagLayout gbl_transitionContentPane = new GridBagLayout();
		gbl_transitionContentPane.columnWidths = new int[]{0, 0};
		gbl_transitionContentPane.rowHeights = new int[]{0, 0, 0};
		gbl_transitionContentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_transitionContentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		transitionContentPane.setLayout(gbl_transitionContentPane);
		
		JPanel screenParentPane = new JPanel();
		GridBagConstraints gbc_screenParentPane = new GridBagConstraints();
		gbc_screenParentPane.insets = new Insets(0, 0, 5, 0);
		gbc_screenParentPane.fill = GridBagConstraints.BOTH;
		gbc_screenParentPane.gridx = 0;
		gbc_screenParentPane.gridy = 0;
		transitionContentPane.add(screenParentPane, gbc_screenParentPane);
		screenParentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Screen");
		screenParentPane.add(lblNewLabel_2, BorderLayout.WEST);
		
		JPanel screenPane = new JPanel();
		screenParentPane.add(screenPane, BorderLayout.CENTER);
		screenPane.setLayout(new BorderLayout(0, 0));
		
		screenText = new JTextField();
		screenPane.add(screenText);
		screenText.setColumns(10);
		
		JPanel actionParentPane = new JPanel();
		GridBagConstraints gbc_actionParentPane = new GridBagConstraints();
		gbc_actionParentPane.fill = GridBagConstraints.BOTH;
		gbc_actionParentPane.gridx = 0;
		gbc_actionParentPane.gridy = 1;
		transitionContentPane.add(actionParentPane, gbc_actionParentPane);
		actionParentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Action");
		actionParentPane.add(lblNewLabel_3, BorderLayout.WEST);
		
		JPanel actionPane = new JPanel();
		actionParentPane.add(actionPane, BorderLayout.CENTER);
		actionPane.setLayout(new BorderLayout(0, 0));
		
		actionText = new JTextField();
		actionPane.add(actionText);
		actionText.setColumns(10);
		
		JPanel statePane = new JPanel();
		rightPane.setLeftComponent(statePane);
		statePane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("State");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statePane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel picPane = new JPanel();
		statePane.add(picPane);
		


	}
}