package chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;

public class Client {

	private JFrame frame;
	private JTextField textFieldUserName;
	private JPasswordField passwordFieldpwd;
	private JTextField textFieldMsgToSend;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
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
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 563, 363);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(0, 0, 65, 24);
		frame.getContentPane().add(lblNewLabel);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(56, 0, 114, 24);
		frame.getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setBounds(184, 3, 45, 18);
		frame.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("\u767B\u9646");
		btnNewButton.setBounds(343, -1, 80, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u6CE8\u518C");
		btnNewButton_1.setBounds(437, 0, 80, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		passwordFieldpwd = new JPasswordField();
		passwordFieldpwd.setBounds(225, 0, 104, 24);
		frame.getContentPane().add(passwordFieldpwd);
		
		JTextPane textPaneMsgRecord = new JTextPane();
		textPaneMsgRecord.setText("\u6D88\u606F\u8BB0\u5F55");
		textPaneMsgRecord.setBounds(14, 49, 424, 202);
		frame.getContentPane().add(textPaneMsgRecord);
		
		textFieldMsgToSend = new JTextField();
		textFieldMsgToSend.setBounds(14, 279, 313, 24);
		frame.getContentPane().add(textFieldMsgToSend);
		textFieldMsgToSend.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("\u53D1\u9001\u6D88\u606F");
		btnNewButton_2.setBounds(345, 278, 93, 27);
		frame.getContentPane().add(btnNewButton_2);
	}
}
