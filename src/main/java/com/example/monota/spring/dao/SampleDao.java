package com.example.monota.spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.monota.spring.entity.Sample;

public interface SampleDao {

	@Select("SELECT * FROM sample")
	List<Sample> selectAll();
}
