package com.phonecountry.locator.controller;

import com.phonecountry.locator.service.CountryCallingCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryCallingCodeControllerTest {

    private CountryCallingCodeController countryCallingCodeController;

    @Mock
    private CountryCallingCodeService countryCallingCodeService;

    @BeforeEach
    void setUp() {
        countryCallingCodeController = new CountryCallingCodeController(countryCallingCodeService);
    }

    @Test
    void lookupCountryByPhoneNumber() {
        when(countryCallingCodeService.lookupCountryByPhoneNumber("447123456789"))
                .thenReturn("UK");

        ResponseEntity<String> responseEntity = countryCallingCodeController.lookupCountryByPhoneNumber("447123456789");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("UK", responseEntity.getBody());
    }
}
