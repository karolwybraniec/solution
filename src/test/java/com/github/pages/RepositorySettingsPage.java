package com.github.pages;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RepositorySettingsPage extends AbstractPage{

    private final By deleteRepositorySelector = By.cssSelector(
            ".Box.Box--danger li:nth-of-type(3) .btn.btn-danger.boxed-action");
    private final By repositoryNameConfirmationSelector = By.xpath("//*[@id=\"facebox\"]/div/div/form/p/input");
    private final By deletionConfirmationSelector = By.xpath("//*[@id=\"facebox\"]/div/div/form/button");


    public RepositorySettingsPage(WebDriver driver) {
        super(driver);
    }

    public RepositorySettingsPage navigateTo(String url) {
        this.driver.navigate().to(url + "/settings");
        return new RepositorySettingsPage(driver);
    }

    public RepositorySettingsPage checkIsDeleteButtonVisibleAndClick() {
        WebElement deleteRepository = driver.findElement(this.deleteRepositorySelector);
        assertTrue(deleteRepository.isDisplayed());
        deleteRepository.click();

        return new RepositorySettingsPage(driver);
    }

    public RepositorySettingsPage insertRepositoryName(String repositoryNameToDelete) {
        WebElement input = driver.findElement(repositoryNameConfirmationSelector);
        input.sendKeys(repositoryNameToDelete);

        return new RepositorySettingsPage(driver);
    }

    public RepositorySettingsPage confirmDeletion() {
        WebElement confirmation = driver.findElement(deletionConfirmationSelector);
        confirmation.submit();
        sleep(2);

        return new RepositorySettingsPage(driver);
    }


}
