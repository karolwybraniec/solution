package com.github.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage{

    private final By signInSelector = By.linkText("Sign in");
    private final By loginFieldSelector = By.id("login_field");
    private final By passwordSelector = By.id("password");
    private final By loginBtnSelector = By.cssSelector("input[class='btn btn-primary btn-block']");

    private final String login = "karolwybraniec";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage loginAsUser(String userLogin, String userPassword) {

        WebElement login = driver.findElement(signInSelector);
        login.click();

        WebElement username = driver.findElement(loginFieldSelector);
        username.sendKeys(userLogin);

        WebElement password = driver.findElement(passwordSelector);
        password.sendKeys(userPassword);

        WebElement signIn = driver.findElement(loginBtnSelector);
        signIn.submit();


        return new LoginPage(driver);
    }

    public String getLogin() {
        return login;
    }
}
