package com.qa.pages;

import java.io.IOException;

import javax.mail.MessagingException;

import org.openqa.selenium.By;

import com.qa.util.Base;
import com.qa.util.Email;



public class LoginPage extends Base {

	public LoginPage()  {
		super();
		// TODO Auto-generated constructor stub
	}

	private By signIn = By.xpath("//a[contains(text(),'Sign in')]");
	
	private By username = By.xpath("//input[@name='login']");
	
	private By password = By.xpath("//input[@name='password']");
	
	private By submit = By.xpath("//input[@type='submit']");
	
	private static Email emailUtils;
	
	public HomePage login(String un, String pwd) throws Exception {
		Base.driver.findElement(signIn).click();
		Base.driver.findElement(username).sendKeys(un);
		Base.driver.findElement(password).sendKeys(pwd);
		 
		try {
			Base.driver.findElement(submit).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Base.driver.findElements(By.xpath("//input[@name='otp']")).size()!=0) {
		 emailUtils = new Email("testuserpooja5@gmail.com", "Testuserpooja@5", "smtp.gmail.com", Email.EmailFolder.INBOX);
		    String verificationCode = emailUtils.getAuthorizationCode();
		    System.out.println(verificationCode);
		    Thread.sleep(8000);
		    Base.driver.findElement(By.xpath("//input[@name='otp']")).sendKeys(verificationCode);
		    Thread.sleep(8000);
		    Base.driver.findElement(By.xpath("//button[text()='Verify']")).click();
		}
		    return new HomePage();
		
	}
	
	
	
}
