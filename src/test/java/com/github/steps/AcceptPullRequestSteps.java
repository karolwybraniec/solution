package com.github.steps;

import com.github.Config;
import com.github.GitClient;
import com.github.pages.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;


public class AcceptPullRequestSteps {

    private WebDriver driver;
    private LoginPage login;
    private ComparePage comparePage;
    private PullRequestPage pullRequestPage;
    private RepoPage repo;
    private GitClient git;
    private Config config;

    @Given("^User is logged in to github$")
    public void userHasLoggedIn() throws Throwable {
        login.navigateTo();
        login.loginAsUser(this.config.getGitUsername(), this.config.getGitPassword());
    }

    @Given("^there is a pull request pending$")
    public void thereIsAPullRequestPending() throws Throwable {
        git.createBranch();
        git.add();
        git.commit();
        git.push();
        repo.navigateTo(git.getRepoUrl());
        repo.clickCompareAndCreatePullRequest();
        comparePage.createPullRequest();
    }

    @And("^pull request is able to be merged$")
    public void pullRequestIsAbleToBeMerged() throws Throwable {
        pullRequestPage.checkIfMergeIsAvailable();
    }

    @When("^user click merge pull request$")
    public void userClickMergePullRequest() throws Throwable {
        pullRequestPage.mergePullRequest();
    }

    @And("^user click confirm message$")
    public void userClickConfirmMessage() throws Throwable {
        pullRequestPage.confirmMerge();
    }

    @Then("^user see a message that pull request has been merged$")
    public void userSeeAMessageThatPullRequestHasBeenMerged() throws Throwable {
        pullRequestPage.checkIfSuccessfullyMerged();
    }

    @And("^user deletes a branch to clean up$")
    public void userDeletesABranchToCleanUp() throws Throwable {
        pullRequestPage.deleteBranch();
    }

    @Before
    public void setUp() throws IOException {
        // Test assumes that there is geckodriver in PATH variable
        // In Linux, type in terminal: `whereis geckodriver`
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        driver = new FirefoxDriver();
        login = new LoginPage(driver);
        comparePage = new ComparePage(driver);
        pullRequestPage = new PullRequestPage(driver);
        repo = new RepoPage(driver);
        git = new GitClient();
        config = new Config();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
