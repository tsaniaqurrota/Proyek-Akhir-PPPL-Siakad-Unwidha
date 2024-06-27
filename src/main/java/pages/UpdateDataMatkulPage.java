package pages;

import objects.UpdateDataMatkulObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpdateDataMatkulPage {
    WebDriver driver;
    UpdateDataMatkulObject updateDataMatkulObject;

    public UpdateDataMatkulPage(WebDriver driver) {
        this.driver = driver;
        this.updateDataMatkulObject = new UpdateDataMatkulObject();
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(updateDataMatkulObject.getInputEmail());
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(updateDataMatkulObject.getInputPassword());
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        WebElement submitButton = driver.findElement(updateDataMatkulObject.getSubmitBtn());
        submitButton.click();
    }

    public void clickMatkulPage() {
        driver.findElement(updateDataMatkulObject.getMatkulBtn()).click();
    }

}
