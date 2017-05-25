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
	private String member_phone;
	private String member_naver_id;
	private String member_kakao_id;
	private String member_facebook_id;

	public Member() {
		super();
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

	public String getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}

	public String getMember_naver_id() {
		return member_naver_id;
	}

	public void setMember_naver_id(String member_naver_id) {
		this.member_naver_id = member_naver_id;
	}

	public String getMember_kakao_id() {
		return member_kakao_id;
	}

	public void setMember_kakao_id(String member_kakao_id) {
		this.member_kakao_id = member_kakao_id;
	}

	public String getMember_facebook_id() {
		return member_facebook_id;
	}

	public void setMember_facebook_id(String member_facebook_id) {
		this.member_facebook_id = member_facebook_id;
	}

	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", member_pw=" + member_pw + ", member_name=" + member_name
				+ ", email=" + email + ", profile=" + profile + ", member_gender=" + member_gender + ", member_birth="
				+ member_birth + ", member_phone=" + member_phone + ", member_naver_id=" + member_naver_id
				+ ", member_kakao_id=" + member_kakao_id + ", member_facebook_id=" + member_facebook_id + "]";
	}

}