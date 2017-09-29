package com.github.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ComparePage extends AbstractPage{

    private final By createPullRequestSelector = By.cssSelector(".timeline-comment .form-actions .btn.btn-primary");

    public ComparePage(WebDriver driver) {
        super(driver);
    }

    public ComparePage createPullRequest() {
        WebElement createPullRequest = driver.findElement(createPullRequestSelector);
        createPullRequest.click();

        return new ComparePage(driver);
    }
}
