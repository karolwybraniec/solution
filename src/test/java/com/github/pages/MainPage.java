package com.github.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;


public class MainPage extends AbstractPage{

    private final By repositoryDeletedMsgSelector = By.cssSelector(".flash-close.js-flash-close");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage isOnMainPage(String githubUrl) {
        String currentUrl = driver.getCurrentUrl();
        assertEquals(githubUrl + "/", currentUrl);

        return new MainPage(driver);
    }

//    public MainPage isSuccessDeletionMsgVisible(String gitUserName) {
//        String message = driver.findElement(repositoryDeletedMsgSelector).getText().trim();
//        String expected = gitUserName.split("@")[0];
//
//        assertEquals("Your repository \"" + expected + "\" was succesfully deleted.", message);
//
//        return new MainPage(driver);
//    }
}
