package ru.praktikum.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.stellarburgers.model.User;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {

    private String accessToken;

    private void getTokens(ValidatableResponse response) {
        if (response.extract().statusCode() == 200) {
            accessToken = response.extract().jsonPath().getString("accessToken").replace("Bearer ", "");
        }
    }

    @Step("Send POST request to /auth/register")
    public void registerUser(User user) {
        given()
                .spec(getBaseSpec())
                .and()
                .body(user)
                .when()
                .post("auth/register")
                .then();
    }

    @Step("Send POST request to /auth/login")
    public ValidatableResponse loginUser(User user) {
        ValidatableResponse validatableResponse = given()
                .spec(getBaseSpec())
                .and()
                .body(user)
                .when()
                .post("auth/login")
                .then();
        getTokens(validatableResponse);
        return validatableResponse;
    }

    @Step("Send DELETE request to /auth/register")
    public void deleteUser() {
        if (accessToken == null) {
            return;
        }
        given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete("auth/user")
                .then()
                .statusCode(202);
    }
}
