package com.codoing.utility;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions {

	static String currentPath = System.getProperty("user.dir");

	// Read Values from Excel File
	public String getElementFromExcel(String Key) throws Throwable {
		String filePath = currentPath + File.separator + "Locators.xlsx";
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sht = wb.getSheet("Data");
		int LastRowExcel = sht.getLastRowNum();
		String LocatorValue = null;
		for (int i = 1; i <= LastRowExcel; i++) {
			if (sht.getRow(i).getCell(0).getStringCellValue().equals(Key)) {
				LocatorValue = sht.getRow(i).getCell(2).getStringCellValue();
				break;
			}
		}
		return LocatorValue;
	}

	public void locateElementToClick(WebDriver driver, By by) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();

	}

	public void sendTextInTextBox(WebDriver driver, By by, String value) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);

	}

	public void enterValueIntoAutoSuggestTextBox(WebDriver driver, By autoSuggestDropdown, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(autoSuggestDropdown));
		List<WebElement> originOptions = driver.findElements(autoSuggestDropdown);
		for (WebElement option : originOptions) {
			if (option.getText().contains(value)) {
				option.click();
			}
		}
	}

	public void selectDateFromDatepicker(WebDriver driver, By by, String futureDate) {

		LocalDate dateToday = LocalDate.parse(LocalDate.now().toString());
		LocalDate dateAfter = LocalDate.parse(futureDate);
		long numberOfDays = ChronoUnit.DAYS.between(dateToday, dateAfter);

		DateFormat dateFormat = new SimpleDateFormat(" EEE, dd MMMMM, yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, (int) numberOfDays);
		String newDate = dateFormat.format(cal.getTime());
		System.out.println("the date today is " + newDate);
		// cal.add(Calendar.DAY_OF_YEAR, (int)numberOfDays);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		driver.findElement(by).sendKeys(cal.getTime() + "" + Keys.ENTER);

	}

	public boolean isElementPresent(WebDriver driver, By by) {

		if (driver.findElement(by) != null) {
			return true;
		}
		return false;

	}

}
