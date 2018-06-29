package com.imnu.chat;

import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.ListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client {

	private final int port = 9999;//端口号
	private Socket socket;
	ObjectInputStream ois;//文件输入流
	ObjectOutputStream oos;//文件输出流
	private String localUserName;
	
	
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
		
		JButton btnLogin = new JButton("\u767B\u9646");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnLogin.getText().equals("登录")) {
					localUserName = textFieldUserName.getText().trim();
				}
					
			}
		});
		btnLogin.setBounds(386, -1, 104, 26);
		frame.getContentPane().add(btnLogin);
		
		passwordFieldpwd = new JPasswordField();
		passwordFieldpwd.setBounds(225, 0, 104, 24);
		frame.getContentPane().add(passwordFieldpwd);
		
		textFieldMsgToSend = new JTextField();
		textFieldMsgToSend.setBounds(14, 279, 313, 24);
		frame.getContentPane().add(textFieldMsgToSend);
		textFieldMsgToSend.setColumns(10);
		
		JButton btnSendMsg = new JButton("\u53D1\u9001\u6D88\u606F");
		btnSendMsg.setBounds(345, 278, 93, 27);
		frame.getContentPane().add(btnSendMsg);
		
		JScrollPane scrollPaneMsgRecord = new JScrollPane();
		scrollPaneMsgRecord.setBounds(10, 37, 307, 229);
		scrollPaneMsgRecord.setViewportBorder(new TitledBorder(UIManager
						.getBorder("TitledBorder.border"), "\u6D88\u606F\u8BB0\u5F55",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(scrollPaneMsgRecord);
		
		JTextPane textPane = new JTextPane();
		scrollPaneMsgRecord.setViewportView(textPane);
		
		JScrollPane scrollPaneOnlineUsers = new JScrollPane();
		scrollPaneOnlineUsers.setBounds(331, 39, 198, 226);
		scrollPaneOnlineUsers.setViewportBorder(new TitledBorder(null,
						"\u5728\u7EBF\u7528\u6237", TitledBorder.LEADING,
						TitledBorder.TOP, null, null));
		frame.getContentPane().add(scrollPaneOnlineUsers);
		
		JList<String> listOnlineUsers = new JList<String>((ListModel) null);
		scrollPaneOnlineUsers.setViewportView(listOnlineUsers);
	}
}
