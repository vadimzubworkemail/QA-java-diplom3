package ru.praktikum.stellarburgers.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends CommonElements {
    public static final String URL_LOGIN = MainPage.URL_BASE + "login";

    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement emailField; // поле ввода почты

    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement passwordField; // поле ввода пароля

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton; // кнопка войти

    @FindBy(how = How.XPATH, using = ".//div/main/div/div/p[1]/a")
    private SelenideElement registerButton; // кнопка зарегистрироваться

    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement forgotPasswordButton; // кнопка восстановить пароль


    public void fillEmail(String email) {
        emailField.setValue(email);
    }

    public void fillPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Ввести логин и пароль")
    public void loginUser(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        clickLoginButton();
    }

    @Step("Кликнуть на кнопку зарегистрироваться")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Кликнуть на кнопку восстановить пароль")
    public void clickForgotPasswordButton() {
        forgotPasswordButton.click();
    }
}
