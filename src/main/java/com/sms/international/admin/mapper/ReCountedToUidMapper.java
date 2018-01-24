package com.sms.international.admin.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReCountedToUidMapper {
	List<Integer> findAll();
	int insert(List<Integer> uids);

}