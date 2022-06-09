package ru.praktikum.stellarburgers.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends CommonElements {
    public final static String URL_BASE = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement logInButton; // кнопка войти в аккаунт

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsButton; // кнопка булки

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement saucesButton; // кнопка соусы

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsButton; // кнопка начинки

    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement bunsText; // текст блока булочки

    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement saucesText; // текст блока соусы

    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement fillingsText; // текст блока соусы

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement createOrderButton;


    @Step("Кликнуть по кнопке войти в аккаунт")
    public void clickLogInButton() {
        logInButton.shouldBe(Condition.visible).click();
    }

    @Step("Кликнуть по кнопке булочки")
    public void clickBunsSection() {
        bunsButton.shouldBe(Condition.visible).click();
    }

    @Step("Кликнуть по кнопке соусы")
    public void clickSaucesSection() {
        saucesButton.shouldBe(Condition.visible).click();
    }

    @Step("Кликнуть по кнопке начинки")
    public void clickFillingsSection() {
        fillingsButton.shouldBe(Condition.visible).click();
    }

    @Step("Получить текст блока булочки")
    public SelenideElement getTextBunsBlock() {
        return bunsText;
    }

    @Step("Получить текст блока соусы")
    public SelenideElement getTextSaucesBlock() {
        return saucesText;
    }

    @Step("Получить текст блока начинки")
    public SelenideElement getTextFillingsBlock() {
        return fillingsText;
    }

    public SelenideElement getCreateOrderButton() {
        return createOrderButton;
    }
}
