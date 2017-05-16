package com.kh.gonggan.message.model.vo;

import java.sql.Date;

public class Message {
	private int msg_id;
	private String sender;
	private String receiver;
	private String msg_text;
	private Date msg_date;
	private String check_yn;

	public Message(){}

	public Message(int msg_id, String sender, String receiver, String msg_text, Date msg_date, String check_yn) {
		super();
		this.msg_id = msg_id;
		this.sender = sender;
		this.receiver = receiver;
		this.msg_text = msg_text;
		this.msg_date = msg_date;
		this.check_yn = check_yn;
	}

	public int getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMsg_text() {
		return msg_text;
	}

	public void setMsg_text(String msg_text) {
		this.msg_text = msg_text;
	}

	public Date getMsg_date() {
		return msg_date;
	}

	public void setMsg_date(Date msg_date) {
		this.msg_date = msg_date;
	}

	public String getCheck_yn() {
		return check_yn;
	}

	public void setCheck_yn(String check_yn) {
		this.check_yn = check_yn;
	}

	@Override
	public String toString() {
		return "Message [msg_id=" + msg_id + ", sender=" + sender + ", receiver=" + receiver + ", msg_text=" + msg_text
				+ ", msg_date=" + msg_date + ", check_yn=" + check_yn + "]";
	}
	
	

}
