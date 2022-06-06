package ru.praktikum.stellarburgers;

import com.codeborne.selenide.Condition;
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

@RunWith(Parameterized.class)
public class LoginTest {
    public static MainPage mainPage;
    private final UserClient userClient = new UserClient();
    private final String driverPath;
    private User user;

    public LoginTest(String driverPath) {
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
        userClient.registerUser(user);
        System.setProperty("webdriver.chrome.driver", driverPath);
        mainPage = open(MainPage.URL_BASE, MainPage.class);
    }

    @After
    public void tearDown() {
        closeWebDriver();
        if (user != null) {
            userClient.deleteUser();
        }
    }

    @Test
    @DisplayName("вход по кнопке Войти в аккаунт на главной")
    public void loginViaLogInButtonTest() {
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(user.getEmail(), user.getPassword());
        sleep(5000);
        mainPage.getCreateOrderButton().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void loginViaAccountProfileTest() {
        mainPage.clickPersonalAreaButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(user.getEmail(), user.getPassword());
        sleep(5000);
        mainPage.getCreateOrderButton().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void loginViaRegistrationPageTest() {
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterButton();
        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.clickLogInButton();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        sleep(5000);
        mainPage.getCreateOrderButton().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void loginViaRecoverPasswordTest() {
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickForgotPasswordButton();
        ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);
        forgotPasswordPage.clickLoginButton();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        sleep(5000);
        mainPage.getCreateOrderButton().shouldBe(Condition.visible);
    }
}
