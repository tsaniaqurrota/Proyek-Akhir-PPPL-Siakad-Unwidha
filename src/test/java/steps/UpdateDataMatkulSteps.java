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
import pages.UpdateDataMatkulPage;
import report.ExtentReportManager;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class UpdateDataMatkulSteps {
    WebDriver driver;
    UpdateDataMatkulPage updateDataMatkulPage;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
    }

    @Given("User login to tambah data matkul page to update data matkul page")
    public void user_login_to_tambah_data_matkul_page() {
        test = extent.createTest("User login to tambah data matkul page to update data matkul page");
        driver = Hooks.getDriver();
        driver.get("http://localhost:5173/login"); // Replace with your login page URL
        updateDataMatkulPage = new UpdateDataMatkulPage(driver);
        test.log(Status.INFO, "Navigated to login page");
    }

    @When("User submits valid credentials and navigates to update data matkul form")
    public void user_submits_valid_credentials_and_navigates_to_update_data_matkul_form() {
        test.log(Status.INFO, "Submitting valid credentials");
        updateDataMatkulPage.enterEmail("siakadadmin@example.com"); // Replace with valid email
        updateDataMatkulPage.enterPassword("password"); // Replace with valid password
        updateDataMatkulPage.clickSubmit();
        test.log(Status.PASS, "Submitted credentials");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        updateDataMatkulPage.clickMatkulPage();
        test.log(Status.PASS, "Navigated to matkul page");

        wait.until(ExpectedConditions.urlToBe("http://localhost:5173/dashboard/data_mata_kuliah"));
        WebElement detailButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("page-detail-matakuliah")));
        detailButton.click();
        test.log(Status.PASS, "Navigated to detail page");

        WebElement updateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-edit-matakuliah")));
        updateButton.click();
        test.log(Status.PASS, "Clicked update button");
    }

    @When("User enters valid data and submits the update form")
    public void user_enters_valid_data_and_submits_the_update_form() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement namaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nama")));
        WebElement kodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("kode")));
        WebElement sksInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sks")));
        WebElement semesterSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("semester")));
        WebElement dosenSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dosen")));
        WebElement hariSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hari")));
        WebElement waktuSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("waktu")));
        WebElement ruangInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ruang")));
        WebElement jenisSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jenis")));

        test.log(Status.INFO, "Waiting for input fields to be populated");

        // Menunggu beberapa detik sebelum menghapus dan mengisi data
        wait.until(ExpectedConditions.attributeToBeNotEmpty(namaInput, "value"));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(kodeInput, "value"));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(sksInput, "value"));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(ruangInput, "value"));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(jenisSelect, "value"));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(semesterSelect, "value"));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(dosenSelect, "value"));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(hariSelect, "value"));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(waktuSelect, "value"));

        test.log(Status.PASS, "Input fields populated");

        namaInput.clear();
        namaInput.sendKeys("Mata Kuliah Contoh 1");
        test.log(Status.PASS, "Entered valid data for mata kuliah");

        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-save-matakuliah")));
        submitButton.click();
        test.log(Status.PASS, "Submitted update form");

        WebElement yaButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm-button")));
        yaButton.click();
        test.log(Status.PASS, "Confirmed submission");
    }

    @Then("User should see a success alert and click OK from update")
    public void user_should_see_a_success_alert_and_click_ok() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertEquals("Data mata kuliah berhasil diupdate!", alert.getText()); // Verifikasi teks alert
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
