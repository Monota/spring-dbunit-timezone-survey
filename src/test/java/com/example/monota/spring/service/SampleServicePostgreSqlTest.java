package com.example.monota.spring.service;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import com.example.monota.spring.SpringSampleApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringSampleApplication.class)
public class SampleServicePostgreSqlTest {

	@Autowired
	private SampleService sampleService;

	@Autowired
	private DataSource dataSource;

	@Test
	public void test001() throws Exception {
		// Cell format type is Date (Number).
		IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
		databaseTester.setDataSet(new XlsDataSet(ResourceUtils.getFile("classpath:test001.xlsx")));
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		sampleService.execute();
	}

	@Test
	public void test002() throws Exception {
		// Cell format type is String.
		IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
		databaseTester.setDataSet(new XlsDataSet(ResourceUtils.getFile("classpath:test002.xlsx")));
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		sampleService.execute();
	}

	@Test
	public void test003() throws Exception {
		// Cell format type is String with timezone.
		IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
		databaseTester.setDataSet(new XlsDataSet(ResourceUtils.getFile("classpath:test003.xlsx")));
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		sampleService.execute();
	}
}
