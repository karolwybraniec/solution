package com.github.steps;

import com.github.Config;
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

public class DeleteRepositorySteps {

    private WebDriver driver;
    private LoginPage login;
    private DeleteRepositoryPage deleteRepositoryPage;
    private RepositorySettingsPage repositorySettingsPage;
    private MainPage mainPage;
    private RepoPage repoPage;
    private Config config;

    @Given("^User is logged into github$")
    public void userHasLoggedIn() throws Throwable {
        login.navigateTo();
        login.loginAsUser(this.config.getGitUsername(), this.config.getGitPassword());
    }

    @And("^repository exists$")
    public void repositoryExists() throws Throwable {
        repoPage.navigateIfExists(this.config.geGitProfileUrl() + "/" + this.config.getGitRepositoryName());

    }
    @Given("^user is on repository settings page$")
    public void userIsOnRepositorySettingsPage() throws Throwable {
        repositorySettingsPage.navigateTo(this.config.getGitRemoteUrl());
    }

    @When("^user scrolls the page to the bottom$")
    public void userScrollsThePageToTheBottom() throws Throwable {
        repositorySettingsPage.scrollDown(2000);
    }

    @And("^user sees delete the repository button$")
    public void userSeesDeleteTheRepositoryButton() throws Throwable {
        repositorySettingsPage.checkIsDeleteButtonVisibleAndClick();
    }

    @And("^user puts repository name into dialog$")
    public void userPutsRepositoryNameIntoDialog() throws Throwable {
        repositorySettingsPage.insertRepositoryName(this.config.getGitRepositoryName());
    }

    @And("^user confirm deleting$")
    public void userConfirmDeleting() throws Throwable {
        repositorySettingsPage.confirmDeletion();
    }

    @Then("^user is redirected delete repository page$")
    public void userIsRedirectedDeleteRepositoryPage() throws Throwable {
        deleteRepositoryPage.isOnDeleteRepositoryPage(this.config.getGitRemoteUrl());
    }


    @And("^user is asked for a password$")
    public void userIsAskedForAPassword() throws Throwable {
        deleteRepositoryPage.insertPassword(this.config.getGitPassword());
    }

    @And("^user confirms the password$")
    public void userConfirmsThePassword() throws Throwable {
        deleteRepositoryPage.submit();
    }

    @Then("^user is redirected to git main page$")
    public void userIsRedirectedToGitMainPage() throws Throwable {
        mainPage.isOnMainPage(this.config.getGithubUrl());
    }

    @Before
    public void setUp() throws IOException {
        // Test assumes that there is geckodriver in PATH variable
        // In Linux, type in terminal: `whereis geckodriver`
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        driver = new FirefoxDriver();
        login = new LoginPage(driver);
        repositorySettingsPage = new RepositorySettingsPage(driver);
        deleteRepositoryPage = new DeleteRepositoryPage(driver);
        mainPage = new MainPage(driver);
        repoPage = new RepoPage(driver);
        config = new Config();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
