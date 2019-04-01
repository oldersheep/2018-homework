package com.xxx.spring.demo.service.impl;

import com.xxx.spring.demo.service.IQueryService;
import com.xxx.spring.framework.annotation.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class QueryService implements IQueryService {

	/**
	 * 查询
	 */
	public String query(String name) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
		return json;
	}

}
