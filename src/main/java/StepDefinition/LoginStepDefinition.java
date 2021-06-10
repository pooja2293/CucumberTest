package StepDefinition;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.Base;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginStepDefinition extends Base{


LoginPage pg  = new LoginPage();
HomePage one = new HomePage();	
	
	@Given("^user is already on github page$")
	public void user_already_on_loginpage() {
		Base.initialization();
		
	}
	
	@When("^user clicks on sign in button$")
	public void abc() throws Exception {
		pg.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Then("^user get sign in$")
	public void abcd() throws InterruptedException {
		one.createRepo();
	}
	
	@Then("^user creates repo by name$")
	public void abcd(DataTable credentials) {
		credentials.raw();
		List<List<String>> data = credentials.raw();
		Base.driver.findElement(one.name).sendKeys(data.get(0).get(0));
		
	}
	
	@Then("^user created private repo$")
	public void bca() throws InterruptedException {
		one.makePrivate();
	}
	
	@Then("^user successfully created repo$")
	public void akjh() throws InterruptedException {
		one.repoCreated();
	}
	
	@Given("^user is on page$")
	public void ajnb() {
		Base.initialization();
	}
	
	@Then("^user sign in$")
	public void iii() throws Exception {
		pg.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Then("^user checkout for setting$")
	public void frt() throws InterruptedException {
		one.settings();
	}
	
	@Then("^user open the repo created$")
	public void openRepo() {
		one.clickRepo();
	}
	
	@Then("^user goes into the settings$")
	public void settingOption() throws InterruptedException {
		one.settings();
	}
	
	@Then("^deletes the repo$")
	public void deleteRepo() throws InterruptedException {
		one.delete();
		
	}
}
