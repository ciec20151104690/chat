package com.imnu.chat;

import java.awt.EventQueue;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
public class Server extends JFrame {
	private ServerSocket serverSocket;
	private final int port = 9999;
	final DefaultTableModel onlineUsersDtm = new DefaultTableModel();
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private JFrame JFrame;
	private JTable tableOnlineUsers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return super.toString();
			}

			public void run() {
				try {
					Server window = new Server();
					window.JFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame = new JFrame();
		JFrame.setTitle("服务端");
		JFrame.setBounds(100, 100, 564, 400);
		JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame.getContentPane().setLayout(null);

		JScrollPane scrollPaneMsgRecord = new JScrollPane();
		scrollPaneMsgRecord.setBounds(0, 0, 271, 305);
		scrollPaneMsgRecord.setViewportBorder(new TitledBorder(null,

				"\u6D88\u606F\u8BB0\u5F55", TitledBorder.LEADING,

				TitledBorder.TOP, null, null));
		scrollPaneMsgRecord.setPreferredSize(new Dimension(100, 300));
		JFrame.getContentPane().add(scrollPaneMsgRecord);

		JTextPane textPaneMsgRecord = new JTextPane();
		textPaneMsgRecord.setPreferredSize(new Dimension(100, 100));
		scrollPaneMsgRecord.setViewportView(textPaneMsgRecord);

		JButton btnStart = new JButton("\u542F\u52A8");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					serverSocket = new ServerSocket(port);
					String msgRecord = dateFormat.format(new Date()) + " 服务器启动成功" + "\r\n";
					addMsgRecord(msgRecord, Color.red, 12, false, false);
					new Thread() {
						@Override
						public void run() {
							while (true) {
								try {
									Socket socket = serverSocket.accept();

								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						};
					}.start();
					btnStart.setEnabled(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			

			private void addMsgRecord(final String msgRecord, Color msgColor, int fontSize, boolean isItalic,
					boolean isUnderline) {
				final SimpleAttributeSet attrset = new SimpleAttributeSet();
				StyleConstants.setForeground(attrset, msgColor);
				StyleConstants.setFontSize(attrset, fontSize);
				StyleConstants.setUnderline(attrset, isUnderline);
				StyleConstants.setItalic(attrset, isItalic);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						Document docs = textPaneMsgRecord.getDocument();
						try {
							docs.insertString(docs.getLength(), msgRecord, attrset);
						} catch (BadLocationException e) {
							e.printStackTrace();
						}
					}
				});
			}

		});
		btnStart.setBounds(235, 313, 63, 27);
		JFrame.getContentPane().add(btnStart);

		JScrollPane scrollPaneOnlineUsers = new JScrollPane();
		scrollPaneOnlineUsers.setPreferredSize(new Dimension(100, 300));
		scrollPaneOnlineUsers.setBounds(285, 0, 261, 305);
		JFrame.getContentPane().add(scrollPaneOnlineUsers);

		onlineUsersDtm.addColumn("用户名");
		onlineUsersDtm.addColumn("IP");
		onlineUsersDtm.addColumn("端口");
		onlineUsersDtm.addColumn("登录时间");
		tableOnlineUsers = new JTable(onlineUsersDtm);
		tableOnlineUsers.setPreferredSize(new Dimension(100, 270));
		tableOnlineUsers.setFillsViewportHeight(true); // 让JTable充满它的容器
		scrollPaneOnlineUsers.setViewportView(tableOnlineUsers);
	}
}
