package com.github.steps;

import com.github.Config;
import com.github.GitClient;
import com.github.pages.LoginPage;
import com.github.pages.RepoPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;


public class PushCommitSteps {

    private WebDriver driver;
    private LoginPage login;
    private RepoPage repo;
    private GitClient git;
    private Config config;

    @Given("^User has logged in on github$")
    public void userHasLoggedIn() throws Throwable {
        login.navigateTo();
        login.loginAsUser(this.config.getGitUsername(), this.config.getGitPassword());
    }

    @Given("^user creates a working branch$")
    public void userCreatesAWorkingBranch() throws Throwable {
        git.createBranch();
    }

    @And("^user adds a sample file$")
    public void userAddsASampleFile() throws Throwable {
        git.add();
    }

    @And("^user commits the file to the index$")
    public void userCommitsTheFileToTheIndex() throws Throwable {
        git.commit();
    }

    @When("^user pushes commit$")
    public void userPushesCommit() throws Throwable {
        git.push();
    }

    @Then("^commit is visible on github repo page$")
    public void commitIsVisibleOnGithubRepoPage() throws Throwable {
        repo.navigateTo(git.getRepoUrl());
        repo.checkCommitRefId(this.git.getLastCommitHash());
    }

    @Before
    public void setUp() throws IOException {
        // Test assumes that there is geckodriver in PATH variable
        // In Linux, type in terminal: `whereis geckodriver`
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        driver = new FirefoxDriver();
        login = new LoginPage(driver);
        repo = new RepoPage(driver);
        git = new GitClient();
        config = new Config();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
