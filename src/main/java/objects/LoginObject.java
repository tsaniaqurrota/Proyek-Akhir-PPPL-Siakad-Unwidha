package objects;

import org.openqa.selenium.By;

public class LoginObject {

    public By getInputEmail() {
        return By.id("email");
    }

    public By getInputPassword() {
        return By.id("password");
    }

    public By getSubmitBtn() {
        return By.xpath("//button[@type='submit']");
    }
}
