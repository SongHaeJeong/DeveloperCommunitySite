package com.ssafy.mapper;

import java.util.List;

import com.ssafy.vo.PfComment;

public interface PfCommentMapper {
	List<PfComment> selectOne(String pfId);
	void insert(PfComment pc);
	void delete(int pfcomId);
	void update(PfComment pc);
}
