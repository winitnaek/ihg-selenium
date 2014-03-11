package com.ihg.tidy;

import com.dynacrongroup.webtest.WebDriverBase;
import com.dynacrongroup.webtest.browser.BrowserLocale;
import com.dynacrongroup.webtest.jtidy.TidyVerifier;
import com.dynacrongroup.webtest.jtidy.TidyVerifierBuilder;
import com.dynacrongroup.webtest.parameter.ParallelRunner;
import com.ihg.model.Brand;
import com.ihg.selenium.Environment;
import com.ihg.selenium.IhgParameterCombination;
import com.ihg.selenium.UrlBase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * User: yurodivuie
 * Date: 11/12/12
 * Time: 2:23 PM
 */


@RunWith( ParallelRunner.class )
public final class JTidySampleTest extends WebDriverBase {


    /**
     * @param parameterCombination
     */
    public JTidySampleTest( IhgParameterCombination parameterCombination ) {
        super( parameterCombination );
    }

    @Ignore( "Currently failing due to missing doctype declaration" )
    @Test
    public void jtidyExample( ) {
        String url = UrlBase.build( Brand.IHG, new BrowserLocale( "en-us" ), Environment.QA );
        driver.get( url );
        TidyVerifier verifier = new TidyVerifierBuilder( ).build( );
        verifier.verifyHtml( driver );
    }
}
