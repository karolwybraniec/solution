package com.github.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DeleteRepositoryPage extends AbstractPage{

    private final By passwordSelector = By.id("sudo_password");
    private final By submitSelector = By.tagName("button");

    public DeleteRepositoryPage(WebDriver driver) {
        super(driver);
    }

    public DeleteRepositoryPage isOnDeleteRepositoryPage(String gitRepositoryUrl) {
        try {
            String currentUrl = driver.getCurrentUrl();
            String repoUrl = gitRepositoryUrl + "/settings/delete";
            assertEquals(repoUrl, currentUrl);
        } catch (Exception e){
            // password might not has to be inserted. If so skip step
        }
        return new DeleteRepositoryPage(driver);
    }

    public DeleteRepositoryPage insertPassword(String gitPassword) {
        try {
            WebElement passwordInput = driver.findElement(passwordSelector);
            passwordInput.sendKeys(gitPassword);
        } catch (Exception e){
            // password might not has to be inserted. If so skip step
        }

        return new DeleteRepositoryPage(driver);
    }

    public DeleteRepositoryPage submit() {
        try {
            WebElement submitBtn = driver.findElement(submitSelector);
            submitBtn.submit();
        } catch (Exception e){
            // password might not has to be inserted. If so skip step
        }

        return new DeleteRepositoryPage(driver);
    }

}
