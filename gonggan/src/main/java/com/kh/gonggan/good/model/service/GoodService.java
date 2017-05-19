package com.kh.gonggan.good.model.service;

import java.util.List;

import com.kh.gonggan.good.model.vo.Good;

public interface GoodService {

	int goodInsert(int postId, String memberId);

	int goodCount(int postId);

	int goodDelete(int postId, String memberId);

	Object goodCheck(int postId, String memberId);

	List<Good> goodList(int postId);

	List<Good> goodSearch(String member_id, int post_id);

}
