package pages;

import objects.DeleteDataMatkulObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteDataMatkulPage {
    WebDriver driver;

    DeleteDataMatkulObject deleteDataMatkulObject;

    public DeleteDataMatkulPage(WebDriver driver) {
        this.driver = driver;
        this.deleteDataMatkulObject = new DeleteDataMatkulObject();
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(deleteDataMatkulObject.getInputEmail());
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(deleteDataMatkulObject.getInputPassword());
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        WebElement submitButton = driver.findElement(deleteDataMatkulObject.getSubmitBtn());
        submitButton.click();
    }

    public void clickMatkulPage() {
        driver.findElement(deleteDataMatkulObject.getMatkulBtn()).click();
    }

    public void clickDetailMatkulPage() {
        driver.findElement(deleteDataMatkulObject.getDetailMatkulPageBtn()).click();
    }

    public void clickDeleteMatkulPage() {
        driver.findElement(deleteDataMatkulObject.getDeleteDataMatkulBtn()).click();
    }


}
