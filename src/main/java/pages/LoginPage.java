package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jdk.internal.org.jline.utils.Log;

public class LoginPage {
	
	private WebDriver driver;
	
	@FindBy(id="Email")
	WebElement usernameTextBox;
	
	@FindBy(id="Password")
	WebElement passwordTextBox;
	
	@FindBy(xpath="//*[@id=\\\"main\\\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button")
	WebElement loginButton;
	
//	private By usernameTextBox = By.id("Email");
//	private By passwordTextBox = By.id("Password");
//	private By loginButton = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username) {
		usernameTextBox.clear();
		usernameTextBox.sendKeys(username);
		
//		driver.findElement(usernameTextBox).clear();
//		driver.findElement(usernameTextBox).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		passwordTextBox.clear();
		passwordTextBox.sendKeys(password);
		
//		driver.findElement(passwordTextBox).clear();
//		driver.findElement(passwordTextBox).sendKeys(password);
	}
	
	public void clickLogin() {
		Log.info("Clicking login button...");
		loginButton.click();
		
//		Log.info("Clicking login button...");
//		driver.findElement(loginButton).click();
	}
}
