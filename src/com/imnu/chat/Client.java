package com.imnu.chat;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;


import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


import com.imnu.chat.UserStateMessage;

import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client {
	private final int port = 8888;
	private Socket socket;
	ObjectInputStream ois;// 文件输入流
	ObjectOutputStream oos;// 文件输出流
	private String localUserName;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//时间的格式

	
	private JFrame frame;
	private JTextField textFieldUserName;
	private JPasswordField passwordFieldpwd;
	private JTextField textFieldMsgToSend;
	private JButton btnSendMsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		frame.setTitle("客户端");
		frame.setBounds(100, 100, 563, 363);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(0, 0, 65, 24);
		frame.getContentPane().add(lblNewLabel);

		JScrollPane scrollPaneMsgRecord = new JScrollPane();
		scrollPaneMsgRecord.setBounds(10, 37, 307, 229);
		scrollPaneMsgRecord.setViewportBorder(new TitledBorder(UIManager

				.getBorder("TitledBorder.border"), "\u6D88\u606F\u8BB0\u5F55",

				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(scrollPaneMsgRecord);

		JTextPane textPane = new JTextPane();
		scrollPaneMsgRecord.setViewportView(textPane);
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

					if (localUserName.length() > 0) {
						try {
							socket = new Socket("127.0.0.1", port);
							oos = new ObjectOutputStream(socket.getOutputStream());
							ois = new ObjectInputStream(socket.getInputStream());
						} catch (UnknownHostException e1) {
							JOptionPane.showMessageDialog(null, "找不到服务器主机");
							e1.printStackTrace();
							System.exit(0);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null,
									"服务器I/O错误，服务器未启动？");
							e1.printStackTrace();
							System.exit(0);
						}
						UserStateMessage userStateMessage = new UserStateMessage(
								localUserName, "", true);
						try {
							oos.writeObject(userStateMessage);
							oos.flush();//明天搜搜看
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						// 在“消息记录”文本框中用红色添加“XX时间登录成功”的信息
						String msgRecord = dateFormat.format(new Date())
								+ " 登录成功\r\n";
						addMsgRecord(msgRecord, Color.red, 12, false, false);
						btnLogin.setText("退出"); 
						btnSendMsg.setEnabled(true);// 将发送消息按钮设为可用状态
							
					}
				} else if (btnLogin.getText().equals("退出")) {
					if (JOptionPane.showConfirmDialog(null, "是否退出?", "退出确认",
							JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

						UserStateMessage userStateMessage = new UserStateMessage(localUserName, "", false);
						try {
							synchronized (oos) {
								oos.writeObject(userStateMessage);
								oos.flush();
							}
							System.exit(0);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}

						private void addMsgRecord(final String msgRecord, Color msgColor, int fontSize, boolean isItalic, boolean isUnderline) {
				
				final SimpleAttributeSet attrset = new SimpleAttributeSet();
				StyleConstants.setForeground(attrset, msgColor);
				StyleConstants.setFontSize(attrset, fontSize);
				StyleConstants.setUnderline(attrset, isUnderline);
				StyleConstants.setItalic(attrset, isItalic);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						Document docs = textPane.getDocument();
						try {
							docs.insertString(docs.getLength(), msgRecord, attrset);
						} catch (BadLocationException e) {
							e.printStackTrace();
						}
					}
				});
				
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

		

		btnSendMsg = new JButton("\u53D1\u9001\u6D88\u606F");
		btnSendMsg.setEnabled(false);
		btnSendMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msgContent = textFieldMsgToSend.getText();
				if (msgContent.length() > 0) {
					ChatMessage chatMessage = new ChatMessage(localUserName,
							"", msgContent);
					try {
						synchronized (oos) {
							oos.writeObject(chatMessage);
							oos.flush();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
					String msgRecord = dateFormat.format(new Date()) + "向大家说:"
							+ msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.blue, 12, false, false);
				}
				
			}

			private void addMsgRecord(final String msgRecord, Color msgColor, int fontSize, boolean isItalic, boolean isUnderline) {
				
				final SimpleAttributeSet attrset = new SimpleAttributeSet();
				StyleConstants.setForeground(attrset, msgColor);
				StyleConstants.setFontSize(attrset, fontSize);
				StyleConstants.setUnderline(attrset, isUnderline);
				StyleConstants.setItalic(attrset, isItalic);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						Document docs = textPane.getDocument();
						try {
							docs.insertString(docs.getLength(), msgRecord, attrset);
						} catch (BadLocationException e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnSendMsg.setBounds(345, 278, 93, 27);
		frame.getContentPane().add(btnSendMsg);

		
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
