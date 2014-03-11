package com.ihg.selenium;

import com.dynacrongroup.webtest.browser.BrowserLocale;
import com.dynacrongroup.webtest.browser.WebDriverConfig;
import com.dynacrongroup.webtest.util.Configuration;
import com.google.common.base.Throwables;
import com.ihg.model.Brand;
import com.typesafe.config.Config;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.fest.assertions.api.Assertions.assertThat;


/**
 * Representation of an IHG web page or common IHG page component.
 * PageObjects are responsible for managing interactions with actual pages under test, containing details
 * about DOM elements, and exposing methods for general page interaction.
 */
public class PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(PageObject.class);
    private static final Config CONFIG = Configuration.getConfigForClass(PageObject.class);
    private List<PageObject> moduleRegistry = new ArrayList<PageObject>();
    private ResourceBundle resourceBundle;
    private ResourceReader resourceReader;

    private WebDriver driver;
    private Brand brand;
    private Environment environment = Environment.fromJson(Configuration.getConfig().getString("webtest.environment"));
    private BrowserLocale browserLocale;
    private WebDriverConfig webDriverConfig;

    /**
     * Constructs a PageObject given a webDriver instance. When this is invoked, the webDriver should already contain
     * the Http response from the page request (made by the webDriver instance). Using the response, the constructor
     * binds the fields and modules of the page objects to their respective DOM elements.
     *
     * @param driver
     */
    public PageObject(WebDriver driver) {
        this.driver = driver;

        Field[] declaredFields = this.getClass().getDeclaredFields();
        if (declaredFields != null) {
            for (Field field : declaredFields) {
                if (PageObject.class.isAssignableFrom(field.getType())) {
                    try {
                        field.setAccessible(true);
                        PageObject module = (PageObject) PageFactory.initElements(driver, field.getType());
                        field.set(this, module);
                        moduleRegistry.add(module);
                    } catch (IllegalAccessException exception) {
                        LOG.error("Failed to access module {} with type {}", field.getName(), field.getType());
                    }
                }
            }
        }
    }

    /**
     * Used to construct a PageObject from a DOM operation (submitting a form, clicking a link, etc).
     *
     * @param newPageClass PageObject to be constructed from the Http response returned by the element's execution.
     * @param <T>          Subclass of PageObject
     * @return Fully wired PageObject
     */
    protected final <T extends PageObject> T makeNewPage(Class<T> newPageClass) {
        return (T) PageBuilder.forDriver(driver).
                forWebDriverConfig(webDriverConfig).
                forEnvironment(environment).
                forBrowserLocale(browserLocale).
                forPage(newPageClass).
                build();
    }

    public final void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            LOG.error(Throwables.getStackTraceAsString(e));
        }
    }

    public final WebElement waitForElementToBeClickable(By findByCondition, int waitInSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), waitInSeconds).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(findByCondition));
        return getDriver().findElement(findByCondition);
    }

    public final WebElement waitForElementPresence(By findByCondition, int waitInSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), waitInSeconds).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(findByCondition));
        return getDriver().findElement(findByCondition);
    }

    public final void waitForAbsenceOf(WebElement element, int waitInSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), waitInSeconds);
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public final void waitForPresenceOfAllElements(By findByCondition, int waitInSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), waitInSeconds).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(findByCondition));
    }

    public final void waitForInvisibility(By findByCondition, int waitInSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), waitInSeconds).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findByCondition));
    }

    public final void waitForVisibility(By findByCondition, int waitInSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), waitInSeconds).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(findByCondition));
    }

    public final void waitForVisibility(WebElement element, int waitInSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), waitInSeconds).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public final void selectValueFromDropDown(WebElement dropDown, String value) {
        if (value != null) {
            if (dropDown.getTagName().equals("select")) {
                new Select(dropDown).selectByValue(value);
            } else {
                dropDown.sendKeys(value);
            }
        }
    }

    public final void selectVisibleTextFromDropDown(WebElement dropDown, String value) {
        if (value != null) {
            if (dropDown.getTagName().equals("select")) {
                new Select(dropDown).selectByVisibleText(value);
            } else {
                dropDown.sendKeys(value);
            }
        }
    }

    public final String getTitle() {
        return driver.getTitle();
    }

    /**
     * Returns the entire contents of the page body element.
     *
     * @return String representation of page body.
     */
    public final String getBodyText() {
        List<WebElement> bodyElements = driver.findElements(By.tagName("body"));
        if (bodyElements.isEmpty()) {
            bodyElements.addAll(driver.findElements(By.tagName("Body")));
        }
        if (bodyElements.isEmpty()) {
            bodyElements.addAll(driver.findElements(By.tagName("BODY")));
        }
        assertThat(bodyElements.size()).isGreaterThanOrEqualTo(1);
        return bodyElements.get(0).getText();
    }

    public final WebDriver getDriver() {
        return driver;
    }

    public final Brand getBrand() {
        return brand;
    }

    /**
     * Sets the page's brand to the brand given. Updates the modules respectively.
     *
     * @param newBrand Brand to associate with the page.
     */
    public final void setBrand(Brand newBrand) {
        this.brand = newBrand;
        for (PageObject module : moduleRegistry) {
            module.setBrand(newBrand);
        }
    }

    public final Environment getEnvironment() {
        return environment;
    }

    /**
     * Sets the page's environment to the environment given. Updates the modules respectively.
     *
     * @param newEnvironment Environment to associate with the page.
     */
    public final void setEnvironment(Environment newEnvironment) {
        this.environment = newEnvironment;
        for (PageObject module : moduleRegistry) {
            module.setEnvironment(newEnvironment);
        }
    }

    public final String formatLocalizedDate(DateTime myTime) {
        return DateUtil.getLocalizedDate(this.browserLocale, myTime);
    }

    public final DateTime parseLocalizedDate(String dateTimeString) {
        return DateUtil.parseLocalizedDate(this.browserLocale, dateTimeString);
    }

    public final String getDateFormatString() {
        return DateUtil.getLocalizedDatePattern(this.browserLocale);
    }

    public final BrowserLocale getBrowserLocale() {
        return browserLocale;
    }

    /**
     * Sets the page's browserLocale to the browserLocale given. Updates the modules respectively.
     *
     * @param newBrowserLocale BrowserLocale to associate with the page.
     */
    public final void setBrowserLocale(BrowserLocale newBrowserLocale) {
        this.browserLocale = newBrowserLocale;
        if (newBrowserLocale != null) {
            ResourceBundle newBundle = ResourceBundle.getBundle(CONFIG.getString("ihg.resource-bundle-name"), newBrowserLocale.getLocale());
            setResourceBundle(newBundle);
            for (PageObject module : moduleRegistry) {
                module.setBrowserLocale(newBrowserLocale, newBundle);
            }
        }//else?
    }

    /**
     * Sets the page's browserLocale to the browserLocale given. Updates the modules respectively.
     *
     * @param newBrowserLocale BrowserLocale to associate with the page.
     */
    public final void setBrowserLocale(BrowserLocale newBrowserLocale, ResourceBundle resourceBundle) {
        this.browserLocale = newBrowserLocale;
        if (newBrowserLocale != null) {
            setResourceBundle(resourceBundle);
            for (PageObject module : moduleRegistry) {
                module.setBrowserLocale(newBrowserLocale, resourceBundle);
            }
        }//else?
    }

    private void setResourceBundle(ResourceBundle newResourceBundle) {
        this.resourceBundle = newResourceBundle;
        this.resourceReader = new ResourceReader(newResourceBundle);
    }

    public final ResourceBundle getResourceBundle() {
        return this.resourceBundle;
    }

    public final String parseResourceString(String rawString) {
        return resourceReader.parse(rawString);
    }

    public final void setWebDriverConfig(WebDriverConfig webDriverConfig) {
        this.webDriverConfig = webDriverConfig;
    }

    public final WebDriverConfig getWebDriverConfig() {
        return this.webDriverConfig;
    }
}
