package com.ihg.selenium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility for looking up values in resource bundles.
 */
public class ResourceReader {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceReader.class);

    private ResourceBundle bundle;

    public ResourceReader( ResourceBundle resourceBundle ) {
        this.bundle = resourceBundle;
    }

    /**
     * Looks up the value for a given key in the resource bundle.
     * @param rawString The key
     * @return The value
     */
    public final String parse( String rawString ) {
        String parsedString = rawString;
        Pattern pattern = Pattern.compile( "\\$\\{([^}]*)\\}", Pattern.DOTALL );
        final Matcher matcher = pattern.matcher( rawString );
        while ( matcher.find( ) ) {
            String key = matcher.group( 1 );
            if ( bundle.containsKey( key ) ) {
                String property = "";
                try {
                    property = new String(bundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
                }
                catch (UnsupportedEncodingException e) {
                    LOG.error("this should never happen; encodings are hard coded", e);
                }
                parsedString = parsedString.replace( String.format( "${%s}", key ), property);
            }
        }
        return parsedString;
    }
}
