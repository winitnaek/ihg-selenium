package com.ihg.selenium;

import com.dynacrongroup.webtest.parameter.ParameterCombinationFactory;
import com.ihg.model.Brand;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * User: yurodivuie
 * Date: 11/6/12
 * Time: 4:40 PM
 */
public final class IhgParameterCombinationTest {

    class TestClass {
        @SuppressWarnings("PMD")
        public TestClass( IhgParameterCombination combination ) {
        }
    }

    @Test
    public void buildFromConfig( ) {
        List<Brand> expectedBrands = new ArrayList<Brand>( );
        expectedBrands.add( Brand.IHG );
        expectedBrands.add( Brand.HI );

        List<Brand> actualBrands = new ArrayList<Brand>( );

        List<IhgParameterCombination> parameterCombinations = new ParameterCombinationFactory( TestClass.class ).make( );
        for ( IhgParameterCombination combination : parameterCombinations ) {
            if ( !actualBrands.contains( combination.getBrand( ) ) ) {
                actualBrands.add( combination.getBrand( ) );
            }
        }

        assertThat( actualBrands ).containsExactly( expectedBrands.toArray( new Brand[]{ } ) );
    }

}
