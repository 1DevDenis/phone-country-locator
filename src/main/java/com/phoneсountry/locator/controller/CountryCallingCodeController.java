package com.phoneсountry.locator.controller;

import com.phoneсountry.locator.service.CountryCallingCodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/country-calling-code")
@AllArgsConstructor
public class CountryCallingCodeController {

    private final CountryCallingCodeService countryCallingCodeService;

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<String> lookupCountryByPhoneNumber(@PathVariable String phoneNumber) {
        String country = countryCallingCodeService.lookupCountryByPhoneNumber(phoneNumber);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }
}
