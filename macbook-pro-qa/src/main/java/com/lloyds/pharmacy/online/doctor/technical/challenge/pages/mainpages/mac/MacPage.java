package com.lloyds.pharmacy.online.doctor.technical.challenge.pages.mainpages.mac;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lloyds.pharmacy.online.doctor.technical.challenge.pages.Page;
import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.SeleniumProperties;
import com.lloyds.pharmacy.online.doctor.technical.challenge.setup.BaseTest;
import com.lloyds.pharmacy.online.doctor.technical.challenge.setup.SeleniumSetup;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MacPage extends Page {

    private WebDriver driver;

    @Autowired
    MacPage(BaseTest baseTest, SeleniumSetup seleniumSetup, SeleniumProperties seleniumProperties) {
        super(baseTest, seleniumSetup, seleniumProperties);
        driver = seleniumSetup.getDriver();
    }

    public boolean isHeaderMacBookProMenuItemDisplayed() {
        WebElement headerMacBookProMenuItem = driver.findElement(By.xpath("//li[@class='chapternav-item chapternav-item-macbook-pro macbook-pro']//span[text()='MacBook Pro']"));

        boolean isMacBookProMenuItemDisplayed = isElementDisplayed(headerMacBookProMenuItem);
        log.info("Is header MacBookPro menu item displayed? : " + isMacBookProMenuItemDisplayed);
        return isMacBookProMenuItemDisplayed;
    }

    public void clickOnMacBookProHeaderMenuItem() {
        WebElement headerMacBookProMenuItem = driver.findElement(By.xpath("//li[@class='chapternav-item chapternav-item-macbook-pro macbook-pro']"));

        clickElement(headerMacBookProMenuItem);
        log.info("Clicked on 'MacBookPro' header menu Item");
    }

    public void clickBuyButtonLink() {
        WebElement headerMacBookProMenuItem = driver.findElement(By.className("ac-ln-button"));

        clickElement(headerMacBookProMenuItem);
        log.info("Clicked on 'Buy' button link in MacBookPro top right corner");
    }

    public void selectMacBookProScreen(String screenSize) {
        WebElement macBookProSizeButton = driver.findElement(By.xpath("//button[@data-toggle-key='" + screenSize + "']"));

        waitForVisibility(macBookProSizeButton);
        clickElementWithRetry(macBookProSizeButton);
        log.info("Selected MacBookPro '{}' Screen", screenSize);
    }

    public void selectMacBookPro15InchScreenColour(String colour) {
        WebElement macBookProColourRadioButton = driver.findElement(By.xpath("//input[@datacolor='" + colour + "'][@name='colorOptionGroup_MACBOOKPRO15-ultimate']"));

        waitForVisibility(macBookProColourRadioButton);
        waitForClickable(macBookProColourRadioButton);
        //clickElement(macBookProColourRadioButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", macBookProColourRadioButton);
        log.info("Selected MacBookPro 15-inch '{}' Colour", colour);
    }

    public String checkMacBookPro15InchSilverProcessor() {
        WebElement macBookProColourRadioButton = driver.findElement(By.xpath("//ul[@class='as-macbundle-modelspecs']/li[text()[contains('2.9GHz')]]"));

        String macBookPro15InchSilverProcessor = getElementText(macBookProColourRadioButton);
        log.info("Silver MacBookPro 15-inch processor is {}", macBookPro15InchSilverProcessor);
        return macBookPro15InchSilverProcessor;
    }


}
