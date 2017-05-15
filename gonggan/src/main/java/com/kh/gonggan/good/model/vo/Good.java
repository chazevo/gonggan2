package com.kh.gonggan.good.model.vo;

public class Good {
	private int POST_ID;
	private String MEMBER_ID;
	private String GOOD_CHECK;
	
	public Good(){}

	public Good(int pOST_ID, String mEMBER_ID, String gOOD_CHECK) {
		super();
		POST_ID = pOST_ID;
		MEMBER_ID = mEMBER_ID;
		GOOD_CHECK = gOOD_CHECK;
	}

	public Good(int pOST_ID, String mEMBER_ID) {
		super();
		POST_ID = pOST_ID;
		MEMBER_ID = mEMBER_ID;
	}

	public int getPOST_ID() {
		return POST_ID;
	}

	public void setPOST_ID(int pOST_ID) {
		POST_ID = pOST_ID;
	}

	public String getMEMBER_ID() {
		return MEMBER_ID;
	}

	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}

	public String getGOOD_CHECK() {
		return GOOD_CHECK;
	}

	public void setGOOD_CHECK(String gOOD_CHECK) {
		GOOD_CHECK = gOOD_CHECK;
	}

	@Override
	public String toString() {
		return "Good [POST_ID=" + POST_ID + ", MEMBER_ID=" + MEMBER_ID + ", GOOD_CHECK=" + GOOD_CHECK + "]";
	}
	
}
