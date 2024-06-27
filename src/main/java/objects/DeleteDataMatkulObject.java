package objects;

import org.openqa.selenium.By;

public class DeleteDataMatkulObject {
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

    public By getDetailMatkulPageBtn() {
        return By.id("page-detail-matakuliah");
    }

    public By getDeleteDataMatkulBtn() {
        return By.id("button-delete-matakuliah");
    }


}
