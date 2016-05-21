package com.example.monota.spring.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import com.example.monota.spring.SpringSampleApplication;
import com.example.monota.spring.entity.Sample;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringSampleApplication.class)
public class DbUnitDateTest {

	@Autowired
	private SampleService service;

	@Autowired
	private DataSource dataSource;

	private TimeZone defaultTimeZone;

	@Before
	public void changeTimezoneToJapan() {
		this.defaultTimeZone = TimeZone.getDefault();
		// If you change the timezone to "UTC", the test will success.
		// TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		// Temporarily I change the default timezone to "Aisa/Tokyo" to get same test result all over the world.
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
	}

	@After
	public void restoreTimezoneToDefault() {
		TimeZone.setDefault(this.defaultTimeZone);
	}

	@Test
	public void useXlsNumberCellForTimestamp() throws Exception {
		// This test will fail.
		this.testExec("classpath:test001.xlsx");
	}

	@Test
	public void useXlsStringCellForTimestamp() throws Exception {
		// This test will success.
		this.testExec("classpath:test002.xlsx");
	}

	private void testExec(String excelPath) throws Exception {
		// Setup test data
		IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
		databaseTester.setDataSet(new XlsDataSet(ResourceUtils.getFile(excelPath)));
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		// Execute logic
		List<Sample> samples = service.execute();
		// Assert results
		assertThat(samples.size(), is(4));
		// Here I assert first record only.
		assertThat(samples.get(0).getId(), is(1));
		assertThat(samples.get(0).getName(), is("Monota"));
		assertThat(samples.get(0).getMail(), is("test1@example.com"));
		// Expected date
		Instant expectedDateTime = ZonedDateTime.of(2016, 1, 9, 18, 59, 21, 0, ZoneId.systemDefault()).toInstant();
		assertThat(samples.get(0).getCreatedAt(), is(Date.from(expectedDateTime)));
		assertThat(samples.get(0).getCreatedAtTimezone(), is(Date.from(expectedDateTime)));
	}
}
