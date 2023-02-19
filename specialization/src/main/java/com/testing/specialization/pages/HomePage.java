package com.testing.specialization.pages;

import java.util.List;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HomePage {

	@Autowired
	private WebDriver webDriver;

	@Value("${app.url}")
	private String url;

	@PostConstruct
	public void initHomePage() {
		PageFactory.initElements(webDriver, this);
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	@FindBy(how = How.CLASS_NAME, using = "ud-search-form-autocomplete-input")
	private WebElement searchBar;

	@FindBy(how = How.CSS, using = "button.ud-btn.ud-btn-large.ud-btn-ghost.ud-heading-md.ud-btn-icon.ud-btn-icon-large")
	private WebElement searchButton;

	@FindBy(how = How.CSS, using = "div.ud-search-form-autocomplete > form > ul.ud-search-form-autocomplete-suggestions > li a")
	private List<WebElement> relatedSearchList;

	@FindBy(how = How.CLASS_NAME, using = "ud-search-form-autocomplete-suggestion-block-list-item")
	private List<WebElement> relatedSearchListV2;

	@FindBy(how = How.CSS, using = "div.ud-search-form-autocomplete > form > ul.ud-search-form-autocomplete-suggestions > li > a")
	private WebElement firstRelatedSearchItem;

	@FindBy(how = How.CSS, using = "div.ud-search-form-autocomplete > form > ul.ud-search-form-autocomplete-suggestions > li > a[href^='/course/spring']")
	private WebElement firstCourseRelatedSearchItem;

	@FindBy(how = How.CSS, using = "header > h1.ud-heading-xl")
	private WebElement searchResultTitle;

	@FindBy(how = How.CSS, using = "div.ud-text-sm.clp-lead > h1.ud-heading-xl")
	private WebElement courseTitle;

	@FindBy(how = How.CSS, using = "select.ud-select")
	private WebElement orderByField;

	public String getTitle() {
		String title = webDriver.getTitle();
		System.out.println("title: " + title);
		return title;
	}

	public void setTextToSearchBar(String txt) {
		System.out.println("test: " + txt);
		this.searchBar.click();
		this.searchBar.sendKeys(txt);
	}

	public void sendKey(CharSequence key) {
		this.searchBar.sendKeys(key);
	}

	public void clickSearchButton() {
		this.searchButton.click();
	}

	public boolean searchButtonIsEnabled() {
		return this.searchButton.isEnabled();
	}

	public boolean relatedSearchListExists() {
		return !this.webDriver
				.findElements(By.cssSelector(
						"div.ud-search-form-autocomplete > form > ul.ud-search-form-autocomplete-suggestions"))
				.isEmpty();
	}

	public boolean relatedSearchListExistsV2() {
		return !this.webDriver.findElements(By.className("ud-search-form-autocomplete-suggestion-block-list-item"))
				.isEmpty();
	}

	public Integer getRelatedSearchListSize() {
		Integer items = 0;
		if (relatedSearchListExists()) {
			items = this.relatedSearchList.size();
		}
		if (relatedSearchListExistsV2()) {
			items = this.relatedSearchListV2.size();
		}
		return items;
	}

	public String getFirstRelatedSearchItem() {
		String itemText = "";
		if (!this.relatedSearchList.isEmpty()) {
			itemText = this.relatedSearchList.get(0).getText();
		}
		if (!this.relatedSearchListV2.isEmpty()) {
			itemText = this.relatedSearchListV2.get(0).getText();
		}
		return itemText;
	}

	public void clickFirstRelatedSearchItem() {
		if (!this.relatedSearchList.isEmpty()) {
			this.relatedSearchList.get(0).click();
		}
		if (!this.relatedSearchListV2.isEmpty()) {
			this.relatedSearchListV2.get(0).click();
		}
	}

	public void clickFirstCourseRelatedSearchItem() {
		if (!this.webDriver.findElements(By.cssSelector(
				"div.ud-search-form-autocomplete > form > ul.ud-search-form-autocomplete-suggestions > li > a[href^='/course/spring']"))
				.isEmpty()) {
			System.out.println("paso 1");
		} else if (!this.webDriver.findElements(By.cssSelector(
				"div.ud-search-form-autocomplete > form > ul.ud-search-form-autocomplete-suggestions-with-image > li > div > a[href^='/course/spring']"))
				.isEmpty()) {
			System.out.println("paso 2");
			this.webDriver.findElements(By.cssSelector(
					"div.ud-search-form-autocomplete > form > ul.ud-search-form-autocomplete-suggestions-with-image > li > div > a[href^='/course/spring']"))
					.get(0).click();
		}
//		this.firstCourseRelatedSearchItem.click();
	}

	public String getSearchResultTitle() {
		return this.searchResultTitle.getText();
	}

	public String getCourseTitle() {
		return this.courseTitle.getText();
	}

	public String getOrderByValue() {
		return this.orderByField.getAttribute("value");
	}

	public void login() {
		System.out.println("Login");
	}

}
