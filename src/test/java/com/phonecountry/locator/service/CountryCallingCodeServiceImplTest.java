package com.phonecountry.locator.service;

import com.phonecountry.locator.exception.CountryNotFoundException;
import com.phonecountry.locator.repository.CountryCallingCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryCallingCodeServiceImplTest {

    private CountryCallingCodeServiceImpl countryCallingCodeService;

    @Mock
    private CountryCallingCodeRepository countryCallingCodeRepository;

    @BeforeEach
    void setUp() {
        countryCallingCodeService = new CountryCallingCodeServiceImpl(countryCallingCodeRepository);
    }

    @Test
    void lookupCountryByPhoneNumber() {
        List<List<String>> mockCountries = new ArrayList<>();
        mockCountries.add(Arrays.asList("1", "USA"));
        mockCountries.add(Arrays.asList("44", "UK"));
        when(countryCallingCodeRepository.findCountryByPhoneNumber("447123456789"))
                .thenReturn(mockCountries);

        String result = countryCallingCodeService.lookupCountryByPhoneNumber("447123456789");

        assertEquals("UK", result);
    }

    @Test
    void lookupCountryByPhoneNumberWhenNoMatches() {
        when(countryCallingCodeRepository.findCountryByPhoneNumber("123456789"))
                .thenReturn(Collections.emptyList());

        try {
            countryCallingCodeService.lookupCountryByPhoneNumber("123456789");
        } catch (CountryNotFoundException ex) {
            assertEquals("No countries found", ex.getMessage());
        }
    }

    @Test
    void lookupCountryByPhoneNumberWhenMultipleMatches() {
        List<List<String>> mockCountries = new ArrayList<>();
        mockCountries.add(Arrays.asList("1", "USA"));
        mockCountries.add(Arrays.asList("44", "UK"));
        mockCountries.add(Arrays.asList("44", "United Kingdom"));
        when(countryCallingCodeRepository.findCountryByPhoneNumber("44123456789"))
                .thenReturn(mockCountries);

        String result = countryCallingCodeService.lookupCountryByPhoneNumber("44123456789");

        assertEquals("UK, United Kingdom", result);
    }
}
