package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {
	
	private WebDriver driver = null;
	
	@When("^I navigate to (.*)$")
	public void i_navigate_to_http_www_vodafone_co_nz(String url) throws Throwable {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/lib/chromedriver.exe");
	    driver = new ChromeDriver();
        driver.manage().window().maximize();  
        driver.get(url);  
	}

	@When("^I click on My Vodafone in the top right corner menu$")
	public void i_click_on_My_Vodafone_in_the_top_right_corner_menu() throws Throwable {
		driver.findElement(By.xpath("//ul[@id='menu']/li[6]/a")).click();
	}

	@When("^I click Login button on the My Vodafone page$")
	public void i_click_Login_button_on_the_My_Vodafone_page() throws Throwable {
		driver.findElement(By.xpath("//a[@title='Login']")).click();
	}

	@When("^I enter wrong username:(.*) and password:(.*)$")
	public void i_enter_wrong_username_test_and_password_test(String userName, String password) throws Throwable {
		driver.findElement(By.xpath("//input[@id='myvfLoginOnlineId']")).sendKeys(userName);
    	driver.findElement(By.xpath("//input[@id='myvfLoginPassword']")).sendKeys(password);
	}

	@When("^I click submit button$")
	public void i_click_submit_button() throws Throwable {
		driver.findElement(By.xpath("//input[@id='sign-in-button']")).submit();
	}

	@Then("^I should see the warning message \"(.*?)\"$")
	public void i_should_see_the_warning_message(String message) throws Throwable {
		String text = driver.findElement(By.cssSelector(".submitError")).getText();
		Assert.assertEquals(message, text);
	}
	
	@After
    public void tearDown() throws Exception {
		if (driver != null) {
			driver.close();  
	    	driver.quit();
		}
    }
}
