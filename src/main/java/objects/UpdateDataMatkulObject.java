package objects;

import org.openqa.selenium.By;

public class UpdateDataMatkulObject {
    public By getInputEmail() {
        return By.id("email");
    }

    public By getInputPassword() {
        return By.id("password");
    }

    public By getSubmitBtn() {
        return By.xpath("//button[@type='submit']");
    }

    public By getMatkulBtn() {
        return By.id("menu-item-matakuliah");
    }
}
