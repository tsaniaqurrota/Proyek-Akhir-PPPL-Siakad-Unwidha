package steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LogoutPage;
import report.ExtentReportManager;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LogoutSteps {
    WebDriver driver;
    LogoutPage logoutPage;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
    }

    @Given("User login")
    public void user_login() {
        test = extent.createTest("User login");
        driver = Hooks.getDriver();
        driver.get("http://localhost:5173/login"); // Replace with your login page URL
        logoutPage = new LogoutPage(driver);
        test.log(Status.INFO, "Navigated to login page");

        logoutPage.enterEmail("siakadadmin@example.com"); // Replace with valid email
        logoutPage.enterPassword("password"); // Replace with valid password
        logoutPage.clickSubmit();
        test.log(Status.PASS, "Submitted valid credentials");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        test.log(Status.PASS, "Navigated to dashboard");
    }

    @When("User clicks logout")
    public void user_clicks_logout() {
        test.log(Status.INFO, "Clicking logout");
        logoutPage.clickLogout();
        test.log(Status.PASS, "Clicked logout");
    }

    @Then("User should be redirected to the login page")
    public void user_should_be_redirected_to_the_login_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        assertEquals("http://localhost:5173/login", driver.getCurrentUrl());
        test.log(Status.PASS, "Redirected to login page");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }
}
