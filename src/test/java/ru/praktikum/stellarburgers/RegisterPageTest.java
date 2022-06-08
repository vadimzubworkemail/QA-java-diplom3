package ru.praktikum.stellarburgers;

import com.codeborne.selenide.Condition;
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
import ru.praktikum.stellarburgers.pages.LoginPage;
import ru.praktikum.stellarburgers.pages.RegisterPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class RegisterPageTest {
    private static final DriverInfo driverInfo = new DriverInfo();
    private final String driverPath;
    LoginPage loginPage;
    private RegisterPage registerPage;
    private UserClient userClient;
    private User user;

    public RegisterPageTest(String driver) {
        this.driverPath = driver;
    }

    @Parameterized.Parameters
    public static Object[][] getDate() {
        return new Object[][]{
                {driverInfo.getChromeDriverAbsolutePath()},
                {driverInfo.getYandexDriverAbsolutePath()},
        };
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        registerPage = open(RegisterPage.URL_REG, RegisterPage.class);
        userClient = new UserClient();
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
        if (user != null) {
            userClient.deleteUser();
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void registerSuccessTest() {
        user = UserDataGeneration.random();
        registerPage.registerNewUser(user.getName(), user.getEmail(), user.getPassword());
        loginPage = page(LoginPage.class);
        webdriver().shouldHave(WebDriverConditions.url(LoginPage.URL_LOGIN));
    }

    @Test
    @DisplayName("Вывод ошибки после ввода в поле пароль меньше шести символов")
    public void passwordTooShortTest() {
        registerPage.registerNewUser("Vasya", "pupkin@mail.com", "12345");
        registerPage.getInvalidPasswordErrorMessage().shouldBe(Condition.visible);
        assertEquals("Ожидаемое сообщение об ошибке не соответствует полученному", "Некорректный пароль", registerPage.getInvalidPasswordErrorMessage().getText());
    }
}
