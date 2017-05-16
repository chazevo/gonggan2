package com.kh.gonggan.member.model.vo;

import java.sql.Date;

public class Member {
	private String member_id;
	private String member_pw;
	private String member_name;
	private String email;
	private String profile;
	private String member_gender;
	private Date member_birth;
	
	public Member() {
		super();
	}

	public Member(String member_id, String member_pw, String member_name, String email, String profile,
			String member_gender, Date member_birth) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.email = email;
		this.profile = profile;
		this.member_gender = member_gender;
		this.member_birth = member_birth;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getMember_gender() {
		return member_gender;
	}

	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}

	public Date getMember_birth() {
		return member_birth;
	}

	public void setMember_birth(Date member_birth) {
		this.member_birth = member_birth;
	}

	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", member_pw=" + member_pw + ", member_name=" + member_name
				+ ", email=" + email + ", profile=" + profile + ", member_gender=" + member_gender + ", member_birth="
				+ member_birth + "]";
	}
}
