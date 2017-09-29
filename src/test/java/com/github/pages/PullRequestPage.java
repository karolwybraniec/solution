package com.github.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


public class PullRequestPage extends AbstractPage{

    private final By openPullRequetsIndicatorSelector = By.cssSelector(".State.State--green");
    private final By mergePullRequestSelector = By.cssSelector(
            ".merge-message > .select-menu > div.btn-group-merge > button:first-child");
    private final By confirmMessageSelector = By.cssSelector(
            ".commit-form-actions .BtnGroup.btn-group-merge button:first-child");
    private final By successMessageSelector = By.cssSelector(".js-details-container.Details .merge-branch-heading");
    private final By deleteBranchSelector = By.cssSelector(".post-merge-message button");
    private final By restoreBranchSelector = By.cssSelector(".pull-request-ref-restore button");

    public PullRequestPage(WebDriver driver) {
        super(driver);
    }

    public PullRequestPage checkOpenPullRequestIndicator() {
       WebElement openPullRequetsIndicator = driver.findElement(openPullRequetsIndicatorSelector);
       boolean visibility = openPullRequetsIndicator.isDisplayed();
       assertTrue(visibility);

       return new PullRequestPage(driver);
    }

    public PullRequestPage checkUrlCorrectness(String url) {
        String currentUrl = driver.getCurrentUrl();
        assertEquals(url, currentUrl);

        return new PullRequestPage(driver);
    }

    public PullRequestPage checkIfMergeIsAvailable() {
        String mergeButtonText = driver.findElement(mergePullRequestSelector).getText().trim();
        assertEquals("Merge pull request", mergeButtonText);

        return new PullRequestPage(driver);
    }

    public PullRequestPage mergePullRequest() {
        WebElement mergePullRequest = driver.findElement(mergePullRequestSelector);
        mergePullRequest.click();
        sleep(2);

        return new PullRequestPage(driver);
    }

    public PullRequestPage confirmMerge() {
        WebElement confirmMessage = driver.findElement(confirmMessageSelector);
        confirmMessage.click();
        sleep(2);

        return new PullRequestPage(driver);
    }

    public PullRequestPage checkIfSuccessfullyMerged() {
        String successMessage = driver.findElement(successMessageSelector).getText().trim();
        assertEquals("Pull request successfully merged and closed", successMessage);

        return new PullRequestPage(driver);
    }

    public PullRequestPage deleteBranch() {
        WebElement deleteBranch = driver.findElement(deleteBranchSelector);
        deleteBranch.click();
        sleep(2);
        boolean restoreBranch = driver.findElement(restoreBranchSelector).isDisplayed();
        assertTrue(restoreBranch);
        return new PullRequestPage(driver);
    }
}
