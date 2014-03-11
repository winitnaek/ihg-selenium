package com.ihg.selenium;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * User: yurodivuie
 * Date: 11/6/12
 * Time: 4:35 PM
 */
public final class EnvironmentTest {

    @Test
    public void testBuildingFromJson( ) {
        assertThat( Environment.fromJson( "QA" ) ).isEqualTo( Environment.QA );
    }
}
