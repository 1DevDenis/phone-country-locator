package com.phoneсountry.locator.repository;

import com.phoneсountry.locator.model.CountryCallingCodeEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CountryCallingCodeRepositoryTest {

    @Autowired
    private CountryCallingCodeRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findCountryByPhoneNumber() {
        CountryCallingCodeEntity countryCallingCode = new CountryCallingCodeEntity(
                "Armenia", "374"
        );
        underTest.save(countryCallingCode);

        List<List<String>> result = underTest.findCountryByPhoneNumber("374234574923");

        assertEquals(1, result.size());
        List<String> countryInfo = result.get(0);
        assertEquals("374", countryInfo.get(0));
        assertEquals("Armenia", countryInfo.get(1));
    }
}