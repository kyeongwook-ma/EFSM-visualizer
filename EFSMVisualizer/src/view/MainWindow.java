package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

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

		JPanel optionPane = new JPanel();
		frame.getContentPane().add(optionPane, BorderLayout.NORTH);

		textField = new JTextField();
		textField.setColumns(10);
		optionPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		optionPane.add(textField);

		JButton btnMerge = new JButton("Merge");
		optionPane.add(btnMerge);

		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		JPanel mergedPane = new JPanel();
		StateView t = new StateView();
		t.setVisible(true);
		mergedPane.add(t);
		panel.add(t);
		optionPane.add(t);
		splitPane.setRightComponent(mergedPane);
		
		
		
	}

}
