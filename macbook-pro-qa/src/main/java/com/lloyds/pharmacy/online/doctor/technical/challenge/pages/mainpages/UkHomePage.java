package com.lloyds.pharmacy.online.doctor.technical.challenge.pages.mainpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lloyds.pharmacy.online.doctor.technical.challenge.pages.Page;
import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.EnvProperties;
import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.SeleniumProperties;
import com.lloyds.pharmacy.online.doctor.technical.challenge.setup.BaseTest;
import com.lloyds.pharmacy.online.doctor.technical.challenge.setup.SeleniumSetup;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UkHomePage extends Page {

    private WebDriver driver;

    @Autowired
    public UkHomePage(BaseTest baseTest, SeleniumSetup seleniumSetup, EnvProperties envProperties, SeleniumProperties seleniumProperties) {
        super(baseTest, seleniumSetup, seleniumProperties);
        driver = seleniumSetup.getDriver();
        driver.get(envProperties.getAppleUkUrl());
    }

    @FindBy(how = How.ID, using = "ac-gn-firstfocus")
    private WebElement headerMenuAppleIcon;

    @FindBy(how = How.XPATH, using = "//nav[@id='ac-globalnav']//div[@class='ac-gn-link ac-gn-link-mac']")
    private WebElement macHeaderMenuLink;


    public boolean isHeaderMenuAppleIconDisplayed() {
        WebElement headerMenuAppleIcon = driver.findElement(By.id("ac-gn-firstfocus"));

        boolean isAppleIconDisplayed = isElementDisplayed(headerMenuAppleIcon);
        log.info("Is apple header menu icon displayed? : " + isAppleIconDisplayed);
        return isAppleIconDisplayed;
    }

    public void clickOnMacHeaderMenuLink() {
        WebElement macHeaderMenuLink = driver.findElement(By.xpath("//li[@class='ac-gn-item ac-gn-item-menu ac-gn-mac']"));

        clickElement(macHeaderMenuLink);
        log.info("Clicked on 'Mac' header menu link");
    }

}
