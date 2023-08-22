package com.phoneсountry.locator.repository;

import com.phoneсountry.locator.model.CountryCallingCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryCallingCodeRepository extends JpaRepository<CountryCallingCodeEntity, String> {

    @Query("SELECT c.callingCode, c.country " +
            "FROM CountryCallingCodeEntity c " +
            "WHERE :phoneNumber LIKE CONCAT(c.callingCode, '%')")
    List<List<String>> findCountryByPhoneNumber(String phoneNumber);
}
