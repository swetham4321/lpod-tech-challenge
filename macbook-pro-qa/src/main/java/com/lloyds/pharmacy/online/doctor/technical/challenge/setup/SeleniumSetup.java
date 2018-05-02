package com.lloyds.pharmacy.online.doctor.technical.challenge.setup;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.EnvProperties;
import com.lloyds.pharmacy.online.doctor.technical.challenge.properties.SeleniumProperties;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SeleniumSetup {
    private String binaryResourcePath;
    private String chromeDriver;
    private String ieDriver;
    private String browser;
    private boolean incognitoMode;
    private long implicitlyWaitTime;
    private String ieRemoteIp;
    private String ieRemotePort;

    private static WebDriver driver;

    public enum Browsers {
        CHROME, CHROME_LINUX_X64, CHROME_MAC, IE_LOCAL, IE_REMOTE
    }

    @Autowired
    public SeleniumSetup(SeleniumProperties seleniumProperties, EnvProperties envProperties) {
        binaryResourcePath = envProperties.getBinaryResourcePath();
        chromeDriver = envProperties.getChromeDriver();
        ieDriver = envProperties.getIeDriver();
        browser = seleniumProperties.getBrowser();
        incognitoMode = seleniumProperties.isIncognitoMode();
        implicitlyWaitTime = seleniumProperties.getImplicitWaitTime();
        ieRemoteIp = seleniumProperties.getIeRemoteIp();
        ieRemotePort = seleniumProperties.getIeRemotePort();
    }

    public void setDriver() {
        if ( driver == null ) {
            driver = getDriverInstance(incognitoMode, browser);
            if (browser.contains("REMOTE")) {
                driver = new Augmenter().augment(driver);
            }
            if (incognitoMode && browser.contains("CHROME")) {
                enableIncognitoChromeAutomationExtension();
            }
            driver.manage().timeouts().implicitlyWait(implicitlyWaitTime, TimeUnit.SECONDS);
        }
    }

    public WebDriver getDriver() {
        setDriver();
        return driver;
    }

    private WebDriver getDriverInstance( boolean incognitoMode, String browser ) {
        Browsers browsers = Browsers.valueOf(browser);
        DesiredCapabilities capability;
        WebDriver newDriver = null;
        ChromeOptions options = new ChromeOptions();

        switch ( browsers ) {
            case CHROME:
                capability = getChromeCapabilities(incognitoMode, false);
                //System.setProperty( "webdriver.chrome.driver", System.getProperty( "user.dir" ) + binaryResourcePath + chromeDriver );
                newDriver = new ChromeDriver( capability );
                break;
            case CHROME_LINUX_X64:
                options.addArguments( "test-type" );
                options.addArguments( "--start-maximized" );
                System.setProperty( "webdriver.chrome.driver", "/usr/bin/chromedriver" );
                System.setProperty( "webdriver.chrome.binary", "/usr/bin/google-chrome" );
                System.setProperty( "webdriver.chrome.driver", System.getProperty( "user.dir" ) + binaryResourcePath + chromeDriver );
                newDriver = new ChromeDriver( options );
                break;
            case CHROME_MAC:
                capability = getChromeCapabilities(incognitoMode, false);
                System.setProperty( "webdriver.chrome.driver", System.getProperty( "user.dir" ) + binaryResourcePath + "chromeMacDriver/chromedriver" );
                newDriver = new ChromeDriver( capability );
                break;
            case IE_LOCAL:
                capability = getIECapabilities();
                System.setProperty( "webdriver.ie.driver", System.getProperty( "user.dir" ) + binaryResourcePath + ieDriver );
                newDriver = new InternetExplorerDriver( capability );
                break;
            case IE_REMOTE:
                capability = getIECapabilities();
                newDriver = getRemoteWebDriver(ieRemoteIp, ieRemotePort, capability);
                break;
            default:
                log.error( "No browser provided. Shutting down the test." );
                System.exit( 1 );
                break;
        }

        return newDriver;
    }

    public void teardown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    public void explicitWait(long millis) {
        try {
            Thread.sleep(millis);
            log.info("Explicitly waiting for " + millis + " milliseconds");
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
    }

    private ChromeOptions getChromeOptions(boolean incognito) {
        ChromeOptions options = new ChromeOptions();
        if (incognito)
        { options.addArguments( "--incognito" ); }
        options.addArguments( "test-type" );
        options.addArguments( "--start-maximized" );
        options.addArguments("--no-sandbox");
        return options;
    }

    private DesiredCapabilities getChromeCapabilities(boolean incognito, boolean remote) {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability( "nativeEvents", false );
        capabilities.setCapability( ChromeOptions.CAPABILITY, getChromeOptions(incognito) );
        if (remote)
        { capabilities.setPlatform( Platform.WINDOWS ); }
        return capabilities;
    }

    private DesiredCapabilities getIECapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setBrowserName("internet explorer");
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        capabilities.setCapability( "nativeEvents", false );
        capabilities.setCapability("ie.ensureCleanSession", true);
        capabilities.setPlatform( Platform.WINDOWS );
        return capabilities;
    }

    public void enableIncognitoChromeAutomationExtension() {
        try {
            getDriver().get("chrome://extensions-frame");
            getDriver().findElement(By.xpath("//*[@focus-type='incognito']")).click();
            log.info("Enabling the automation extension for Chrome via incognito.");
        } catch (Exception e) {}
    }

    private static RemoteWebDriver getRemoteWebDriver(String hostName, String portNumber, DesiredCapabilities capabilities) {
        RemoteWebDriver remoteDriver = null;
        try {
            remoteDriver = new RemoteWebDriver(new URL( "http://"
                    + hostName
                    + ":"
                    + portNumber
                    + "/wd/hub" ), capabilities);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return remoteDriver;
    }

}
