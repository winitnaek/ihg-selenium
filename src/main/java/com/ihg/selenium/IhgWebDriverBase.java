package com.ihg.selenium;

import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dynacrongroup.webtest.WebDriverBase;
import com.dynacrongroup.webtest.browser.Browser;
import com.dynacrongroup.webtest.browser.BrowserLocale;
import com.dynacrongroup.webtest.util.Configuration;
import com.ihg.model.Brand;
import com.typesafe.config.Config;

/**
 * Base class of IHG tests. Supplies logger, brand, environment, locale info. Leverages the Retry for cases where pages refresh instead of behave as they should (known issue in
 * system under test).
 */
public class IhgWebDriverBase extends WebDriverBase {

    private static final Logger LOG = LoggerFactory.getLogger(IhgWebDriverBase.class);
    private static final String HTTP_PREFIX = "http://";
    private final IhgParameterCombination parameters;
    private final BrandLanguageMappingUtil brandLanguageUtil = new BrandLanguageMappingUtil();

    public IhgWebDriverBase(IhgParameterCombination parameterCombination) {
        super(parameterCombination);
        this.parameters = parameterCombination;
        this.brandLanguageUtil.brandLanguageResolver(parameterCombination.getBrand(), parameterCombination.getBrowserLocale());
    }

    private boolean authCookieSet() {
        return this.driver.manage().getCookieNamed("Selenium") != null;
    }

    /**
     * deleteNonSeleniumCookies
     */
    private void deleteNonSeleniumCookies() {
        LOG.info("Deleting non-auth cookies.");
        Set<Cookie> cookieSet = this.driver.manage().getCookies();
        for (Iterator<Cookie> cookieIterator = cookieSet.iterator(); cookieIterator.hasNext();) {
            Cookie cookie = cookieIterator.next();
            if (!cookie.getName().equalsIgnoreCase("Selenium")) { // This cookie is the auth bypass
                this.driver.manage().deleteCookieNamed(cookie.getName());
                LOG.debug("Deleted cookie: " + cookie.getName() + ". Cookies left: " + this.driver.manage().getCookies().size());
            }
        }
    }

    public Brand getBrand() {
        return this.parameters.getBrand();
    }

    public Browser getBrowser() {
        return this.parameters.getWebDriverConfig().getBrowser();
    }

    public BrowserLocale getBrowserLocale() {
        return this.parameters.getBrowserLocale();
    }

    /**
     * Looks up the environment from the webtest.environment variable in application.conf.
     * 
     * @return Environment constructed from current application configuration.
     */
    public Environment getEnvironment() {
        Config config = Configuration.getConfigForClass(this.getClass());
        return Environment.fromJson(config.getString("webtest.environment"));
    }

    public IhgParameterCombination getParameters() {
        return this.parameters;
    }

    @Before
    public void performCookieAuth() {
        if (!this.authCookieSet() && Boolean.parseBoolean(Configuration.getConfig().getString("useCookieAuth"))) {
            this.primeAuthCookies();
        }
        this.deleteNonSeleniumCookies();
    }

    /**
     * primeAuthCookies Setting auth cookies for environment
     */
    private void primeAuthCookies() {
        LOG.info("Setting auth cookies for environment: {}.", this.getEnvironment().toString());
        this.driver.get(HTTP_PREFIX.concat(this.getEnvironment().toString()).concat(".www.ihg.com/hotels/us/en/reservation?selenium=true"));
        this.driver.get(HTTP_PREFIX.concat(this.getEnvironment().toString()).concat(".www.priorityclub.com/sso/cookie/drop?selenium=true&dp=true"));
        this.driver.get(HTTP_PREFIX.concat(this.getEnvironment().toString()).concat(".www.ihgarmyhotels.com/hotels/us/en/reservation?dp=true&Selenium=true"));
        this.driver.get(HTTP_PREFIX.concat(this.getEnvironment().toString()).concat(".www.intercontinental.com/hotels/gb/en/reservation/?dp=true&Selenium=true"));
    }
}
