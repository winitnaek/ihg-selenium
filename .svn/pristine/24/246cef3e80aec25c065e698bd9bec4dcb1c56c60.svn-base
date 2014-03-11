package com.ihg.selenium;

import org.apache.commons.lang.StringUtils;

import com.dynacrongroup.webtest.browser.BrowserLocale;
import com.ihg.model.Brand;

/**
 * Builder for an IHG base url.
 */
public final class UrlBase {

    /**
     * Creates the base url for IHG brands, environments, and locales
     * 
     * @param browserLocale
     *            Locale of the page under test.
     * @param targetEnvironment
     *            Environment, ordinarily supplied from application.conf.
     * @return String representation of the base url.
     */
    public static String build(Brand brand, BrowserLocale browserLocale, Environment targetEnvironment) {

        String environmentPart = getEnvironmentPart(targetEnvironment);
        String localePart = getUrlLocaleFromBrowserLocale(browserLocale);

        return String.format("http://%swww.ihg.com%s/hotels/%s/", environmentPart, brand.getConsolidatedDomainPath(), localePart);
    }

    /**
     * 
     * 
     * @param browserLocale
     *              Locale of the page under test.
     * @param targetEnvironment
     *              Environment, ordinarily supplied from application.conf.
     * @param rootPath
     *              Root path of the application like /intercontinental, /hotels, /rewards.
     * @return String representation of the base url.
     */
    public static String build(Brand brand, BrowserLocale browserLocale, Environment targetEnvironment, String rootPath) {

        String environmentPart = getEnvironmentPart(targetEnvironment);
        String localePart = getUrlLocaleFromBrowserLocale(browserLocale);
        if(!StringUtils.isBlank(rootPath)){
            return String.format("http://%swww.ihg.com%s/%s/%s/", environmentPart, brand.getConsolidatedDomainPath(), rootPath, localePart);
        }else{
            //By default return url with /hotels path.
            return String.format("http://%swww.ihg.com%s/hotels/%s/", environmentPart, brand.getConsolidatedDomainPath(), localePart);
        }

    }

    /**
     * Creates the base url for input url with environments
     * 
     * @param targetEnvironment
     *            Environment, ordinarily supplied from application.conf.
     * @return String representation of the base url.
     */
    public static String build(String url, Environment targetEnvironment) {

        String environmentPart = getEnvironmentPart(targetEnvironment);
        return url.replace("://", "://".concat(environmentPart));
    }

    /**
     * Creates the dp url for IHG brands, environments, and locales
     * 
     * @param browserLocale
     *            Locale of the page under test.
     * @param targetEnvironment
     *            Environment, ordinarily supplied from application.conf.
     * @return String representation of the base url.
     */
    public static String buildDPURL(Brand brand, BrowserLocale browserLocale, Environment targetEnvironment) {
        String environmentPart = getEnvironmentPart(targetEnvironment);
        String localePart = getUrlLocaleFromBrowserLocale(browserLocale);
        return String.format("http://%swww.%s/hotels/%s/", environmentPart, brand.getDPUrl(), localePart);
    }

    private static String getEnvironmentPart(Environment targetEnvironment) {
        return targetEnvironment.name().toLowerCase() + '.';
    }

    private static String getUrlLocaleFromBrowserLocale(BrowserLocale browserLocale) {
        return browserLocale.getCountry() + "/" + browserLocale.getLanguage();
    }

    private UrlBase() {
    }

}