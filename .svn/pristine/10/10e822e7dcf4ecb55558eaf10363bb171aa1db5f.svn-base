package com.ihg.selenium;

import org.openqa.selenium.WebDriver;

/**
 * Test Page for use with PageBuilderTest
 * <p/>
 * User: yurodivuie
 * Date: 11/11/12
 * Time: 10:38 AM
 */
public final class CompositeTestPage extends PageObject {

    private TestPage subTestPage;

    private PageObject subPage;

    public CompositeTestPage( WebDriver driver ) {
        super( driver );
    }

    public TestPage goToNewPage( ) {
        return this.makeNewPage( TestPage.class );
    }

    public TestPage getSubTestPage() {
        return subTestPage;
    }

    public PageObject getSubPage() {
        return subPage;
    }
}
