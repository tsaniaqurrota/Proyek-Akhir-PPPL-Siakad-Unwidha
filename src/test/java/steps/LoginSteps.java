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
import pages.LoginPage;
import report.ExtentReportManager;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
    }

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        test = extent.createTest("User is on the login page");
        driver = Hooks.getDriver();
        driver.get("http://localhost:5173/login"); // Sesuaikan URL untuk halaman login jika perlu
        loginPage = new LoginPage(driver);
        test.log(Status.INFO, "Navigated to login page");
    }

    @When("User submit valid credentials")
    public void user_submit_valid_credentials() {
        test.log(Status.INFO, "Submitting valid credentials");
        loginPage.enterEmail("siakadadmin@example.com");
        loginPage.enterPassword("password");
        loginPage.clickSubmit();
        test.log(Status.PASS, "Submitted valid credentials");
    }

    @Then("User should be redirected to the Data Dosen Page")
    public void user_should_be_redirected_to_the_data_dosen_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        assertEquals("http://localhost:5173/dashboard", driver.getCurrentUrl());
        test.log(Status.PASS, "Redirected to the Data Dosen Page");
    }

    @When("user submits {string} and {string}")
    public void user_submits_and(String email, String password) {
        test.log(Status.INFO, "Submitting credentials: " + email + " / " + password);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();
        test.log(Status.PASS, "Submitted credentials: " + email);
    }

    @Then("user still on the login page")
    public void user_still_on_the_login_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        assertEquals("http://localhost:5173/login", driver.getCurrentUrl());
        test.log(Status.PASS, "User is still on the login page");
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
