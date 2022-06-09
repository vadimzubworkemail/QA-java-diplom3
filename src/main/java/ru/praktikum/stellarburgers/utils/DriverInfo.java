package ru.praktikum.stellarburgers.utils;

import lombok.Getter;

import java.io.File;

public class DriverInfo {
    private final String chromeDriverName = "chromedriver.exe";
    private final String yandexDriverName = "yandexdriver.exe";
    private final ClassLoader classLoader = DriverInfo.class.getClassLoader();
    private final File chromeDriverFile = new File(classLoader.getResource(chromeDriverName).getFile());
    @Getter
    public final String chromeDriverAbsolutePath = chromeDriverFile.getAbsolutePath();
    private final File yandexDriverFile = new File(classLoader.getResource(yandexDriverName).getFile());
    @Getter
    public final String yandexDriverAbsolutePath = yandexDriverFile.getAbsolutePath();

}
