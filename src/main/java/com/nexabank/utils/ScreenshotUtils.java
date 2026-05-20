package com.nexabank.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScreenshotUtils {
    private ScreenshotUtils() {
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "screenshots/" + testName + "_" + timestamp + ".png";
        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Path.of("screenshots"));
            Files.copy(source.toPath(), Path.of(screenshotPath));
            return screenshotPath;
        } catch (IOException exception) {
            throw new RuntimeException("Unable to capture screenshot", exception);
        }
    }
}
