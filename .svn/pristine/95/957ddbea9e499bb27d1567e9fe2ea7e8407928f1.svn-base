package com.ihg.selenium;

import com.dynacrongroup.webtest.browser.BrowserLocale;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Utility for IHG Date display formats.
 */
public class DateUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

    /**
     * Overloaded version of getLocalizedDate(String, String, DateTime) that accepts a Locale.
     *
     * @param locale Target locale of date to be formatted.
     * @param dt     DateTime to be expressed in IHG standard date format
     * @return String representation of localized datetime in IHG standard format.
     */
    public static String getLocalizedDate(BrowserLocale locale, DateTime dt) {
        return getLocalizedDate(locale.getLanguage(), locale.getCountry(), dt);
    }

    /**
     * @param languageCode - standard 2 letter language identifier
     * @param countryCode  - standard 2 letter country identifier
     * @param dt           - date as expressed in DateTime object
     * @return String representation of localized datetime in IHG standard format.
     */
    public static String getLocalizedDate(String languageCode, String countryCode, DateTime dt) {
        LOG.debug("Parameter 'languageCode' = " + languageCode);
        LOG.debug("Parameter 'countryCode' = " + countryCode);
        LOG.debug("Parameter 'dt' = " + dt.toString());

        final String pattern = getLocalizedDatePattern(languageCode, countryCode);
        LOG.debug("Variable 'pattern' = " + pattern);

        Locale localeObj = new Locale(languageCode, countryCode);

        String dateString = DateTimeFormat.forPattern(pattern).withLocale(localeObj).print(dt);
        LOG.info("Return value 'dateString' = " + dateString);

        return dateString;
    }

    public static DateTime parseLocalizedDate(BrowserLocale browserLocale, String dateTimeString) {
        String languageCode = browserLocale.getLanguage();
        String countryCode = browserLocale.getCountry();
        LOG.debug("Parameter 'languageCode' = " + languageCode);
        LOG.debug("Parameter 'countryCode' = " + countryCode);
        LOG.debug("Parameter 'dateTime' = " + dateTimeString);

        final String pattern = getLocalizedDatePattern(languageCode, countryCode);
        LOG.debug("Date formatter 'pattern' = " + pattern);

        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern).withLocale(browserLocale.getLocale());

        return formatter.parseDateTime(dateTimeString);
    }

    /**
     * @param locale - A BrowserLocale
     * @return IHG date pattern customized for the provided browser/locale
     */
    public static String getLocalizedDatePattern(BrowserLocale locale) {
        return getLocalizedDatePattern(locale.getLanguage(), locale.getCountry());
    }

    /**
     * Lookup date pattern appropriate for given language and country code combination
     * Result is specific to IHG website
     *
     * @param languageCode - standard 2 letter language identifier
     * @param countryCode  - standard 2 letter country identifier
     * @return IHG date pattern customized for the provided browser/locale
     */
    public static String getLocalizedDatePattern(String languageCode, String countryCode) {
        LOG.trace("getLocalizedDatePattern - Look up date format pattern");

        languageCode = languageCode.toLowerCase();
        countryCode = countryCode.toLowerCase();

        LOG.debug("Parameter 'languageCode' = " + languageCode);
        LOG.debug("Parameter 'countryCode' = " + countryCode);

        final String EURO_DATE_PATTERN = "EEE d MMM yyyy";
        final Map<String, String> CONSTANT_MAP =
                Collections.unmodifiableMap(new HashMap<String, String>() {{
                    put("enus", "MMM-d-yyyy");
                    put("dede", EURO_DATE_PATTERN);
                    put("esus", EURO_DATE_PATTERN);
                    put("frfr", EURO_DATE_PATTERN);
                    put("itit", EURO_DATE_PATTERN);
                    put("ptpt", EURO_DATE_PATTERN);
                    put("jajp", "yyyy'年'M'月' d'日' EEE");
                    put("kokr", "yyyy'년'M'월' d'일' EE");
                    put("zhcn", "yyyy'年'M'月'd'日' EEEE");
                    put("trtr", "d MMM yyyy");
                    put("iwil", "yyyy MMMM d E");
                    put("arae", "yyyy MMMM dd EE");
                    put("ruru", EURO_DATE_PATTERN);
                    put("engb", "d MMM yyyy");
                    put("enuk", "d MMM yyyy");
//                    put("enuk", "MMM-d-yyyy");
                }});

        String key = languageCode + countryCode;

        String datePattern = CONSTANT_MAP.get(key);

        if (datePattern == null) {
            datePattern = "";
        }

        return datePattern;
    }
}