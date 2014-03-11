package com.ihg.selenium;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Simple container for environment described in application.conf.
 */
public enum Environment {
    QA, INT, QAP;

    @JsonCreator
    public static Environment fromJson( String text ) {
        return valueOf( text.toUpperCase( ) );
    }
}
