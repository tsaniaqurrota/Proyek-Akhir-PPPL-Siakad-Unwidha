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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.TambahDataMatkulPage;
import report.ExtentReportManager;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class TambahDataPageSteps {
    WebDriver driver;
    TambahDataMatkulPage tambahDataMatkulPage;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
    }

    @Given("User login to tambah data matkul page")
    public void user_login_to_tambah_data_matkul_page() {
        test = extent.createTest("User login to tambah data matkul page");
        driver = Hooks.getDriver();
        driver.get("http://localhost:5173/login"); // Replace with your login page URL
        tambahDataMatkulPage = new TambahDataMatkulPage(driver);
        test.log(Status.INFO, "Navigated to login page");
    }

    @When("User submits valid credentials and navigates to tambah data matkul form")
    public void user_submits_valid_credentials_and_navigates_to_tambah_data_matkul_form() {
        test.log(Status.INFO, "Submitting valid credentials");
        tambahDataMatkulPage.enterEmail("siakadadmin@example.com"); // Replace with valid email
        tambahDataMatkulPage.enterPassword("password"); // Replace with valid password
        tambahDataMatkulPage.clickSubmit();
        test.log(Status.PASS, "Submitted credentials");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        tambahDataMatkulPage.clickMatkulPage();
        test.log(Status.PASS, "Navigated to matkul page");

        wait.until(ExpectedConditions.urlToBe("http://localhost:5173/dashboard/data_mata_kuliah"));
        tambahDataMatkulPage.clickTambahDataPage();
        test.log(Status.PASS, "Navigated to tambah data page");
    }

    @When("User enters valid data and submits the form")
    public void user_enters_valid_data_and_submits_the_form() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement namaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nama")));
        WebElement kodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("kode")));
        WebElement sksInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sks")));
        WebElement semesterSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("semester")));
        WebElement dosenSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dosen")));
        WebElement hariSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hari")));
        WebElement waktuSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("waktu")));
        WebElement ruangInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ruang")));
        WebElement jenisSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jenis")));

        test.log(Status.INFO, "Entering valid data for mata kuliah");
        namaInput.sendKeys("Mata Kuliah Contoh");
        kodeInput.sendKeys("MKC123");
        sksInput.sendKeys("3");

        new Select(semesterSelect).selectByIndex(1); // Pilih semester ke-2

        // Tunggu sampai elemen dropdown dosen tersedia dan terisi
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#dosen option"), 1));
        Select dosenDropdown = new Select(dosenSelect);
        List<WebElement> dosenOptions = dosenDropdown.getOptions();
        if (dosenOptions.size() > 1) {
            dosenDropdown.selectByIndex(1); // Pilih dosen ke-1 (atau yang sesuai)
        } else {
            throw new NoSuchElementException("Tidak ada opsi dosen yang tersedia");
        }

        // Tunggu sampai elemen dropdown hari tersedia dan terisi
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#hari option")));
        // Pilih hari dari dropdown
        new Select(hariSelect).selectByIndex(1); // Ganti dengan teks yang sesuai

        // Tunggu sampai elemen dropdown waktu tersedia dan terisi
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#waktu option"), 1));
        Select waktuDropdown = new Select(waktuSelect);
        List<WebElement> waktuOptions = waktuDropdown.getOptions();
        if (waktuOptions.size() > 1) {
            waktuDropdown.selectByIndex(1); // Pilih sesi waktu ke-1 (atau yang sesuai)
        } else {
            throw new NoSuchElementException("Tidak ada opsi waktu yang tersedia");
        }

        ruangInput.sendKeys("A101");
        new Select(jenisSelect).selectByIndex(1); // Pilih jenis "Wajib"

        test.log(Status.PASS, "Entered valid data");

        // Submit form
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit-datamatkul")));
        submitButton.click();
        test.log(Status.PASS, "Submitted form");

        // Confirm submission
        WebElement yaButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm-button")));
        yaButton.click();
        test.log(Status.PASS, "Confirmed submission");
    }

    @Then("User should see a success alert and click OK")
    public void user_should_see_a_success_alert_and_click_ok() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertEquals("Data mata kuliah berhasil ditambahkan!", alert.getText()); // Verifikasi teks alert
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
