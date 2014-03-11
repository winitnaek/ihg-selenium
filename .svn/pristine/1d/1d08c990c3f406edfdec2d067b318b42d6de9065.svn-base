package com.ihg.selenium;

import com.dynacrongroup.webtest.parameter.ParameterCombination;
import com.google.common.base.Joiner;
import com.ihg.model.Brand;

/**
 * Container for brand code and locale.
 * Used primarily to log out browser | browser version | operating system | browser locale | brand code.
 */
public class IhgParameterCombination extends ParameterCombination {

    public static final Brand DEFAULT_BRAND = Brand.IHG;

    private Brand brand = DEFAULT_BRAND;

    public final Brand getBrand( ) {
        return brand;
    }

    public final void setBrand( Brand newBrand ) {
        this.brand = newBrand;
    }

    /**
     * @return browser | browser version | operating system | browser locale | brand code
     */
    @Override
    public final String toString( ) {
        return Joiner.on( '|' ).join( this.getWebDriverConfig( ), this.getBrowserLocale( ), this.getBrand( ).getBrandCode());
    }
}
