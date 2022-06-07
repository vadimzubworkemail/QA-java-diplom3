package ru.praktikum.stellarburgers;

import com.codeborne.selenide.Condition;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
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

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class RegisterPageTest {
    private final String driverPath;

    private RegisterPage registerPage;
    private UserClient userClient;
    private User user;

    public RegisterPageTest(String driver) {
        this.driverPath = driver;
    }

    @Parameterized.Parameters
    public static Object[][] getDate() {
        return new Object[][]{
                {"C:\\Users\\Dick\\Documents\\studying\\Diplom\\QA-java-diplom3\\yandexdriver.exe"},
                {"C:\\Users\\Dick\\Documents\\studying\\Diplom\\QA-java-diplom3\\chromedriver.exe"},
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
        closeWebDriver();
        if (user != null) {
            userClient.deleteUser();
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void registerSuccessTest() {
        user = UserDataGeneration.random();
        LoginPage loginPage = registerPage.registerNewUser(user.getName(), user.getEmail(), user.getPassword());
        ValidatableResponse validatableResponse = userClient.loginUser(user);
        assertThat("Пользователь не зарегистрирован", validatableResponse.extract().statusCode(), equalTo(200));
    }

    @Test
    @DisplayName("Вывод ошибки после ввода в поле пароль меньше шести символов")
    public void passwordTooShortTest() {
        registerPage.registerNewUser("Vasya", "pupkin@mail.com", "12345");
        registerPage.getInvalidPasswordErrorMessage().shouldBe(Condition.visible);
        assertEquals("Ожидаемое сообщение об ошибке не соответствует полученному", "Некорректный пароль", registerPage.getInvalidPasswordErrorMessage().getText());
    }
}
