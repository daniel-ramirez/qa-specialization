package com.testing.specialization.libraries;

import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;

@Configuration
public class WebDriverLibrary {

	@Bean
	public WebDriver getChromeDriver() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions co = new ChromeOptions();
		co.addArguments("start-maximized");
		co.addArguments("--disable-blink-features=AutomationControlled");
		co.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//		co.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-logging"));
		co.setExperimentalOption("useAutomationExtension", Boolean.FALSE);

		return new ChromeDriver(co);
	}

}
