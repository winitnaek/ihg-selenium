package com.ihg.selenium;

import com.dynacrongroup.webtest.WebDriverBase;
import com.dynacrongroup.webtest.browser.BrowserLocale;
import com.dynacrongroup.webtest.parameter.ParallelRunner;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(ParallelRunner.class)
public final class PageObjectTest extends WebDriverBase {

    private static final String DEFAULT_URL = "http://www.holidayinn.com";
    private static final String DEFAULT_HOME_PAGE_CONTENT = "holiday";
    private static final String CUSTOM_LOCALE_LANG = "fr";
    private static final String CUSTOM_LOCALE_COUNTRY = "fr";
    private static final int DATE_YEAR = 2012;
    private static final int DATE_MONTH = 12;
    private static final int DATE_DAY = 24;
    private static final int DATE_HOUR = 11;
    private static final int DATE_MINUTE = 0;
    private static final String DATE_CUSTOM_FORMAT = "lun. 24 déc. 2012";
    private static final String DATE_PATTERN = "EEE d MMM yyyy";
    private static final String ERROR_INVALID_DATE_PATTERN = "Localized Date Pattern is not valid";
    private PageObject page;

    public PageObjectTest(IhgParameterCombination combination) {
        super(combination);
    }

    @Before
    public void setup() {
        driver.get(DEFAULT_URL);
        page = PageBuilder.forDriver(driver).build();

    }

    @Test
    public void getBodyText() {
        assertThat(page.getBodyText()).containsIgnoringCase(DEFAULT_HOME_PAGE_CONTENT);
    }

    @Test
    public void testFormatLocalizedDate() {
        Locale locale = new Locale(CUSTOM_LOCALE_LANG, CUSTOM_LOCALE_COUNTRY);
        BrowserLocale browserLocale = new BrowserLocale(locale);
        page.setBrowserLocale(browserLocale);

        DateTime myDate = new DateTime(DATE_YEAR, DATE_MONTH, DATE_DAY, DATE_HOUR, DATE_MINUTE);
        String testTime = page.formatLocalizedDate(myDate);

        assertEquals(testTime, DATE_CUSTOM_FORMAT);
    }

    @Test
    public void testGetLocalizedDatePattern() {
        Locale locale = new Locale(CUSTOM_LOCALE_LANG,CUSTOM_LOCALE_COUNTRY);
        BrowserLocale browserLocale = new BrowserLocale(locale);
        page.setBrowserLocale(browserLocale);

        assertTrue(ERROR_INVALID_DATE_PATTERN, page.getDateFormatString().equals(DATE_PATTERN));
    }
}
