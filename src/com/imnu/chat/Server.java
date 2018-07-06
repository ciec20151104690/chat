package com.imnu.chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JButton;

public class Server extends JFrame {

	private JFrame JFrame;
	private JTable tableOnlineUsers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		btnStart.setBounds(235, 313, 63, 27);
		JFrame.getContentPane().add(btnStart);
		
		JScrollPane scrollPaneOnlineUsers = new JScrollPane();
		scrollPaneOnlineUsers.setPreferredSize(new Dimension(100, 300));
		scrollPaneOnlineUsers.setBounds(285, 0, 261, 305);
		JFrame.getContentPane().add(scrollPaneOnlineUsers);
		
		tableOnlineUsers = new JTable((TableModel) null);
		tableOnlineUsers.setPreferredSize(new Dimension(100, 270));
		tableOnlineUsers.setFillsViewportHeight(true);
		scrollPaneOnlineUsers.setViewportView(tableOnlineUsers);
	}
}
