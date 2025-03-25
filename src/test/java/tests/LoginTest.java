package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {

	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {

		String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount - 1][2];

		for (int i = 1; i < rowCount; i++) {
			data[i - 1][0] = ExcelUtils.getCellData(i, 0); // fetches Username
			data[i - 1][1] = ExcelUtils.getCellData(i, 1); // fetches Password
		}

		ExcelUtils.closeExcel();
		return data;
	}

	@DataProvider(name = "LoginData2")
	public Object[][] getData() {
		return new Object[][] { { "user1", "pass1" }, { "user2", "pass2" }, { "admin@yourstore.com", "admin" },
				{ "user3", "pass3" } };
	}

//	@Test(dataProvider = "LoginData2")
//	@Test
//	@Parameters({"username", "password"})
	@Test
	public void testValidLogin() {

		Log.info("Starting login test...");
		test = ExtentReportManager.createTest("Login Test ");

		test.info("Navigating to URL");
		LoginPage loginPage = new LoginPage(driver);

		Log.info("Adding credentials...");
		test.info("Adding credentials");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
//		loginPage.enterUsername(username);
//		loginPage.enterPassword(password);
		test.info("Clicking Login button");
		loginPage.clickLogin();

		System.out.println("Title of the page is : " + driver.getTitle());

		Log.info("Verifying page title...");
		test.info("Verifying page title");
		Assert.assertEquals(driver.getTitle(), "Just a moment...");
		test.info("Login Successful");
	}

	@Test(dataProvider = "LoginData")
	public void testInvalidLogin(String username, String password) {

		Log.info("Starting login test...");
		test = ExtentReportManager.createTest("Login Test With Invalid Credentials - " + username);

		test.info("Navigating to URL");
		LoginPage loginPage = new LoginPage(driver);

		Log.info("Adding credentials...");
		test.info("Adding credentials");
//		loginPage.enterUsername("admin@yourstore.com");
//		loginPage.enterPassword("admin");
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		test.info("Clicking Login button");
		loginPage.clickLogin();

		System.out.println("Title of the page is : " + driver.getTitle());

		Log.info("Verifying page title...");
		test.info("Verifying page title");
		Assert.assertEquals(driver.getTitle(), "Just a moment...12");
		test.info("Login Successful");
	}
}
