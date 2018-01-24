package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.CaiwuAccPassMapper;
import com.sms.international.admin.service.CaiwuAccPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CaiwuAccPassService")
@Transactional
public class CaiwuAccPassServiceImpl implements CaiwuAccPassService {

	@Autowired
	private CaiwuAccPassMapper caiwuMapper;
	
	@Override
	public String findPass() {
		return caiwuMapper.findPass();
	}

	@Override
	public int update(String pass) {
		return caiwuMapper.update(pass);
	}

}
