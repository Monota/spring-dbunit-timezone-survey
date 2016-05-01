package com.example.monota.spring.service;

import com.example.monota.spring.SpringSampleApplication;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.FileOutputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringSampleApplication.class)
@ActiveProfiles("mysql")
public class SampleServiceMySqlTest {

	@Autowired
	private SampleService sampleService;

	@Autowired
	private DataSource dataSource;

	@Test
	public void testExcel001() throws Exception {
		// Using Excel: Cell format type is Date (Number).
		IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
		databaseTester.setDataSet(new XlsDataSet(ResourceUtils.getFile("classpath:test001.xlsx")));
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		sampleService.execute();
		IDataSet resultDataSet = new DatabaseConnection(dataSource.getConnection()).createDataSet();
		XlsDataSet.write(resultDataSet, new FileOutputStream("target/test001-mysql-result.xls"));
	}

	@Test
	public void testExcel002() throws Exception {
		// Using Excel: Cell format type is String.
		IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
		databaseTester.setDataSet(new XlsDataSet(ResourceUtils.getFile("classpath:test002.xlsx")));
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		sampleService.execute();
		IDataSet resultDataSet = new DatabaseConnection(dataSource.getConnection()).createDataSet();
		XlsDataSet.write(resultDataSet, new FileOutputStream("target/test002-mysql-result.xls"));
	}

	@Test
	public void testExcel003() throws Exception {
		// Using Excel: Cell format type is String with timezone.
		IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
		databaseTester.setDataSet(new XlsDataSet(ResourceUtils.getFile("classpath:test003.xlsx")));
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		sampleService.execute();
		IDataSet resultDataSet = new DatabaseConnection(dataSource.getConnection()).createDataSet();
		XlsDataSet.write(resultDataSet, new FileOutputStream("target/test003-mysql-result.xls"));
	}

	@Test
	public void testXml001() throws Exception {
		// Using XML
		IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
		databaseTester.setDataSet(new FlatXmlDataSetBuilder().build(ResourceUtils.getFile("classpath:test001.xml")));
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		sampleService.execute();
		IDataSet resultDataSet = new DatabaseConnection(dataSource.getConnection()).createDataSet();
		FlatXmlDataSet.write(resultDataSet, new FileOutputStream("target/test001-mysql-result.xml"));
	}
}
