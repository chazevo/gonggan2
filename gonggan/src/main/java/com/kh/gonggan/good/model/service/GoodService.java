package com.kh.gonggan.good.model.service;

import com.kh.gonggan.good.model.vo.Good;

public interface GoodService {

	int goodInsert(int postId, String memberId);

	Good goodCount(int postId);

	int goodDelete(int postId, String memberId);

	Object goodCheck(int postId, String memberId);

}
