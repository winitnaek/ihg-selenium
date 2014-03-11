package com.ihg.selenium;

import com.dynacrongroup.webtest.browser.BrowserLocale;
import com.ihg.model.Brand;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Locale;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * User: yurodivuie
 * Date: 11/11/12
 * Time: 10:11 AM
 */
public final class PageBuilderTest {

    private Capabilities capabilities = new DesiredCapabilities();
    private WebDriver driver = new PhantomJSDriver(capabilities);

    @Test
    public void buildPageWithNoOptions() {
        PageObject page = PageBuilder.forDriver( driver ).build();
        assertThat( page.getDriver() ).isEqualTo( driver );
    }

    @Test
    public void buildPageWithAllOptions() {
        final Class<TestPage> pageClass = TestPage.class;
        final Environment environment = Environment.QA;
        final Brand brand = Brand.IHG;
        final BrowserLocale browserLocale = new BrowserLocale( "en-US" );

        TestPage testPage = ( TestPage ) PageBuilder.forDriver( driver )
                .forPage( pageClass )
                .forEnvironment( environment )
                .forBrand( brand )
                .forBrowserLocale( browserLocale )
                .build();

        assertThat( testPage.getDriver() ).isEqualTo( driver );
        assertThat( testPage.getBrand() ).isEqualTo( brand );
        assertThat( testPage.getEnvironment() ).isEqualTo( environment );
        assertThat( testPage.getClass().getCanonicalName() ).isEqualTo( TestPage.class.getCanonicalName() );
        assertThat( testPage.getBrowserLocale() ).isEqualTo( browserLocale );
    }

    @Test
    public void buildCompositePage() {
        final Brand brand = Brand.IHG;

        CompositeTestPage page = ( CompositeTestPage ) PageBuilder.forDriver( driver )
                .forPage( CompositeTestPage.class )
                .forBrand( brand )
                .build();

        assertThat( page.getBrand() ).isEqualTo( brand );
        assertThat( page.getDriver() ).isEqualTo( driver );
        assertThat( page.getSubPage().getBrand() ).isEqualTo( brand );
        assertThat( page.getSubTestPage().getBrand() ).isEqualTo( brand );
        assertThat( page.getSubPage().getDriver() ).isEqualTo( driver );
        assertThat( page.getSubTestPage().getDriver() ).isEqualTo( driver );
    }

    @Test
    public void useMessageResources() {
        PageObject page = PageBuilder.forDriver( driver ).forBrowserLocale( new BrowserLocale( Locale.ITALY ) ).build();
        assertThat( page.getResourceBundle().getString( "label.select.number.parking.nights" ) )
                .containsIgnoringCase( "Seleziona il numero di notti di parcheggio" );
    }

    @Test
    public void useParameterCombination() {
        IhgParameterCombination combination = new IhgParameterCombination();
        combination.setBrand( Brand.CROWNEPLAZA );
        combination.setBrowserLocale( new BrowserLocale( Locale.JAPAN ) );

        PageObject page = PageBuilder.forDriver( driver ).forParameters( combination ).build();

        assertThat( page.getBrand() ).isEqualTo( combination.getBrand() );
        assertThat( page.getBrowserLocale() ).isEqualTo( combination.getBrowserLocale() );
    }

    @Test
    public void gettingPageFromAnotherPage() {
        final Brand brand = Brand.HIEX;
        final Environment environment = Environment.INT;
        final BrowserLocale browserLocale = new BrowserLocale( "es-US" );

        CompositeTestPage page = ( CompositeTestPage ) PageBuilder.forDriver( driver )
                .forPage( CompositeTestPage.class )
                .forBrand( brand )
                .forEnvironment( environment )
                .forBrowserLocale( browserLocale )
                .build();

        final TestPage testPage = page.goToNewPage();

        assertThat( testPage.getClass().getCanonicalName() ).isEqualTo( TestPage.class.getCanonicalName() );
        assertThat( testPage.getDriver() ).
                isEqualTo( page.getDriver() ).
                isEqualTo( driver );
        assertThat( testPage.getBrowserLocale() ).
                isEqualTo( page.getBrowserLocale() ).
                isEqualTo( browserLocale );
        assertThat( testPage.getEnvironment() ).
                isEqualTo( page.getEnvironment() ).
                isEqualTo( environment );
    }
}
