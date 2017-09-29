package com.github.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends AbstractPage{

    private final By newRepoBtnSelector = By.cssSelector("a[class='btn btn-sm btn-primary']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage checkForCreateRepositoryBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(newRepoBtnSelector));
        return new DashboardPage(driver);
    }

    public DashboardPage clickCreateRepository() {
        WebElement newRepoBtn = driver.findElement(newRepoBtnSelector);
        newRepoBtn.click();

        return new DashboardPage(driver);
    }
}
