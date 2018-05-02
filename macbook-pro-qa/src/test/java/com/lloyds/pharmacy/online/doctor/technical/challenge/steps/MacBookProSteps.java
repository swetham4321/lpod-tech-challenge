package com.lloyds.pharmacy.online.doctor.technical.challenge.steps;

import java.util.Map;

import com.lloyds.pharmacy.online.doctor.technical.challenge.pages.mainpages.mac.MacPage;
import com.lloyds.pharmacy.online.doctor.technical.challenge.pages.mainpages.mac.macbookpro.MacBookProPage;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MacBookProSteps {

    private MacPage macPage;

    public MacBookProSteps(MacPage macPage) {
        this.macPage = macPage;
    }

    @When("^I choose a MacBook Pro with the following features and accessories$")
    public void iChooseAMacBookProWithTheFollowingFeaturesAndAccessories(DataTable dataTable) {

        String screen = "";
        String processor = "";
        String colour = "";
        String software = "";
        String displayAdapter = "";

        for (Map<String, String> data : dataTable.asMaps(String.class, String.class)) {
            String option = data.get("Option");
            String specification = data.get("Specification");

            switch (option) {
                case "Screen":
                    screen = specification;
                    break;
                case "Processor":
                    processor = specification;
                    break;
                case "Colour":
                    colour = specification;
                    break;
                case "Software":
                    software = specification;
                    break;
                case "DisplayAdapter":
                    displayAdapter = specification;
                    break;
                default:
                    log.error("Option is not recognised, please provide a valid option");
                    break;
            }
        }

        macPage.isHeaderMacBookProMenuItemDisplayed();
        macPage.clickOnMacBookProHeaderMenuItem();
        macPage.clickBuyButtonLink();
        macPage.selectMacBookProScreen(screen);
        macPage.selectMacBookPro15InchScreenColour(colour);

    }

}
