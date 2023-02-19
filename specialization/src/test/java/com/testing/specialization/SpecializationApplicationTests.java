package com.testing.specialization;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.testing.specialization.pages.HomePage;

@SpringBootTest
class SpecializationApplicationTests {

	@Autowired
	private WebDriver webDriver;
	
	@Value("${app.url}")
	private String url;
	
	@Value("${app.environment}")
	private String environment;
	
	@Value("${app.browsers}")
	private List<String> browsers;

	@Autowired
	private HomePage homePage;

	@BeforeEach
	public void beforeEach() {
		webDriver.navigate().to(url);
		TestUtil.delay(5);
	}

	@AfterEach
	public void afterEach() {

		TestUtil.delay(2);
		webDriver.quit();
	}

	@Test
	void contextLoads() {
		webDriver.navigate().to(url);
		assertThat(homePage.getTitle()).contains("Udemy");
	}

}
