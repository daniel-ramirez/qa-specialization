package com.testing.specialization;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.testing.specialization.pages.HomePage;

@SpringBootTest
class HomePageSearchBarTests {

	@Value("${app.url}")
	private String url;

	@Value("testing")
	private String environment;

	@Value("chrome,firefox,edge")
	private List<String> browsers;

	@Autowired
	private HomePage homePage;

	@BeforeEach
	public void beforeEach() {
		homePage.getWebDriver().navigate().to(url);
	}

	@AfterEach
	public void afterEach() {
		homePage.getWebDriver().close();
	}

	@Test
	void searchBarTestCP001() {
		homePage.setTextToSearchBar("html5");
		homePage.clickSearchButton();
		TestUtil.delay(5);

		assertThat(homePage.getSearchResultTitle()).contains("html5");
		assertThat(homePage.getOrderByValue()).contains("relevance");
	}

	@Test
	void searchBarTestCP002() {
		assertThat(homePage.searchButtonIsEnabled()).isFalse();
	}

	@Test
	void searchBarTestCP003() {
		homePage.setTextToSearchBar("a");
		TestUtil.delay(5);

		assertThat(homePage.relatedSearchListExists()).isFalse();
	}

	@Test
	void searchBarTestCP004() {
		homePage.setTextToSearchBar("aa");
		TestUtil.delay(5);

		assertThat(homePage.getRelatedSearchListSize()).isEqualTo(12);
	}

	@Test
	void searchBarTestCP005() {
		homePage.setTextToSearchBar("Antonio García Villarán");
		TestUtil.delay(5);

		assertThat(homePage.getFirstRelatedSearchItem()).isEqualToIgnoringCase("Antonio García Villarán");
	}

	@Test
	void searchBarTestCP006() {
		homePage.setTextToSearchBar("html5");
		TestUtil.delay(5);

		assertThat(homePage.getRelatedSearchListSize()).isEqualTo(12);

		homePage.clickFirstRelatedSearchItem();
		TestUtil.delay(5);

		assertThat(homePage.getSearchResultTitle()).contains("html5");
		assertThat(homePage.getOrderByValue()).contains("relevance");
	}

	@Test
	void searchBarTestCP007() {
		homePage.setTextToSearchBar("html5");
		TestUtil.delay(5);

		assertThat(homePage.getRelatedSearchListSize()).isEqualTo(12);

		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ENTER);
		TestUtil.delay(5);

		assertThat(homePage.getSearchResultTitle()).contains("html5");
		assertThat(homePage.getOrderByValue()).contains("relevance");
	}

	@Test
	void searchBarTestCP008() {
		homePage.setTextToSearchBar("https://www.udemy.com/");
		TestUtil.delay(5);

		assertThat(homePage.relatedSearchListExists()).isFalse();
	}

	@Test
	void searchBarTestCP009() {
		homePage.setTextToSearchBar("Spring Boot");
		TestUtil.delay(5);
		
		homePage.clickFirstCourseRelatedSearchItem();
		TestUtil.delay(5);

		assertThat(homePage.getCourseTitle()).contains("Spring Boot");
	}

	@Test
	void searchBarTestCP010() {
		homePage.setTextToSearchBar("Spring Boot");
		TestUtil.delay(5);

		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ENTER);
		TestUtil.delay(5);

		assertThat(homePage.getCourseTitle()).contains("Spring Boot");
	}

	@Test
	void searchBarTestCP011() {
		homePage.login();
		homePage.setTextToSearchBar("Spring Boot");
		TestUtil.delay(5);
		
		homePage.clickFirstCourseRelatedSearchItem();
		TestUtil.delay(5);

		assertThat(homePage.getCourseTitle()).contains("Spring Boot");
	}

	@Test
	void searchBarTestCP012() {
		homePage.login();
		homePage.setTextToSearchBar("Spring Boot");
		TestUtil.delay(5);

		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ARROW_DOWN);
		homePage.sendKey(Keys.ENTER);
		TestUtil.delay(5);

		assertThat(homePage.getCourseTitle()).contains("Spring Boot");
	}

}
