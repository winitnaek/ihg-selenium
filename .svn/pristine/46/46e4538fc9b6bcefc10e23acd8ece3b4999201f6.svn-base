package com.ihg.selenium;


import java.util.Set;

import com.ihg.model.Brand;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The class <code>BrandTest</code> contains tests for the enum.
 *
 * @author SharmaRaj4
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("PMD")
public class BrandTest {

    @Test
    public void testContainsBrand() {
        assertFalse(Brand.INDIGO.containsBrandCode("an"));
        assertTrue(Brand.IHG.containsBrandCode("an"));
        assertTrue(Brand.IHG.containsBrandCode("6c"));
        assertTrue(Brand.HI.containsBrandCode("ss"));
    }

    @Test
    public void testFromAlternateDomain_2() throws Exception {
        String alternateDomain = "holidayinnexpress.com";

        Brand result = Brand.fromAlternateDomain(alternateDomain);

        // add additional test code here
        assertNotNull(result);
        assertEquals("/holidayinnexpress", result.getCanonicalPath());
        assertEquals("ihg.com/holidayinnexpress", result.getCanonicalUrl());
        assertEquals("/holidayinnexpress", result.getConsolidatedDomainPath());
        assertTrue(result.getAlternateDomains().contains("holidayinnexpress.com"));
        assertEquals("ihg.com", result.getConsolidatedDomain());
        assertEquals("ihg.com", result.getCanonicalDomain());
        assertEquals("hiexpress.com", result.getDPDomain());
        assertEquals("ex", result.getBrandCode());
        assertEquals(false, result.isMultiBrand());
        assertEquals("", result.getDPPath());
        assertEquals("Holiday Inn Express", result.getBrandName());
        assertEquals("hiexpress.com", result.getDPUrl());
        assertEquals("HIEX", result.name());
        assertEquals("HIEX", result.toString());
        assertEquals(2, result.ordinal());
    }

    @Test
    public void testFromDomainName_2() throws Exception {
        String domainName = "hiexpress.com";
        Brand result = Brand.fromDomainName(domainName);
        assertEquals(Brand.HIEX, result);
    }

    @Test
    public void testFromString_1() throws Exception {
        String brandId = "ex";

        Brand result = Brand.fromString(brandId);

        // add additional test code here
        assertNotNull(result);
        assertEquals(Brand.HIEX, result);
    }

    @Test
    public void testFromString_2() throws Exception {
        String brandId = "";

        Brand result = Brand.fromString(brandId);

        // add additional test code here
        assertNotNull(result);
        assertEquals(Brand.IHG, result);
    }

    @Test
    public void testGetAlternateDomains_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        Set<String> result = fixture.getAlternateDomains();

        // add additional test code here
        assertEquals(null, result);
    }

    @Test
    public void testGetBrandCode_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        String result = fixture.getBrandCode();

        // add additional test code here
        assertEquals("cw", result);
    }

    @Test
    public void testGetBrandName_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        String result = fixture.getBrandName();

        // add additional test code here
        assertEquals("Candlewood Suites", result);
    }

    @Test
    public void testGetCanonicalDomain_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        String result = fixture.getCanonicalDomain();

        // add additional test code here
        assertEquals("ihg.com", result);
    }

    @Test
    public void testGetCanonicalDomain_2() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        String result = fixture.getCanonicalDomain();

        // add additional test code here
        assertEquals("ihg.com", result);
    }

    @Test
    public void testGetCanonicalPath_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        String result = fixture.getCanonicalPath();

        // add additional test code here
        assertEquals("/candlewood", result);
    }

    @Test
    public void testGetCanonicalUrl_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        String result = fixture.getCanonicalUrl();

        // add additional test code here
        assertEquals("ihg.com/candlewood", result);
    }

    @Test
    public void testGetConsolidatedDomain_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        String result = fixture.getConsolidatedDomain();

        // add additional test code here
        assertEquals("ihg.com", result);
    }

    @Test
    public void testGetConsolidatedDomainPath_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        String result = fixture.getConsolidatedDomainPath();

        // add additional test code here
        assertEquals("/candlewood", result);
    }

    @Test
    public void testGetDPDomain_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        String result = fixture.getDPDomain();

        // add additional test code here
        assertEquals("candlewoodsuites.com", result);
    }

    @Test
    public void testGetDPPath_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        String result = fixture.getDPPath();

        // add additional test code here
        assertEquals("", result);
    }

    @Test
    public void testGetDPUrl_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        String result = fixture.getDPUrl();

        // add additional test code here
        assertEquals("candlewoodsuites.com", result);
    }

    @Test
    public void testIsMultiBrand_1() throws Exception {
        Brand fixture = Brand.CANDLEWOOD;

        boolean result = fixture.isMultiBrand();

        // add additional test code here
        assertEquals(false, result);
    }

    @Test
    public void fromJson(){

        assertEquals(Brand.fromJson("6c"), Brand.IHG);
    }

    @Test
    public void fromJsonNotFound(){

        Brand brand = Brand.fromJson("xx");
        assertNull(brand);
    }
}
