package ru.praktikum.stellarburgers.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegisterPage extends CommonElements {
    public static final String URL_REG = MainPage.URL_BASE + "register";


    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement nameField; // поле ввода имени

    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement emailField; // поле ввода почты

    @FindBy(how = How.XPATH, using = ".//fieldset[3]/div/div/input")
    private SelenideElement passwordField; // поле ввода пароля

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton; // кнопка зарегистрироваться

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement logInButton; // кнопка войти

    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement passwordErrorMessage; // оповещение некорректный пароль


    public void fillName(String name) {
        nameField.setValue(name);
    }

    public void fillEmail(String email) {
        emailField.setValue(email);
    }

    public void fillPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickRegisterButton() {
        registerButton.shouldBe(Condition.visible).click();
    }

    @Step("Регистрация пользователя")
    public LoginPage registerNewUser(String name, String email, String password) {
        fillName(name);
        fillEmail(email);
        fillPassword(password);
        clickRegisterButton();
        return page(LoginPage.class);
    }

    @Step("Кликнуть кнопку войти")
    public void clickLogInButton() {
        logInButton.shouldBe(Condition.visible).click();
    }

    @Step("Получить оповещение системы о невалидном пароле")
    public SelenideElement getInvalidPasswordErrorMessage() {
        return passwordErrorMessage;
    }
}

