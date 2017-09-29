package com.github.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage navigateTo(){
        driver.navigate().to("https://github.com");
        assertTrue(driver.getTitle().startsWith("The world's leading"));
        return new LoginPage(driver);
    }

    public AbstractPage navigateTo(String repoUrl) {
        driver.navigate().to(repoUrl);
        return new AbstractPage(driver);
    }

    public AbstractPage sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new AbstractPage(driver);
    }

    public AbstractPage scrollDown(int numberOfPixels) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0," + numberOfPixels + ")", "");

        return new AbstractPage(driver);
    }
}
