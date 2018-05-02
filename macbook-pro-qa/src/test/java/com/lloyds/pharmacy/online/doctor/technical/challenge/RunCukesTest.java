package com.lloyds.pharmacy.online.doctor.technical.challenge;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
        format = {"json:target/cucumber-report.json",
                "html:target/cucumber-html-report"},
    tags = {"@qa", "not @Ignore"},
        features = "src/test/resources",
        monochrome = true,
        glue = "com.lloyds.pharmacy.online.doctor.technical.challenge"
)
public class RunCukesTest {
}
