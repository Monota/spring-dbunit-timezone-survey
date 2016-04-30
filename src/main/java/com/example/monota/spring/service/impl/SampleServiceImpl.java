package com.example.monota.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.monota.spring.dao.SampleDao;
import com.example.monota.spring.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleDao dao;

	@Override
	public void execute() {
		dao.selectAll().forEach(System.out::println);
	}
}
