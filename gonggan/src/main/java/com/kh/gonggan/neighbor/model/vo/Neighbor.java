package com.kh.gonggan.neighbor.model.vo;

import java.sql.Date;

public class Neighbor {
	public String sender;
	public String receiver;
	public String check_yn;
	public String alram_yn;
	public Date accept_date;
	public Neighbor() {
		super();
	}
	public Neighbor(String sender, String receiver, String check_yn, String alram_yn, Date accept_date) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.check_yn = check_yn;
		this.alram_yn = alram_yn;
		this.accept_date = accept_date;
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
	public String getCheck_yn() {
		return check_yn;
	}
	public void setCheck_yn(String check_yn) {
		this.check_yn = check_yn;
	}
	public String getAlram_yn() {
		return alram_yn;
	}
	public void setAlram_yn(String alram_yn) {
		this.alram_yn = alram_yn;
	}
	public Date getAccept_date() {
		return accept_date;
	}
	public void setAccept_date(Date accept_date) {
		this.accept_date = accept_date;
	}
	@Override
	public String toString() {
		return "Neighbor [sender=" + sender + ", receiver=" + receiver + ", check_yn=" + check_yn + ", alram_yn="
				+ alram_yn + ", accept_date=" + accept_date + "]";
	}
	
	

	
}
