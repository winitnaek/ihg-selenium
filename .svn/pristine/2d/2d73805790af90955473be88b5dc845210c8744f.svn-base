package com.ihg.selenium;

import com.dynacrongroup.webtest.browser.BrowserLocale;
import com.dynacrongroup.webtest.browser.WebDriverConfig;
import com.ihg.model.Brand;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Builder for Page objects. Supplies brand, locale, environment, and webDriver info to Page
 * for logging and interaction with page elements.
 */
public final class PageBuilder {

    private Class pageClass = PageObject.class;
    private Brand brand;
    private Environment environment;
    private WebDriver webDriver;
    private WebDriverConfig webDriverConfig;
    private BrowserLocale browserLocale;

    private PageBuilder( WebDriver newDriver ) {
        this.webDriver = newDriver;
    }

    public static PageBuilder forDriver( WebDriver newDriver ) {
        return new PageBuilder( newDriver );
    }

    public PageBuilder forParameters( IhgParameterCombination parameters ) {
        return this.forBrand( parameters.getBrand() )
                .forBrowserLocale( parameters.getBrowserLocale() )
                .forWebDriverConfig( parameters.getWebDriverConfig() );
    }

    public PageBuilder forPage( Class newPageClass ) {
        this.pageClass = newPageClass;
        return this;
    }

    public PageBuilder forBrand( Brand newBrand ) {
        this.brand = newBrand;
        return this;
    }

    public PageBuilder forEnvironment( Environment newEnvironment ) {
        this.environment = newEnvironment;
        return this;
    }

    public PageBuilder forBrowserLocale( BrowserLocale newBrowserLocale ) {
        this.browserLocale = newBrowserLocale;
        return this;
    }

    public PageBuilder forWebDriverConfig( WebDriverConfig webDriverConfig ) {
        this.webDriverConfig = webDriverConfig;
        return this;
    }

    public PageObject build( ) {

        PageObject page = ( PageObject ) PageFactory.initElements( webDriver, pageClass );
        page.setBrand(brand);
        page.setBrowserLocale( browserLocale );
        page.setEnvironment( environment );
        page.setWebDriverConfig( webDriverConfig );

        suppressPopupsInClient(webDriver);

        return page;
    }


    /**
     * Use jQuery (JavaScript) to monitor the page for the appearance of any elements with an ID from the
     * selectors list. If found, remove from the DOM.
     * @param driver WebDriver instance
    */
    private void suppressPopupsInClient(WebDriver driver) {

        //Add more jQuery selectors here if/as other unwanted elements are discovered plaguing the app:
        String selectors = "#bgCover, #bModal, #pcrPopupLightBoxDiv, #ipeL, #invL, #oo_feedback_float";

        //hunt & kill the above, immediately and then every 500 milliseconds
        String script = "if(typeof jQuery!==\"undefined\") (function($){ " //checks for jQuery first
                + " function suppress(){ $(\"" + selectors + "\").remove(); } "
                + " suppress(); setInterval(suppress, 500);"
                + "})(jQuery);";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }

}
