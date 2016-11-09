package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {

	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/lib/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}

	@When("^I navigate to (.*)$")
	public void i_navigate_to_http_www_vodafone_co_nz(String url)
			throws Throwable {
		driver.manage().window().maximize();
		driver.get(url);
	}

	@When("^I click on My Vodafone in the top right corner menu$")
	public void i_click_on_My_Vodafone_in_the_top_right_corner_menu()
			throws Throwable {
		WebElement myVodafone = waitForDisplay(By
				.xpath("//ul[@id='menu']/li[6]/a"));
		myVodafone.click();
	}

	@When("^I click Login button on the My Vodafone page$")
	public void i_click_Login_button_on_the_My_Vodafone_page() throws Throwable {
		WebElement loginButton = waitForDisplay(By
				.xpath("//a[@title='Login']"));
		loginButton.click();
	}

	@When("^I enter wrong username:(.*) and password:(.*)$")
	public void i_enter_wrong_username_test_and_password_test(String userName,
			String password) throws Throwable {
		WebElement userNameInput = waitForDisplay(By
				.xpath("//input[@id='myvfLoginOnlineId']"));
		userNameInput.sendKeys(userName);
		WebElement passwordInput = waitForDisplay(By
				.xpath("//input[@id='myvfLoginPassword']"));
		passwordInput.sendKeys(password);
	}

	@When("^I click submit button on login page$")
	public void i_click_submit_button() throws Throwable {
		WebElement submitButton = waitForDisplay(By
				.xpath("//input[@id='sign-in-button']"));
		submitButton.submit();
	}

	@Then("^I should see the error message \"(.*?)\"$")
	public void i_should_see_the_warning_message(String message)
			throws Throwable {
		WebElement errorLabel = waitForDisplay(By.cssSelector(".submitError"));
		String errorMessage = errorLabel.getText();
		Assert.assertEquals(message, errorMessage);
	}

	@After
	public void tearDown() throws Exception {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

	private WebElement waitForDisplay(By by) {
		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(by));
		Assert.assertNotNull(element);
		return element;
	}
}
