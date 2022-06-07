package ru.praktikum.stellarburgers.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CommonElements {


    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement logoButton; // кнопка логотип
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private SelenideElement personalAreaButton; // кнопка входа в личный кабинет
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton; // кнопка конструктор
    @FindBy(how = How.CLASS_NAME, using = "undefined ml-2")
    private SelenideElement orderFeedButton; // кнопка лента заказов

    @Step("Клик на кнопку логотип")
    public void clickLogoButton() {
        logoButton.click();
    }


    @Step("Клик на кнопку личный кабинет")
    public void clickPersonalAreaButton() {
        personalAreaButton.click();
    }

    @Step("Клик на кнопку конструктор")
    public void clickConstructorButton() {
        constructorButton.click();
    }
}
