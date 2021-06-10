package com.qa.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.qa.util.Base;

public class HomePage extends Base {

	
	public static By  repo         = By.xpath("(//a[text()='Create repository'])[1]");
	public static By  name 	       = By.xpath("//input[@id='repository_name']");
	public static By  repoPrivate  = By.xpath("//input[@value='private']");
	public static By  created      = By.xpath("(//button[@type='submit'])[2]");
	public static By  setting      = By.xpath("//span[@data-content='Settings']");
	public static By  testRepo     = By.xpath("(//a[@href='/Test-spec-cmd/Demo'])[1]");
	public static By  deleteRepo   = By.xpath("//summary[contains(text(),'Delete this repository')]");
	public static By  verify       = By.xpath("(//input[@name='verify'])[3]");
	public static By  confirm      = By.xpath("//span[text()='I understand the consequences, delete this repository']");
	
	
	public HomePage()  {
		super();
		
	}
	
	public void createRepo() throws InterruptedException {
		Thread.sleep(8000);
		Base.driver.findElement(repo).click();
		
	}
	
	public void makePrivate() throws InterruptedException {
		Thread.sleep(5000);
		Base.driver.findElement(repoPrivate).click();
	}
	
	public void repoCreated() throws InterruptedException {
		Thread.sleep(5000);
		Base.driver.findElement(created).click();
	}
	
	public void settings() throws InterruptedException {
		Thread.sleep(5000);
		Base.driver.findElement(setting).click();
	}
	
	public void clickRepo() {
		Base.driver.findElement(testRepo).click();
		
	}
	
	public void delete() throws InterruptedException {
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor)Base.driver;
		js.executeScript("window.scrollBy(0, 1000)");
		Thread.sleep(4000);
		Base.driver.findElement(deleteRepo).click();
		Thread.sleep(4000);
		Base.driver.switchTo().activeElement();
		Base.driver.findElement(verify).sendKeys("Test-spec-cmd/Demo");
		Thread.sleep(3000);
		Base.driver.findElement(confirm).click();
	}
	
}
