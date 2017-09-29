package com.github.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class RepoPage extends AbstractPage{


    private final By commitHashSelector = By.cssSelector("a[class='commit-tease-sha']");
    private final By compareAndCreateSelector = By.cssSelector(".RecentBranches .btn.btn-sm.btn-primary");

    public RepoPage(WebDriver driver) {
        super(driver);
    }

    public RepoPage waitForPageToLoad() {
        sleep(2);
        return new RepoPage(driver);
    }

    public RepoPage checkRepoNameInUrl(String repoName) {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(repoName));

        return new RepoPage(driver);
    }

    public RepoPage checkUsernameInUrl(String username) {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(username));

        return new RepoPage(driver);
    }

    public RepoPage checkCommitRefId(String lastCommitRef) {
        String commitHash = driver.findElement(commitHashSelector).getText();
        assertEquals(lastCommitRef, commitHash);

        return new RepoPage(driver);
    }

    public RepoPage clickCompareAndCreatePullRequest() {
        sleep(2);
        WebElement compareAndCreate = driver.findElement(compareAndCreateSelector);
        compareAndCreate.click();

        return new RepoPage(driver);
    }

    public RepoPage navigateIfExists(String repoUrl) throws IOException {
        URL url = new URL(repoUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int code = connection.getResponseCode();
        assertEquals(200, code);

        driver.navigate().to(repoUrl);

        return new RepoPage(driver);
    }
}
