package com.lloyds.pharmacy.online.doctor.technical.challenge.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.EnvProperties;
import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.SeleniumProperties;
import com.lloyds.pharmacy.online.doctor.technical.challenge.setup.BaseTest;
import com.lloyds.pharmacy.online.doctor.technical.challenge.setup.SeleniumSetup;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Page {

    private WebDriver driver;
    private WebDriverWait webdriverWait;
    private int webDriverWaitTimeout;
    private BaseTest baseTest;

    @Autowired
    public Page(BaseTest baseTest, SeleniumSetup seleniumSetup, SeleniumProperties seleniumProperties) {
        this.baseTest = baseTest;
        this.driver = seleniumSetup.getDriver();
        webDriverWaitTimeout = seleniumProperties.getWebDriverWaitTimeout();
        webdriverWait = new WebDriverWait(driver, seleniumProperties.getWebDriverWaitTimeout());
    }

    public void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            baseTest.takeScreenShot();
            log.error(e.getLocalizedMessage());
        }
    }

    public void typeTextInField(WebElement element, String text) {
        try {
            element.click();
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            baseTest.takeScreenShot();
            log.error(e.getLocalizedMessage());
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            baseTest.takeScreenShot();
            return false;
        } catch (TimeoutException e) {
            baseTest.takeScreenShot();
            return false;
        }
    }

    public String getElementText(WebElement element) {
        String elementText = "";
        try {
            elementText = element.getText();
        } catch (Exception e) {
            baseTest.takeScreenShot();
            log.error(e.getLocalizedMessage());
        }
        return elementText;
    }

    public void setCheckBoxByState(WebElement element, boolean setCheckBoxStateTo) {
        try {
            if (setCheckBoxStateTo && !element.isSelected()) {
                element.click();
            }
            if (!setCheckBoxStateTo && element.isSelected()) {
                element.click();
            }
        } catch (Exception e) {
            baseTest.takeScreenShot();
            log.error(e.getLocalizedMessage());
        }
    }

    public void clickElementWithRetry(final WebElement element) {
        FluentWait<WebElement> customWait = new FluentWait<WebElement>(element)
                .withTimeout(webDriverWaitTimeout, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(StaleElementReferenceException.class);

        customWait.until(new Function<WebElement, Boolean>() {
            public Boolean apply(WebElement element) {
                try {
                    element.click();
                    return true;
                } catch (Exception e) {
                    baseTest.takeScreenShot();
                    return false;
                }
            }
        });
    }

    public void waitForVisibility(WebElement element) throws Error {
        webdriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickable(WebElement element) throws Error {
        webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForNumberOfWindowsToEqual(final int numberOfWindows) throws Error {
        webdriverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (driver.getWindowHandles().size() == numberOfWindows);
            }
        });
    }

    public void waitForInvisibile(By locator) throws Error {
        webdriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForTextVisibility(final WebElement element, final String text) {
        webdriverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (element.getText().equals(text));
            }
        });

    }

}
