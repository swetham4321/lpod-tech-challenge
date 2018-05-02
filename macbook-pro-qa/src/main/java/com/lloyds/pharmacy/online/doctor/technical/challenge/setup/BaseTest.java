package com.lloyds.pharmacy.online.doctor.technical.challenge.setup;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.EnvProperties;
import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.SeleniumProperties;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BaseTest {
    private String screenshotResourcePath;
    private boolean captureScreenShot;
    private SeleniumSetup seleniumSetup;

    private String currentTestName;

    @Autowired
    public BaseTest(SeleniumProperties seleniumProperties, EnvProperties envProperties, SeleniumSetup seleniumSetup) {
        screenshotResourcePath = envProperties.getScreenshotResourcePath();
        captureScreenShot = envProperties.isCaptureScreenShot();
        seleniumSetup = seleniumSetup;
    }


    protected void setCurrentTestName(Method currentTestName) {
        this.currentTestName = currentTestName.getName();
    }

    public void takeScreenShot() {
        if (captureScreenShot) {
            try {
                String screenshotName = currentTestName + "_" + System.currentTimeMillis() + ".jpg";
                File screenshot = ((TakesScreenshot) seleniumSetup.getDriver()).getScreenshotAs(OutputType.FILE);
                String testResultDirectoryPath = System.getProperty("user.dir") + screenshotResourcePath + currentTestName + "/";
                File directory = new File(testResultDirectoryPath);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                FileUtils.copyFile(screenshot, new File(testResultDirectoryPath + screenshotName));
                log.info("File name of screen capture: {}", screenshotName);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
            }
        }
    }

}
