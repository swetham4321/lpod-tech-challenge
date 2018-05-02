package com.lloyds.pharmacy.online.doctor.technical.challenge.steps;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lloyds.pharmacy.online.doctor.technical.challenge.pages.mainpages.UkHomePage;
import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.EnvProperties;
import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.SeleniumProperties;
import com.lloyds.pharmacy.online.doctor.technical.challenge.setup.BaseTest;
import com.lloyds.pharmacy.online.doctor.technical.challenge.setup.SeleniumSetup;
import cucumber.api.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonSteps {

    private UkHomePage ukHomePage;

    public CommonSteps(UkHomePage ukHomePage) {
        this.ukHomePage = ukHomePage;
    }

    @Given("^I go to the website$")
    public void iGoToTheWebsite() {
        assertThat(ukHomePage.isHeaderMenuAppleIconDisplayed(), is(true));
        ukHomePage.clickOnMacHeaderMenuLink();
    }


}
