package com.example.monota.spring.service.impl;

import com.example.monota.spring.entity.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.monota.spring.dao.SampleDao;
import com.example.monota.spring.service.SampleService;

import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleDao dao;

	@Override
	public List<Sample> execute() {
		List<Sample> samples = dao.selectAll();
		samples.forEach(System.out::println);
		return samples;
	}
}
