package ru.praktikum.stellarburgers.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.praktikum.stellarburgers.pages.CommonElements;

public class MainPage extends CommonElements {
    public final static String URL_BASE = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement LogInButton; // кнопка войти в аккаунт

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsButton; // кнопка булки

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement saucesButton; // кнопка соусы

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsButton; // кнопка начинки

    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement BunsText; // текст блока булочки

    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement SaucesText; // текст блока соусы

    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement FillingsText; // текст блока соусы

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement createOrderButton;


    @Step("Кликнуть по кнопке войти в аккаунт")
    public void clickLogInButton() {
        LogInButton.click();
    }

    @Step("Кликнуть по кнопке булочки")
    public void clickBunsSection() {
        bunsButton.click();
    }

    @Step("Кликнуть по кнопке соусы")
    public void clickSaucesSection() {
        saucesButton.click();
    }

    @Step("Кликнуть по кнопке начинки")
    public void clickFillingsSection() {
        fillingsButton.click();
    }

    @Step("Получить текст блока булочки")
    public SelenideElement getTextBunsBlock() {
        return BunsText;
    }

    @Step("Получить текст блока соусы")
    public SelenideElement getTextSaucesBlock() {
        return SaucesText;
    }

    @Step("Получить текст блока начинки")
    public SelenideElement getTextFillingsBlock() {
        return FillingsText;
    }

    public SelenideElement getCreateOrderButton() {
        return createOrderButton;
    }
}
