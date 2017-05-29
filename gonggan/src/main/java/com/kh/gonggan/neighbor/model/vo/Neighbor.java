package com.kh.gonggan.neighbor.model.vo;

public class Neighbor {
	public String sender;
	public String receiver;
	public String check_yn;
	public String alram_yn;
	public Neighbor(){}
	public Neighbor(String sender, String receiver, String check_yn, String alram_yn) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.check_yn = check_yn;
		this.alram_yn = alram_yn;
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
	@Override
	public String toString() {
		return "Neighbor [sender=" + sender + ", receiver=" + receiver + ", check_yn=" + check_yn + ", alram_yn="
				+ alram_yn + "]";
	}

	
}
