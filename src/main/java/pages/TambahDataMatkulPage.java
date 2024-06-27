package pages;

import org.openqa.selenium.WebDriver;
import objects.TambahDataMatkulObject;
import org.openqa.selenium.WebElement;

public class TambahDataMatkulPage {
    WebDriver driver;

    TambahDataMatkulObject tambahDataMatkulObject;

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(tambahDataMatkulObject.getInputEmail());
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(tambahDataMatkulObject.getInputPassword());
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        WebElement submitButton = driver.findElement(tambahDataMatkulObject.getSubmitBtn());
        submitButton.click();
    }

    public void clickSubmitData() {
        WebElement submitButton = driver.findElement(tambahDataMatkulObject.getSubmitDataMatkulBtn());
        submitButton.click();
    }


    public TambahDataMatkulPage(WebDriver driver) {
        this.driver = driver;
        this.tambahDataMatkulObject = new TambahDataMatkulObject();
    }

    public void clickMatkulPage() {
        driver.findElement(tambahDataMatkulObject.getMatkulBtn()).click();
    }
    public void clickTambahDataPage() {
        driver.findElement(tambahDataMatkulObject.getTambahMatkulBtn()).click();
    }


}
