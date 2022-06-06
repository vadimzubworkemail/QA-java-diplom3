package ru.praktikum.stellarburgers;

import com.codeborne.selenide.Condition;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@RunWith(Parameterized.class)
public class ConstructorTest {
    private static MainPage mainPage;
    private final String driverPath;

    public ConstructorTest(String driverPath) {
        this.driverPath = driverPath;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"C:\\Users\\Dick\\Documents\\studying\\Diplom\\QA-java-diplom3\\yandexdriver.exe"},
                {"C:\\Users\\Dick\\Documents\\studying\\Diplom\\QA-java-diplom3\\chromedriver.exe"},
        };
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        mainPage = open(MainPage.URL_BASE, MainPage.class);
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Проверяем переход к разделу Булки")
    public void opensBunsBlockTest() {
        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();
        mainPage.getTextBunsBlock().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Проверяем переход к разделу Соусы")
    public void opensSaucesBlockTest() {
        mainPage.clickSaucesSection();
        mainPage.getTextSaucesBlock().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Проверяем переход к разделу Начинки")
    public void opensFillingsBlockTest() {
        mainPage.clickFillingsSection();
        mainPage.getTextFillingsBlock().shouldBe(Condition.visible);
    }
}
