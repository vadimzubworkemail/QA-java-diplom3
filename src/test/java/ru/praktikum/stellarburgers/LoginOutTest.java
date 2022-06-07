package ru.praktikum.stellarburgers;

import com.codeborne.selenide.WebDriverRunner;
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

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static ru.praktikum.stellarburgers.pages.LoginPage.URL_LOGIN;

@RunWith(Parameterized.class)
public class LoginOutTest {
    static MainPage mainPage;
    private final String driverPath;
    private User user;
    private final UserClient userClient = new UserClient();


    public LoginOutTest(String driverPath) {
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
        user = UserDataGeneration.random();
        System.setProperty("webdriver.chrome.driver", driverPath);
        userClient.registerUser(user);
        mainPage = open(MainPage.URL_BASE, MainPage.class);
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickLoginButton();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        sleep(5000);
    }

    @After
    public void tearDown() {
        closeWebDriver();
        if (user != null) {
            userClient.deleteUser();
        }
    }

    @Test
    public void logOutTest() {
        mainPage.clickPersonalAreaButton();
        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);
        sleep(5000);
        accountProfilePage.clickExitButton();
        sleep(5000);
        assertEquals(URL_LOGIN, WebDriverRunner.getWebDriver().getCurrentUrl());
    }
}
