package ru.praktikum.stellarburgers.model;

import com.github.javafaker.Faker;

public class UserDataGeneration {
    private static final Faker faker = new Faker();

    public static User random() {
        return User.builder()
                .name(faker.name().firstName())
                .password(faker.internet().password(6, 10))
                .email(faker.internet().emailAddress())
                .build();
    }
}
