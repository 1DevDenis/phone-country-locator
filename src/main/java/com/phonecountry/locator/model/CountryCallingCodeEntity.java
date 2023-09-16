package com.phonecountry.locator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country_calling_codes")
public class CountryCallingCodeEntity {

    @Id
    @SequenceGenerator(
            name = "country_calling_codes_sequence",
            sequenceName = "country_calling_codes_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "country_calling_codes_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "calling_code")
    private String callingCode;

    @Column(name = "country")
    private String country;

    public CountryCallingCodeEntity(String country, String callingCode) {
        this.country = country;
        this.callingCode = callingCode;
    }
}