package com.codoing.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.sun.javafx.PlatformUtil;

public class Config {

	public static WebDriver driver;
	private static String baseURL;
	private static String CHROME = "Chrome";
	static String currentPath = System.getProperty("user.dir");
	private static String browserType;

	@BeforeTest
	public static void browserSetUP() throws IOException {

		browserType = getProperty("Browser");
		driver = getDriver(browserType);
	}

	private static String setDriverPath() {
		String executable = null;
		if (PlatformUtil.isMac()) {

			executable = "chromedriver";
			return executable;

		}
		if (PlatformUtil.isWindows()) {
			executable = currentPath + File.separator + "chromedriver.exe";
			return executable;

		}
		if (PlatformUtil.isLinux()) {
			executable = "chromedriver_linux";
			return executable;

		}
		return executable;
	}

	public static WebDriver getDriver(String browserType) throws IOException {

		if (CHROME.equals(browserType)) {
			System.setProperty("webdriver.chrome.driver", setDriverPath());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("start-maximized");
			return new ChromeDriver(options);
		}

		else {
			throw new RuntimeException(String.format("Unknown browser type: \"%s\"", browserType));
		}

	}

	private static String getProperty(String Key) throws IOException {

		String filePath = currentPath + File.separator + "config.properties";
		String Value = null;
		Properties prop = new Properties();
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		Value = prop.getProperty(Key);
		return Value;
	}

	public static String getBaseURL() {
		try {
			baseURL = getProperty("URL");
		} catch (IOException e) {

		}
		return baseURL;
	}

	public static void setBaseURL(String baseURL) {
		Config.baseURL = baseURL;
	}

}
