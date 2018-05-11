package chat;

import java.io.Serializable;



public class Message implements Serializable {
	private String srcUser;//发送者
	private String dstUser;//接收者
	
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

