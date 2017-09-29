package com.github.steps;

import com.github.Config;
import com.github.pages.LoginPage;
import com.github.pages.DashboardPage;
import com.github.pages.RepoCreationPage;
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


public class CreatingNewRepoSteps {

    private WebDriver driver;
    private LoginPage login;
    private DashboardPage profile;
    private RepoCreationPage repoCreation;
    private RepoPage repo;
    private Config config;

    @Given("^User has logged in$")
    public void userHasLoggedIn() throws Throwable {
        login.navigateTo();
        login.loginAsUser(this.config.getGitUsername(), this.config.getGitPassword());
        profile.checkForCreateRepositoryBtn();
    }

    @Given("^user clicks new repository button$")
    public void userClicksNewRepositoryButton() throws Throwable {
        profile.clickCreateRepository();
    }

    @And("^user inserts repository name$")
    public void userInsertsRepositoryName() throws Throwable {
        repoCreation.insertRepoName();
    }

    @When("^user clicks create repository$")
    public void userClicksCreateRepository() throws Throwable {
        repoCreation.clickCreateNewRepo();
    }

    @Then("^user is redirected to new repository page$")
    public void userIsRedirectedToNewRepositoryPage() throws Throwable {
        repo.waitForPageToLoad();
        repo.checkRepoNameInUrl(repoCreation.getRepoName());
        repo.checkUsernameInUrl(login.getLogin());
    }

    @Before
    public void setUp() throws IOException {
        // Test assumes that there is geckodriver in PATH variable
        // In Linux, type in terminal: `whereis geckodriver`

        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        driver = new FirefoxDriver();
        login = new LoginPage(driver);
        profile = new DashboardPage(driver);
        repoCreation = new RepoCreationPage(driver);
        repo = new RepoPage(driver);
        config = new Config();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}