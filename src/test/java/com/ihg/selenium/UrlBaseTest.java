package com.ihg.selenium;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * User: yurodivuie
 * Date: 11/4/12
 * Time: 11:45 AM
 */
public final class UrlBaseTest {

    private final IhgParameterCombination parameters = new IhgParameterCombination( );

    @Test
    public void testDefaultUrl( ) {
        String url = UrlBase.build( this.parameters.getBrand( ), this.parameters.getBrowserLocale( ), Environment.QA );

        this.verifyURL(url);
    }

    @Test
    public void testURLwithRootPath(){

        String url = UrlBase.build( this.parameters.getBrand( ), this.parameters.getBrowserLocale( ), Environment.QA,  "");
        this.verifyURL(url);

        assertThat( url ).contains("/hotels");

        url = UrlBase.build( this.parameters.getBrand( ), this.parameters.getBrowserLocale( ), Environment.QA,  null);
        this.verifyURL(url);

        assertThat( url ).contains("/hotels");


        url = UrlBase.build( this.parameters.getBrand( ), this.parameters.getBrowserLocale( ), Environment.QA,  "ambasador");
        this.verifyURL(url);

        assertThat( url ).contains("/ambasador");


    }

    private void verifyURL(String url){
        assertThat( url ).startsWith( "http://" + Environment.QA.name( ).toLowerCase( ) );
        assertThat( url ).containsIgnoringCase( IhgParameterCombination.DEFAULT_BRAND.getConsolidatedDomainPath( ) );
        assertThat( url ).containsIgnoringCase( this.parameters.getBrowserLocale( ).getLanguage( ) );
        assertThat( url ).containsIgnoringCase( this.parameters.getBrowserLocale( ).getCountry( ) );
    }

}
