package ru.praktikum.stellarburgers;

import com.codeborne.selenide.WebDriverConditions;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.stellarburgers.client.UserClient;
import ru.praktikum.stellarburgers.model.User;
import ru.praktikum.stellarburgers.model.UserDataGeneration;
import ru.praktikum.stellarburgers.pages.AccountProfilePage;
import ru.praktikum.stellarburgers.pages.LoginPage;
import ru.praktikum.stellarburgers.pages.MainPage;
import ru.praktikum.stellarburgers.utils.DriverInfo;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.praktikum.stellarburgers.pages.LoginPage.URL_LOGIN;

@RunWith(Parameterized.class)
public class LoginOutTest {
    private static final DriverInfo driverInfo = new DriverInfo();
    static MainPage mainPage;
    private final String driverPath;
    private final UserClient userClient = new UserClient();
    private User user;


    public LoginOutTest(String driverPath) {
        this.driverPath = driverPath;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {driverInfo.getChromeDriverAbsolutePath()},
                {driverInfo.getYandexDriverAbsolutePath()},
        };
    }

    @Before
    public void setUp() {
        user = UserDataGeneration.random();
        System.setProperty("webdriver.chrome.driver", driverPath);
        userClient.registerUser(user);
        mainPage = open(MainPage.URL_BASE, MainPage.class);
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(user.getEmail(), user.getPassword());
        mainPage.getCreateOrderButton().shouldBe(visible);
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
        if (user != null) {
            userClient.deleteUser();
        }
    }

    @Test
    @DisplayName("Проверяем выход по кнопке Выйти из личного кабинета")
    public void logOutTest() {
        mainPage.clickPersonalAreaButton();
        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);
        accountProfilePage.clickExitButton();
        webdriver().shouldHave(WebDriverConditions.url(URL_LOGIN));
    }
}
