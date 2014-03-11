package com.ihg.selenium;

import static org.junit.Assume.assumeTrue;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dynacrongroup.webtest.browser.BrowserLocale;
import com.ihg.model.Brand;

@SuppressWarnings("static-access")
public class BrandLanguageMappingUtil {

    private static final Logger LOG = LoggerFactory.getLogger(BrandLanguageMappingUtil.class);
    private static final String EN_GB = "en_gb";
    private static final String AR_AE = "ar_ae";
    private static final String KO_KR = "ko_kr";
    private static final String IW_IL = "iw_il";
    private static final String FR_FR = "fr_fr";
    private static final String DE_DE = "de_de";
    private static final String JA_JP = "ja_jp";
    private static final String IT_IT = "it_it";
    private static final String PT_PT = "pt_pt";
    private static final String ES_US = "es_us";
    private static final String RU_RU = "ru_ru";
    private static final String TR_TR = "tr_tr";
    // All languages not supported by the respective brands
    // Resorts languages are same for Even and Club Vacations

    private final List<String> Resorts = Arrays.asList("Resorts", this.EN_GB, this.FR_FR, this.DE_DE, this.JA_JP, "zh_cn", this.KO_KR, this.IT_IT, this.AR_AE, this.IW_IL,
            this.PT_PT, this.ES_US, this.RU_RU, this.TR_TR);
    private final List<String> HolidayInn = Arrays.asList("HolidayInn", this.KO_KR, this.AR_AE, this.IW_IL, this.PT_PT, this.RU_RU, this.TR_TR);
    private final List<String> Express = Arrays.asList("Express", this.KO_KR, this.AR_AE, this.IW_IL, this.PT_PT, this.RU_RU, this.TR_TR, this.JA_JP);
    private final List<String> CrownePlaza = Arrays.asList("CrownePlaza", this.KO_KR, this.AR_AE, this.IW_IL, this.PT_PT, this.RU_RU, this.TR_TR, this.JA_JP, this.IT_IT);
    private final List<String> InterContinental = Arrays.asList("InterContinental", "en_us", this.FR_FR, this.DE_DE, this.IT_IT, this.AR_AE, this.IW_IL, this.PT_PT, this.RU_RU,
            this.TR_TR);
    private final List<String> HotelIndigo = Arrays.asList("HotelIndigo", this.KO_KR, this.AR_AE, this.IW_IL, this.PT_PT, this.RU_RU, this.TR_TR, this.JA_JP, this.IT_IT,
            this.EN_GB);
    private final List<String> Staybridge = Arrays.asList("Staybridge", this.DE_DE, this.JA_JP, "zh_cn", this.KO_KR, this.IT_IT, this.AR_AE, this.IW_IL, this.PT_PT, this.RU_RU,
            this.TR_TR);
    private final List<String> CandlewoodSuites = Arrays.asList("CandlewoodSuites", this.EN_GB, this.DE_DE, this.JA_JP, "zh_cn", this.KO_KR, this.IT_IT, this.AR_AE, this.IW_IL,
            this.PT_PT, this.ES_US, this.RU_RU, this.TR_TR);

    private final List<List<String>> allBrands = Arrays.asList(this.HolidayInn, this.Express, this.CrownePlaza, this.InterContinental, this.HotelIndigo, this.Staybridge,
            this.CandlewoodSuites, this.Resorts);

    public void brandLanguageResolver(Brand brand, BrowserLocale locale) {

        for (List<String> getBrand : this.allBrands) {
            if (getBrand.get(0).toString().equalsIgnoreCase(brand.getBrandName())) {
                String getLocale = locale.getLanguage() + "_" + locale.getCountry();
                if (getBrand.contains(getLocale)) {
                    LOG.info("The Locale " + getLocale + " is not supported for this brand " + brand.getBrandName());
                    assumeTrue(false);
                    break;
                }
            }
        }
    }
}
