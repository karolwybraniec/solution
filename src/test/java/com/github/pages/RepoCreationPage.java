package com.github.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RepoCreationPage extends AbstractPage{
    private final By repoNameSelector = By.cssSelector(".owner-reponame dl:last-child input");
    private final By createRepoSelector = By.cssSelector("button[class='btn btn-primary first-in-line']");

    private final String repoName = "temp5";

    public RepoCreationPage(WebDriver driver) {
        super(driver);
    }

    public RepoCreationPage insertRepoName() {

        WebElement repoName = driver.findElement(repoNameSelector);
        repoName.sendKeys(this.repoName);

        return new RepoCreationPage(driver);
    }

    public RepoCreationPage clickCreateNewRepo() {

        WebElement createRepo = driver.findElement(createRepoSelector);
        createRepo.submit();

        return new RepoCreationPage(driver);
    }

    public String getRepoName() {
        return repoName;
    }

}
