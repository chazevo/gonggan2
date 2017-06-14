
package com.kh.gonggan.alarm.model.vo;

public class Alarm {

	private int alarm_num;
	private String type_cg;
	private String writer_id;
	private int post_id;
	
	public int getPost_id() {
		return post_id;
	}



	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}



	public Alarm(int alarm_num, String type_cg, String writer_id, int post_id) {
		super();
		this.alarm_num = alarm_num;
		this.type_cg = type_cg;
		this.writer_id = writer_id;
		this.post_id = post_id;
	}



	public Alarm(){}
	
	
	
	public Alarm(int alarm_num, String type_cg, String writer_id) {
		super();
		this.alarm_num = alarm_num;
		this.type_cg = type_cg;
		this.writer_id = writer_id;
	}



	public int getAlarm_num() {
		return alarm_num;
	}
	public void setAlarm_num(int alarm_num) {
		this.alarm_num = alarm_num;
	}
	public String getType_cg() {
		return type_cg;
	}
	public void setType_cg(String type_cg) {
		this.type_cg = type_cg;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	
	
}
