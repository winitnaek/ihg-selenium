package com.ihg.selenium;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * User: yurodivuie
 * Date: 11/11/12
 * Time: 12:00 PM
 */
public final class MessageResourceTest {

    private static final Logger LOG = LoggerFactory.getLogger( MessageResourceTest.class );

    @Test
    public void sample( ) {
        ResourceBundle bundle = ResourceBundle.getBundle( "messageResources" );
        LOG.info( bundle.getString( "label.pandg.location" ) );
    }
}
