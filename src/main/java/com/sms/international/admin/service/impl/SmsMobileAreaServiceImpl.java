package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsMobileAreaMapper;
import com.sms.international.admin.service.SmsMobileAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsMobileAreaServiceImpl implements SmsMobileAreaService {
	@Autowired
	private SmsMobileAreaMapper smsMobileAreaMapper;
}
