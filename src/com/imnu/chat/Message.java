package com.imnu.chat;

import java.io.Serializable;





public class Message implements Serializable {
	private String srcUser;//∑¢ÀÕ’ﬂ
	private String dstUser;//
	
	public Message(String srcUser, String dstUser) {
		super();
		this.srcUser = srcUser;
		this.dstUser = dstUser;
	}
	
	public String getSrcUser() {
		return srcUser;
	}
	public void setSrcUser(String srcUser) {
		this.srcUser = srcUser;
	}
	public String getDstUser() {
		return dstUser;
	}
	public void setDstUser(String dstUser) {
		this.dstUser = dstUser;
	}
	
}

class ChatMessage extends Message {
	private String msgContent;

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public ChatMessage(String srcUser, String dstUser, String msgContent) {
		super(srcUser, dstUser);
		this.msgContent = msgContent;
	}
	
	public boolean isPubChatMessage() {
		return getDstUser().equals("");
	}
}

class UserStateMessage extends Message {
	private boolean userOnline;
	
	public UserStateMessage(String srcUser, String dstUser) {
		super(srcUser, dstUser);
		// TODO Auto-generated constructor stub
		
		
		
	}

	public boolean isUserOnline() {
		return userOnline;
	}

	public void setUserOnline(boolean userOnline) {
		this.userOnline = userOnline;
	}

	public UserStateMessage(String srcUser, String dstUser, boolean userOnline) {
		super(srcUser, dstUser);
		this.userOnline = userOnline;
	}
	
}
