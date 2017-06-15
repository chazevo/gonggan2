package com.kh.gonggan.free.model.service;

import java.util.List;

import com.kh.gonggan.free.model.vo.Free;

public interface FreeService {

	List<Free> selectAll(int rownum, int rownum2);
	List<Free> selectAll_index2();
	List<Free> selectAll_myhome(String writer_id);
	Free freeDetail(int postId);
}
