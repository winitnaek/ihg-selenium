package com.ihg.selenium;

import org.junit.Test;

import java.util.ResourceBundle;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * User: yurodivuie
 * Date: 11/21/12
 * Time: 12:47 AM
 */
public final class ResourceReaderTest {

    @Test
    public void parseSingleKey( ) {
        ResourceBundle bundle = ResourceBundle.getBundle( "messageResources" );
        ResourceReader reader = new ResourceReader( bundle );
        assertThat( reader.parse( "${label.pandg.location}" ) ).isEqualToIgnoringCase( "Location" );
    }

    @Test
    public void parseComplexString( ) {
        ResourceBundle bundle = ResourceBundle.getBundle( "messageResources" );
        ResourceReader reader = new ResourceReader( bundle );
        assertThat(
                reader.parse( "This ${label.pandg.location} is ${notFound} but ${label.pandg.Parking} ${fake} is ${label.pandg.location}." )
        ).
                isEqualToIgnoringCase( "This Location is ${notFound} but Parking ${fake} is Location." );
    }
}
