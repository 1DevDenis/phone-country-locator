package com.phoneсountry.locator.service;

import com.phoneсountry.locator.exception.CountryNotFoundException;
import com.phoneсountry.locator.repository.CountryCallingCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CountryCallingCodeServiceImpl implements CountryCallingCodeService {

    private static final String DELIMITER = ", ";
    private static final String NO_COUNTRIES_FOUND = "No countries found";

    private final CountryCallingCodeRepository countryCallingCodeRepository;

    @Override
    public String lookupCountryByPhoneNumber(String phoneNumber) {
        List<List<String>> countries = countryCallingCodeRepository.findCountryByPhoneNumber(phoneNumber);

        Map<Integer, List<String>> countryMap = groupCountriesByCountryCallingCodeLength(countries);

        return countryMap.entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(entry -> String.join(DELIMITER, entry.getValue()))
                .orElseThrow(() -> new CountryNotFoundException(NO_COUNTRIES_FOUND));
    }

    private Map<Integer, List<String>> groupCountriesByCountryCallingCodeLength(List<List<String>> countries) {
        return countries.stream()
                .collect(Collectors.groupingBy(
                        list -> list.get(0).length(),
                        Collectors.mapping(list -> list.get(1), Collectors.toList())
                ));
    }
}
