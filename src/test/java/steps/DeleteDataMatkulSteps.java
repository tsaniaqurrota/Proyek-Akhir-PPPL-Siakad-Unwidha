package steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DeleteDataMatkulPage;
import report.ExtentReportManager;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class DeleteDataMatkulSteps {
    WebDriver driver;
    DeleteDataMatkulPage deleteDataMatkulPage;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
    }

    @Given("User is logged in and on the matkul page to delete data matkul")
    public void user_is_logged_in_and_on_the_matkul_page() {
        test = extent.createTest("User is logged in and on the matkul page to delete data matkul");
        driver = Hooks.getDriver();
        driver.get("http://localhost:5173/login"); // Replace with your login page URL
        deleteDataMatkulPage = new DeleteDataMatkulPage(driver);
        test.log(Status.INFO, "Navigated to login page");

        deleteDataMatkulPage.enterEmail("siakadadmin@example.com"); // Replace with valid email
        deleteDataMatkulPage.enterPassword("password"); // Replace with valid password
        deleteDataMatkulPage.clickSubmit();
        test.log(Status.INFO, "Entered valid credentials");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        deleteDataMatkulPage.clickMatkulPage();
        test.log(Status.PASS, "Navigated to matkul page");
    }

    @When("User navigates to matkul details and deletes the data matkul")
    public void user_navigates_to_matkul_details_and_deletes_data_matkul() {
        test.log(Status.INFO, "Navigating to matkul details to delete data matkul");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement detailButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("page-detail-matakuliah")));
        detailButton.click();
        test.log(Status.PASS, "Navigated to detail page");

        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-delete-matakuliah")));
        deleteButton.click();
        test.log(Status.PASS, "Clicked delete button");

        WebElement yaButton = driver.findElement(By.id("confirm-button"));
        wait.until(ExpectedConditions.elementToBeClickable(yaButton));
        yaButton.click();
        test.log(Status.PASS, "Confirmed deletion");
    }

    @Then("User should see a success alert and click OK to delete data matkul")
    public void user_should_see_a_success_alert_and_click_ok() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertEquals("Mata kuliah berhasil dihapus!", alert.getText()); // Verifikasi teks alert
        alert.accept(); // Klik tombol OK
        test.log(Status.PASS, "Verified success alert and clicked OK");
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
