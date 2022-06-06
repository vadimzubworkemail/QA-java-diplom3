package ru.praktikum.stellarburgers;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.stellarburgers.client.UserClient;
import ru.praktikum.stellarburgers.model.User;
import ru.praktikum.stellarburgers.model.UserDataGeneration;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OpenPersonalAccountTest {
    static MainPage mainPage;
    private final UserClient userClient = new UserClient();
    private final String driverPath;

    public OpenPersonalAccountTest(String driverPath) {
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
        User user = UserDataGeneration.random();
        userClient.registerUser(user);
        mainPage.clickLogInButton();
        sleep(5000);
        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(user.getEmail(), user.getPassword());
        sleep(5000);
    }

    @After
    public void tearDown() {
        closeWebDriver();
        userClient.deleteUser();
    }

    @Test
    @DisplayName("Тест перехода в «Личный кабинет»")
    public void openAccountProfileTest() {
        mainPage.clickPersonalAreaButton();
        sleep(5000);
        assertEquals(AccountProfilePage.URL_PROF, WebDriverRunner.getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Тест перехода в «Личный кабинет»")
    public void openConstructorFromAccountProfilePageTest() {
        mainPage.clickPersonalAreaButton();
        sleep(5000);
        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);
        accountProfilePage.clickConstructorButton();
        sleep(5000);
        assertEquals(MainPage.URL_BASE, WebDriverRunner.getWebDriver().getCurrentUrl());
    }
}
