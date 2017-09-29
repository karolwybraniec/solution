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


public class CreatePullRequestSteps {

    private WebDriver driver;
    private LoginPage login;
    private ComparePage comparePage;
    private PullRequestPage pullRequestPage;
    private RepoPage repo;
    private GitClient git;
    private Config config;

    @Given("^User is logged in on github$")
    public void userHasLoggedIn() throws Throwable {
        login.navigateTo();
        login.loginAsUser(this.config.getGitUsername(), this.config.getGitPassword());
    }

    @Given("^there is following repo and branch on users github$")
    public void thereIsFollowingRepoAndBranchOnUsersGithub() throws Throwable {
        git.createBranch();
        git.add();
        git.commit();
        git.push();
    }

    @When("^user goes to the given branch repo page$")
    public void userGoesToTheGivenBranchRepoPage() throws Throwable {
        repo.navigateTo(git.getRepoUrl());
        repo.checkRepoNameInUrl(this.config.getGitRepositoryName());
    }

    @And("^clicks compare and create pull request$")
    public void clicksCompareAndCreatePullRequest() throws Throwable {
        repo.clickCompareAndCreatePullRequest();
    }

    @Then("^user is redirected to the next step$")
    public void userIsRedirectedToTheNextStep() throws Throwable {
        // TODO Something should be checked here =)
    }

    @And("^user click create pull request button$")
    public void userClickCreatePullRequestButton() throws Throwable {
        comparePage.createPullRequest();
    }

    @And("^user is redirected to pull requets page$")
    public void userIsRedirectedToPullRequetsPage() throws Throwable {
        pullRequestPage.checkUrlCorrectness(this.git.getPullReqestUrl());
        pullRequestPage.checkOpenPullRequestIndicator();
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
